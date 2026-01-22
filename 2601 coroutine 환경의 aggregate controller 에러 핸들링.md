# Issue:

spring webflux + kotlin coroutine 환경에서 여러 비동기 호출을 `coroutineScope { async { ... } }` 로 묶어놨을 때, 하위 호출 하나가 400(또는 다른 예외)을 던지면 나머지 호출들도 같이 캔슬되면서 전체 요청이 실패하는 상황.
<br/>
<br/>

## 상황:

특정 컨트롤러(`SpecialController`)에서 템플릿 조회, noti 정보 조회, 최근 스페셜 조회 등을 `async` 로 병렬 처리하고 있었음.

- noti 조회 쪽(예: `likeService.getNotiMemberDto`)이 400을 던지면
- 같은 `coroutineScope` 안에 있는 템플릿 조회, 최근 스페셜 조회까지 전부 캔슬되면서
- 결국 페이지 전체가 500 비슷하게 죽는 것처럼 보였음
- java 시절에 쓰던 패턴(CompletableFuture 등)에서는 한 호출 400 난다고 나머지까지 같이 죽지는 않던 기억이 있어서 더 혼란스러웠음

이걸, noti 같은 부가 정보는 실패해도 페이지는 뜨게 만들고 싶었음.
<br/>
<br/>

## 알게된 부분 정리:

- 코루틴의 "구조적 동시성" 때문에 `coroutineScope` 안에서 자식 코루틴 하나가 예외를 던지면, 부모 스코프가 실패 상태가 되고, 다른 자식들도 캔슬됨
- java 에서 쓰던 일반적인 비동기(Future, CompletableFuture, Reactor 조합 등)는 꼭 이렇게 동작하지 않아서 느낌이 달랐던 것뿐
- optional 한 비동기 호출은
  - `async` 안에서 `try/catch` 혹은 `runCatching` 으로 예외를 흡수하거나
  - 아예 `supervisorScope` / `SupervisorJob` 을 쓰고 `await` 쪽에서 개별적으로 처리해야 함
- "어떤 호출 때문에 전체가 죽었는지"를 명확히 남기고 싶으면
  - WebClient 레벨에서 service/uri/status 를 담은 custom exception 으로 래핑해서 던지고
  - 전역 `@RestControllerAdvice` 에서 해당 예외를 핸들링하면서 로그/응답에 원인을 남기는 식으로 설계 가능
- 이 프로젝트에서는 템플릿 쪽 WebClient는 이미 try/catch + 기본값 패턴을 쓰고 있었고,
  - noti, 최근 스페셜, joined 여부 같은 부가 정보들도 같은 방향으로 처리하는 게 자연스러워 보였음

<br/>
<br/>

## 개념: coroutineScope, supervisorScope, 구조적 동시성

<br/>
  kotlin coroutine 쪽에서 핵심은 "구조적 동시성"이라는 개념.

- `suspend fun foo() = coroutineScope { ... }` 안에 `async` 로 자식 코루틴을 여러 개 띄운 경우
  - 자식 하나가 예외를 던지면 → 그 예외는 부모 스코프에 전파되고
  - 그 순간 부모 스코프는 실패 상태가 되고 → 나머지 자식 코루틴은 `CancellationException` 으로 캔슬됨
- 그래서 `async` 로 병렬 호출을 묶어놓고 한쪽에서 400 같은 예외가 터지면, 구조적으로 "그 트리 전체가 실패" 하는 게 기본 동작
- 반대로 `supervisorScope` 를 쓰면
  - 한 자식이 실패해도 다른 자식이 자동으로 캔슬되지는 않고,
  - 각 `await()` 시점에서 예외를 개별적으로 다뤄줄 수 있음
- 다만 supervisorScope 를 쓰더라도
  - `await()` 하는 쪽에서 예외를 그대로 두면 결국 거기서 터지기 때문에,
  - 필수/선택 의존성을 나눠서, 선택적인 호출은 runCatching 등으로 예외를 먹어줘야 함

> 예시 느낌 (개념용, 실제 코드는 별도):
>
> `suspend fun handler() = coroutineScope {
> val a = async { callA() }
> val b = async { callB() } // 여기서 400 예외
>
>     val ra = a.await()
>     val rb = b.await()       // 이 await 에서 예외 터지면서 전체 scope 실패
>
> }`
>
> `suspend fun handler() = supervisorScope {
> val a = async { callA() }
> val b = async { callB() }
>
>     val ra = runCatching { a.await() }.getOrNull()
>     val rb = runCatching { b.await() }.getOrNull()
>
> }`

<br/>
<br/>
<br/>

        참조:

- Kotlin 공식 문서 - Structured concurrency, coroutineScope, supervisorScope
- spring + coroutine 예제들 (sibling coroutine cancel 사례들)

<br/>

## 개념: optional 비동기 호출을 공통적으로 처리하는 패턴 (optionalAsync)

<br/>
  noti, 최근 스페셜, joined 여부 같은 것들은 "있으면 좋고 없어도 페이지는 떠야 하는" 데이터라서, 실패해도 전체 요청을 망가뜨리면 안 됨.
  이걸 컨트롤러마다 똑같이 try/catch 쓰기 싫어서, 패턴을 하나 정리해두는 게 낫겠다고 생각.

아이디어는 간단하게:

- 선택 의존성(부가 데이터)은
  - `async` 내부에서 `runCatching` 으로 예외를 잡고
  - 실패 시 공통 로그 찍고
  - `null` 혹은 기본값으로 내려보내기
- 이런 걸 헬퍼 함수 하나로 감싸서 쓰면, 컨트롤러 코드가 좀 더 정리됨

> 대략 이런 헬퍼를 상상:
>
> `in fun <T> CoroutineScope.optionalAsync(

    name: String,
    crossin block: suspend () -> T

): Deferred<T?> = async {
runCatching { block() }
.onFailure { e ->
log.warn(e) { "Optional async task failed: $name" }
}
.getOrNull()
}`

> 그리고 컨트롤러에서는:
>
> `val notiInfoDeferred = MemberDetails?.let { details ->

    optionalAsync("likeService.getNotiMemberDto") {
        likeService.getNotiMemberDto(details, false)
    }

}`

> `val recentSpecialsDeferred = optionalAsync("getRecentSpecials:$specialName") {

    // 기존 recentSpecials 계산 로직

}`

이렇게 해두면, "이 호출은 실패해도 전체 요청은 실패시키지 않는다"는 의도가 코드 레벨에서 드러나고,
실패했을 때 로그 포맷도 통일시킬 수 있음.

<br/>
<br/>
<br/>

        참조:

- 현재 프로젝트에서 `SpecialController` 의 noti, recentSpecials, joined 여부에 runCatching 적용한 코드
- RemoteTemplateWebClient 에서 이미 쓰고 있는 try/catch + 기본값 패턴

<br/>

## 이 방식으로 실제 적용한 해결 내용

<br/>
  SpecialController 기준으로, 필수/선택 의존성을 나눠서 처리 방식 바꿈.

1. **list**
   - 템플릿 목록 조회는 필수, noti 정보는 선택.
   - noti 쪽 `likeService.getNotiMemberDto` 를 `async` + `runCatching` 으로 감싸서
     - 400 이나 기타 예외가 나도 로그만 찍고 `null` 리턴하게 수정.
   - 그래서 noti 서비스가 망가져도 리스트 페이지는 뜸.

> 핵심 구조만 정리하면:
>
> `val notiInfoDeferred = MemberDetails?.let { details ->

    async {
        runCatching {
            likeService.getNotiMemberDto(details, false)
        }.onFailure { e ->
            log.warn(e) { "Failed to get noti info for special list" }
        }.getOrNull()
    }

}`

2. **index** (특정 스페셜 상세 페이지)
   - 템플릿 모델은 필수.
   - noti, 최근 스페셜은 선택.
   - noti: list 와 동일하게 `runCatching` 으로 감싸서 실패 시 null.
   - 최근 스페셜: 템플릿 parent 의 `relatedSpecials` 기반으로 또 다른 템플릿을 조회하는데,
     이 부분도 통째로 `runCatching` 으로 감싸서 실패 시 빈 리스트.
   - 이렇게 바꾸고 나면, noti 쪽이나 관련 템플릿 조회가 400/500 등을 내더라도 페이지 자체는 그대로 뜨고,
     부가 영역만 비어 있는 형태로 노출됨.

> 최근 스페셜 부분 구조:
>
> `val recentSpecialsDeferred = async {

    runCatching {
        // relatedSpecials 파싱 + templateService.getTemplates 호출 + Special.convert
    }.onFailure { e ->
        log.warn(e) { "Failed to get recent specials for special: $specialName" }
    }.getOrDefault(emptyList())

}`

3. **getCampaignPolicy**
   - 캠페인 정책 조회는 필수.
   - joined 여부는 선택(알아두면 좋은 정보 정도).
   - joined 쪽만 `runCatching` 으로 감싸서 실패 시 false.
   - 정책 API 가 살아 있고 joined 만 깨진 상황에서는, 정책 정보는 내려가고 joined 는 false 로 내려가도록.

> joined 부분:
>
> `val joinedDeferred = async {

    runCatching {
        if (MemberDetails != null) {
            promotionCampaignService.hasJoined(promotionNo, MemberDetails)
        } else {
            false
        }
    }.onFailure { e ->
        log.warn(e) { "Failed to check joined status for promotion: $promotionNo" }
    }.getOrDefault(false)

}`

4. **joinCampaign**
   - 이쪽은 실제 비즈니스 핵심(참여 시도)라서, 실패를 조용히 삼키는 건 오히려 안 맞음.
   - 여기서는 기존 플로우 그대로 두고, 정책 invalid, join 실패 등은 정상적으로 실패 처리.

정리하자면,

- "이거 깨지면 페이지/요청 전체가 같이 죽어야 하는가?" 를 기준으로 필수/선택 나누고,
- 선택 쪽은 `runCatching` + 로그 + 기본값 패턴으로 정리해서,
- 코루틴 구조(`coroutineScope + async`) 안에서도 한쪽 400 때문에 나머지까지 줄줄이 캔슬되지 않도록 바꿨다.

앞으로 비슷한 병렬 호출 붙일 때는, 그냥 습관적으로 `async` 만 쓰지 말고

- 이 호출이 필수인지, 선택인지 먼저 생각하고
- 선택이면 helper(optionalAsync 같은 것)나 runCatching 으로 감싸는 쪽으로 가는 게 낫겠다고 느꼈다.

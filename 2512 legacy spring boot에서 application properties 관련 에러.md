<!--
author: Dailyscat
purpose: issue arrange
rules:
 (1) 헤더와 문단사이
    <br/>
    <br/>
 (2) 코드가 작성되는 부분은 >로 정리
 (3) 참조는 해당 내용 바로 아래
    <br/>
    <br/>
 (4) 명령어는 bold
 (5) 방안은 ## 안의 과정은 ###
-->

# Issue:

<br/>
<br/>

애플리케이션 기동 시 `InvalidConfigDataPropertyException` 발생.
`application-local.yml` 안에서 `spring.profiles`를 사용해서 설정을 가져오려고 하다가 에러가 나는 상황.

---

## 상황:

<br/>
<br/>

애플리케이션 실행 시 로그:

> Application run failed
> org.springframework.boot.context.config.InvalidConfigDataPropertyException: Property 'spring.profiles' imported from location 'class path resource [application-local.yml]' is invalid and should be replaced with 'spring.config.activate.on-profile'

문제가 된 설정(yml 중 민감한 정보 제거):

> spring:
> profiles: jp
> redis:
> cluster:
> nodes:
>
> - … (host 생략)
> - …
> - …
>   password: … (생략)
>   max-redirects: 5
>   prefix: …
>   jpa:
>   generate-ddl: false
>   lifecycle:
>   timeout-per-shutdown-phase: 30s

스프링 부트 2.4+부터는 `application-*.yml` 내부에서 `spring.profiles` 메타 설정을 쓰는 게 더 이상 허용되지 않고, `spring.config.activate.on-profile`로 대체되면서 위 예전 방식이 에러로 떨어지는 상태.

---

## 생각해낸 방안:

<br/>
<br/>

- `spring.profiles` → `spring.config.activate.on-profile`로 교체해서 사용
- 또는 아예 파일을 `application-jp.yml` 같이 프로파일 이름으로 분리하고, 프로파일 활성화는 `spring.profiles.active`만 쓰도록 정리

---

## 방안:

<br/>
<br/>

### 1. application-local.yml 수정해서 에러 제거

`spring.profiles` 블록을 새 방식으로 변경:

> spring:
> config:
> activate:
> on-profile: jp
> redis:
> cluster:
> nodes:
>
> - …
> - …
> - …
>   password: …
>   max-redirects: 5
>   prefix: …
>   jpa:
>   generate-ddl: false
>   lifecycle:
>   timeout-per-shutdown-phase: 30s

의미는 기존이랑 동일하게
“**jp 프로파일이 활성화됐을 때만 이 설정을 적용**” 하는 것.

---

### 2. jp 프로파일 활성화 방식 정리

애플리케이션 실행 쪽에서는 아래 중 하나로 **jp 프로파일 활성화**:

> JVM 옵션: **-Dspring.profiles.active=jp**
> 또는 환경변수: **SPRING_PROFILES_ACTIVE=jp**
> 또는 공통 `application.yml`에
>
> > spring:
> > profiles:
> > active: jp

`spring.profiles.active`는 여전히 사용 가능하고, 에러 나는 건 yml 안의 메타 설정(`spring.profiles`) 부분이라 그쪽만 새 방식으로 바꿔주면 됨.

---

### 3. 대안: 파일을 프로파일별로 분리

지금 로컬용 파일을 아래처럼 재구성하는 방식도 고려 가능:

> application-jp.yml (현재 `application-local.yml` 내용 이쪽으로 이동)

그리고 실행 시:

> **-Dspring.profiles.active=jp**

만 맞춰 주면, 별도의 `spring.config.activate.on-profile` 없이도 jp 프로파일일 때 자동으로 해당 yml이 로딩됨.

---

<br/>
<br/>
<br/>
<br/>

```
    참조:
```

<br/>

> Spring Boot 공식 문서 – Profile 관련 설정 변경 (`spring.config.activate.on-profile` 사용)

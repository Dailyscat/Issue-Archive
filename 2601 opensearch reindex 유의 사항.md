<!-- author: Dailyscat purpose: issue arrange rules: (1) 헤더와 문단사이 <br/> <br/> (2) 코드가 작성되는 부분은 >로 정리 (3) 참조는 해당 내용 바로 아래 <br/> <br/> (4) 명령어는 bold (5) 방안은 ## 안의 과정은 ### -->

Issue:
상황:
<br/> <br/>

host-cms-os-test에서 \_reindex를 쏘는데 Unauthorized(401) 떨어짐

reindex.remote.whitelist는 host-cms-os-test-jp-api:12000 넣어둔 상태라 “화이트리스트 문제 아니냐?” 싶었는데, 실제로는 그거랑 결이 좀 다름

요청 구조는 test(\_reindex 실행) → test-jp(remote source)에서 읽어서 → test(dest)에 쓰는 형태

<br/> <br/>
알게된 부분 정리:

reindex.remote.whitelist는 “원격 접속 허용”만 해주는 거고, 인증(Unauthorized) 해결해주는 설정은 아님

Unauthorized가 어디에서 터지는지가 핵심

클라이언트 → test /\_reindex 호출 자체가 인증 실패

test가 remote(test-jp) 붙는 과정에서 인증 실패

지금 curl은 헤더 Authorization이랑 body remote username/password가 섞여 있어서, test 호출용 인증이 아닌 걸 넣었을 가능성이 큼

/\_reindex 호출은 “test에서 유효한 계정”으로 인증해야 함

remote 붙는 건 body의 remote.username/password로 따로 인증함

권한 부족도 비슷하게 security_exception으로 떨어질 수 있음(401/403 비슷하게 보이는 환경도 있음)

<br/> <br/>
개념:
<br/> <br/>

원격 reindex 동작 방식(헷갈리는 포인트)

<br/> <br/>

\_reindex는 dest(여기선 test) 에서 실행됨

실행 주체가 test라서, test가 remote(source)로 HTTP 호출을 때림

그래서 설정도 test쪽에 들어가야 하고, 인증도 2단으로 생각해야 함

(A) curl이 test에 붙을 때 인증

(B) test가 remote(test-jp)에 붙을 때 인증

<br/> <br/> <br/>
참조:

<br/> <br/>

Elasticsearch Remote Reindex / reindex.remote.whitelist 개념 (공식 문서 기준)

\_reindex 요청에 source.remote가 들어가면 dest 노드가 remote로 접속하는 구조

<br/>
개념:
<br/> <br/>

Unauthorized(401) 원인 갈래

<br/> <br/>

보통 whitelist 안맞으면 “not whitelisted” 류로 떨어지고, 401은 거의 인증/권한 문제임

그래서 401 뜨면 아래 중 하나로 좁혀야 함

test가 \_reindex API 자체를 못 쓰는 계정임 (헤더 Authorization 문제)

remote(test-jp) 쪽 계정이 read 권한이 없음 or 인증 자체 실패

test에 dest index write/create 권한이 없음

<br/> <br/> <br/>
참조:

<br/> <br/>

security_exception / unauthorized는 allowlist가 아니라 사용자 인증/권한 이슈로 분리해서 봐야 함

\_reindex는 source read + dest write(필요시 create_index) 권한이 필요

<br/>
방안:
1) “어디서” Unauthorized가 나는지부터 확정
<br/> <br/>

\_reindex 응답 JSON에서 error.root_cause, error.reason 확인

메시지에 /\_reindex가 직접 찍히면 test 인증/권한 문제 쪽

remote 관련 문구(원격 401, remote 접속 실패 등) 나오면 test-jp 인증 문제 쪽

<br/> <br/>

(응답 에러 JSON에서 password/token 같은 건 마스킹하고 error 블록만 보면 됨)

<br/> <br/> 2) 헤더 Authorization을 “test에서 유효한 계정”으로 바꾸기
<br/> <br/>

지금 헤더 토큰이 remote 계정이랑 섞여있을 가능성이 높아서, test에서 \_reindex 가능한 계정으로 다시 시도

<br/> <br/>

curl --location 'https://host-cms-os-test-api...:12000/\_reindex?wait_for_completion=true
'
--header 'Authorization: Basic (test용 계정 토큰)'
--header 'Content-Type: application/json'
--data '{ ... remote는 그대로 ... }'

<br/> <br/> 3) remote 계정 검증 (test-jp에 직접 붙어서 확인)
<br/> <br/>

body에 넣은 remote.username/password로 실제로 test-jp에서 조회가 되는지 체크

최소한 index가 열리고 read 되는지 보면 됨

<br/> <br/>

curl -u id:passwd
'https://host:12000/template_config/\_count
'

<br/> <br/> 4) 권한(roles) 체크 포인트
<br/> <br/>

remote(test-jp): template_config에 read, view_index_metadata 정도는 있어야 함

dest(test): template_config에 write (+ 필요 시 create_index) 필요

그리고 \_reindex 실행이 막혀있으면 cluster 권한 쪽도 필요할 수 있음(환경별)

<br/> <br/>

(이건 운영 정책/보안 설정마다 role 이름이 달라서, “어느 role이 붙어있냐”를 봐야 정확히 말할 수 있음)

<br/> <br/>
결과:
<br/> <br/>

whitelist는 “원격 접속 허용”만 담당이라 401이랑 직접 원인-해결 관계가 아님

401은 거의 인증/권한 문제라서

(1) test에 붙는 Authorization이 맞는지

(2) remote credential이 실제로 read 가능한지

(3) dest에 write/create 권한 있는지
이 순서로 보면 해결이 빠름

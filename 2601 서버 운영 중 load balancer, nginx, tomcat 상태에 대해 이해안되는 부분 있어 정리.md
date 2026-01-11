<!-- author: Dailyscat purpose: issue arrange rules: (1) 헤더와 문단사이 <br/> <br/> (2) 코드가 작성되는 부분은 >로 정리 (3) 참조는 해당 내용 바로 아래 <br/> <br/> (4) 명령어는 bold (5) 방안은 ## 안의 과정은 ### -->

Issue:
<br/> <br/> 서버 운영 중 load balancer, nginx, tomcat 상태에 대해 이해안되는 부분 있어 정리.
상황:
<br/> <br/> - Verda L7LB(Generic Proxy mode) 사용 중, 밸런싱은 Round robin - health check 경로는 `/monitor/l7check.nhn` - 실제 동작은 LB → Nginx(Real server)까지는 확인하지만, Tomcat 상태는 직접 확인하지 않음 - Nginx에서 health check는 Tomcat을 안 보고 정적 파일을 리턴하도록 구성함 <br/> <br/>

location /healthcheck.ok {
alias html/healthcheck.ok;
}

location = /monitor/l7check.nhn {
alias html/healthcheck.ok;
}

// healthcheck.ok
I am healthy

<br/> <br/> - 걱정 포인트: “Tomcat이 실제로 요청 처리 가능한지 LB가 모르는데도, 로그 보면 10대에 요청이 골고루 감” - 관측: 대체로 균등 분배처럼 보이지만, 가끔 CPU 튀는 서버가 있음 <br/>
알게된 부분 정리:
<br/> <br/> - LB 헬스체크는 “분산 비율 계산”이 아니라, “이 서버를 풀에서 뺄지(UP/DOWN)”만 결정하는 용도임 - 지금 구성은 `/monitor/l7check.nhn`이 항상 200을 주니까, LB 입장에서는 10대 전부 계속 UP 상태로 남아있음 - 그래서 Round robin이면 그냥 UP 목록(10대)에 기계적으로 골고루 뿌리는 게 정상 동작임 - Tomcat이 느려지거나 스레드가 막혀도, Nginx health check가 200이면 LB는 그 서버를 DOWN으로 못 빼는 구조가 될 수 있음 - “요청 수가 균등”이어도 “요청 비용/GC/캐시/특정 작업” 때문에 CPU는 일부 서버만 튈 수 있음 (특히 짧은 구간으로 보면 keep-alive/연결 재사용 때문에 뭉쳐 보일 수도 있음) <br/>
개념:
<br/> LB Health Check vs Load Balancing (Round robin 기준) <br/> <br/> - Real server가 “Nginx”로 잡혀있는 경우, LB가 보는 대상은 “Nginx가 살아있나 + 지정 URL이 200이냐”임 - health check가 성공하면 그 서버는 UP으로 남고, Round robin은 UP 목록을 대상으로 순서대로 분산함 - 즉 “Tomcat이 받을 수 있냐”는 지금 health check가 반영하지 못함 그래서 **장애 시**에는 “죽은 Tomcat을 가진 Nginx”로도 트래픽이 계속 갈 수 있음 <br/> <br/> <br/>
참조:

<br/> <br/> - Verda LB (Generic Proxy mode) / Health Check 사양 문서 - 운영 경험칙: 헬스체크는 UP/DOWN(풀 제외) 판단용, 라운드로빈은 UP 대상에만 균등 분배 <br/>
개념:
<br/> Spring Actuator liveness / readiness 동작 방식 <br/> <br/> - liveness/readiness는 “헬스 URL”이라기보단, 스프링 내부에 “가용성 상태”를 저장해두고 그걸 헬스로 노출하는 구조 - liveness: 앱 자체가 정상인지(죽었거나 회복 불가 상태인지) 중심 - readiness: “지금 트래픽 받아도 되는지” 중심 (시작 중/종료 중/일시 거부 같은 흐름에 유용) - 상태는 내부적으로 이벤트로 바꾸고(AvailabilityChangeEvent), actuator는 그 상태를 `/actuator/health/liveness`, `/actuator/health/readiness`로 보여주는 형태 - 주의: readiness에 뭘 넣을지는 설계 문제고, liveness에 외부 의존성(DB 등)을 넣으면 장애 시 재시작 폭주 같은 리스크가 생길 수 있음 <br/> <br/> <br/>
참조:

<br/> <br/> - Spring Boot Actuator / Kubernetes Probes (liveness, readiness) 공식 문서 - Spring Boot ApplicationAvailability / AvailabilityChangeEvent API 문서 <br/>
방안:
<br/> <br/>

1. health check가 Tomcat readiness를 보도록 만들기 (정석)
   <br/> <br/> - Nginx의 `/monitor/l7check.nhn`을 Tomcat의 readiness endpoint로 프록시 - Tomcat이 준비 안 된 상태면 503 같은 코드로 내려서 LB가 DOWN 처리할 수 있게 함 <br/> <br/> > location = /monitor/l7check.nhn { > &nbsp;&nbsp;proxy_pass http://127.0.0.1:8080/actuator/health/readiness; > &nbsp;&nbsp;proxy_connect_timeout 1s; > &nbsp;&nbsp;proxy_read_timeout 1s; > } <br/> <br/>
2. 파일 기반 유지하되 “항상 200”은 피하기 (현실적인 타협안)
   <br/> <br/> - 준비 상태면 파일 존재 → 200 - 준비 안 되면 파일 없음 → 503 - Java 앱(또는 watchdog)이 상태 따라 파일 생성/삭제 <br/> <br/> > location = /monitor/l7check.nhn { > &nbsp;&nbsp;try_files /healthcheck.ok =503; > } <br/> <br/>
3. Actuator 노출/설정 정리 (운영 측면)
   <br/> <br/> - readiness/liveness 엔드포인트 노출 확인 - 관리 포트를 분리했으면 “프로브는 성공인데 실제 서비스 포트는 죽은” 상황이 안 생기게 경로/포트 접근도 같이 점검 - 점검용으로는 아래처럼 **curl**로 빠르게 확인 가능 <br/> <br/> > **curl** -s -i http://127.0.0.1:8080/actuator/health/readiness > **curl** -s -i http://127.0.0.1:8080/actuator/health/liveness <br/> <br/>
4. “CPU 튐” 재발 방지를 위한 최소 관측 포인트
   <br/> <br/> - 서버별 (1분 단위) 요청 수/응답코드/응답시간 - Nginx upstream 502/504 비율 - Tomcat thread pool, GC 타이밍, connection pool 대기 시간 이 3개만 맞춰봐도 “균등 분배인데 일부만 튐” 원인이 꽤 좁혀짐 <br/> ::contentReference[oaicite:0]{index=0}

# Issue:

톰캣 배포용 ansible role에 OTEL(javaagent + properties) 설정을 추가했는데, 처음에 디렉터리가 안 생겨서 삽질한 내용 정리

## 상황:

웹앱 톰캣을 ansible로 배포하면서 OTEL java agent를 같이 붙이려고 함.

톰캣 role에 `apps/otel` 디렉터리랑 `execute.sh` 템플릿은 이미 있었는데, 실제 서버에서 디렉터리가 안 보였음.

실행한 명령:

 **ansible-playbook -i inventories//hosts_jp_.yml webapp_install.yml --limit --check**

- `roles/tomcat/tasks/main.yml` 안에 `apps/otel` 디렉터리와 `execute.sh` 템플릿 생성 태스크가 있음
- 근데 서버에 접속해서 보면 `apps/otel` 자체가 없음
- 동시에 OTEL binary(jar)랑 `otel-{}.properties`도 ansible에서 같이 관리하고 싶었음

## 생각해낸 방안:

- 먼저 왜 `apps/otel`이 안 생기는지부터 확인

  - ansible `--check` 모드는 실제 파일/디렉터리를 안 만든다는 걸 다시 상기함 (dry-run)

- 그 다음에 OTEL 관련 항목들을 아예 tomcat role에 묶어서 관리

  - javaagent jar 다운로드 태스크 추가
  - env(profile)에 따라 dev/release용 properties 템플릿 추가
  - 이미 있는 `execute.sh`에서 사용하는 경로랑 변수 이름에 맞춰서 정리

## 방안:

### 1. check 모드 때문에 디렉터리가 안 생긴 문제 정리

- 기존에 썼던 명령어:

 **ansible-playbook -i inventories//hosts_jp_.yml webapp_install.yml --limit --check**

- `--check` 때문에

  - ansible가 파일/디렉터리 생성, 템플릿 렌더링을 실제로는 수행 안 함
  - 출력상으로는 `changed`로 보일 수 있지만, 서버에는 아무것도 안 생김

- 그래서 실제 반영이 필요할 때는 `--check`를 빼고 실행해야 함:

 **ansible-playbook -i inventories//hosts_jp_.yml webapp_install.yml --limit**

### 2. OTEL용 디렉터리/파일 구조 정리

톰캣 role에서 OTEL 관련 구조를 이렇게 맞춤:

- 설치 베이스(변수): `{{ user_app_dir }}`
- OTEL 디렉터리: `{{ user_app_dir }}/otel` (실제 예: `/home1/irteam/apps/otel` 느낌으로 사용)
- binary(jar): `{{ user_app_dir }}/otel/opentelemetry-javaagent.jar`
- properties: `{{ user_app_dir }}/otel/otel-{{ otel_profile }}.properties`
- 실행 스크립트: `{{ user_app_dir }}/otel/execute.sh`

`execute.sh.j2`에서는 이미 아래처럼 쓰고 있음:

 -javaagent:/home1/irteam/apps/otel/opentelemetry-javaagent.jar\
 -Dotel.javaagent.configuration-file=/home1/irteam/apps/otel/otel-${OTEL_PROFILE}.properties

여기 맞춰서 ansible task와 템플릿 파일 이름을 정리했다.

### 3. ansible task 추가 (tomcat role)

`roles/tomcat/tasks/main.yml`에 OTEL 관련 태스크를 추가:

1. OTEL 디렉터리 생성

 - name: create otel directory under tomcat install base\
   file:\
   path: '{{ user_app_dir }}/otel'\
   state: directory\
   mode: '0755'

2. OTEL javaagent(jar) 다운로드

 - name: download otel javaagent\
   get_url:\
   url: '[](https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar')<https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar'\
   dest: '{{ user_app_dir }}/otel/opentelemetry-javaagent.jar'\
   mode: '0644'\
   when: not ansible_check_mode

3. OTEL properties 템플릿 생성

 - name: template otel properties\
   template:\
   src: 'otel/otel-{{ otel_profile }}.properties.j2'\
   dest: '{{ user_app_dir }}/otel/otel-{{ otel_profile }}.properties'\
   mode: '0644'\
   when: not ansible_check_mode

4. 기존 `execute.sh` 템플릿 적용 (이미 있던 것 유지)

 - name: template otel execute script next to tomcat\
   template:\
   src: 'otel/execute.sh.j2'\
   dest: '{{ user_app_dir }}/otel/execute.sh'\
   mode: '0755'

`when: not ansible_check_mode`를 jar/properties에만 걸어놔서, check 모드에서는 시뮬레이션만 하고 실제 다운로드/파일 생성은 안 하도록 했음.

### 4. OTEL properties 템플릿 구성

환경별로 이미 `otel_profile`이 정리되어 있어서, 그 값에 맞는 템플릿 두 개를 만들었다.

- dev 계열 (alpha/beta): `otel_profile: -dev`
  - `roles/tomcat/templates/otel/otel--dev.properties.j2`
- release 계열 (staging/real): `otel_profile: -release`
  - `roles/tomcat/templates/otel/otel--release.properties.j2`

각 파일 내용은 요청대로 셋업:

#### dev/staging (`otel--dev.properties.j2`)

 ## OpenTelemetry Java Agent properties for dev/staging

 # resource

 otel.service.name=2-alert-api-staging\
 otel.resource.attributes=instanceId=2-alert-api,instancePhase=staging,.trace.tenant.key={{ otel_tenant_key }}

 # trace sampler

 otel.traces.sampler.arg=0.5\
 otel.traces.sampler=parentbased_traceidratio

 # exporter

 otel.traces.exporter=otlp\
 otel.metrics.exporter=none\
 otel.logs.exporter=none

 otel.exporter.otlp.traces.endpoint=[](http://-trace-dev..com:4317)<http://-trace-dev..com:4317\
 otel.exporter.otlp.traces.protocol=grpc\
 otel.exporter.otlp.traces.compression=gzip\
 otel.exporter.otlp.traces.timeout=3000

 # agent logging

 otel.javaagent.logging=application

 # instrumentation

 ## related to metrics

 otel.instrumentation.micrometer.enabled=false\
 otel.instrumentation.dropwizard-metrics.enabled=false

 ## related to logs

 otel.instrumentation.logback-appender.enabled=false\
 otel.instrumentation.log4j-appender.enabled=false\
 otel.instrumentation.jboss-logmanager-appender.enabled=false

여기서 `<tenantKey` 자리는 ansible 변수 `{{ otel_tenant_key }}`로 대체해서, 환경별 group_vars에 있는 값을 그대로 쓰도록 했다.

#### release (`otel--release.properties.j2`)

 ## OpenTelemetry Java Agent properties for release

 # resource

 otel.service.name=2-alert-api-release\
 otel.resource.attributes=instanceId=2-alert-api,instancePhase=release,.trace.tenant.key={{ otel_tenant_key }}

 # trace sampler

 otel.traces.sampler.arg=0.15\
 otel.traces.sampler=parentbased_traceidratio

 # exporter

 otel.traces.exporter=otlp\
 otel.metrics.exporter=none\
 otel.logs.exporter=none

 otel.exporter.otlp.traces.endpoint=[](http://-trace..com:4317)<http://-trace..com:4317\
 otel.exporter.otlp.traces.protocol=grpc\
 otel.exporter.otlp.traces.compression=gzip\
 otel.exporter.otlp.traces.timeout=3000

 # agent logging

 otel.javaagent.logging=application

 # instrumentation

 ## related to metrics

 otel.instrumentation.micrometer.enabled=false\
 otel.instrumentation.dropwizard-metrics.enabled=false

 ## related to logs

 otel.instrumentation.logback-appender.enabled=false\
 otel.instrumentation.log4j-appender.enabled=false\
 otel.instrumentation.jboss-logmanager-appender.enabled=false

### 5. 실행 및 확인

1. check 모드로 변화만 먼저 확인 (옵션)

 **ansible-playbook -i inventories//hosts_jp_.yml webapp_install.yml --limit --check --diff**

2. 실제 반영

 **ansible-playbook -i inventories//hosts_jp_.yml webapp_install.yml --limit**

3. 대상 서버에서 확인

 ls -l /home1/irteam/apps/otel

 # opentelemetry-javaagent.jar

 # otel--dev.properties or otel--release.properties

 # execute.sh

4. 톰캣 기동 스크립트 쪽에서 `execute.sh`가 잘 호출되고, `CATALINA_OPTS`에 OTEL 관련 옵션이 붙는지 확인

 echo $CATALINA_OPTS


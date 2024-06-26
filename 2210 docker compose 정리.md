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

# Issue: docker compose 문서 정리

## 상황:

<br/>

## 개념: 

<br/>

### Key features of Docker Compose

#### 단일 호스트에 여러 개의 격리된 환경 보유
    - 한 프로젝트에서 각 기능 분기 브랜치에 대한 실행환경 가능
    - ci 서버에서 빌드가 서로 간섭되지 않게 하기 위해 프로젝트 이름을 고유 빌드 번호로 설정할 수 있다.
    - -p, COMPOSE_PROJECT_NAME 를 사용해서 프로젝트 이름을 변경할 수 도 있다.
    - 기본 디렉토리는 compose file이 있는 디렉토리지만 --project-directory 옵션을 이용해서 변경도 가능하다.

#### 컨테이너가 생성 될 때 볼륨 데이터 보존.
    - compose는 서비스를 위한 모든 볼륨데이터를 유지한다. docker compose up을 실행할 때 이전에 실행됐던 컨테이너를 찾았다면 compose가 해당 컨테이너에서 사용됐던 볼륨 데이터를 카피해서 새 컨테이너로 옮긴다.

#### 변화가 있을 때만 컨테이너를 재생성한다.
    - compose는 컨테이너를 생성할 때의 설정을 캐싱한다. 만약 컨테이너를 재시작 했을 때 변화가 없다면 compose는 캐싱된 컨테이너를 다시 사용한다.

#### 변수와 환경 간 컨테이너 설정 사용
    - compose file에서 변수 사용이 가능하며 각기 다른 환경과 유저에 따라 이를 활용하여 compose 환경 구성을 할 수 있다.

#### bind mounts

볼륨데이터 저장(volumes)과 비교하면 제한된 기능들이 많다. bind mounts를 사용하면 파일이다 디렉토리가 컨테이너의 호스트 머신에 자리하며 이때는 호스트의 절대경로를 참조한다.. volume의 경우 도커의 저장소 디렉토리 내부에 생기며 도커가 컨텐츠를 관리한다.

-v, --voulume 옵션의 경우 모든 옵션을 결합하여 제공되는반면 --mounts 옵션은 옵션들을 분리하여 제공할 수 있게한다.
-v 는 세가지 옵션을 제공한다.
- 첫 번째 필드는 호스트 시스템의 파일 또는 디렉토리 경로다.
- 두 번째 필드는 파일 또는 디렉토리가 컨테이너에서 마운트되는 경로다.
- 세 번째 필드는 선택 사항이며 ro, z 및 Z와 같이 쉼표로 구분된 옵션 목록이다.

--mount 옵션의 경우 쉼표로 구분되고 각각 <key>=<value> 튜플로 구성된 여러 키-값 쌍으로 구성된다.
--mount 구문은 -v 또는 --volume보다 더 장황하지만 키의 순서는 중요하지 않으며 플래그 값을 더 쉽게 이해할 수 있다.

-v 및 --volume 플래그는 오랫동안 Docker의 일부였기 때문에 해당 동작을 변경할 수 없다. 이는 -v와 --mount 사이에 한 가지 다른 동작이 있음을 의미한다.

-v 또는 --volume을 사용하여 Docker 호스트에 아직 존재하지 않는 파일 또는 디렉토리를 바인드 마운트하는 경우 -v가 엔드포인트를 생성하고 이는 항상 디렉토리로 생성된다.

--mount를 사용하여 Docker 호스트에 아직 존재하지 않는 파일 또는 디렉토리를 바인딩 마운트하면 Docker가 자동으로 생성하지 않지만 오류를 생성한다.


#### Variable substitution

```
db:
  image: "postgres:${POSTGRES_VERSION}"
```

compose는 실행되기전 image에 대해 확인하는데 POSTGRES_VERSION가 세팅 안되어 있다면 빈값이 들어간다. 혹은 이 값은 .env file에서도 가져올 수 있다.
Compose가 프로젝트 디렉토리(Compose 파일의 상위 폴더)에서 자동으로 찾는 .env 파일을 사용하여 환경 변수의 기본값을 설정할 수 있다.
셸 환경에 설정된 값은 .env 파일에 설정된 값보다 우선한다.
.env 파일 기능은 docker-compose up 명령을 사용할 때만 작동하며 docker stack deploy에서는 작동하지 않는다.

구성에 리터럴 달러 기호가 필요한 경우 $$(이중 달러 기호)를 사용할 수 있다. 이것은 또한 Compose가 값을 보간하는 것을 방지하므로 $$를 사용하면 Compose에서 처리하지 않으려는 환경 변수를 참조할 수 있다.



<br/>
<br/>
<br/>

        참조:
        https://docs.docker.com/storage/bind-mounts/
        https://docs.docker.com/compose/compose-file/compose-file-v3/#variable-substitution

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

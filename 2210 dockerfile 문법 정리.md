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

# Issue: dockerfile 문법 정리

## 상황: dockerfile 문법 정리

<br/>

## 정리: https://docs.docker.com/engine/reference/builder

<br/>

  #### FROM
  FROM 명령어는 새 빌드 단계를 초기화하고 후속 명령어에 대한 기본 이미지를 설정한다.
  한 도커 파일에서 여러개의 FROM을 볼 수 있는데 이는 여러개의 이미지를 만들 수 도 혹은 한 빌드 단계를 다른 빌드 단계에 대한 종속성으로 사용할 수 있게 한다.
  FROM image AS builder 에서 AS 문법을 통해 builder란 단계를 정의하면 COPY --from=builder를 통해 빌드된 이미지를 참조할 수 있다.

  #### WORKDIR
  WORKDIR 명령어는 Dockerfile에서 뒤에 오는 모든 RUN, CMD, ENTRYPOINT, COPY 및 ADD 명령어에 대한 작업 디렉터리를 설정한다.
  WORKDIR 명령어는 Dockerfile에서 여러 번 사용할 수 있고, 상대 경로가 제공되면 이전 WORKDIR 명령어의 경로를 기준으로 한다.
  WORKDIR를 지정하지 않으면 기본 작업 디렉토리는 /인데 이때 Dockerfile을 처음부터 빌드하지 않는 경우(FROM 스크래치) WORKDIR은 사용 중인 기본 이미지에 의해 설정될 수 있으므로 명시적으로 표현해놓는게 좋다.

  https://stackoverflow.com/questions/51066146/what-is-the-point-of-workdir-on-dockerfile

  #### COPY
 `[--chown=<user>:<group>]` <src>... <dest>
 
<br/>
<br/>
<br/>

        참조:

<br/>
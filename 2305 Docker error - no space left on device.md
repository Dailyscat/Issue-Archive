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

# Issue: Docker error : no space left on device

## 상황: Docker error : no space left on device

<br/>

## 알게된 부분 정리:

- docker system prune

<br/>

## 개념: docker system prune

<br/>
  docker volume rm $(docker volume ls -qf dangling=true)
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/30604846/docker-error-no-space-left-on-device

<br/>
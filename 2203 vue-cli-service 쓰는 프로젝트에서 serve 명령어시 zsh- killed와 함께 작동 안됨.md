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

# Issue: vue-cli-service 쓰는 프로젝트에서 serve 명령어시 zsh- killed와 함께 작동 안됨

## 상황: vue-cli-service 쓰는 프로젝트에서 serve 명령어시 zsh- killed와 함께 작동 안됨

<br/>

## 알게된 부분 정리:

- 상위 버전의 node로 npm install 했을 때 생긴 문제

<br/>

## 개념: 상위 버전의 node로 npm install 했을 때 생긴 문제

<br/>
  특정 node 버전으로 계속 작업을 해온 프로젝트.
  새로온 분이 lts도 아닌 가장 최신 버전의 node 버전으로 npm intsall
  이때 package-lock이 다 기억은 하고 있지만 높은 node 버전으로 install을 했을 때 유령모듈, 혹은 잘못 노말라이징된 패키지가 들어왔던 것 같다.
  
  이를 위해서 node_modules 날리고 다시 운용 중이던 버전으로 install 하니 정상.
<br/>
<br/>
<br/>

        참조:

<br/>


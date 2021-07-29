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

# Issue: network 문제로 페이지 뜨지 않을 때 확인해볼 순서 정리

## 상황: admin툴 내에 iframe으로 js 번들을 띄우는 방식에서 404 뜨는 상황

1. iframe에 적용된 https 도메인 -> https://a.wow.com -> load balancer -> b.wow -> 정적자원 서버에 정적 자원 요청 -> 정적자원 서빙 되지 않음

<br/>

## 알게된 부분 정리:

- 순서

<br/>

## 개념: 순서

<br/>
  1. iframe에 적용된 도메인에서 https://a.wow.com으로 요청이 제대로 가는가?
    1. https://a.wow.com으로 요청을 보냈을 때의 network 설정 확인(nginx)
    2. 직접 요청을 보내보고 확인한다.
    3. http인지 https 인지 요청을 둘다 보내고 확인한다.
  2. https://a.wow.com에서 load balancer로의 요청이 http인가 https인가를 확인하자.
  3. nginx 설정을 확인하자.
  4. 2 ~ 3 사이의 요청이 제대로 오고 가는지 확인하자.
  5. https://b.wow에서 정적자원 서버로의 요청과 응답을 확인하자.
    1. 정적자원 서버에 curl 요청
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

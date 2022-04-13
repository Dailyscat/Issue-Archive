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

# Issue: spring pathVariable 길이 제한으로 인한 에러

## 상황:
백엔드로 요청하는데 400 에러발생

<br/>

## 알게된 부분 정리:

- pathVariable에는 길이 제한이 존재한다.

<br/>

## 개념: pathVariable에는 길이 제한이 존재한다.

<br/>
  대략 8000자 정도를 보내니 에러가 발생
  pathVariable에는 제한이 있고
  max-http-header-size: 16KB의 적용을 해볼 수 도 있지만
  2000자 이상이 넘으면 get 방식에서는 길 수 록 느려진다.
  이정도 요청은 post로 넘어가자.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/37005382/spring-pathvariable-length-limit
<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

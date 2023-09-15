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

# Issue: webflux에서 BAD_REQUEST "Request body is missing"

## 상황: post 요청 이슈에 관해 조사하다가 확인

<br/>

## 알게된 부분 정리:

- serverWebExchange.getRequest().getBody() 를 한번이라도 호출하면 그 다음 chain 요청에서는 body를 찾을 수 없음.

<br/>

## 개념:

<br/>
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/68566554/spring-webflux-webfilter-removes-the-body-from-the-request
        https://github.com/spring-cloud/spring-cloud-gateway/issues/1587
        https://github.com/spring-cloud/spring-cloud-gateway/issues/1307

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

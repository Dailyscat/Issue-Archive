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

# Issue: webclient에서 동기 요청

## 상황:

<br/>

## 알게된 부분 정리:

- block()

<br/>

## 개념: block()

<br/>
하지만 block()을 통한 요청은 안티패턴.
이유는 쓰레드 병목을 일으켜 자원을 낭비하게 되고 성능에 영향을 미침.
  
  ```
  webClient.post()
         .bodyValue(myDto)
         .retrieve()
         .toEntity(MyDto.class)
         .block(); // <-- This line makes trick
  ```
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/58750918/how-to-use-webclient-to-execute-synchronous-request
        https://stackoverflow.com/questions/51449889/block-blockfirst-blocklast-are-blocking-error-when-calling-bodytomono-afte/64337846#64337846

<br/>

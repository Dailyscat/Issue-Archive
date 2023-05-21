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

# Issue: 2305 http로 구동중인 spring app https로 요청했을 때 에러.md

## 상황: 2305 http로 구동중인 spring app https로 요청했을 때 에러.md

<br/>

## 알게된 부분 정리:

- +

<br/>

## 개념:

<br/>
  You may be running the spring boot app on http only and then hitting it via https (or your browser is redirecting to https), so the http version is not parsed appropriately. You should have also:

java.lang.IllegalArgumentException: invalid version format: S���JZ
        at io.netty.handler.codec.http.HttpVersion.<init>(HttpVersion.java:116) ~[netty-codec-http-4.1.73.Final.jar!/:4.1.73.Final]

Please assure the proper browser/request/protocol/encoding.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/70539708/netty-decoding-failed-defaultfullhttprequest

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

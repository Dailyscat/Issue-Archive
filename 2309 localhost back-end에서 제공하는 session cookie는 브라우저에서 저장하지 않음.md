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

# Issue: localhost back-end에서 제공하는 session cookie는 브라우저에서 저장하지 않음

## 상황:

<br/>

## 알게된 부분 정리:

- localhost back-end에서 제공하는 session cookie는 브라우저에서 저장하지 않음

<br/>

## 개념: localhost back-end에서 제공하는 session cookie는 브라우저에서 저장하지 않음

<br/>
  보안 이슈로 인해
  도메인이 localhost이고 fqdn에 최소 두개 이상의 점이 포함되지 않았으며 http라면 브라우저는 backend에서 제공하는 session cookie를 저장하지 않음.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/1134290/cookies-on-localhost-with-explicit-domain/1188145#1188145
        https://stackoverflow.com/questions/69231649/cookie-not-being-set-in-headers-using-cors-with-nodejs
        https://github.com/axios/axios/issues/4783#issuecomment-1254765164

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

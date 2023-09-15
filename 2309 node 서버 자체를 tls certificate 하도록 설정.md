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

# Issue: next 서버 자체를 tls certificate 하게 설정

## 상황:
https를 가진 spring server에 요청하려면 next node 서버도 certificate를 가져야해서 조사. 하지만 next에서 제대로 제공하는 certificate는 없음. hacky한 방식만 존재.

<br/>

## 알게된 부분 정리:

- NODE_EXTRA_CA_CERTS 환경변수를 통한 ca.pem 파일 적용
- next.js에서 server.js를 따로 만들어서 next.js 서버 관리.

<br/>

## 개념: NODE_EXTRA_CA_CERTS 환경변수를 통한 ca.pem 파일 적용

<br/>
  https://github.com/vercel/next.js/discussions/29043?sort=old
  https://github.com/vercel/next.js/discussions/13546
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념:

<br/>
  https://gist.github.com/GabenGar/078de497f181742fb9a669dd8dcfa130
<br/>
<br/>
<br/>

        참조:

<br/>

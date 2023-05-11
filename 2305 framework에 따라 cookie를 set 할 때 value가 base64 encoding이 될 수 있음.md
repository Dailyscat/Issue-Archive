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

# Issue: framework에 따라 cookie를 set 할 때 value가 base64 encoding이 될 수 있음

## 상황: framework에 따라 cookie를 set 할 때 value가 base64 encoding이 될 수 있음

<br/>

## 알게된 부분 정리:

- framework에 따라 cookie를 set 할 때 value가 base64 encoding이 될 수 있음

<br/>

## 개념: framework에 따라 cookie를 set 할 때 value가 base64 encoding이 될 수 있음

<br/>
  spring에서 응답에 cookie set 할 때는 literal 그대로 set 되지만 next.js에서 응답에 set을 하니 value를 자꾸 encoding함. 왜이런가 했더니 이게 권장사항 spec. url-encodes 처리해버림.
  https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Set-Cookie
  https://wicg.github.io/cookie-store/#CookieStore-set

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/49205195/should-cookie-values-be-url-encoded

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

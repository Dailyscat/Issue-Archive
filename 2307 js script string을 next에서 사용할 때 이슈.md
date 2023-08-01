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

# Issue: js script string을 next에서 사용할 때 이슈

## 상황:
Prop으로 내려오는 Js script 처리할 때 이슈.

<br/>

## 알게된 부분 정리:

- next head로 감싸기.
- eval, Function으로 해당 함수 실행.

<br/>

## 개념: next head로 감싸기.

<br/>

  ```
      <Head>
        <script>{scriptContent}</script>
      </Head>

  warn-once.js:11 Do not add <script> tags using next/head (see inline <script>). Use next/script instead.
  ```

위 방식은 콘솔 에러를 자아냄. 그리고 seo 관점에서 페이지 로딩 시에 script 태그를 확인해보는 프로세스를 거칠 수 밖에 없음.

<br/>
<br/>
<br/>

        참조:
        https://github.com/vercel/next.js/discussions/17919

<br/>

## 개념: eval, Function으로 해당 함수 실행.

<br/>
  eval, function은 약간의 위험을 포함한다.
  하지만 이 위험은 페이지내에서 입력값이 eval, function과 관계가 있을때만 유효하다.
  다만 eval, function 자체는 jit compiler 사용에 있어 performance에 큰 저하를 일으키게 된다.
<br/>
<br/>
<br/>

        참조:
        https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/eval

<br/>

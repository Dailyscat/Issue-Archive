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

# Issue: substr, substring 분류

## 상황:

substr, substring 적재적소에 사용하기 위해 정리

<br/>

## 알게된 부분 정리:

- 가져오는 개수, 가져오는 포인트로 정리할 수 있다.

<br/>

## 개념: 가져오는 개수, 가져오는 포인트로 정리할 수 있다.

<br/>
  const exam = "012345";

exam.subStr(1,3) => 123; index 1부터 3개의 문자를 가져온다.
exam.subString(1,3) => index 1부터 index 3전까지의 문자열을 가져온다.
<br/>
<br/>
<br/>

        참조:
        https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/substr

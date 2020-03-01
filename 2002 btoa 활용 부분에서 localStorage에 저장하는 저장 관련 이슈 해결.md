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

# Issue: btoa를 활용하여 localStorage에 저장하는 과정 관련 이슈

## 방안: encodeURIComponent()`를 병행 사용 (성공)

<br/>
  데이터 객체 자체를 인코딩하여 저장하려는 방식을 처음에 시도했습니다. 이 부분에서 `Failed to execute 'btoa' on 'Window': The string to be encoded contains characters outside of the Latin1 range.` 와 같은 에러가 발생했고 ASCII Table에서 괄호와 같은 것들을 변환할 수 없어서 생기는 에러로 확인했습니다. 이를 위하여 UTF-8로 인코딩해주는 API인 `encodeURIComponent()`를 병행 사용하여 성공적으로 인코딩과 디코딩을 진행하였습니다.
<br/>
<br/>
<br/>

        참조:
    json - Base64 encode a javascript object - Stack Overflow

    https://stackoverflow.com/questions/38134200/base64-encode-a-javascript-object

    Base64 encoding and decoding - Web API | MDN
    https://developer.mozilla.org/ko/docs/Web/API/WindowBase64/Base64_encoding_and_decoding

    Web Storage API 사용하기 - Web API | MDN
    https://developer.mozilla.org/ko/docs/Web/API/Web_Storage_API/Using_the_Web_Storage_API

    [JAVASCRIPT] 자바스크립트를 이용해 문자열 BASE64 인코딩/디코딩 하기
    http://1004lucifer.blogspot.com/2016/01/javascript-base64.html

    reactjs - componentWillUnmount() not being called when refreshing the current page - Stack Overflow
    https://stackoverflow.com/questions/39084924/componentwillunmount-not-being-called-when-refreshing-the-current-page

<br/>

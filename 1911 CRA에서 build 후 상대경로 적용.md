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

# Issue: CRA에서 Build 후 index.html에 상대경로 적용

## 상황:

icon등 정적 자원의 사용을 위해 build 후 상대경로를 지원해야 하는 상황

<br/>

## 생각해낸 방안:

- package.json의 homepage에 ./ 추가

<br/>

## 방안: package.json의 homepage에 ./ 추가 (성공))

<br/>
  [stackover flow](https://stackoverflow.com/questions/46235798/relative-path-in-index-html-after-build)
  [Cra의 Issue](https://github.com/facebook/create-react-app/issues/165)

<br/>
<br/>
<br/>

        참조:

<br/>

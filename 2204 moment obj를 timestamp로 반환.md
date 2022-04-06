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

# Issue: moment obj를 timestamp로 반환

## 상황:
moment를 의존성으로 가지고 있는 라이브러리를 사용하는 상황에서 timestamp값으로 비교해야하는 상황

<br/>

## 알게된 부분 정리:

- moment().unix, moment().valueOf();

<br/>

## 개념: moment().unix, moment().valueOf();

<br/>
  필요했던건 moment 오브젝트에서 timestamp 값을 milliseconds로 갖는거라서 unix * 1000 로 해결이 되나 했는데 가독성으로도 안좋고 해서 고민하다가 valueOf로 정확히 원하는 값을 받을 수 있었다.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/25734743/how-to-return-the-current-timestamp-with-moment-js
        https://momentjs.com/docs/#/parsing/utc/
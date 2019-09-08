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

# Issue: vue에서 반복문 사용하여 리스트 ui 사용시에 key 값과 관련한 오류

## 상황:

vue에서 for문을 사용하여 만든 div를 CRUD 하는 과정에서 unique한 key값을 주지않아서 제대로 작동하지 않던 상황

<br/>

## 생각해낸 방안:

- unique한 key값을 주어 해결

<br/>

## 방안: unique한 key값을 주어 해결 (성공)

<br/>
vue를 한번도 사용해본적은 없었지만..

vue 프로젝트에서 트러블 슈팅 하는 과정을 같이 해결했다.

vue는 react와 달리 state를 직접 업데이트 하는데 그 과정에서 ui가 원하는대로 업데이트 되지 않았다.

두개의 캐러셀을 사용하는데
한 캐러셀은 현재 해당하는 요소의 확대 이미지, 그리고 한 캐러셀은 추가한 사진들의 배열이었다.

이 과정에서 사진들의 배열에서 어떤 사진을 지웠을 때 위의 캐러셀은 지워지지 않던 상황.

같은 state를 공유하는데 왜 안되는지 찾는 과정에서 유일하게 달랐던 점이 key값이었다.

다른 캐러셀과 같이 blob의 url을 받아서 key 값을 주니
제대로 작동하는 것을 확인할 수 있었다.
<br/>
<br/>
<br/>

        참조:
        https://medium.com/@hozacho/%EB%A7%A8%EB%95%85%EC%97%90vuejs-v-for-%EB%A6%AC%EC%8A%A4%ED%8A%B8-%EB%A0%8C%EB%8D%94%EB%A7%81-%EB%B0%98%EB%B3%B5%EB%AC%B8-83501d7a635a

<br/>

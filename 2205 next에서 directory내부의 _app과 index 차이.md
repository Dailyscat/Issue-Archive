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

# Issue: next에서 directory내부의 _app과 index 차이

## 상황:
_app이 react의 엔트리 컴포넌트를 커스텀해서 사용하는 건 알겠는데 index로 들어오는거랑 무슨차이인가 싶어서 검색

<br/>

## 알게된 부분 정리:

- _app과 index 차이

<br/>

## 개념: _app과 index 차이

<br/>
기본적으로 /으로 들어왔을 때와 전체 페이지를 다루는 관점에서 다르다.
만약에 _app.js에 <div>hello world</div>를 넣으면 모든 페이지에서 이걸 볼 수 있음
반면 index.js는 /로 접근했을 때만 보여지는 페이지
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/70456065/whats-is-the-difference-between-index-js-and-app-js-in-nextjs

<br/>

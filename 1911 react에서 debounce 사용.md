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

# Issue: React에서 Debounce 사용

## 상황:

admin에서 user에 대한 정보를 memo하는 부분에서 자동 저장에 대한 니즈가 있어서 debounce 사용하여 처리하도록 결정

<br/>

## 생각해낸 방안:

- lodash의 debounce 사용하여 서버 부하를 줄이기로 했다.

<br/>

## 방안: (성공)

<br/>

이현섭님의 블로그글에서 쉬운 해답을 찾아서 아카이빙 해놓는다.

> Lodash의 throttle과 debounce는 기본적으로 이벤트 핸들러의 동기적인 실행을 막는 메소드이기 때문에 React의 Synthetic 이벤트와 궁합이 맞지 않는다.

> 해결방법은 간단하다. 이벤트 핸들러를 두 개로 쪼개면 된다. React의 SyntheticEvent를 동기적으로 핸들링할 Debounce되지 않은 이벤트 핸들러 하나, 필요한 데이터를 받아 무거운 동작을 수행할 Debounce된 이벤트 핸들러 하나.

<br/>
<br/>
<br/>

        참조:
            http://blog.weirdx.io/post/55337
            https://stackoverflow.com/questions/23123138/perform-debounce-in-react-js
            https://reactjs.org/docs/events.html#event-pooling

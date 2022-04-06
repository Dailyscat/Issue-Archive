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

# Issue: vue에서 currentTarget이 null일 때

## 상황: 
vue에서 이벤트 핸들러의 인자로 event를 받아서 input을 제어하는 과정
이때 특정 상황에서 cumstom으로 만든 confirm을 클릭 후에 
event.currentTarget을 실행
이때 계속 null 발생

<br/>

## 알게된 부분 정리:

- currentTarget과 target이 다른점

<br/>

## 개념: currentTarget과 target이 다른점

<br/>
  currentTarget과 target의 다른점으로 일단 이벤트 핸들러가 실제로 매핑된곳인지 아닌지에 차이라고 알고 있었는데 실질적으로는 틀린말이었고 좀더 정확히 정의해보면
  currentTarget은 이벤트를 핸들링하는 시점에만 유효하다. 즉 이벤트가 끝난 후에는 null이 된다.
  나는 input event 후에 confirm에서 click 이벤트를 지난 후에 input event의 currentTarget을 잡으려고해서 이때는 이미 null로 참조할 수 없었던 상황
<br/>
<br/>
<br/>

        참조:
        https://developer.mozilla.org/en-US/docs/Web/API/Event/currentTarget
        https://github.com/vuejs/vue/issues/2236
        https://stackoverflow.com/questions/26496176/console-log-event-object-shows-different-object-properties-than-it-should-have
<br/>

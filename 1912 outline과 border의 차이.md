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

# Issue: outline과 border의 차이

## 상황:

ckeditor4를 texterea와 같은 형태로 구축하는 과정에서 focusing 되면 노란색 테두리가 박히는 방식을 사용해야 했다. 근데 border을 조정해봐도 테두리가 도통 박히지 않았는데 이를 해결해야하는 상황

<br/>

## 생각해낸 방안:

- focus가 되면 생기는 border의 정체알기

<br/>

## 과정: focus가 되면 생기는 border의 정체알기

<br/>
  [데모](https://jsfiddle.net/EungyuLee/onbr28je/)를 만들어 봤다.

즉 outline은 말그대로 element 외부 둘레를 얘기하는 것이고 border는 요소의 경계를 얘기한다.

물론 outline과 border는 본질적으로 정확히 같고 심지어 css에서 사용되는 문법조차 같지만 적용되는 위치가 다르다.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/3397113/how-to-remove-focus-border-outline-around-text-input-boxes-chrome
        https://cssreset.com/css-tips-outline-vs-border/

<br/>

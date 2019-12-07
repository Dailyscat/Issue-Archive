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

# Issue: editable이 가능한 div(Element)에서 Onchange

## 상황:

editable 속성을 사용할 때 보통 onChange와 같은 메소드가 필요한 경우가 많은데 찾아본 정보를 정리해놓는다.

<br/>

## 생각해낸 방안:

- onInput 이벤트를 적용하여 처리

<br/>

## 방안: onInput 이벤트를 적용하여 처리 (실패)

<br/>

onInput 이벤트를 사용하면 onChange와 같은 방식으로 사용이 가능하다.

하지만 어떠한 editor를 사용할 때 editor 내부의 변화를 감지할 수는 없기 때문에(글꼴, 색깔 등등) 이를 목표로 함으로써 사용하는 것은 좋지않다.

또한 IE의 낮은 버전 브라우저에서는 작동하지 않는 경우가 만힉 때문에 MutationObserver api를 조합해서 사용해야 한다.

꼭 필요한 경우에만 사용하게 될 것 같다.

<br/>
<br/>
<br/>

        참조:
        https://github.com/facebook/react/issues/278#issuecomment-67952133

        https://stackoverflow.com/questions/1391278/contenteditable-change-events

<br/>

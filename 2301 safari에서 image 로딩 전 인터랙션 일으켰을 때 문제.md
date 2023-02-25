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

# Issue: safari에서 image 로딩 전 인터랙션 일으켰을 때 문제

## 상황: safari에서 image 로딩 전 인터랙션 일으켰을 때 해당 컴포넌트에 붙은 이벤트 절대 일어나지 않음.

다만 다른 컴포넌트를 클릭해서 인터랙션을 한번 일으 킨 후에 다시 이전 컴포넌트를 누르면 인터랙션이 정상적으로 일어남.

<br/>

## 알게된 부분 정리:

- iOS WebView에서는 일부 JavaScript 코드가 비동기적으로 실행되며, UI 렌더링과 JavaScript 이벤트 처리는 병행적으로 실행된다.

<br/>

## 개념: iOS WebView에서는 일부 JavaScript 코드가 비동기적으로 실행되며, UI 렌더링과 JavaScript 이벤트 처리는 병행적으로 실행된다.

<br/>
이 문제는 iOS WebView에서 JavaScript 코드가 실행되는 방식과 관련이 있다. iOS WebView에서는 일부 JavaScript 코드가 비동기적으로 실행되며, UI 렌더링과 JavaScript 이벤트 처리는 병행적으로 실행된다.

따라서, 첫 번째 시도에서는 input 요소에 대한 focus 이벤트가 동작하지 않을 수 있. 그러나, 이후에 다른 버튼을 클릭하여 인터랙션을 발생시키면, WebView는 JavaScript 코드를 처리하기 위해 새로운 작업 큐를 생성한다. 이때, 이전에 쌓인 작업들이 처리되고, input 요소에 대한 focus 이벤트가 발생할 수 있다.

그리고, 다시 input 요소에 focus 이벤트를 발생시키면, WebView는 이미 JavaScript 코드를 처리하기 위한 작업 큐를 생성했으며, 이전에 처리되지 못했던 input 요소의 focus 이벤트도 함께 처리되므로, 예상한 대로 인터랙션이 동작할 수 있다.

이런 경우에는, input 요소에 대한 focus 이벤트 핸들러 함수를 작성할 때, input 요소에 focus 이벤트가 발생하면 어떤 작업을 수행할지에 대한 로직을 구현하면 된다. 이벤트 처리 순서가 보장되지 않으므로, input 요소에 대한 focus 이벤트 핸들러 함수가 호출되지 않을 수도 있다. 이때는, requestAnimationFrame() 함수를 사용하여 이벤트 처리를 지연시키는 것이 좋다.
<br/>
<br/>
<br/>

        참조:

<br/>

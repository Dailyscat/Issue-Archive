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

# Issue: type="number"인 input에서 decimal이 입력되지 않게 하기

## 상황: type이 number인 input은 숫자, 점, -(대쉬)외에 다른 char이 쳐지지 않는다.
이때 decimal, 소수를 표현하는걸 막아야 하는 상황

<br/>

## 알게된 부분 정리:

- onKeyPress 이벤트 활용, but drop
- 좀 더 확실하게 그냥 type=number를 지우고 script로 반영되도록 설정


<br/>

## 개념: onKeyPress 이벤트 활용

<br/>

```
<input name="num" 
                      type="number"
                      onkeypress='return event.charCode >= 48 && event.charCode <= 57'
                      title="Numbers only">
```

하지만 keyPress 이벤트는 웹 표준에서 drop됐다.
이와 같은 부분의 해결로는
[beforeInput event](https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/beforeinput_event)
[keydown](https://developer.mozilla.org/en-US/docs/Web/API/Document/
keydown_event)
이 대신될 수 있다.

이때 이벤트 발생 순서는 아래와 같다.

```
Currently, UI Events defines event order of "keypress" event is:

keydown
beforeinput
keypress
input
keyup

However, both Chrome and Safari uses this event order:

keydown
keypress
beforeinput
input
keyup
```


<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/37043867/how-to-avoid-decimal-values-in-input-type-number
        https://developer.mozilla.org/en-US/docs/Web/API/Document/keypress_event
        https://github.com/w3c/uievents/issues/220

<br/>

## 개념: 좀 더 확실하게 그냥 type=number를 지우고 script로 반영되도록 설정

<br/>
  기존 attribute를 사용하는게 더 편하고 의미에 맞게 사용하는 부분이지만
  어쨌든 number라는 속성에서 소수를 제외하는것 자체가 hacky하게 사용하게 되는 부분이라서
  스크립트로 제외해주어서 사용했다.

  ```
   const targetPoint = ev.currentTarget.value;
            ev.currentTarget.value = (targetPoint.indexOf(".") >= 0) ? (targetPoint.substr(0, targetPoint.indexOf("."))) : targetPoint;
  ```
<br/>
<br/>
<br/>

        참조:

<br/>

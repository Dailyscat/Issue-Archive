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

# Issue: react에서 scroll이벤트를 추가했다가 삭제하는 상황에서 에러

## 상황:

scroll의 height를 확인하여 sider가 fix 되야 하는 지점이 있었다.
이때 스크롤 이벤트를 처음에 등록하고 unMount될 때 스크롤 이벤트를 없애는 방식이었는데 스크롤 이벤트가 remove 되지 않던 상황

<br/>

## 생각해낸 방안:
+ this 값에서 발생하는 오류
+ react는 다르지 않을까?


<br/>

## 방안: this 값에서 발생하는 오류 (실패)
<br/>
  
  이벤트를 등록하는 과정에서 bind(this)를 추가하여 스크롤 발생시 함수가 제대로 작동하게 만들었다.
  이와 비슷하게 unMount에서 발생하는 오류도 잡아낼 수 있을 것 같아서 bind(this)를 중심으로 하여 디버깅 해봤지만 오류를 잡아낼 수 없었다.

<br/>
<br/>
<br/>

        참조:
        
<br/>

## 방안: react는 다르지 않을까? (성공)
<br/>
  
  constructor에서 처음에 함수를 bind 시키지 않고,
  arrowFunction을 사용한 함수 표현식도 사용되지 않았다면

  함수 선언식으로 작성된 함수를 react에서 사용할 때
  그 함수는 render와 함께 매번 새롭게 만들어 진다

  그렇다보니 이벤트 등록 당시의 함수와
  remove 함수가 작동할 때 등록하는 함수가 서로 다르니
  제대로 이벤트가 지워지질 못하는 상황이었다.
  
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/38564080/remove-event-listener-on-unmount-react

<br/>


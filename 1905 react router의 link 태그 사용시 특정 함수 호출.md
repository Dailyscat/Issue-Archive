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

# Issue:  모바일에서 react router의 link를 사용했을 때 상단으로 올라가게 해야하는 상황

## 상황:

모바일 웹에서 react router의 Link 태그를 클릭했을 때 window.scrollTo(0.0)을 사용해야하는 상황

<br/>

## 생각해낸 방안:
+ button으로 감싸야 하나 고민을 잠시...
+ Link 자체에 마련해 놓은 속성을 확인


<br/>

## 방안: button으로 감싸기 (실패)
<br/>

링크 태그 자체가 a태그와 같이 default로 동작하기 때문에, wrapping 하는거는 최후의 수단으로 생각하기로 했다.

<br/>
<br/>


## 방안: Link 자체에 마련해 놓은 속성을 확인 (성공)
<br/>

[react router 문서](https://reacttraining.com/react-router/web/api/Link/innerref-function)에 너무나 친절하게 innerRef: function이라는 속성을 소개하고 있다. 이전에 link의 state 값을 사용해서 이전에 보던 페이지로 돌아가는 기능을 구현해본 적이 있어서 바로 떠올릴 수 있었다!

<br/>
<br/>
<br/>

        참조:
        https://reacttraining.com/react-router/web/api/Link/innerref-function

<br/>


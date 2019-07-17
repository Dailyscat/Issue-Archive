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

# Issue: Stacking Context의 오남용으로 인한 footer의 아이콘들이 클릭되지 않던 오류

## 상황:
홈페이지 우측 하단에 카카오톡 상담하기 버튼이 있는데
이게 footer까지 스크롤을 했을 때 클릭되지 않고
오류가 있어보이는 뷰를 보여서 해결해야했던 상황

<br/>

## 생각해낸 방안:
+ z-index 주기
+ footer 컴포넌트 아래에 카카오톡 상담 아이콘 태그를 위치시킨다.


<br/>

## 방안: z-index 주기  (실패)
<br/>

  무작정 z-index를 주었지만 z-index도 부모 div 끼리의 stacking context를 이기진 못하기 때문에 실패.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: footer 컴포넌트 아래에 카카오톡 상담 아이콘 태그를 위치시킨다. (성공)
<br/>

  footer 컴포넌트와 fixed 해야 하는 아이콘 자체를 결합하여
  새로운 컴포넌트로 만들어서 stacking context를 지키고
  해결할 수 있었다.
<br/>
<br/>
<br/>

        참조:

<br/>


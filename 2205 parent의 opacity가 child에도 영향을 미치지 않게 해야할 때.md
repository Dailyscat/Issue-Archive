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

# Issue: parent의 opacity가 child에도 영향을 미치지 않게 해야할 때

## 상황:
parent는 dim이라서 opacity를 가져야하는데 child는 내용을 명확히 표현해야함.

<br/>

## 알게된 부분 정리:

- w3c의 opacity 스펙
- 파훼법

<br/>

## 개념: w3c의 opacity 스펙

<br/>
  
  opacity는 후처리 작업으로 분류되기 때문에 각 엘레멘트들이 렌더링 된 이후에 opacity를 가지고 있는 엘레멘트, 그리고 그 자식들 전부 opacity를 적용받게 된다.

<br/>
<br/>
<br/>

        참조:
        https://www.w3.org/TR/css-color-3/#transparency

<br/>

## 개념: 파훼법

<br/>
  
  엘레멘트가 rgba offscreen image로 렌더링 된 이후 opacity가 적용되기 때문에 opacity가 아닌 background: rgba(64, 64, 64, 0.5) 와 같이 rgba의 마지막 인자로 투명도를 조절한다.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/5148128/parent-div-transparent-background-but-not-affect-child-div-transparency
        https://stackoverflow.com/questions/5770341/i-do-not-want-to-inherit-the-child-opacity-from-the-parent-in-css
        https://stackoverflow.com/questions/10879045/how-to-set-opacity-in-parent-div-and-not-affect-in-child-div?noredirect=1&lq=1

<br/>

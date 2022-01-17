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

# Issue: 천지인 키보드를 사용하는 유저의 키패드에 검색, 개행이라고 표시되도록 input 구성

## 상황:
천지인 키보드를 사용하는 유저의 키패드에 엔터가 표시되는 부분에 검색, 개행이라고 표시되도록 input 구성

<br/>

## 알게된 부분 정리:

- input을 감싼 form 태그에 action 속성 추가

<br/>

## 개념:

<br/>
  
  ```
  <form class="" onsubmit="return false;" novalidate="" action>
                <fieldset>
                    <input type="search" id="" name="" class="" title=" " placeholder="" value="nike" required="">
                </fieldset>
            </form>
  ```
<br/>
<br/>
<br/>

        참조:

<br/>
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

# Issue: react-slick 적용 과정에서 'getBoundingClientRect' 에러

## 상황:

    Exception in delivering result of invoking 'options.getIndexOptions': TypeError: Cannot read property 'getBoundingClientRect' of null

<br/>

## 생각해낸 방안:

- slick을 사용할 때의 태그 내부 문제
  <br/>

## 방안: slick을 사용할 때의 태그 내부 문제 (성공)

<br/>
  보통 slick의 사용은

`import Slider from "react-slick";`

를 사용하고 Slider 컴포넌트에 속성과 children에 원소들을 주는 방식으로 작성한다.

이 과정에서 Slider에 children이 없는 경우 위와 같은 에러 메세지가 출력된다.

<br/>
<br/>
<br/>

        참조:

<br/>

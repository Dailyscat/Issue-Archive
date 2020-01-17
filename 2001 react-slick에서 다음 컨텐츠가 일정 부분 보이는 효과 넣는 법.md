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

# Issue: react-slick에서 다음 컨텐츠가 일정 부분 보이도록 퍼블리싱

## 상황:

기획상 다음 컨텐츠의 일부분이 보여야하는 상황이다. 하지만 infinite 옵션을 적용한 상태의 react-slick은 이게 굉장히 까다로운 상황

<br/>

## 생각해낸 방안:

- padding과 em을 활용한 방법
- decimal point 활용......

<br/>

## 방안: padding과 em을 활용한 방법(실패)

<br/>

[참고](https://github.com/akiran/react-slick/issues/443#issuecomment-419682040)
여기서 em은 body의 font-size를 상속받는데, 이는 현재 화면에서 보이는 캐러셀의 길이를 조절할 수 있고 padding을 줌으로써 각 간격을 조절한다는건데, 이는 사실 컨텐츠 자체의 CSS에 따라 다르다. 많은 이들이 추카추카를 눌러놨지만.. 활용도는 있으나 적합한 방식은 아니었다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: decimal point 활용 (성공)

<br/>

역시 사람마음은 다 똑같아. [이슈](https://github.com/akiran/react-slick/issues/674#issue-215201378)를 올린 사람의 마음이 나와 같다.

중간에 easy Enough를 댓글로 단 분의 말도 이해는 가지만 모든 컴포넌트가 다 다르기 때문에 이도 적용안되는.. 나같은 사람이 있을것이다.

[답변](https://github.com/akiran/react-slick/issues/674#issuecomment-445106473) 이 답변이야말로 위와 같은 UI를 구성하는데 모든 사람이 사용할 수 있는 방법일 것이다.

어쨌든 컴포넌트의 width는 지정이 될 수 밖에 없는 상황이 많기 때문에..

물론 위와같은 decimal 포인트로 작성을 했지만 margin과 padding을 조정하여 최적의 UI를 구성해야했다.

<br/>
<br/>
<br/>

        참조:

<br/>

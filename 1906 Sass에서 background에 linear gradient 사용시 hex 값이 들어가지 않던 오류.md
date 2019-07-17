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

# Issue: Sass에서 background에 linear gradient 사용시 hex 값이 들어가지 않던 오류

## 상황:

ellipsis를 대체하여 gradient로 컨텐츠가 더 있음을 표현하려는 중에 #값이 적용되지 않아서 오류가 발생하고 있었다.

<br/>

## 생각해낸 방안:
+ 오류에 떠 있던 것과 같이 검색하였고, 디버깅을 해보았다.

<br/>


## 방안: 오류에 떠 있던 것과 같이 검색하였고, 디버깅을 해보았다. (성공)
<br/>

  주석처리를 해보며 디버깅을 하나씩 해나갔다.

  sass 업데이트도 적용해보았으나 안되었다.

  에러 검색을 해보다가 특수문자 사용이 문제였다는 내용을 보았고, hex 값에 있는 #이 혹시 문제가 되나 싶어서 삭제를 해보았더니
<br/>
<br/>
<br/>

        참조:
        linear-gradient mixin conflicts with CSS background property · Issue #1358 · Compass/compass
        https://github.com/Compass/compass/issues/1358

<br/>


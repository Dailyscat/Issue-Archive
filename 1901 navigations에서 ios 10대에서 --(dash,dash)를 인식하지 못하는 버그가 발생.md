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

# Issue: navigations에서 ios 10대에서 --(dash,dash)를 인식하지 못하는 버그가 발생

## 상황:

이전에 적용했던 slider가 ios10의 기기에서 제대로 작동하지 않는다는 보고를 받고 해결해나가야 했던 상황

<br/>

## 생각해낸 방안:
+ 디버깅하며 겨우겨우 찾아낸 --에러..
+


<br/>

## 방안: --를 바꿔보자.  (성공)
<br/>

  에러가 워낙 말도 안되는 에러였어서 이것저것 다 해봤다.
  지쳐갈 즈음
  참조에 있는 사이트를 보게됐고, 혹시나 해서 --를 변경해보니
  제대로 작동했다.

<br/>
<br/>
<br/>

        참조:
        https://github.com/pugjs/pug/issues/2907
        https://www.reddit.com/r/web_design/comments/4ji5p7/safari_completely_ignores_classnames_starting/

<br/>


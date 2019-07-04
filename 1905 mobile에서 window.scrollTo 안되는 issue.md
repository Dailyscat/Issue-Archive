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

# Issue: mobile에서 window.scrollTo가 되지 않는 오류

## 상황:

모바일에서 페이지가 전환 될 때 window.scrollTo가 필요한 부분이 있었는데 되지 않았다.

<br/>

## 생각해낸 방안:
+ ui library의 오류 
+ meteor를 모바일에서 사용할 때의 레거시 자체의 오류
+ scroll 자체의 오류


<br/>

## 방안: ui library의 오류, meteor를 모바일에서 사용할 때의 레거시 자체의 오류   (실패)
<br/>

  예전에 개인 프로젝트를 진행할 때 window.scrollTo가 안됐던 경험이 있어서 비슷한 맥락으로 접근을 시도 했다.

  그때는 ul library자체 인풋에 focus가 default로 걸려 있어서
  scrollTo가 되지 않았었다.
  그래서 비슷한 맥락으로 접근하려고 했으나 이번 이슈는 setTimeOut을 해도 되지 않는 부분이 있었고, 거진 모든 페이지에서 안됐기 때문에 다른 대안을 찾아야 했다.

  meteor 자체에서 오류가 발생할 수 도 있다고 생각하여 찾아봤으나 이 정도 오류면 이슈가 꽤 있을 거 같지만 1도 없었다.

<br/>


## 방안: scroll 자체의 오류 (성공)
<br/>
  
  레거시 코드 문제였다.

  desktop 랜딩에서의 css 설정을 mobile에서는 다르게 줘야 했는데, 따로 설정을 안하고 html, body에 둘다 overflow-x: hidden을 줘놓았다. overflow가 hidden이면 scrollTo가 작동하지 않기 때문에 안되고 있었던 것이다.

  그래서 아예 리팩토링을 하여, overflow-x를 주지 않게끔 문제를 해결해 놓았다. 문제는 랜딩자체의 width 값이 모바일에 적용되어서 모바일에서 width가 한없이 넓어진다는 문제였는데, 그 위의 media query 자체에서 몇가지 설정만 바꿔줬으면 금방해결되는 문제였다.

<br/>
<br/>
<br/>

        참조: 
        https://greensock.com/forums/topic/11336-html-overflow-hidden-preventing-javascript-scrolling-in-parallax-application/

<br/>


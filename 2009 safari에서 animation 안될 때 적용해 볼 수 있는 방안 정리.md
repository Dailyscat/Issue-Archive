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

# Issue: safari 관련 background, animation known 이슈 정리

## 상황: safari에서 발생되는 애니메이션 관련 에러 찾아보다가 발견한 known issue 정리

<br/>

## 알게된 부분 정리:

- 오래된 safari 버전(4,5)에서는 animation 관련 shorthand가 작동되지 않을 수 있음
- background: rgba(255, 255, 255, .8) 에서 0.8로 주면 안될 수 있음
- background-color: rgba(255,255,255,0.5); 은 오래된 browser에서는 단순히 white background로 보이게 된다.
- safari에서 animation 안될 때 해볼법한 방안

<br/>

## 개념: safari에서 animation 안될 때 해볼법한 방안

<br/>
1.
  @-webkit-keyframes 내부의 from to 를 
  0% ~ 100% 로 바꿔보기

2. 
  background: rgba(255,255,255,.2) 를 
  
  background: #fff;
  opacity: .2;
  로 바꿔보기

3. 
  오래된 브라우저에서 발생될 수 있는 문제로는 shorthand 문법을 펼쳐서 적용해보기.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/5144541/css-background-image-only-working-in-chrome-and-safari
        https://stackoverflow.com/questions/22742389/background-image-position-50-not-working-in-safari
        https://stackoverflow.com/questions/29530655/background-color-css-not-working-in-safari-but-working-fine-in-chrome-firefox

<br/>

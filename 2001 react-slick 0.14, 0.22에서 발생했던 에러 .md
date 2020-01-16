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

# Issue: react-slick 0.14, 0.22에서 에러 발생

## 상황:

autoPlay를 적용한 상태였는데 웹에서는 잘 되는데 mobile view에서는 잘 되지 않던 상황

<br/>

## 생각해낸 방안:

- version Change

<br/>

## 방안: version Change(성공)

<br/>
  (1) 현재 0.14 버전이었고 업그레이드 하라는 issue를 확인하고 업그레이드했으나 setInterval이 계속 도는지 키보드가 뜨거워지고 콘솔에 
  
  <img src="https://user-images.githubusercontent.com/224268/37970811-c824c744-31aa-11e8-9d21-9e07677fcc75.png"/>

위와 같은 에러가 계속 발생했다.

(2) 또 찾아보니 0.23 version에서는 이를 고쳤다는 말을 봐서 바로 업그레이드 하니 다시 제대로 동작했다. [댓글](https://github.com/akiran/react-slick/issues/1139#issuecomment-574018365)도 달았다

<br/>
<br/>
<br/>

        참조:
        https://github.com/akiran/react-slick/issues/1150
        https://stackoverflow.com/questions/52900291/how-to-add-an-initial-delay-to-autoplay-in-react-slick-slider
        https://github.com/akiran/react-slick/issues/887
        https://github.com/akiran/react-slick/issues/709
        증말 이건 맨날 찾는거같다 기억을 못해가지고..
        https://stackoverflow.com/questions/10972176/find-the-version-of-an-installed-npm-package

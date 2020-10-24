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

# Issue: ios에서 modal을 띄울 때 body의 스크롤 막기

## 상황: 상품에 관해 모달을 띄웠을 때 해당 모달에서만 스크롤이 되는게 아니라 body에서도 동작하는 버그

## 생각해낸 방안:

- body에 hidden
- body에 pointerEvents에 none
- body에 touchmove eventlistener 추가
- fixed로 스크롤을 막고 scrollTo로 모달이 사라질 시 다시 스크롤링 되도록 설정

## 방안(실패): body에 hidden

<br/>

- body에 hidden
    - ios safari에서는 동작하지 않는다.
- body에 pointerEvents에 none
    - ios 12 버전 이하에서는 동작하지 않는다.
- body에 touchmove eventlistener 추가
    - 이벤트는 막지만 모달 내부의 스크롤도 막는다.
<br/>
<br/>
<br/>

        참조:

<br/>

## 방안(성공): fixed로 스크롤을 막고 scrollTo로 모달이 사라질 시 다시 스크롤링 되도록 설정

<br/>


모달을 클릭했을 때

body.style.position = 'fixed';
body.style.top = `-${scrollPosition}px`;

모달을 닫았을 때

body.style.removeProperty('position');
window.scrollTo(0, scrollPosition);

<br/>
<br/>
<br/>

        참조:
        https://im-developer.tistory.com/201?category=846746

<br/>


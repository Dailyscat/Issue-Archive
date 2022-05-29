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

# Issue: passive true의 의미 정리

## 상황:
ios 12, 13에서 스크롤 이벤트 제어가 안되는 상황

<br/>

## 알게된 부분 정리:

- passive true, false가 의미하는 것

<br/>

## 개념: passive true, false가 의미하는 것

<br/>
  
  passvie: false는 default이지만 보통 사파리나 ie 같은 경우 true가 default다.

  { passive:true } 의 진정한 의미는 이벤트를 받는 컴포지터 스레드에 해당 이벤트가 메인 스레드의 처리를 기다리지 않고 바로 Composite를 수행해도 된다는 힌트를 주는 것입니다.

  이 부분에서 스크롤 성능 개선과 관련한 아티클이 많지만 사파리의 경우 passive: false를 주어서 스크롤 이벤트를 제어, preventDefault를 적용해야 하는 경우가 생김.

<br/>
<br/>
<br/>

        참조:
        https://developer.mozilla.org/en-US/docs/Web/API/Navigator/userAgent
        https://developer.mozilla.org/en-US/docs/Web/API/EventTarget/addEventListener#improving_scrolling_performance_with_passive_listeners
        https://amati.io/eventlisteneroptions-passive-true/#:~:text=%7B%20passive%3Atrue%20%7D%20%EC%9D%98%20%EC%A7%84%EC%A0%95%ED%95%9C%20%EC%9D%98%EB%AF%B8%EB%8A%94%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%EB%A5%BC%20%EB%B0%9B%EB%8A%94,%ED%9E%8C%ED%8A%B8%EB%A5%BC%20%EC%A3%BC%EB%8A%94%20%EA%B2%83%EC%9E%85%EB%8B%88%EB%8B%A4.
        https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=crazymonlong&logNo=221580029479

<br/>
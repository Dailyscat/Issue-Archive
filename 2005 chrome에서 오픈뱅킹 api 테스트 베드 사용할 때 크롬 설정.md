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

# Issue: chrome에서 오픈뱅킹 api 테스트 베드 사용할 때 크롬 설정

## 상황:

오픈뱅킹 테스트 베드 사용 상황

<br/>

## 생각해낸 방안:

- 크롬 exe를 바탕화면으로 복사
- 복사한 크롬의 바로가기 생성
- 속성을 클릭후 바로가기 탭으로 이동
- 해당탭에 --disable-web-security --user-data-dir="C:\chrome" 적용

<br/>

하지만 테스트베드는 잘 작동하지 않는다..

애초에 명세와 response 자체가 다르게 전달된다.

다음엔 꼭 사용해보고싶다..

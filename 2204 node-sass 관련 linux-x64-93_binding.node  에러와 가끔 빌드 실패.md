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

# Issue: Cannot download "https://github.com/sass/node-sass/releases/download/v4.13.0/linux-x64-93_binding.node" 에러와 가끔 빌드 실패

## 상황:

정기적으로 배포진행되도록 해두었는데 가끔 실피해서 로그 보니 해당로그와 함께 발생

<br/>

## 알게된 부분 정리:

- network 문제일 확률이 높으니 아예 다운받아서 환경변수로 관리.

<br/>

## 개념: network 문제일 확률이 높으니 아예 다운받아서 환경변수로 관리.

<br/>
  네트워크가 접근하지 못하는 경우라고 인하우스로 관리하라는 코멘트인데 yarn pnp를 넣을까 해도 이 부분은 os 의존성에서 나오는 부분이라 해결이 될지 미지수였다.
<br/>
<br/>
<br/>

        참조:
        https://github.com/sass/node-sass/issues/2773

<br/>

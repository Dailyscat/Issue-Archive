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

# Issue: Linux에서 VScode 한글 깨짐 에러

## 상황:

가ㅌ, 가ㄴ, 가ㅇ 이런 식으로 vscode에서 에러 발생

<br/>

## 생각해낸 방안:

- utf-8 설정
- vscode 자체 font-family 수정

<br/>

## 방안: utf-8 설정 (실패)

<br/>

약간 다른 오류긴 했지만 보통 html에서도 utf-8 설정을 안했을 때
에러가 나는 경우가 있었어서 확인해봤지만
한글 자체가 나오긴 나오기 때문에 이 문제는 아니었다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: vscode 자체 font-family 수정 (성공)

<br/>

vsCode 자체 환경 문제라고 생각되어 찾아보니 font-family 문제를 겪었던 사람들이 정리해뒀던게 몇개 있었다.

즉

  <img src="https://mblogthumb-phinf.pstatic.net/MjAxNjEyMTlfMTY1/MDAxNDgyMTI2OTc2NTgx.62PZRU--mTmSCA4OL3YXIm8XOtsQS7zBq0Xy737T_oYg.rwGQ-iuGv1mNfN2fPGt5enqcctfe_SVlZGbaZI4-wc0g.PNG.doogle/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%2C_2016-12-19_14-25-41.png?type=w800">

이 방식으로 Droid Sans Fallback을 제거해줬더니 정상작동했다.

번외로 아예 한글 폰트를 추가하여 사용하는 [방법](http://blog.naver.com/PostView.nhn?blogId=dotnetquery&logNo=220888060914)도 있다.

<br/>
<br/>
<br/>

        참조:
        https://m.blog.naver.com/PostView.nhn?blogId=doogle&logNo=220889604173&proxyReferer=https%3A%2F%2Fwww.google.com%2F

<br/>

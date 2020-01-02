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

# Issue: kakao 인앱 브라우져에서 발생하는 에러를 URL Scheme를 활용하여 해결

## 상황:

android에서 네이버아이디로 로그인이 제대로 실행되지 않는 오류.
window.open에서 비롯되는 오류로 확인되어서 이를 어떻게 해결할까 하는 과정에서 url scheme을 알게 되었다.

<br/>

## 생각해낸 방안:

- 딥 링크 방식의 JS 솔루션
- android를 위한 Intent 방식

<br/>

## 방안: 딥 링크 방식의 JS 솔루션 (실패)

<br/>

    window.location.replace("yourapp://path/"); setTimeout(function () {

    window.location.replace("https://itunes.apple.com/app/id12345678"); }, 2000);

    이전부터 계속 차용되어왔던 방식을 사용하려고 했는데 이는 카카오톡 오픈채팅방에서 클릭을 통한 연결로 이루어져 있기 때문에 오픈 채팅방에서 열린 웹으로 돌아갔을 때 2초마다 계속 페이지가 열리는 현상이 발생했다.

    인앱 브라우져에서 발생하는 이슈가 생각보다 많고 디버깅이 힘들었기 때문에 ios와 android 둘 다 적용하려고 했지만 일단 시급한 부분은 android만 필요했기 때문에 intent 방식을 차용하기로 결정했다.

<br/>
<br/>
<br/>

        참조:
        https://www.adjust.com/blog/dive-into-deeplinking/

<br/>

## 방안: android를 위한 Intent 방식 (성공)

<br/>

    <script> location.href='intent://www.test.com#Intent;scheme=http;package=com.android.chrome;end' </script>

위와 같은 방식으로 처리를 할 수 있었다. ios의 경우 ftp를 통한 접근으로 safari가 강제적으로 열리게 하는 방법이 있으나 이는 보안적으로 좋지 않은 방법이라고 생각되어서 일단 android만 적용을 해두었다.

<br/>
<br/>
<br/>

        참조:
        http://cheonbrave.blogspot.com/2018/01/android-chrome-url-scheme.html
        https://www.burndogfather.com/201
        https://devtalk.kakao.com/t/topic/27890/3

<br/>

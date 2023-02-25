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

# Issue: nginx 환경에서 next.js 서버 구동시 소켓 관련 에러

## 상황: WebSocket connection to 'ws://localhost/\_next/webpack-hmr' failed;

<br/>

## 알게된 부분 정리:

- nginx에 socket 관련 헤더 추가 필요.

<br/>

## 개념: nginx에 socket 관련 헤더 추가 필요.

<br/>
            # https websocket
            proxy_set_header       Upgrade $http_upgrade;
            proxy_set_header       Connection "upgrade";
            proxy_set_header Host $host;
<br/>
<br/>
<br/>

        참조:
        https://velog.io/@tkddls8848/%EB%AC%B8%EC%A0%9C%EB%8A%94-%EC%96%B8%EC%A0%A0%EA%B0%80-%ED%95%B4%EA%B2%B0%EB%90%9C%EB%8B%A4

<br/>

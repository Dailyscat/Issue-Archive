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

# Issue: CRA에서 IE 폴리필을 적용해도 script 5022 에러 발생

## 상황:

CRA가 IE를 지원하지 않기 때문에 보통 polyfill을 적용하는데 이를 적용해도 되지 않는 상황

<br/>

## 생각해낸 방안:

- package.json의 browserlist의 개발환경에 ie11 추가
- webSocket 설정의 slashes 속성값 변경

<br/>

## 방안: package.json의 browserlist의 개발환경에 ie11 추가 (실패)

<br/>
  개발환경과 프로덕션 환경의 browserlist를 같이 맞춰줬지만 실패..

<br/>
<br/>
<br/>

        참조:
        https://medium.com/@matwankarmalay/create-react-app-ie11-script1002-syntax-error-how-to-get-rid-of-it-d70000c53ddf

<br/>

## 방안: webSocket 설정의 slashes 속성값 변경 (성공)

<br/>
  5022 에러는 webSocket 관련 에러였다.

이를 확인하고 찾아보다가 [이슈해결](https://github.com/facebook/create-react-app/issues/8084#issuecomment-562930472)한 사람의 얘기를 확인했다.

결국 개발 환경에서 slash가 잘못된 명령을 실행하고 있기 때문이다.

이는

    ./node_modules/react-dev-utils/webpackHotDevClient.js:60 add slashes: true

    // Connect to WebpackDevServer via a socket.
    var connection = new WebSocket(
      url.format({
        protocol: window.location.protocol === 'https:' ? 'wss' : 'ws',
        hostname: window.location.hostname,
        port: window.location.port,
        // Hardcoded in WebpackDevServer
        pathname: '/sockjs-node',
        slashes: true,
      })
    );

로 해결이 가능하다. 멋진사람..
<br/>
<br/>
<br/>

        참조:

<br/>

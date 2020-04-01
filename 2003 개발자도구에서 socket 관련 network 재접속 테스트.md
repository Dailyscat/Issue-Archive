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

# Issue: 개발자 도구에서 network가 offline이 되어도 소켓은 끊기지 않음

## 상황: Network 재접속시에 발생하는 오류 해결 중 소켓 부분은 제대로 확인되지 않던 문제

<br/>

## 생각해낸 과정:

- 개발자도구에서 offline
- wifi 끄면서 확인...
  <br/>

## 과정: 개발자도구에서 offline 설정해두고 redux action 로그 확인

<br/>

개발자도구에서 network를 offline으로 설정해두고 확인해도 소켓 관련 redux action이 계속 발생해서 계속 이상하게 생각하면서 로그만 이곳 저곳 찍어보는 중이었는데 혹시 몰라서 NETWORK의 소켓 탭을 확인해보니 메세지가 왔다갔다 하는 중이었다..

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/44533111/chrome-59-websocket-frames-no-longer-visible-in-devtools

<br/>

## 방안: wifi 끄면서 확인... (성공)

<br/>

소켓은 offline 속성에 대해 제대로 물리질 않는다. 하여 진정한 재접속 관련 부분을 위해서는 wifi를 끄고 확인해야한다ㅠㅠ
<br/>
<br/>
<br/>

        참조:

<br/>
```

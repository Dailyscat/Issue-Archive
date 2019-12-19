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

# Issue: ngrok 활용

## 상황:

third party에서 요청이 들어오는 부분을 처리하는데 domain에서만 테스트가 가능한 상황

<br/>

## 생각해낸 방안:

- ngrok 활용

<br/>

## 방안: ngrok 활용 (성공)

<br/>

ngrok은 방화벽을 넘어 외부에서 로컬에 접속 가능하게 하는 터널 프로그램이다.

즉 로컬환경에서도 http, https url과 연결하여 여러 테스트를 진행, 배포 없이도 접속할 수 있다.

요청에 대한 로그도 보여주고 사용법도 굉장히 쉽다.

> ./ngrok http 3000
> 로컬의 3000포트와 가상의 url을 연결시켜준다.

<br/>
<br/>
<br/>

        참조:
        https://blog.outsider.ne.kr/1159

<br/>

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

# Issue: Meteor Server 단에서 rest api 활용과 요청 콜백에서 db 접근할 때 생기는 이슈

## 상황:

rest로 요청을 받아야 하는 일이 생겨서 meteor server 단에서 url로 요청을 받고 callback을 활용하여 db 조작을 해야 하는 상황

<br/>

## 생각해낸 방안:

- Meteor WepApp api 사용하기
- Meteor.bindEnvironment를 콜백에 적용하여 활용하기

<br/>

## 방안: Meteor WepApp api 사용하기 (rest api 요청 받기 자체는 성공)

<br/>

Meteor에서 제공하는 WepApp 패키지를 활용하여 server에서 지정 url로 요청을 받고 이를 활용할 수 있었다.

> Meteor.absoluteUrl()을 활용하면 현재 url을 받을 수 있다.
> Meteor.absoluteUrl()/routing 이름 작성을 활용하여 받기도 가능

하지만 콜백 내부의 db를 조작하는 부분에서

> Error: Meteor code must always run within a Fiber. Try wrapping callbacks that you pass to non-Meteor libraries with Meteor.bindEnvironment.

와 같은 에러가 났다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: Meteor.bindEnvironment를 콜백에 적용하여 활용하기 (성공)

<br/>

이 에러는 비동기 함수를 핸들링하는 경우, 즉 콜백 함수를 사용할 때 콜백 함수는 Meteor의 Environment context를 잃기때문에 Meteor에서 사용할 수 있는 mongo shell에 연결할 수 없기 때문에 발생하는 에러다.

WebApp api와 같이 활용하려고 할때는

    WebApp.connectHandlers.use(
      "/routingName",
      Meteor.bindEnvironment((req, res, next) => {})
    )

위와 같은 방식으로 사용하면 에러해결이 가능하다.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/49355421/how-to-insert-in-collection-within-a-fiber
        https://guide.meteor.com/using-npm-packages.html#bind-environment
        https://stackoverflow.com/questions/28996727/wrapping-mongodb-calls-within-a-promise

<br/>

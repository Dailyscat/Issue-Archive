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

# Issue: Meteor에서 BindEnviroment, Bitly api를 사용하여 url 축소

## 상황:

클래스 등록, 수락에 대한 알림이 문자로 가는데 이 때 url이 길게 가서 쓸데 없는 요금이 나오는 경우가 있어서 Bitly api를 사용하여 축소

<br/>

## 생각해낸 방안:

- Bitly Api 사용
- Meteor에서 bindEnvironment 사용하여 api의 콜백 wrapping하여 사용

<br/>

## 방안: Bitly Api 사용 (실패)

<br/>

Bitly의 shortenLink api를 사용하는데 error가 났는데
알고보니 Meteor는 동기적으로 코드를 보도록 시스템화 되어있고, 비동기적인 부분을 해소해주는 모듈로 fiber라는 모듈을 사용한다. 즉 외부 api를 사용하는 과정에서 meteor의 server context에 초기화를 해줘야 했다.
<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: Meteor에서 bindEnvironment 사용하여 api의 콜백 wrapping하여 사용 (성공)

<br/>

이때 사용했던 api는 bindEnvironment인데, 외부 api의 비동기를 관리하기 위하여 초기화를 해주는 역할을 해준다.

    Bitly.shortenLink(
        requestedProgramUrl,
        Meteor.bindEnvironment((err, results) => {
          const url = JSON.parse(results).data.url;
        })
    );

위와 같은 방식으로 콜백을 감싸줘서 사용해야했다.

<br/>
<br/>
<br/>

        참조:
        https://guide.meteor.com/using-npm-packages.html#async-callbacks

<br/>

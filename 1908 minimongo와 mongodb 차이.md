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

# Issue: minimongo와 mongodb 차이

## 상황:

meteor 자체에서 쓰는 minimongo의 objectId와 mongodb에서 쓰는 objectId의 차이를 확인

<br/>

## 방안:

관련 답변 번역 후 정리

<br/>

`Meteor.Collection.ObjectID() is the same as MongoDB's ObjectID`
둘 다 같은 방식이기 때문에 Meteor.collection.objectID로 meteor api에 의존하여 사용이 가능하다.

minimongo :
클라이언트의 캐싱되어 있는 일종의 몽고디비이다. 관련 데이터는 mongodb 서버에서 브라우저가 로딩될 때 가져오게 되고, 서버에 어떤 변화를 던질 때 minimongo 내부도 변하게 된다.

server Mongodb :
서버 몽고디비.

meteor는 이 두가지의 db를 기능적으로 래핑하여 Meteor.collection이라는 api를 클라이언트와 서버 자체에서 사용할 수 있도록 만들었다.

위와 같은 이유에서 클라이언트에서 `new Meteor.Collection.ObjectID()`의 방식으로 나오는 객체를 사용하거나, id를 사용하는 것은 서버에 어떤 영향을 미치지 않고 오히려 쉽게 사용할 수 있게 만들어졌다.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/15318184/meteor-collection-objectid-vs-mongodb-objectid

<br/>

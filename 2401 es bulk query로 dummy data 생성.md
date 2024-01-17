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

# Issue: es bulk query로 dummy data 생성

## 상황: dummy data를 사용해서 생성된 데이터 수에 따라 달라지는 로직 테스트가 필요했음.

<br/>

## 알게된 부분 정리:

- _bulk api를 통해 dummy data 추가.

<br/>

## 개념:

<br/>
    https://www.javainuse.com/ndjson

    해당 사이트에서 json을 ndjson으로 변경해서 쿼리 작성

  ```
  POST test_doc/_bulk
{"index":{"_id":0}}
{"age":40,"gender":"woman","memberNo":557992,"count":1,"division":"TEST"}
{"index":{"_id":1}}
{"age":40,"gender":"woman","memberNo":465882,"count":2,"division":"TEST"}
{"index":{"_id":2}}
{"age":40,"gender":"woman","memberNo":301227,"count":3,"division":"TEST"}
{"index":{"_id":3}}
{"age":40,"gender":"woman","memberNo":132866,"count":4,"division":"TEST"}
{"index":{"_id":4}}
{"age":40,"gender":"woman","memberNo":345890,"count":5,"division":"TEST"}
{"index":{"_id":5}}
{"age":40,"gender":"woman","memberNo":582333,"count":6,"division":"TEST"}
{"index":{"_id":6}}
{"age":40,"gender":"woman","memberNo":870069,"count":7,"division":"TEST"}
{"index":{"_id":7}}
{"age":40,"gender":"woman","memberNo":306438,"count":8,"division":"TEST"}
{"index":{"_id":8}}
{"age":40,"gender":"woman","memberNo":422649,"count":9,"division":"TEST"}
{"index":{"_id":9}}
{"age":40,"gender":"woman","memberNo":948103,"count":10,"division":"TEST"}
{"index":{"_id":10}}
{"age":40,"gender":"woman","memberNo":132375,"count":11,"division":"TEST"}
{"index":{"_id":11}}
{"age":40,"gender":"woman","memberNo":982450,"count":12,"division":"TEST"}
{"index":{"_id":12}}
{"age":40,"gender":"woman","memberNo":668192,"count":13,"division":"TEST"}
{"index":{"_id":13}}
{"age":40,"gender":"woman","memberNo":147448,"count":14,"division":"TEST"}
{"index":{"_id":14}}
{"age":40,"gender":"woman","memberNo":313099,"count":15,"division":"TEST"}
{"index":{"_id":15}}
{"age":40,"gender":"woman","memberNo":801516,"count":16,"division":"TEST"}
{"index":{"_id":16}}
{"age":40,"gender":"woman","memberNo":158089,"count":17,"division":"TEST"}
{"index":{"_id":17}}
{"age":40,"gender":"woman","memberNo":653857,"count":18,"division":"TEST"}
{"index":{"_id":18}}
{"age":40,"gender":"woman","memberNo":876194,"count":19,"division":"TEST"}
{"index":{"_id":19}}
{"age":40,"gender":"woman","memberNo":164724,"count":20,"division":"TEST"}
{"index":{"_id":20}}
{"age":40,"gender":"woman","memberNo":709695,"count":21,"division":"TEST"}
{"index":{"_id":21}}
{"age":40,"gender":"woman","memberNo":397251,"count":22,"division":"TEST"}
{"index":{"_id":22}}
{"age":40,"gender":"woman","memberNo":884653,"count":23,"division":"TEST"}
{"index":{"_id":23}}
{"age":40,"gender":"woman","memberNo":931800,"count":24,"division":"TEST"}
{"index":{"_id":24}}
{"age":40,"gender":"woman","memberNo":345216,"count":25,"division":"TEST"}
{"index":{"_id":25}}
{"age":40,"gender":"woman","memberNo":773354,"count":26,"division":"TEST"}
{"index":{"_id":26}}
{"age":40,"gender":"woman","memberNo":347172,"count":27,"division":"TEST"}
{"index":{"_id":27}}
{"age":40,"gender":"woman","memberNo":908276,"count":28,"division":"TEST"}
{"index":{"_id":28}}
{"age":40,"gender":"woman","memberNo":537266,"count":29,"division":"TEST"}
{"index":{"_id":29}}
{"age":40,"gender":"woman","memberNo":880328,"count":30,"division":"TEST"}
{"index":{"_id":30}}
{"age":40,"gender":"woman","memberNo":294764,"count":31,"division":"TEST"}
{"index":{"_id":31}}
{"age":40,"gender":"woman","memberNo":537990,"count":32,"division":"TEST"}
{"index":{"_id":32}}
{"age":40,"gender":"woman","memberNo":203648,"count":33,"division":"TEST"}
{"index":{"_id":33}}
{"age":40,"gender":"woman","memberNo":634430,"count":34,"division":"TEST"}
{"index":{"_id":34}}
{"age":40,"gender":"woman","memberNo":562678,"count":35,"division":"TEST"}
{"index":{"_id":35}}
{"age":40,"gender":"woman","memberNo":78257,"count":36,"division":"TEST"}
{"index":{"_id":36}}
{"age":40,"gender":"woman","memberNo":876463,"count":37,"division":"TEST"}
  
  ```
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

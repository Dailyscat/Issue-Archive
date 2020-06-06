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

# Issue: csv 형식의 문자열을 array 형태로 변환 참조 사이트

## 상황: csv 형식의 문자열, 즉 개행처리된 문자열들을 Array로 바꾸는 상황

<br/>

## 알게된 부분 정리:

- 변환 사이트 정리

<br/>

## 개념: 변환 사이트 정리

<br/>

[변환 사이트1](https://www.seabreezecomputers.com/excel2array/)
[변환 사이트2](https://csvjson.com/)

- 너무 큰 자료 10^6 정도의 자료는 웹에서 돌리지 못하기 때문에 local에서 스크립트를 짜서 처리하는게 좋다.

`아래는 로컬에서 큰 데이터를 json으로 변환할 때 작성해본 코드이다.`

```
const fs = require("fs");
const parse = require("csv-parse");
const { tradesArray } = require("./obj");
const parser = parse({
  delimiter: ","
});
let data = [];
parser
  .on("readable", function() {
    let record;
    while ((record = parser.read())) {
      const [timestamp, price, size] = record;
      data.push({
        timestamp,
        price,
        size
      });
    }
  })
  .on("error", function(err) {
    console.error(err.message);
  })
  .on("end", function() {
    const result = transformTradeData(data, 3000);
    console.log(result);
    console.log("completed");
  });
fs.createReadStream("./.korbitKRW.csv").pipe(parser);

```

  <br/>
  <br/>
  <br/>

          참조:

<br/>

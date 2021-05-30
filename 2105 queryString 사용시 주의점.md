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

# Issue: queryString 사용시 주의해야할 부분

## 상황: queryParam을 조작하는 과정에서 queryString 라이브러리를 사용했는데 몇가지 놓칠 수 있는 부분에 대해 정리



## 생각해낸 방안:

- 중복제거

## 방안: 중복된 queryParam이 있을 때
`queryString.parse(location.search)`

위와 같이 함수를 호출 했을 때 search에 중복된 param이 있다면 return되는 객체에

`중복값: [1,2,3,4]` 과 같이 배열이 value로 적용된다.

Array.isArray 같은 함수로 배열을 체크하는 부분이 필요.

```
'none': Parse arrays with elements using duplicate keys:
const queryString = require('query-string');

queryString.parse('foo=1&foo=2&foo=3');
//=> {foo: ['1', '2', '3']}
```

<br/>

<br/>
<br/>
<br/>

        참조: https://www.npmjs.com/package/query-string

<br/>


## 방안: 특정 queryParam을 없애야할 때
`queryString.parse(location.search)`

위에서 리턴된 객체에서 특정 값을 빈값으로 가야할 때

경우에 따라 null을 넣어줄 수 도 있지만

`delete returnObj.aaa;`을 통해서 속성 값 자체를 없애는게 더 확실하다.

<br/>

<br/>
<br/>
<br/>

        참조:

<br/>

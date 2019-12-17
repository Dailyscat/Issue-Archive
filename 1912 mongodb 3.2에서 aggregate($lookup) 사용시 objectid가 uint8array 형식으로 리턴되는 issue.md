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

# Issue: mongodb 3.2에서 aggregate(\$lookup) 사용시 objectid가 uint8array 형식으로 리턴되는 issue

## 상황:

aggregate에서 \$lookup 사용 상황에서 objectid로 db에 존재하는 필드가 uint8array 형식으로 리턴되는 상황

[사례1](https://forums.meteor.com/t/mongodb-aggregation-return-uint8array-ids/37151/2)
[사례2](https://github.com/robfallows/tunguska-reactive-aggregate/issues/18)

<br/>

## 생각해낸 방안:

- mongodb의 toString 함수 사용
- uint8array decode, encode
- bytes 배열을 hex string으로 변환

<br/>

## 방안: mongodb의 toString 함수 사용 (실패)

<br/>

mongodb 4.0에서 지원하는 toString 함수를 project혹은 group에서 함께 사용하면 금방 끝났을텐데... product는 3.2이고... 다음주 내로 ..무조건.. 올린다..

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/16478552/convert-objectid-mongodb-to-string-in-javascript
        https://docs.mongodb.com/manual/reference/operator/aggregation/toString/

<br/>

## 방안: uint8array decode, encode (실패)

<br/>

utf-8로 incoded 된 bytes의 형태가 나와서 encoding을 하면 될 줄 알았는데 이상한 문자가 나왔다.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/14603205/how-to-convert-hex-string-into-a-bytes-array-and-a-bytes-array-in-the-hex-strin
        https://ourcodeworld.com/articles/read/164/how-to-convert-an-uint8array-to-string-in-javascript
        https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Uint8Array

<br/>

## 방안: uint8array decode, encodebytes 배열을 hex string으로 변환 (성공)

<br/>

    // Convert a byte array to a hex string

    function bytesToHex(bytes) {
        for (var hex = [], i = 0; i < bytes.length; i++) {
            var current = bytes[i] < 0 ? bytes[i] + 256 : bytes[i];
            hex.push((current >>> 4).toString(16));
            hex.push((current & 0xF).toString(16));
        }

        return hex.join("");
    }

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/14603205/how-to-convert-hex-string-into-a-bytes-array-and-a-bytes-array-in-the-hex-strin

<br/>

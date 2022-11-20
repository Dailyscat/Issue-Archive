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

# Issue: elastic restHighLevelClient 관련 이슈

## 상황:
es7 버전업 이후 며칠 후에 갑자기 npe 발생

<br/>

## 알게된 부분 정리:

- restHighLevelClient 사용시 httpcore, httpcore-nio 버전업

<br/>

## 개념:

<br/>

  ```
  java.lang.NoSuchMethodError:
org.apache.http.ConnectionClosedException: method <init>()V not found
  at org.apache.http.nio.protocol.HttpAsyncRequestExecutor.endOfInput(HttpAsyncRequestExecutor.java:356)
  at org.apache.http.impl.nio.DefaultNHttpClientConnection.consumeInput(DefaultNHttpClientConnection.java:261)
  at org.apache.http.impl.nio.client.InternalIODispatch.onInputReady(InternalIODispatch.java:81)
  ```

관련 에러 발행, 간헐적으로 발생한 이유는 bulk로 크기가 큰 요청을할 때만 발생

```
If your project is using both httpcore and httpcore-nio dependencies, ensure that both of their versions are either simultaneously <= 4.4.10 or > 4.4.10.
```

https://mvnrepository.com/artifact/org.elasticsearch.client/elasticsearch-rest-client/7.10.2
해당 사이트에서 es client가 필요로 하는 디펜던시를 확인할 수 있음.
위를 따라서 4.4.12로 변경

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/59714835/connectionclosedexception-while-using-elastic-resthighlevelclient-bulk-request

<br/>


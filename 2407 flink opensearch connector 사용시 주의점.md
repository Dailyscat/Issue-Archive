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

# Issue: flink opensearch connector 사용시 주의점

## 상황:
flink opensearch connector로 데이터 연결해서 읽고 쓰는걸 시도하려고 했으나 쓰는것만 가능.

<br/>

## 알게된 부분 정리:

- flink opensearch connector는 index에 쓰는것만 가능.

<br/>

## 개념:flink opensearch connector는 index에 쓰는것만 가능.


<br/>
  https://nightlies.apache.org/flink/flink-docs-master/docs/connectors/datastream/opensearch/
  아니 그럼 여기에 써있어야되는데 https://opensearch.org/blog/OpenSearch-Flink-Connector/ 여기에서 정보를 찾아서 어떻게 읽을 수 있지 하고 한참 찾았다.
  예제코드 따라하면 쉽게 할 수 있음.

  추가로 디펜던시 추가가 좀 명확하게 안 되어 있는데
  ```
    flinkShadowJar 'org.apache.flink:flink-connector-base:1.19.1'
    flinkShadowJar 'org.apache.flink:flink-connector-opensearch:1.2.0-1.19'

    implementation "org.opensearch:opensearch:2.5.0"
    implementation "org.opensearch.client:opensearch-rest-high-level-client:2.5.0"
  ```

  위처럼 os에 접근하는 클라이언트 코드, 커넥터 베이스 디펜던시가 둘 다 필요하다.

<br/>
<br/>
<br/>

        참조:
        https://opensearch.org/blog/OpenSearch-Flink-Connector/

<br/>

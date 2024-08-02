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

# Issue: flink의 dataStream, table api 차이

<br/>

## 개념: Flink의 DataStream API와 Table API의 차이

<br/>

Flink의 DataStream API와 Table API의 차이

1. 추상화 수준
   DataStream API: 더 낮은 수준의 프로그래밍 모델을 제공한다. 사용자가 데이터 스트림을 조작하는 연산자를 직접 정의해야 한다.
   Table API: SQL과 유사한 선언적 방식으로 데이터 처리를 정의할 수 있다. 데이터 처리 논리를 테이블 연산으로 정의할 수 있으며, 내부적으로 이를 최적화된 실행 계획으로 변환한다.
2. 사용 용도
   DataStream API: 실시간 스트리밍 데이터 처리에 적합하다. 이벤트 시간 처리, 상태 관리, 커스텀 연산 등 저수준의 제어가 필요할 때 사용된다.
   Table API: 배치 및 스트리밍 데이터 처리를 모두 지원한다. SQL과 유사한 문법을 사용하여 데이터를 쉽게 쿼리하고 변환할 수 있다.
3. 실행 계획
   DataStream API: 사용자가 정의한 연산자 그래프가 그대로 실행 계획이 된다. 추가적인 최적화 단계가 없다.
   Table API: 논리적 실행 계획을 최적화된 물리적 실행 계획으로 변환하는 과정이 포함된다. 다양한 최적화 전략이 적용될 수 있다.
4. 데이터 형식
   DataStream API: 데이터 스트림의 각 요소는 Java/Scala 객체로 표현된다. 사용자가 직접 데이터 타입을 정의하고 관리해야 한다.
   Table API: 테이블의 각 행은 테이블 스키마에 따라 정의된다. 스키마 기반의 데이터 접근이 가능하다.
5. 연산 지원
   DataStream API: 맵, 필터, 리듀스, 윈도우 등 기본적인 스트림 연산을 지원한다. 커스텀 연산자를 쉽게 정의할 수 있다.
   Table API: SQL 쿼리, 집계, 조인, 윈도우 함수 등 고급 연산을 지원한다. SQL 문법을 사용하여 복잡한 쿼리를 쉽게 작성할 수 있다.
6. 외부 시스템과의 통합
   DataStream API: 다양한 커넥터를 통해 Kafka, HDFS, JDBC 등 외부 시스템과 통합할 수 있다. 사용자 정의 소스와 싱크를 쉽게 구현할 수 있다.
   Table API: 카탈로그와 커넥터를 통해 외부 시스템과 쉽게 통합할 수 있다. SQL DDL 문법을 사용하여 테이블을 정의하고 관리할 수 있다.
7. 상태 관리
   DataStream API: 상태 관리를 위한 다양한 API를 제공한다. 상태를 직접 관리하고, 체크포인트를 설정할 수 있다.
   Table API: 상태 관리는 내부적으로 처리되며, 사용자가 직접 상태를 관리할 필요가 없다.
   요약
   DataStream API: 저수준의 제어가 필요한 실시간 스트리밍 데이터 처리에 적합하다. 사용자가 직접 연산자를 정의하고, 상태를 관리해야 한다.
   Table API: SQL과 유사한 선언적 방식으로 데이터 처리를 정의할 수 있으며, 배치 및 스트리밍 데이터 처리를 모두 지원한다. 고급 연산과 최적화된 실행 계획을 제공한다.

<br/>
<br/>
<br/>

        참조:
        https://nightlies.apache.org/flink/flink-docs-master/docs/dev/table/overview/
        https://velog.io/@zxshinxz/Flink%EB%A1%9C-%EC%8B%9C%EC%9E%91%ED%95%98%EB%8A%94-Stream-processing-3-%EB%8B%A4%EC%96%91%ED%95%9C-Transformation-Sink-Operator

<br/>

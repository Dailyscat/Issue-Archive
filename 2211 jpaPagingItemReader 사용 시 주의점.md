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

# Issue: jpaPagingItemReader 사용 시 주의점

## 상황:
총 데이터의 절반 정도만 읽고 배치가 끝나버림

<br/>

## 알게된 부분 정리:

- 페이징을 읽어올 때 이미 변경된 데이터 이후부터 읽기 때문에 누락 발생

<br/>

## 개념: 페이징을 읽어올 때 이미 변경된 데이터 이후부터 읽기 때문에 누락 발생

<br/>

이건 Spring Batch의 문제가 아닌 Paging 이라는 행위 자체의 문제로

즉, 웹 어플리케이션에서 구현할때도 같은 문제가 발생할 수는 있습니다.

Paging이란 offset과 limit을 이용하여 매 조회마다 다음 페이지를 읽어오는 방식을 얘기합니다.
이 방식이 지금 상황에선 문제가 될 수 있는데요.

예를 들어 첫번째 Paging 쿼리는 다음과 같습니다.

SELECT * FROM Pay 
WHERE successStatus = false 
offset 0 limit 10
이 쿼리가 테이블에 실행되면 다음과 같이 조회됩니다.

paging1

그리고 이 조회된 데이터를 Update 합니다.
조회한 데이터를 Update 한 것이 중요합니다.
이 Update로 인해서 이제 테이블에는 false인 데이터가 40개만 존재합니다.

이 상태에서 다음 Paging 쿼리를 실행합니다.
다음 Paging 쿼리는 아래와 같습니다.

SELECT * FROM Pay 
WHERE successStatus = false 
offset 11 limit 10
자! 이 쿼리가 Update되어 40개만 남은 테이블에 실행되면 어떻게 될까요?


40개 데이터에서 11번째 부터인 데이터를 가져옵니다.
앞서 실행된 쿼리에서 대상을 Update하여 제외되이 원래 의도대로라면 21번째인 데이터가 11번째 데이터로 조회가 됩니다.
그러다보니 원래 11번째 데이터부터 20번째까지 데이터가 조회되지 않습니다.

```
@Bean
    @StepScope
    public JpaPagingItemReader<Pay> payPagingReader() {

        JpaPagingItemReader<Pay> reader = new JpaPagingItemReader<Pay>() {
            @Override
            public int getPage() {
                return 0;
            }
        };

        reader.setQueryString("SELECT p FROM Pay p WHERE p.successStatus = false");
        reader.setPageSize(chunkSize);
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setName("payPagingReader");

        return reader;
    }
```

jpa에는 cursor가 지원되지 않아서 Update될때마다 대상 데이터 범위가 줄어드니, 일부러 Page 번호를 계속 변경시키지 않고 0번째로 고정시키도록 설정

<br/>
<br/>
<br/>

        참조:
        https://jojoldu.tistory.com/337
        https://jojoldu.tistory.com/339
        https://jojoldu.tistory.com/166

<br/>

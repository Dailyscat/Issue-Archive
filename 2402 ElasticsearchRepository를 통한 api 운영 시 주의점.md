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

# Issue: ElasticsearchRepository를 통한 api 운영 시 주의점

## 상황:

20만건 정도의 document를 운영하는데
특정 로직에서 findBy~~s를 통해 list를 가져온 뒤 size를 가져오는 부분이 있었다.
코드 리뷰에서 한번 걸려졌어야 했는 부분인데 체크하지 못해서 삼만건 이상 넘어갈 때 time out 발생시작.

## 생각해낸 방안:

- countBy~~~s 를 통해 es에서 집계처리 후 count만 넘기도록 처리

## 방안: countBy~~~s 를 통해 es에서 집계처리 후 count만 넘기도록 처리

```
public interface EntityRepository extends ElasticsearchRepository<Entity, String> {
    Long countBy필드(필드클래스 필드);
}

```

count prefix 메소드를 통해 jpa에서 동적 쿼리를 작성하게 하고 Es에서 집계하도록 부하를 없앤뒤 count만 가져온다.
<br/>

<br/>
<br/>
<br/>

        참조:

<br/>

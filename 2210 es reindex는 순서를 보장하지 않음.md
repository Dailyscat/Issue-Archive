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

# Issue: 2210 es reindex는 순서보장하지 않음

## 상황:
reindex하는 과정에서 순서를 보장하지 않아서 이슈 발생

<br/>

<br/>

## 개념:

<br/>

  ```
  
    Deprecated in 7.6.
    Sort in reindex is deprecated. Sorting in reindex was never guaranteed to index documents in order and prevents further development of reindex such as resilience and performance improvements. If used in combination with max_docs, consider using a query filter instead.
  ```

<br/>
<br/>
<br/>

        참조:
        https://www.elastic.co/guide/en/elasticsearch/reference/master/docs-reindex.html

<br/>

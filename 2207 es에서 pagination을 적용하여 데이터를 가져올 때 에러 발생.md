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

# Issue: es에서 pagination을 적용하여 데이터를 가져올 때 에러 발생

## 상황:
es에 pagination 쿼리를 함께 넣어서 보낼 때 이슈 발생

<br/>

## 알게된 부분 정리:

- es setting 설정

<br/>

## 개념: es setting 설정

<br/>

  ```
  Result window is too large, from + size must be less than or equal to: [10000] but was [12000]. See the scroll api for a more efficient way to request large data sets. This limit can be set by changing the [index.max_result_window] index level setting
  ```

  es에서 10,000건을 초과하는 데이터가 저장되어 있고 이를 페이지네이션을 적용해서 데이터를 가져올 때 pageNumber + size의 요청이 10,000건을 넘는 지점일 때 발생하는 에러

  es index의 설정 중 index.max_result_window의 기본값이 10,000건으로 제한되어 있어서 발생하는 문제이며 해결로는
  키바나에서 아래 요청
    PUT template_data/_settings
    {
    “index.max_result_window”: 원하는 데이터 수
    }
  과 같이 적용 혹은 
  
  ```
  "settings": {
    "index": {
      ...
      "max_result_window": 원하는 데이터 수
    },
  ```

  으로 처리

<br/>
<br/>
<br/>

        참조:
        아래는 그냥 검색해봄.
        https://stackoverflow.com/questions/66414349/how-does-elasticsearch-7-track-total-hits-improve-query-speed
        https://stackoverflow.com/questions/66414349/how-does-elasticsearch-7-track-total-hits-improve-query-speed
        https://www.elastic.co/guide/en/elasticsearch/reference/7.17/search-your-data.html#track-total-hits

<br/>


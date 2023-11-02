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

# Issue: es NativeSearchQueryBuilder에 pageable 설정시에 결과가 제대로 안나오는 이슈

## 상황: NativeSearchQueryBuilder를 써서 제대로 쿼리를 만들고 total 값에 1이 포함되서 오는데도 result에는 제대로 담겨져 오지 않는 상황.

## 생각해낸 방안:

-  페이징이 적용되었고, 요청한 페이지에 해당하는 결과가 없는 경우

## 방안: 페이징이 적용되었고, 요청한 페이지에 해당하는 결과가 없는 경우

<br/>

    Pageable pageable = PageRequest.of(page, size);
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    ...
    queryBuilder.withPageable(pageable);

    SearchHits<ss> searchHits = searchOperations.search(queryBuilder.build(), ss.class);

    이런 코드였는데 ...에 적용된 검색 조건에 부합하는 데이터는 있지만 앞서 적용한 페이징으로 구현된 페이지에 해당하는 결과가 없어서 searchHits에 total 값은 존재하지만 데이터는 존재하지 않았다.

    페이징 관련 코드를 빼니까 제대로 데이터를 받아오게 됐다.

<br/>
<br/>
<br/>

        참조:

<br/>

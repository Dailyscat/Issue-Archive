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

# Issue: es devtool에서 특정 값 업데이트, 특정 값 삭제

## 상황:
특정 값을 찾아서 업데이트 혹은 삭제 필요
<br/>

## 알게된 부분 정리:

- _update_by_query
- _delete_by_query

<br/>

## 개념: POST data/_update_by_query

<br/>
  POST data/_update_by_query

  {
  "query": {
    "term": {
      "document의 필드 값": {
        "value": 1234123
      }
    }
  },
  "script": {
    "source": "ctx._source.document의 수정을 위한 필드 값 = params.start_date",
    "params": {
      "start_date": 1679448611000
    }
  }
}
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: POST /data/_delete_by_query

<br/>
  
  POST /data/_delete_by_query
  {
  "query": {
    "match": {
      "document의 필드 값": "document의 찾고 있는 필드 값"
    }
  }
}
<br/>
<br/>
<br/>

        참조:

<br/>

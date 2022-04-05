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

# Issue: kibana에서 document 부분 수정

## 상황: 
test data 생성을 위해 부분 수정 필요

<br/>

## 알게된 부분 정리:

- 검색 후 부분 수정 

<br/>

## 개념: 검색 후 부분 수정 

<br/>

```

  GET template_data/_search
{
  "query": {
    "term": {
      "no": {
        "value": 1004845
      }
    }
  }
}

POST /바꿀 대상의 _index_/바꿀 대상의 _type/바꿀 대상의 _id/_update
{
  "doc": {
    "필요로 하는 field": 바꾸고자 하는 value
  }
}

```
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/19563215/update-only-specific-field-value-in-elasticsearch

<br/>
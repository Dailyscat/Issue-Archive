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

# Issue: es devtool에서 검색 시 사용되는 필터 템플릿

## 상황: bool 쿼리에 다수 must filter 추가.

<br/>

## 알게된 부분 정리:

- bool 쿼리에 다수 filter 추가
- bool 쿼리에 must + 다수 filter

<br/>

## 개념: bool 쿼리에 다수 filter 추가

<br/>
  GET data/_search
{
  "query": {
  "bool" : {
    "must" : [
      {
        "term" : {
          "찾고자 하는 필드" : {
            "value" : "찾고자 하는 값.",
            "boost" : 1.0
          }
        }
      },
      {
        "term" : {
          "enable" : {
            "value" : true,
            "boost" : 1.0
          }
        }
      },
      {
        "term" : {
          "tg" : {
            "value" : false,
            "boost" : 1.0
          }
        }
      },
      {
        "range" : {
          "startDate" : {
            "from" : 1679250693422,
            "to" : 1680546693444,
            "include_lower" : true,
            "include_upper" : true,
            "boost" : 1.0
          }
        }
      }
    ],
    "adjust_pure_negative" : true,
    "boost" : 1.0
  }
}
}
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: bool 쿼리에 must + 다수 filter

<br/>
  {
  "bool" : {
    "must" : [
      {
        "range" : {
          "startDate" : {
            "from" : "2023-03-21T06:53:00.293Z",
            "to" : "2023-04-05T06:53:00.293Z",
            "include_lower" : true,
            "include_upper" : true,
            "boost" : 1.0
          }
        }
      }
    ],
    "filter" : [
      {
        "term" : {
          "찾고자 하는 필드" : {
            "value" : "찾고자 하는 값.",
            "boost" : 1.0
          }
        }
      },
      {
        "term" : {
          "st" : {
            "value" : false,
            "boost" : 1.0
          }
        }
      },
      {
        "range" : {
          "startDate" : {
            "from" : null,
            "to" : 1680677580293,
            "include_lower" : true,
            "include_upper" : true,
            "boost" : 1.0
          }
        }
      },
      {
        "range" : {
          "endDate" : {
            "from" : 1680677580293,
            "to" : null,
            "include_lower" : true,
            "include_upper" : true,
            "boost" : 1.0
          }
        }
      },
      {
        "term" : {
          "enable" : {
            "value" : true,
            "boost" : 1.0
          }
        }
      }
    ],
    "adjust_pure_negative" : true,
    "boost" : 1.0
  }
}
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: bool 쿼리에 must + 다수 match

<br/>

GET data/_search
{
  "query": {
    "bool": {
      "must": [
        { "match": { "dateMark": "20230413150000" } },
        { "match": { "rank": 2 } }
      ]
    }
  }
}
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: 필터를 통한 삭제

```
POST /test/_delete_by_query
{
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "division": "원하는값"
          }
        },
        {
          "range": {
            "count": {
              "gt": 4800
            }
          }
        }
      ]
    }
  }
}

```

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

# Issue: es 특정 필드 업데이트(bulk도 가능)

## 상황:
특정 필드들 다건을 특정 값으로 변경해야하는경우

<br/>

## 알게된 부분 정리:

- _update_by_query, _update

<br/>

## 개념: _update_by_query, _update

<br/>
  
  ```
    GET item/_update_by_query
        {
        "query": {
            "bool": { 
            "must": [
                { "match": { "dateMark":   "2022-07-05"        }},
                { "match": { "itemType": "QUERY_V3" }}
            ]
            }
        },
        "script": {
            "inline": "ctx._source.date = '20220714201000'",
            "lang": "painless"
        }
        }
  ```

  ```
  POST /template_data/templatedata/1004843/_update
        {
        "doc": {
            "date": 1651449600000,
            "date": 1656374400000
        }
        }
  ```

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/10189019/how-do-i-update-multiple-items-in-elasticsearch
        https://stackoverflow.com/questions/44725905/how-to-update-a-string-field-with-elasticsearch-update-by-query

<br/>

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

# Issue: 동일한 필드를 가지고 있는 document 찾는 kibana query

## 상황:
동일한 필드를 가진 document가 얼마나 있는지 확인하는 쿼리

<br/>


## 개념:

<br/>

```
POST /ai_assistant/_search
{
  "size": 0,
  "aggs": {
    "단순 쿼리 제목": {
      "terms": {
        "field": "필드", 
        "size": 10000
      }
    }
  }
}
```

<br/>
<br/>
<br/>

        참조:

<br/>

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

# Issue: es remote reindex 준비

## 상황:
 es remote reindex 준비

<br/>

## 알게된 부분 정리:

-  es remote reindex 과정

<br/>

## 개념: es remote reindex 과정

<br/>
  0. 인덱스 세팅
  ```
  PUT template_data {

    "settings": {
        "max_result_window" : 1000000 // note this
    },
    "mappings": {
        "properties": {
            "first_name": {
                "type": "text"
            },
            "last_name": {
                "type": "text"
            },
            "country": {
                "type": "text"
            },
            "state": {
                "type": "text"
            },
            "city": {
                "type": "text"
            }
        }
    }
}

  ```
  1. 타겟 es의 remote.whitelist에 ip 추가.
  ex. reindex.remote.whitelist: "example.com:10200, wow.com:12312"
  2. 
  ```
  POST _reindex
{
  "source": {
    "remote": {
      "host": "http://otherhost:9200",
      "username": "user",
      "password": "pass"
    },
    "index": "my-index-000001",
    "query": {
      "match": {
        "test": "data"
      }
    }
  },
  "dest": {
    "index": "my-new-index-000001"
  }
}
  ```

특정 document 삭제
  ```
  POST /template_data/_delete_by_query
    {
    "query": {
        "term": {
        "no": {
            "value": 100
        }
        }
    }
    }
  ```

wait_for_completion 옵션을 통해 reindex를 async하게 적용
```
POST _reindex?wait_for_completion=false
{
  "source": {
    "remote": {
      "host": "dns",
      "username": "ㅂㅈㄷㅂ",
      "password": "wow"
    },
    "index": "ㅋㅋㅋ"
  },
  "dest": {
    "index": "ㅋㅋㅋ"
  }
}
```

위의 코드를 통해 리턴된 id를 아래 코드를 통해 완료되었는지 확인

```
GET _tasks/mIDQOYWmSqCvGVmEh6-5Hg:1494
```

<br/>
<br/>
<br/>

        참조:
        https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete-by-query.html
        https://kyungseop.tistory.com/7
        https://somjang.tistory.com/entry/Elasticsearch-Not-whitelisted-in-reindexremotewhitelist-%ED%95%B4%EA%B2%B0-%EB%B0%A9%EB%B2%95

<br/>

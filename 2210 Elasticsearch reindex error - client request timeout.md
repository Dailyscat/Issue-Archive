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

# Issue: Elasticsearch reindex error - client request timeout

## 상황:
reindex시에 발생하는 에러

<br/>

## 알게된 부분 정리:

- async로 처리

<br/>

## 개념: async로 처리

<br/>
  
  ```
    504 simply means that the request is still running but the HTTP connection from Kibana to ES timed out.

    You can still see the request going on by using the task management API like this:

    GET _tasks?actions=*reindex&detailed
    If you want to run the task asynchronously you can also do it with the following command:

    POST _reindex?wait_for_completion=false
    This will return a task id whose progress can then be checked with:

    GET _tasks/<task-id>  
  
  ```

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/51852960/elasticsearch-reindex-error-client-request-timeout

<br/>
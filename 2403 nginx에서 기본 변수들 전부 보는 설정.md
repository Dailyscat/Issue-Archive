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

# Issue: nginx에서 기본 변수들 전부 보는 설정

## 상황:

<br/>

## 알게된 부분 정리:

- +

<br/>

## 개념:

<br/>
  
  ```
  http {
    log_format full_log '$remote_addr - $remote_user [$time_local] '
                        '"$request" $status $body_bytes_sent '
                        '"$http_referer" "$http_user_agent" '
                        '$request_length $request_time $pipe $upstream_connect_time '
                        '$upstream_header_time $upstream_response_time $upstream_status '
                        '$http_cookie $http_host $server_protocol $server_addr '
                        '$server_port $request_uri';

    # ... 나머지 구성 ...
    }
  ```

<br/>
<br/>
<br/>

        참조:

<br/>

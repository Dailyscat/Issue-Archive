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

# Issue: 실시간으로 쌓이는 nginx log에서 특정 시간대, 특정 문자열 가져올 때 주의

## 상황:

장애 상황에서 nginx 로그 확인하여 피해 상황 확인
<br/>

## 알게된 부분 정리:

- 시간대, 로그 내의 특정 문자열 파싱하여 필터링

<br/>

## 개념: 시간대, 로그 내의 특정 문자열 파싱하여 필터링

<br/>
```
time:28/Feb/2025:00:01:18 +0900	user:-	remote_addr:4124.12412.412.412	request_method:GET	request_referer:-	request_path:/	request_screen:-	response_status:200	response_time:0.049	redirect_to:-	cookies:
```

위와 같은 형식의 로그들이 쌓이고 있고 시간대별로 user id가 있는 요청들만 파싱해야했다.

```
cat access.log \
  | sed -n '/28\/Feb\/2025:18:23/,/28\/Feb\/2025:19:31/p' \
  | awk -F'user:' 'NF > 1 {
      # user: 뒤쪽 값을 공백 기준으로 나눈 후, 첫 번째 토큰이 '-' 가 아닌 경우만 출력
      split($2, arr, "[ \t]");
      if (arr[1] != "-") {
        print $0
      }
    }'

여기서 유니크한 user만 파싱

sed -n '/28\/Feb\/2025:18:23/,/28\/Feb\/2025:19:31/p' \
  | awk -F'mid:' 'NF > 1 {
      split($2, arr, "[ \t]");
      if (arr[1] != "-") {
        print arr[1];  # mid 값만 출력
      }
    }' \
  | sort -u \
  | wc -l

```

이때 실시간으로 쌓이는 로그 파일을 대상으로는 출력이 계속 바뀌어서 나옴;;
이걸 몰랐어서 덤프떠서 실제 파일을 대상으로 하니까 제대로 출력됨.
<br/>
<br/>
<br/>

        참조:

<br/>

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

# Issue: redis cli scan 문법

## 상황:
redis insight에서 1m이 넘어가는 데이터는 scan으로 찾기 힘들다. scan 할 수 밖에 없음.


<br/>

## 알게된 부분 정리:

- scan 문법 사용

<br/>
 
## 개념: scan 문법 사용

<br/>

```
    >> scan 280576 MATCH "*ad*"
    1) "141312"
    2) (empty list or set)
    >> scan 141312 MATCH "*ad*"
    1) "239616"
    2) (empty list or set)
    >> scan 239616 MATCH "*ad*"
    1) "485376"
    2) (empty list or set)
    >> scan 239616 MATCH "*ad*" COUNT 100000
    1) "411090"
    2) (empty list or set)
    >> scan 411090 MATCH "*ad*" COUNT 100000
    1) "104681"
    2) (empty list or set)
    >> scan 104681 MATCH "*ad*" COUNT 100000
    1) "392519"
    2) (empty list or set)
    >> scan 392519 MATCH "*ad*" COUNT 100000
    1) "0"
    2) 1) "ad:affiliateMarketingByPathName::test_test_1003"
```

<br/>
<br/>
<br/>

        참조:
        https://line-enterprise.slack.com/archives/CE7R095RQ/p1639036727237900?thread_ts=1639034486.232000&cid=CE7R095RQ

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

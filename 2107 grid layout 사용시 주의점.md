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

# Issue: grid layout 사용 시 주의점

## 상황: 지인이 999개의 row는 적용이 되는데 1000개부터 크롬에서만 row가 인식이 안된다고 공유해줬다.

## 알게 된 부분:

- 크롬 엔진에서 막아둠

## 방안: 크롬 엔진에서 막아둠

<br/>

```
the 1000 rows (and also 1000 columns) limit has been intentionally introduced into the Chrome engine for reasons of stability and RAM consumption. A new version of the Grid functionality seems to be in progress and should solve the problem.
```

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/47218341/css-grid-layout-in-chrome-seems-not-to-work-properly-with-more-than-1000-rows

<br/>

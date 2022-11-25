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

# Issue: cypress 설정 관련 정리

## 상황:


<br/>

## 알게된 부분 정리:

- 기본적으로 테스트 실패 스크린샷과 비디오가 archive 되기 때문에 기본 설정 끄는게 좋다.
- circleci cron tab */5 같은 표현식 안됨.

<br/>

## 개념: 기본적으로 테스트 실패 스크린샷과 비디오가 archive 되기 때문에 기본 설정 끄는게 좋다.


<br/>

시간 너무 오래걸리고 자원도 많이 사용됨.

  ```
        screenshotOnRunFailure: false,
        video: false,
  ```
<br/>
<br/>
<br/>

        참조:
        https://docs.cypress.io/guides/references/configuration#Videos

<br/>

## 개념: circleci cron tab */5 같은 표현식 안됨.

<br/>


  
```
Note: Cron step syntax (for example, */1, */20) is not supported. Range elements within comma-separated lists of elements are also not supported. In addition, range elements for days (for example, Tue-Sat) is not supported. Use comma-separated digits instead.
```


<br/>
<br/>
<br/>

        참조:
        https://circleci.com/docs/workflows/#specifying-a-valid-schedule

<br/>

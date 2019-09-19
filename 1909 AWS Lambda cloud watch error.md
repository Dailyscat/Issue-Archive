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

# Issue: AWS Lambda에서 결제 정보 에러 나타나는 상황

## 상황:

람다 내부에서 aws sdk 사용 과정 중,
나의 결제 정보를 불러오는 코드에서 에러 발생

`"Data is not available. Please try to adjust the time period. If just enabled Cost Explorer, data might not be ingested yet"`

<br/>

## 생각해낸 방안:

- 에러 메시지 내용 참조하여 사용 내역이 없는 걸 확인 후 임의의 값을 할당하여 테스트
-

<br/>

## 방안: 에러 메시지 내용 참조하여 사용 내역이 없는 걸 확인 후 임의의 값을 할당하여 테스트 (성공)

<br/>

로그를 확인해보니 ResultsByTime 자체에서 null을 반환하는 걸 확인했다.

    var yesterdayBilling = costResult.ResultsByTime[0].Total.UnblendedCost.Amount;

    위 코드를

    ​varyesterdayBilling = "39.1603300457"

    변경

후에 테스트를 해보니 정상작동 하였다.

<br/>
<br/>
<br/>

        참조:

<br/>

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

# Issue: localDateTime에서 특정 시간대 떨구기

## 상황:
초 이하 값들을 버려야 하는 상황

<br/>

## 알게된 부분 정리:

-  localDateTime의 TruncatedTo 사용

<br/>

## 개념: localDateTime의 TruncatedTo 사용

<br/>
  
  ```
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	dateMark = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).format(dateTimeFormatter);
  ```

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/a/45326061

<br/>


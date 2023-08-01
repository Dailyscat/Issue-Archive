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

# Issue: spring boot에서 테스트 작성

## 상황:

<br/>

## 알게된 부분 정리:

- test시 기본 세팅

<br/>

## 개념:

<br/>
  ```
  // tomcat 구동시 사용하는 Profile을 넣어준다.
  @ActiveProfiles("local")
  // root application을 적용, 해당 어플리케이션에서 사용하는 bean, config들이 적용된다.
  @SpringBootTest(classes = TestApiApplication.class)
  ```
<br/>
<br/>
<br/>

        참조:

<br/>

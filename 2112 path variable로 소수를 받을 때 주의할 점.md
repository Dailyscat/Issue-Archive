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

# Issue: path variable로 소수를 받을 때 주의할 점

## 상황:
pathVariable을 소수로 받는 부분에서 
막상 파라미터로 전달된건 소수점 아래 값을 제외한 정수로 온다.

<br/>

## 알게된 부분 정리:

- 해당 변수에 variable:.+ 패턴을 적용

<br/>

## 개념: 해당 변수에 variable:.+ 패턴을 적용

<br/>
  콜론(":") 뒤에 있는 정규식 패턴을 받아들여서 
  소수로 오는 값도 제대로 들어올 수 있도록 한다.

  .+의 의미는 .이 포함된 모든 문자를 받아올 수 있도록 한다로 받아들인다.
  
  [스프링 공식문서에서의 소스](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestmapping-uri-templates)

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/3526523/spring-mvc-pathvariable-getting-truncated
        https://stackoverflow.com/questions/16332092/spring-mvc-pathvariable-with-dot-is-getting-truncated/18378817#18378817
        https://stackoverflow.com/questions/20527321/what-is-the-meaning-of-id-in-a-spring-mvc-requestmapping-handler-method/20527499

<br/>

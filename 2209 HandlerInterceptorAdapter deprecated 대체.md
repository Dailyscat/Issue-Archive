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

# Issue: HandlerInterceptorAdapter 대체

## 상황: 

<br/>

## 알게된 부분 정리:

- +

<br/>

## 개념:

<br/>

  ```
  spring 5.3 version 이상에서는 HandlerInterceptorAdapter 를 사용하는 대신 HandlerInterceptor를 implements 해서 사용하는 방식으로 바뀌었다고 한다. (필자는 springboot 2.3.x to 2.5.x 로 변경하니 spring version 이 5.3 이상으로 올라가서 이렇게 되었다.)

  HandlerInterceptor
  따라서 interceptor를 만들때는 위와 같이 HandlerInterceptor를 implements 해서 사용하도록 하자. 
  기존 방식과 차이점은 HandlerInterceptorAdapter를 사용할때는 extends해서 사용하므로 부모의 메소드를 반환할 수 있었으나 HandlerInterceptor를 implements 하는 방식에서는 true를 반환하는 방법만 존재한다.
  ```

<br/>
<br/>
<br/>

        참조:
        https://oingdaddy.tistory.com/399

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

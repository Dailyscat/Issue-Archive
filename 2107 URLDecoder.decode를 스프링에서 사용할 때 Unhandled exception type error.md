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

# Issue: URLDecoder.decode를 스프링에서 사용할 때 Unhandled exception type error

## 상황:
decode를 사용해서 인코딩된 문자열을 디코딩 하는 과정에서 해당 에러가 발생했고 빌드에서 에러를 자아내는 상황

## 생각해낸 방안:

- try catch 적용

## 방안: try catch 적용

<br/>

        Unhandled exception type IOException 컴파일 에러 때 

        아래 코드 추가



        import java.io.IOException;



        try {

        // 원래 코드

        } catch (IOException e) {

        System.out.println (ex.toString());

        }

<br/>
<br/>
<br/>

        참조:

<br/>

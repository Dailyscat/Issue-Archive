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

# Issue: spring boot server https 세팅


## 알게된 부분 정리:

- 서버 세팅 정리

<br/>

## 개념: 서버 세팅 정리

<br/>
  1. mkcert -pkcs12 ...도메인이름
  2. mkcert ...도메인이름
  3.
  ```
    server:
        ssl:
            key-store: classpath:cert/...도메인이름.p12 이때 해당 파일은 resource/ 에 위치해야한다.
            key-store-type: PKCS12
            key-store-password: changeit
  ```
<br/>
<br/>
<br/>

        참조:
        https://seungdols.tistory.com/994
        https://jojoldu.tistory.com/350
        https://velog.io/@10000doo/Nginx-SpringBoot-Https-%EC%A0%81%EC%9A%A9-3-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-Tomcat-Https-%EC%84%A4%EC%A0%95
        https://jinsiri.tistory.com/585
        https://stackoverflow.com/questions/55364537/spring-java-app-not-finding-the-keystore-file

<br/>

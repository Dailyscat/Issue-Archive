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

# Issue: jpaPagingItemReader의 query 적용시 주의 점

## 상황:
테이블 네임을 적용하여 쿼리 작성하는데 잘 안됨.

<br/>

## 알게된 부분 정리:

- jpaPagingItemReader에서 적용하는 쿼리는 jpql

<br/>

## 개념: jpaPagingItemReader에서 적용하는 쿼리는 jpql

<br/>
  jpa는 엔티티 이름을 바라보기 때문에 엔티티로 사용되는 클래스의 이름을 적용하여 query 사용해야함.

  ```
  select r from Wow r where r.updateDate < :updateDate order by updateDate
  ```
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/8230309/jpa-mapping-querysyntaxexception-foobar-is-not-mapped
        https://matthew.kr/spring-boot-jpa%EC%97%90%EC%84%9C-update-%EC%BF%BC%EB%A6%AC-%EC%82%AC%EC%9A%A9%EC%8B%9C-xxx-is-not-mapped-%EC%97%90%EB%9F%AC-%EB%B0%9C%EC%83%9D%EC%8B%9C-%EB%8C%80%EC%B2%98%EB%B0%A9%EB%B2%95/
        https://stackoverflow.com/questions/25893528/how-to-make-hql-selects-in-spring-batch

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

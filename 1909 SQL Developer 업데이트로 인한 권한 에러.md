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

# Issue: SQL Developer에서 Create Table시 권한 오류 발생

## 상황:

SQL Developer 사용 중, Create Table 사용 시에 error 발생

<br/>

## 생각해낸 방안:

- system 계정으로 유저 생성 후 테이블 스테이스에 할당량을 부여하는 권한 주기
- 에러 발생 이유 확인

<br/>

## 방안: system 계정으로 유저 생성 후 테이블 스테이스에 할당량을 부여하는 권한 주기 (과정1)

<br/>
  system 계정으로 유저 생성 후 테이블 스페이스에 할당량을 부여하는 권한을 줬다.

alter user 유저계정명 default tablespace users quotaunlimited on users;

<br/>
<br/>
<br/>

        참조:
        https://m.blog.naver.com/PostView.nhn?blogId=xxsaintxx&logNo=20121647322&proxyReferer=&proxyReferer=http%3A%2F%2Fblog.naver.com%2FPostView.nhn%3FblogId%3Dxxsaintxx%26logNo%3D20121647322

<br/>

## 방안: 에러 발생 이유 확인 (과정2)

<br/>

11g 이전의 버전에서 스키마용 유저 작성시 절차는

1. 유저 작성﻿ (Create user ... )

2. 권한 부여 (기본 connect , resource )

특별히 사용용량의 제한을 두고 싶을 때는 유저에 quota를 지정하기도 한다. 그런데 resource롤로 권한을 부여하면 자동적으로 unlimited tablespace 권한이 부여되어, 유저에게 쿼터를 부여하더라도 의미가 없어져 버린다. 물론, 엄밀히 관리하고자 한다면 resource롤을 사용 안하면 되긴 했지만. 12c에서는 이 부분이 개선되어 resource롤을 부여해도 unlimited tablespace권한은 부여되지 않는다.

위와 같은 이유로 발생한 오류다.

즉 12c에서는

1. 유저작성
2. 권한 부여
3. quota 부여 또는 unlimited tablespace 권한 부여

가 필요하다.

<br/>
<br/>
<br/>

        참조:
        https://m.blog.naver.com/PostView.nhn?blogId=dibidibijp&logNo=150181644741&proxyReferer=https%3A%2F%2Fwww.google.com%2F

<br/>

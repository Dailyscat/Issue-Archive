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

# Issue: next 개발 issue 정리

## 상황:
이슈 정리

<br/>

## 알게된 부분 정리:

- api 사용시 handler의 인자 type은 node의 req, res의 타입과 다르다.

<br/>

## 개념: api 사용시 handler의 인자 type은 node의 req, res의 타입과 다르다.

<br/>
  보통 req, res의 타입은 아래와 같다.

  return (req: IncomingMessage, res: ServerResponse) => {

  하지만 next에서 api routes를 사용하는 부분에서 req, res는 아래 타입으로 명시해서 사용되어야 한다. 아니면 res.status에서 status는 명시되지 않았다고 에러 발생.

  import { NextApiRequest, NextApiResponse } from "next";

  
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/67009540/property-status-does-not-exist-on-type-serverresponse-ts2339

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

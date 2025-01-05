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

# Issue: colab에서 pymongo 사용할 때 에러

## 상황:

mongo cloud에서 data api 25년 9월에 없앤대서 맘 잡고 python mongo driver로 연동하도록 함

근데 아래 이슈들 발생

'bad auth Authentication failed.', code: 8000,
ConfigurationError: The "dnspython" module must be installed to use
https://www.mongodb.com/ko-kr/docs/languages/python/pymongo-driver/current/get-started/create-a-connection-string/
https://stackoverflow.com/questions/60020381/mongodb-atlas-bad-auth-authentication-failed-code-8000

<br/>

## 알게된 부분 정리:

- pymongo[srv]를 통해서 사용하기

<br/>

## 개념: pymongo[srv]를 통해서 사용하기

<br/>
 https://cloud.mongodb.com/v2/62446e1671514f45b9c40697#/clusters/connect?clusterId=stock

colab이 python3를 사용해서 3version 지정해서
python -m pip install "pymongo[srv]"==3.11
이런 식으로 사용했는데 자꾸 에러 발생해서
python -m pip install "pymongo[srv]"
버전 지정없이 아래와 같이 사용하니 이슈 없음.

import pymongo
from pymongo import MongoClient
client = MongoClient( uri )

기본적으로는 srv 형식으로 연결이 안된다는 에러였는데
버전별로 내부적으로 라이브러리 내에 임포트 하지 않은 부분이 있는듯..
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

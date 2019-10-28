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

# Issue: s3에서 react 웹앱 배포하고 cloudfront를 사용하여 https를 적용하는 과정에서 access denied 에러

## 상황:

카메라 접근 권한이 필요하여 https를 적용하는 과정에서 cloud front를 사용하는데 access denied 발생

<br/>

## 생각해낸 과정:

- 이전 과정
  - 참조: https://react-etc.vlpt.us/08.deploy-s3.html
- principal 수정
- cloudfront의 origin 수정

<br/>

## 방안: principal 수정 (실패)

<br/>

    "Principal": {
        "AWS": "arn:aws:iam::cloudfront:user/CloudFront Origin Access Identity *********"
    },

    cloudfront를 통한 origin 접근은 전부 허용하도록 해놨지만 계속 access denied 였다.

<br/>
<br/>
<br/>

        참조:
        https://serverfault.com/questions/675475/cloudfront-s3-access-denied
        https://www.onlab.kr/2018/01/04/cloudfront-custom-origins3%ec%82%ac%ec%9a%a9%ec%8b%9c-origin-%ec%84%9c%eb%b2%84-%ec%a7%81%ec%a0%91-%ec%97%91%ec%84%b8%ec%8a%a4-%eb%b0%a9%ec%a7%80%ed%95%98%ea%b8%b0/

<br/>

## 방안: cloudfront의 origin 수정 (성공)

<br/>

s3 객체에서 생성되는 url은 중간에 어플의 형식과 리전에 대한 정보가 존재한다.

    ex)
    버킷이름.s3-website.ap-northeast-2.amazonaws.com

그런데 cloudfront에서 생성하는 origin domain name은 앱의 형식, 리전에 대한 정보가 없는 단순 버킷 이름에 .amazonaws.com 이 더해진 url이다.

애초에 퍼블릭 접근 권한을 준 객체의 url로 요청을 보내는게 아니라 애먼 url로 요청을 보내고 있어서 accessDenied가 발생하고 있었다.

흑흑 삽질했다.

<br/>
<br/>
<br/>

        참조:
        https://aws.amazon.com/ko/premiumsupport/knowledge-center/s3-website-cloudfront-error-403/
        https://jsdev.kr/t/s3-cloudfront-lambda/3320
        https://serverfault.com/questions/450940/why-s3-website-redirect-location-is-not-followed-by-cloudfront/471929#471929

<br/>

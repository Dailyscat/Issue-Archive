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

# Issue: cloudfront에 캐싱되어 있어서 s3의 호스팅한 객체를 업데이트하고 도메인에 접속해도 캐싱된 자원이 응답되는 경우

## 상황:

cli를 통해 react 앱을 바로 s3에 푸쉬하게 사용하고 있고 이를 cloudfront를 통해 https를 달았는데, 업데이트를 해도 캐싱된 자원만 응답으로 보내는 경우

<br/>

## 생각해낸 방안:

- 객체 Invalidation, 객체 versioning
- 정적 파일에 캐싱 무시 메타 데이터 추가

<br/>

## 방안: 객체 Invalidation, 객체 versioning (방안1)

<br/>

S3 객체를 무효화하여 CloudFront 배포의 캐시에서 해당 객체를 제거한다.
캐시에서 객체를 제거한 후 다음 요청에서는 Amazon S3에서 직접 객체를 검색한다.

cloudfront의 호스팅되어 있는 목록을 선택하고 Invalidation 항목으로 이동하여 create Invalidation을 클릭하고 /\*을 선택한다. 이 과정을 통해 현재 캐싱되어 있는 전체 파일을 무효화하고 요청이 있을 때 다시 s3에 업데이트된 객체를 응답으로 보내주도록 설정할 수 있다.

캐싱 시간의 설정이 가능하지만 업데이트마다 무효화를 하는게 가장 빠르다.

<br/>
<br/>
<br/>

        참조:
        https://docs.aws.amazon.com/ko_kr/AmazonCloudFront/latest/DeveloperGuide/Invalidation.html
        http://faq.hostway.co.kr/AWS_FAQ/7582
        https://walkinpcm.blogspot.com/2017/06/aws-aws-s3-origin-cloudfront.html

<br/>

## 방안: 정적 파일에 캐싱 무시 메타 데이터 추가 (방안2)

<br/>

정적 파일에 캐싱 무시 메타 데이터 추가

> If you never want index.html to be cached, set the Cache-Control: max-age=0 header on that file only. CloudFront will make a request back to your origin S3 bucket on every request, but it sounds like this is desired behavior.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/45727454/how-to-make-cloudfront-never-cache-index-html-on-s3-bucket

<br/>

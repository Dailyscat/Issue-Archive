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

# Issue: spring config 설정 변경 시 compile이 필수적으로 필요

## 상황:
multipart form data에서 이미지를 받아서 해당 이미지를 다른 모듈로 restTemplate을 사용해서 relay하는 과정에서 서버를 껐다켜봐도 문제가 발생.

<br/>

## 알게된 부분 정리:

- Could not write request: no suitable HttpMessageConverter found for request type [org.springframework.web.multipart.commons.CommonsMultipartFile]
- spring httpmediatypenotsupportedexception content type 'application/octet-stream' not supported
- postman 으로 file 전송 테스트

<br/>

## 개념: Could not write request: no suitable HttpMessageConverter found for request type [org.springframework.web.multipart.commons.CommonsMultipartFile]

<br/>
  이를 위해 FormHttpMessageConverter를 추가해줘도 계속 안됨. 다른 플젝에서는 같은 방식으로 해서 이미지 릴레이가 됐는데 머리 쥐어뜯고 있던상황. 갑자기 혹시 config가 바뀌었는데 컴파일을 안해서 그런가 해서 컴파일 해보니 작동. 
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/4118670/sending-multipart-file-as-post-parameters-with-resttemplate-requests

<br/>

## 개념: spring httpmediatypenotsupportedexception content type 'application/octet-stream' not supported

<br/>
    msa로 되어 있는 다른 모듈들에서 api를 요청했을 때 해당 에러 메세지.
    보통은 인증을 위한 토큰을 허용하지 않았을 때 관련 응답을 받는데, dto가 이러한 에러 메세지를 못받아서 이렇게 오게 되는것 같다.
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: postman 으로 file 전송 테스트

<br/>

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/39037049/how-to-upload-a-file-and-json-data-in-postman

<br/>

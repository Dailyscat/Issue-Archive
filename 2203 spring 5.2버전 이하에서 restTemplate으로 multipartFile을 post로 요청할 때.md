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

# Issue: spring 5.2버전 이하에서 restTemplate으로 multipartFile을 post로 요청할 때

## 상황:
클라이언트에서 전달받은 multipartFile을 다른 api로 전달

<br/>

## 알게된 부분 정리:

- 5.2 버전 이하에서는 multipart 관련 데이터 기본 지원
- 클라에서 받아온 multipartFile을 전달하려면 getResource 메소드 적용해야함.

<br/>

## 개념: 5.2 버전 이하에서는 multipart 관련 데이터 기본 지원

<br/>
  하지만 이하 버전에서는 지원하지 않음
  FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
  요 방식으로 컨버터를 만들고 restTemplate.setMessageConverter()에 set 해줘야함.

<br/>
<br/>
<br/>

        참조:
        https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/converter/FormHttpMessageConverter.html

<br/>

## 개념: 클라에서 받아온 multipartFile을 전달하려면 getResource 메소드 적용해야함.

<br/>
  ```
  		// HTTP Request Header
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache());
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		// Body
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("image", file.getResource());
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		HttpEntity<ImageUploadedResult> result = jsonRestTemplate.postForEntity(imageSearchApiUrl, requestEntity, ImageUploadedResult.class);
		return result.getBody();
  ```

  기본 틀은 위와 같은데 여기서 getResource를 하지 않으면 `No HttpMessageConverter for org.springframework.util.LinkedMultiValueMap and content type "multipart/form-data"` `Could not write request: no suitable HttpMessageConverter found for request type CommonsMultipartFile`와 같은 에러발생

  
<br/>
<br/>
<br/>

        참조:
        https://medium.com/red6-es/uploading-a-file-with-a-filename-with-spring-resttemplate-8ec5e7dc52ca
        https://enterkey.tistory.com/276
        https://www.baeldung.com/spring-httpmessageconverter-rest
        https://stackoverflow.com/questions/45501630/restclientexception-could-not-write-request-no-suitable-httpmessageconverter-f
        https://stackoverflow.com/questions/54656530/httpmessageconverter-exception-restclientexception-could-not-write-request-n

<br/>

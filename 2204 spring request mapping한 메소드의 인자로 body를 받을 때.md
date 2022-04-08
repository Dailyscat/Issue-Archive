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

# Issue: spring request mapping한 메소드의 인자로 body를 받을 때

## 상황:
List<Long> ids를 body로 받으려고 하는데 시리얼라이즈 에러 발생

JSON parse error : Cannot deserialize instance of 'java.util.ArrayList' out of START_OBJECT token

<br/>

## 알게된 부분 정리:

- map형태의 body를 리턴받도록한다.

<br/>

## 개념:

<br/>

```
  @RequestMapping(value="/requestotp",method = RequestMethod.POST) 
    public String requestOTP( @RequestBody Map<String,Object> body) {
        return customerService.requestOTP(body.get("idNumber").toString(), body.get("applicationId").toString());

```

DTO를 만들어서 하는것도 방법인데 그건 너무 귀찮기도하고~
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/49670209/can-spring-map-post-parameters-by-a-way-other-than-requestbody
        https://gogo-jjm.tistory.com/6


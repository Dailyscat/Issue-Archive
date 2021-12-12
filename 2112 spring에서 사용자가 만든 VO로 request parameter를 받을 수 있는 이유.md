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

# Issue: spring에서 사용자가 만든 VO로 request parameter를 받을 수 있는 이유

## 상황:
request mapping된 controller에서 요청과 함께 온 파라미터들이 
내가 만든 VO로 인자를 받으면 알아서 바인딩 되는데
어떻게 바인딩 되는지 살펴봤다.
```
public class Page {
    private int pageOffset;
    private int pageSize;

    // Getters and setters omitted
}

@RequestMapping("/people")
public void findAll(Page page) {
}
```


<br/>

## 알게된 부분 정리:

- ServletModelAttributeMethodProcessor에서 HandlerMethodArgumentResolver 인터페이스를 구현하면서 가능하게 된다.

<br/>

## 개념: ServletModelAttributeMethodProcessor에서 HandlerMethodArgumentResolver 인터페이스를 구현하면서 가능하게 된다.


<br/>
HandlerMethodArgumentResolver은 컨트롤러 메서드에서 특정 조건에 맞는 파라미터가 있을 때 원하는 값을 바인딩해주는 인터페이스다.

스프링에서는 Controller에서 @RequestBody 어노테이션을 사용해 Request의 Body 값을 받아올 때,
@PathVariable 어노테이션을 사용해 Request 의 Path Parameter 값을 받아올 때 이 HandlerMethodArgumentResolver를 사용해서 값을 받아온다.


```
InvocableHandlerMethod#getMethodArgumentValues

→ HandlerMethodArgumentResolverComposite#resolveArgument

→ HandlerMethodArgumentResolver#resolveArgument

→ ModelAttributeMethodProcessor#resolveArgument → createAttribute

→ ServletModelAttributeMethodProcessor#createAttribute → super#createAttribute

= ModelAttributeMethodProcessor#createAttribute → constructAttribute → resolveConstructorArgument

= ServletModelAttributeMethodProcessor#resolveConstructorArgument

```

ServletModelAttributeMethodProcessor#resolveConstructorArgument
해당 메서드에서 아래와 같이 작동한다.

```
package org.springframework.web.servlet.mvc.method.annotation; 

public class ServletModelAttributeMethodProcessor extends ModelAttributeMethodProcessor { 
    // .. 
    @Override 
    @Nullable 
    public Object resolveConstructorArgument(String paramName, Class<?> paramType, NativeWebRequest request) throws Exception { 
    // 1. ModelAttributeMethodProcessor 에 먼저 요청 
    Object value = super.resolveConstructorArgument(paramName, paramType, request); 
    if (value != null) { return value; } 
    // 2. fall-back 
    ServletRequest servletRequest = request.getNativeRequest(ServletRequest.class); 
    if (servletRequest != null) { String attr = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE; 
    @SuppressWarnings("unchecked") 
    Map<String, String> uriVars = (Map<String, String>) servletRequest.getAttribute(attr); 
    return uriVars.get(paramName); } return null; 
    }

```

1. modelAttribute에 해당 VO(java bean)이 있는지 확인하고 
2. 없으면 String URI_TEMPLATE_VARIABLES_ATTRIBUTE = HandlerMapping.class.getName() + ".uriTemplateVariables"; key에 바인딩 되어 있는, parameter에 선언한 VO와 같은 이름의 객체를 uriVars map에 할당하여 사용할 수 있게 한다.



<br/>
<br/>
<br/>

        참조:
        https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/ServletModelAttributeMethodProcessor.html
        https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/method/support/HandlerMethodArgumentResolver.html
        https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/WebDataBinder.html
        https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/ServletRequestDataBinder.html

        https://stackoverflow.com/questions/3423262/what-is-modelattribute-in-spring-mvc/3423501#3423501
        https://stackoverflow.com/questions/16942193/spring-mvc-complex-object-as-get-requestparam

        https://stackoverflow.com/questions/33592417/clean-way-to-bind-requestparam-to-an-object-instead-of-a-simple-type
        https://namocom.tistory.com/998
        https://namocom.tistory.com/894
        http://bluesky-devstudy.blogspot.com/2016/11/modelattribute-annotation.html
        http://bluesky-devstudy.blogspot.com/2016/09/annotation-controller-aop.html
        https://stackoverflow.com/questions/11291933/requestbody-and-responsebody-annotations-in-spring
        https://velog.io/@kingcjy/Spring-HandlerMethodArgumentResolver%EC%9D%98-%EC%82%AC%EC%9A%A9%EB%B2%95%EA%B3%BC-%EB%8F%99%EC%9E%91%EC%9B%90%EB%A6%AC

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

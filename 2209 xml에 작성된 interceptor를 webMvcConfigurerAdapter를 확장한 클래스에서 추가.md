<!--
author: Dailyscat
purpose: \issue arrange
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

# Issue: xml에 작성된 interceptor를 webMvcConfigurerAdapter를 확장한 클래스에서 추가

## 상황: 

## 생각해낸 방안:

- webMvcConfigurerAdapter를 확장한 클래스에서 addInterceptors override하여 추가.

## 방안: 

<br/>

```
    <mvc:interceptors>
        <mvc:interceptor>
        ...
```

```
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestResolverInterceptor())
        ...
	}
```

<br/>
<br/>
<br/>

        참조:

<br/>

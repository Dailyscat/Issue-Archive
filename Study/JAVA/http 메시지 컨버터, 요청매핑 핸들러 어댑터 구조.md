HTTP 메시지 컨버터
뷰 템플릿으로 HTML으로 응답하는 게 아니라, HTTP API처럼 JSON 데이터를 HTTP 메시지 바디에서 읽거나 쓰는 경우 HTTP 메시지 컨버터를 사용하면 편리하다.

 

스프링 MVC는 HTTP 요청과 응답에 HTTP 메시지 컨버터를 적용한다.

- HTTP 요청 : @RequestBody, HttpEntity(RequestEntity)

- HTTP 응답 : @ResponseBody, HttpEntity(ResponseEntity)

 

HTTP 메시지 컨버터 인터페이스 : org.springframework.http.converter.HttpMessageConverter

public interface HttpMessageConverter<T> {

	boolean canRead(Class<?> clazz, @Nullable MediaType mediaType);

	boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType);

	List<MediaType> getSupportedMediaTypes();

	default List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
		return (canRead(clazz, null) || canWrite(clazz, null) ?
				getSupportedMediaTypes() : Collections.emptyList());
	}

	
	T read(Class<? extends T> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException;

	void write(T t, @Nullable MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException;

}
canRead(), carWrite() : 메시지 컨버터가 해당 클래스, 미디어타입을 지원 여부 체크

read(), write() : 메시지를 읽고 쓰는 기능

 

스프링 부트 기본 메시지 컨버터

스프링 부트는 핸들러 매핑과 핸들러 어댑터처럼 다양한 메시지 컨버터를 제공하며

대상 클래스와 미디어의 타입을 체크하여 사용 여부를 결정한다.

0 = ByteArrayHttpMessageConverter
1 = StringHttpMessageConverter
2 = MappingJackson2HttpMessageConverter
- ByteArrayHttpMessageConverter : byte[] 데이터를 처리

클래스 타입 : byte[]

미디어 타입 : */*

요청 ex) @RequestBody byte[] data

응답 ex) @ResponseBody return byte[] 쓰기 미디어타입 'application/octet-stream'


- StringHttpMessageConverter : String 문자로 데이터를 처리

클래스 타입 : String

미디어 타입 : */*

요청 ex) @RequestBody String data

응답 ex) @ResponseBody return "ok" 쓰기 미디어타입 'text/plain'


- MappingJackson2HttpMessageConverter : application/json

클래스 타입 : 객체 or HashMap

미디어 타입 : application/json

요청 ex) @RequestBody HelloData data

응답 ex) @ResponseBody return helloData 쓰기 미디어타입 'application/json'

 

HTTP 요청 데이터 읽기

요청이 오면 컨트롤러에서 @RequestBody, HttpEntity 파라미터를 사용

-> 메시지 컨버터가 carRead()로 확인, 클래스타입와 미디어타입 확인

-> 조건을 만족하면 read()로 객체를 생성하고 반환

 

HTTP 요청 데이터 쓰기
컨트롤러에서 @RequestBody, HttpEntity 로 값이 반환

-> 메시지 컨버터가 canWrite()로 확인, 클래스 타입와 Accept 미디어타입 확인

-> 조건을 만족하면 write()로 HTTP 응답 메시지 바디에 데이터를 생성

요청 매핑 핸들러 어댑터 구조

HTTP 메시지 컨버터는 어디에 위치할까?
 

HTTP 메시지 컨버터는 핸들러 어댑터의 RequestMappingHandlerAdapter에 있다.


 

ArgumentResolver (정확한 명칭은 HandlerMethodArgumentResolver)

지금까지 애노테이션 기반 컨트롤러에서 다양한 파라미터(@RequestParam, @ModelAttribute, HttpEntity...)를 사용할 수 있었다.

ArgumentResolver 덕분에 유연하게 파라미터를 처리할 수 있다.

 

RequestMappingHandlerAdapter는 ArgumentResolver를 호출해서 다양한 파라미터를 생성해서 컨트롤러에게 넘겨준다. 

 

HandlerMethodArgumentResolver 인터페이스 : org.springframework.web.method.support.HandlerMethodArgumentResolver

public interface HandlerMethodArgumentResolver {

	boolean supportsParameter(MethodParameter parameter);

	@Nullable
	Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception;

}
동작 방식

supportsParameter()로 지원 여부를 확인하고 resolveArgument()를 호출해서 객체를 생성하여 넘겨준다.

 

ReturnValueHandler (정확한 명칭은 HandleMethodReturnValueHandler)

ArgumentResolver가 요청을 처리한다면 ReturnValueHandler은 응답 값을 변환하고 처리한다.

지금까지 컨트롤러에서 다양한 값을 반환해도 동작할 수 있는 이유다.

 

HTTP 메시지 컨버터 위치


 

확장

스프링은 위의 기능들을 확장해서 사용할 수 있도록 인터페이스를 제공한다

- HandleMethodArgumentResolver

- HandleMethodReturnValueHandler

- HttpMessageConverter

하지만 스프링이 대부분의 기능을 제공해서 실제로 확장할 일은 거의 없다.

원하면 WebMvcConfigurer를 상속받아 스프링 빈으로 등록하면 된다. (다음 단계에서 진행할 예정)

https://loopstudy.tistory.com/223 자료 아카이브
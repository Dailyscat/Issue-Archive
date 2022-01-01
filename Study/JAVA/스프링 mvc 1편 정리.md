# 스프링 mvc 1편 정리

###### 김영한님의 스프링 mvc 1편 정리

## WS와 WAS 차이

- 둘의 경계는 모호.
- 요청에 대하여 자원이 어떻게 응답되는가에 차이.

WS(웹 서버)
- 이미 완전히 개별의 파일로 마련되어 있는 자원을 요청에 대하여 제공

WAS(웹 어플리케이션 서버)
- 어떤 자원에 대한 요청을 받고 나서 그 자원에 대해 요청 스펙에 맞는 특정 처리?를 실행한 후에 제공.

## 서블릿

![](/image/2022-01-01-16-19-36.png)

```
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 애플리케이션 로직
    }
}
```

- 서블릿은 단순하다. 위의 코드처럼 구성되었으며 urlPatterns에 맞게 호출이 된다.
- HTTP 요청이면 HttpServletRequest로 사용한다.
- HTTP 응답이면 HttpServletResponse로 제공한다. 
- HTTP 스펙을 편리하게 사용할 수 있다. (기본적인 HTTP 스펙을 알고 있어야한다.)

### 서블릿 흐름

![](/image/2022-01-01-16-20-47.png)

HTTP 요청, 응답 흐름

1. WAS는 Request, Response 객체를 생성해서 서블릿 객체를 호출

2. 개발자는 Request 객체에서 HTTP 요청 정보를 꺼내서 사용

3. 개발자는 Response 객체에 HTTP 응답 정보를 입력

4. WAS는 Response 객체의 내용으로 HTTP 응답 정보를 생성

 

서블릿 컨테이너

- 톰캣처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너라고 함

- 서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기를 관리

- 서블릿 객체는 싱글톤으로 관리

    - 요청마다 생성은 낭비

    - 최초 로딩 시점에 객체를 생성해 놓고 재활용

    - 고객의 모든 요청은 동일한 인스턴스에 접근

    - 공유 변수 사용 주의

    - 서블릿 컨테이너 종료 시 함께 종료

- JSP도 서블릿응로 변환 되어서 사용

- 동시에 요청을 위한 멀티 쓰레드 처리 지원

### 동시 요청 - 멀티 쓰레드

쓰레드

- 애플리케이션 코드를 순차적으로 실행하는 건 쓰레드

- 자바 메인 메서드를 실행하면 main 쓰레드가 실행

```
public static void main(String[] args) {
    
}
```
- 쓰레드가 없다면 애플리케이션 실행이 불가능

- 쓰레드는 한번에 하나의 코드 라인만 수행

- 동시 처리가 필요하면 쓰레드가 추가로 생성

 ![](/image/2022-01-01-16-22-33.png)

 ![](/image/2022-01-01-16-22-52.png)


 ### 쓰레드풀

 - WAS 튜닝 포인트는 최대 쓰레드(max thread)다
     - 이 값이 너무 작으면 서버 리소스는 여유있지만 클라이언트는 응답 지연 받게 된다.
     - 이 값이 너무 높으면 서버 리소스 임계점 초과로 서버 다운이 될 수 있다.

- 장애 발생시
     - 클라우드면 서버를 늘리고 튜닝하면 된다.(서비스는 지속되어야한다)
     - 클라우드가 아니면 열심히 튜닝해야한다.(죽으면 서비스는 중단된다.)

 

#### 쓰레드 풀 적정 숫자

- 애플리케이션 로직의 복잡도, CPU, 메모리, IO 리소스 상황마다 모두 다르다.

- 성능 테스트로 파악할 수 있다.

 

#### WAS의 멀티 쓰레드 지원 (핵심)

- WAS가 멀티 쓰레드 부분을 처리한다.

- 개발자가 멀티 쓰레드 관련 코드를 신경쓰지 않아도 된다.

- 개발자는 마치 싱글 쓰레드처럼 편리하게 개발한다.

- 단, 싱글톤 객체를 주의해서 사용해야 한다. 

 
#### 파라미터 조회 방법
1. 전체 파라미터 조회
```
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
    }
}
위의 url를 넣고 실행하면 결과가 나온다.

[전체 파라미터 조회] - start
username=hello
age=20
[전체 파라미터 조회] - end
```
2. 단일 파라미터 조회

```
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");
    }
}
```
3. 이름이 같은 복수 파라미터 조회

http://localhost:8080/request-param?username=hello&username=kim&age=20

 
```
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[복수 파라미터 조회] - start");
        String[] usernames = request.getParameterValues("username");
        Arrays.stream(usernames).forEach(name -> System.out.println("username = " + name));
        System.out.println("[복수 파라미터 조회] - end");
    }
}
```


## 스프링 MVC 시작하기

스프링이 제공하는 컨트롤러는 애노테이션 기반으로 동작해서, 매우 유연하고 실용적이다.

과거에는 애노테이션이 없었다, 스프링도 초반엔 xml 방식으로 컨트롤러를 제공했었는데 당시엔 스트럿츠 외에도 여러 프레임워크가 있었다.

하지만 애노테이션 기반의 컨트롤러가 등장하면서 스프링 완승으로 끝났다.

 

@RequestMapping

- RequestMappingHandlerMapping

- RequestMappingHandlerAdapter

위의 RequestMappingHandlerMapping와 RequestMappingHandlerMapping는 @RequestMapping의 앞글자를 따서 만든 이름이다. 대부분 실무의 99.9%는 이 방식의 컨트롤러를 사용한다.

 

이전 시간에 했던 코드를 재사용하여 스프링MVC 기반으로 하나씩 변경해보자.

```
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
```

#### @Controller

- 스프링이 자동으로 스프링 빈으로 등록한다. 내부에 @Component가 있다.

```
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {

	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any (or empty String otherwise)
	 */
	@AliasFor(annotation = Component.class)
	String value() default "";

}
```

- 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식

 

#### @RequestMapping

- 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출되며, 애노테이션 기반으로 동작해서 메서드의 이름은 임의로 지으면 된다.

 

#### ModelAndView

- 모델과 뷰 정보를 담아서 반환한다.

RequestMappingHandlerMapping은 @RequestMapping, @Controller가 클래스 레벨에 붙어 있으면 매핑 정보로 인식한다.

```
//@Controller
@Component
@RequestMapping
public class SpringMemberFormControllerV1 {
    ...
}
@Bean 방식으로 설정해도 작동한다.

@Bean
SpringMemberFormControllerV1 newform() {
    return new SpringMemberFormControllerV1();
}
하지만 매번 선언하기 불편하니 깔끔하게 @Controller를 애용하자.

```
 
```
@Controller
public class SpringMemberSaveControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}

@Controller
public class SpringMemberListControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", memberRepository.findAll());
        return mv;
    }
}
```

모델을 추가할 때에는 ModelAndView.addObject(key, value)를 이용하면 된다.


#### 스프링 MVC - 컨트롤러 통합

@RequestMapping 위치를 보면 메소드 레벨인걸 알 수 있다.

컨트롤러를 여러개 만들 필요 없이 하나로 통합할 수 있다.

```
@Controller
public class SpringMemberControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v2/members")
    public ModelAndView members(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", memberRepository.findAll());
        return mv;
    }

    @RequestMapping("/springmvc/v2/members/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }

    @RequestMapping("/springmvc/v2/members/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }
}
```

하나로 통합된 모습.

그런데 매핑 경로를 보면 /spring/v2/members 중복되는 걸 볼 수 있다.

이 중복을 해결하는 방법은 간단하다. 클레스 레벨에도 @RequestMapping(중복경로)를 추가하면 된다.

 
```
@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping
    public ModelAndView members(HttpServletRequest request, HttpServletResponse response) {
        ...
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        ...
    }

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        ...
    }
}
```

#### 조합

클래스 레벨과 메소드 레벨을 이용해서 중복을 제거한 걸 조합이라 한다.

 

클래스 레벨 @RequestMapping("/springmvc/v2/members")

- 메서드 레벨 @RequestMapping("") -> /springmvc/v2/members

- 메서드 레벨 @RequestMapping("/save") -> /springmvc/v2/members/save

- 메서드 레벨 @RequestMapping("/new-form") -> /springmvc/v2/members/new-form

스프링 MVC - 실용적인 방식
이전까지 보면서 매번 반복되는 게 더 있었다.

new ModelAndView <-- 매번 반환

이걸 개선한 방식이 존재한다.

```
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping
    public String members(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "members";
    }

    @RequestMapping("/save")
    public String save(
                @RequestParam("usename") String username,
                @RequestParam("age") int age,
                Model model
                ) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @RequestMapping("/new-form")
    public String newForm() {
        return "new-form";
    }
}
```

다양한 변화가 존재한다.

1. ModelAndView 제거.

- 뷰네임을 반환하는 방식으로 변경

- 모델은 Model 파라미터로 받음

 

2. @RequestParam 사용 (자세한 원리는 나중에) 

- HttpServletRequest.getParameter로 받아온 데이터를 직접 관리했지만, @RequestParam(key)를 이용해서 편하게 파라미터를 관리

 

추가적으로 GET, POST 호출 방식을 제한 걸 수 있다.

@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(...) {
    ...
}
이전까지는 GET, POST 상관없이 호출이 되었지만 이제 save는 POST 방식으로만 호출이 된다.


GET 은 접근 불가
여기서 더 나아가

```

@RequestMapping(value = "/save", method = RequestMethod.POST)를 한번 더 개선된 기능이 나온다.

@PostMapping, @GetMapping으로 위의 기능을 하나로 합친 애노테이션이다.

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping
    public String members(Model model) {
        ...
    }

    @PostMapping("/save")
    public String save(
        ...
    }

    @GetMapping("/new-form")
    public String newForm() {
        ...
    }
}
```

해당 애노테이션 내부에 @RequestMapping이 포함된 모습도 확인할 수 있다.

```
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.POST)
public @interface PostMapping {
```

### 스프링 MVC 기본 기능 - 로깅 간단히 알아보기
이전까지 System.out.print() 시스템 콘솔로 정보를 출력했지만, 이제는 별도의 로깅 라이브러리로 로그를 출력해보자.

로그 라이브러리도 내용이 끝이 없기 때문에 간단히 사용방법만 알고 넘어간다.

스프링 부트 라이브러리를 사용하면 기본적으로 로깅 라이브러리가 포함되어있다.

```
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "spring";
        log.info("name = " + name);

        return "ok";
    }
}
```

```
@RequestMapping("/log-test")
public String logTest() {
    String name = "spring";
    log.trace("trace log={}", name);
    log.debug("debug log={}", name);
    log.info(" info log={}", name);
    log.warn(" warn log={}", name);
    log.error("error log={}", name);

    return "ok";
}
```

![](/image/2022-01-01-16-32-13.png)

trace와 debug가 찍히지 않았다.

로그 level은 trace, debug, info, warn, error 순서로 있으며 디폴트로 info로 지정되어있다.
바꾸려면 application.properties 에서 설정한다.

#hello.springmvc 패키지와 그 하위 로그 레벨 설정
logging.level.hello.springmvc=trace

trace 로그가 출력된다.
그리고 처음에 log.info("name = " + name)로 사용했지만 그 다음부터는 log.info("name = {}", name)으로 사용했다.

그 이유는 ("name = " + name) 방식은 문자 더하기 연산이 발생하기 때문이고, name = {}은 연산이 일어나지 않는다.

즉, 연산이 일어나면 쓸모없는 리소스를 사용하기 때문에 log.info("name = {}", name)을 사용해야 한다.


```
로그를 사용할 때 클래스마다 아래의 코드를 선언해야 할까?

private final Logger log = LoggerFactory.getLogger(??.class);

이전과 마찬가지로 로그를 편안하게 사용할 수 있는 기능이 존재한다.

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);
    ...
}
@Slf4j 를 사용하면 된다.
```

##### 로그의 장점

- 쓰레드 정보, 클래스 정보를 볼 수 있고 출력 모양을 조정할 수 있다.

- 로그 레벨 제어로 상황에 맞게 로그를 출력할 수 있다.

- System.out.println()은 시스템 콘솔에만 출력되지만 로그는 파일, 네트워크, 디비 등 별도의 위치에 남길 수 있고, 시간이나 용량에 따라 분할도 가능하다.

- 성능이 일반 System.out보다 좋기 때문에 실무에서 사용한다.


### 스프링 MVC 기본 기능 - 요청 매핑
매핑 기능에 대해 알아보자.

간단한 컨트롤러를 하나 선언한다.

```
@Slf4j
@RestController
public class MappingController {

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("hello baisc");
        return "ok";
    }
}
```

localhost:8080/hello-basic을 호출하면 ok가 잘 반환된다.

매핑에는 여러 가지 기능이 존재한다. 그중 배열로 하나의 메서드를 실행하게 할 수 있다.

```    
@RequestMapping({"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("hello baisc");
        return "ok";
    }
```
 

HTTP 메서드

매핑에 method 속성이 존재하는데, 없으면 GET, POST 무관하게 호출이 된다.

HTTP 메서드 매핑은 다음과 같다

```
@Slf4j
@RestController
public class MappingController {
    ...
    
    // HTTP 매핑
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }
}
```

하지만 경로와 HTTP를 매핑도 반복되어 단축된 방법도 있다.

 

HTTP 매핑 메서드 축약

```    
/**
     * 편리한 축약 애노테이션 (코드보기) * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }
```

해당 매핑 메서드 내부를 보면 RequestMethod.??를 볼 수 있다. 

 

PathVariable(경로 변수) 사용

```    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }
```

경로를 값으로 이용한다.

localhost:8080/mapping/100 


@PathVariable의 이름과 변수명이 같으면 생략할 수 있다.

@PathVariable("userId") String userId -> @PathVariable String userId

 

PathVariable 다중 사용

경로 변수를 다중으로 지정할 수 있다. 
```
    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }
```
위와 동일한 방법으로 호출하면 된다.

 

파라미터 추가 매핑

```
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }
```   

특정 파라미터와 값이 존재해야 해당 메서드가 호출이 된다.


#### 특정 헤더로 추가 매핑
```
    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }
```

#### Content-type 헤더 기반 추가 매핑

```
    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }
```

#### Accept 헤더 기반 Media Type

```
    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html" * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
```

#### HTTP 요청 - 기본, 헤더 조회
애노테이션 기반의 스프링 컨트롤러는 다양한 파라미터를 지원한다.

 

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie
                          ) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}

HttpMethod : HTTP 메서드를 조회
Locale : locale 정보를 조회
@RequestHeader MultiValueMap<String, String> headerMap : 모든 HTTP 헤더를 MultiValueMap 형식으로 조회

@RequestHeader(key) : 특정 HTTP 헤더를 조회
@CookieValue(key) : 특정 쿠키를 조회

 

참고 : MultiValueMap는 하나의 키에 여러 값을 받을 수 있는 Map이다.

 

스프링 컨트롤러의 다양한 파라미터와 응답 목록은 가이드를 참고하자.

https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments

https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-return-types

HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form
HTTP 요청 데이터 조회 - 개요

이전 서블릿에서 HTTP 요청 데이터를 조회 방법을 스프링이 얼마나 깔끔하게 해 주는지 알아보자.

// 서블릿 방식
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    System.out.println("username = " + username);
    ...
}
 

클라이언트에서 서버로 요청 데이터를 전달할 때는 3가지 방법을 사용

1. GET - 쿼리 파라미터

- /url?username=hello&age=20

- 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달

- 예) 검색, 필터, 페이징 등에서 많이 사용하는 방식 (URL에 노출돼도 상관없는 정보)

 

2. POST - HTML Form

- content-type : application/x-www-form-urlencoded

- 메시지 바디에 쿼리 파라미터 형식으로 전달 username=hello&age=20

- 예) 회원 가입, 상품 주문, HTML Form 사용 (노출되면 안 되는 정보)  

<form action="/request-param-v1" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
 

3. HTTP message body

- HTTP API에서 주로 사용 JSON, XML, TEXT

- 데이터 형식은 주로 JSON 사용

- POST, PUT, PATCH

HTTP 요청 파라미터 - @RequestParam
스프링이 제공하는 @RequestParam을 이용하면 편안하게 요청 데이터를 사용할 수 있다.

 

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
 

더 나아가 @RequestParam(key)에서 key와 변수명이 동일한 경우 key를 생략할 수 있다.

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

 

String, int, Integer 같은 단순 타입이면 @RequestParam 생략도 가능하다. 

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

 

필수 파라미터 여부

@RequestParam 속성엔 required가 있다. default=true이며, 필수인 값이 없으면 400 에러가 반환된다. 

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParaRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

필수값 username 없어서 에러 반환
만약에 null 값이 올 경우 변수 타입에 신경을 써야 한다.


int 타입은 null을 받을 수 없어서 에러가 발생.

username= 으로 보내면 빈문자로 인식하여 통과될 수도 있다. 


 

@RequestParam은 defaultValue 속성이 존재한다.

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

필수 데이터가 없어도 디폴트 값을 지정해서 400 에러가 발생하지 않는다.

 

Map 타입으로 요청 데이터를 담을 수 있다.

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamDefault(
            @RequestParam Map<String, Object> paramMap
    ) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
만약에 데이터가 배열로 넘어온다면 MultiValueMap을 사용하자.

 

HTTP 요청 파라미터 - @ModelAttribute
보통 데이터를 받으면 객체를 생성하고 그 객체에 값을 세팅하는데,

스프링은 이 과정을 자동화시켜주는 @ModelAttribute 기능을 제공한다.

 

@Data
public class HelloData {
    private String username;
    private int age;
}
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData.getUsername());
        return "ok";
    }
롬복의 @Data를 사용하면 

@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 적용해준다.

 

정상적으로 작동하는지 확인해보자



편하게 객체가 생성되고 값이 세팅되었다.

 

스프링의 @ModelAttribute 실행 순서

- HelloData 객체 생성

- 요청 파라미터의 이름으로 HelloData 객체 프로퍼티를 찾고, setter 호출해서 값을 바인딩한다.

- 예) 파라미터 이름이 username 이면 setUsername() 을 메서드를 찾아 호출하면서 값을 입력. (요청 데이터와 객체의 프로퍼티 명이 일치해야 한다) 

- 만약 타입에 어긋나는 데이터가 들어오면 에러가 발생한다.

 

@ModelAttribute도 생략이 가능하다.

    // @ModelAttribute 생략이 가능
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData.getUsername());
        return "ok";
    }
 

스프링은 @RequestParam, @ModelAttribute 생략하면 다음의 규칙을 따른다.

- String, int, Integer 같은 단순 타입이면 @RequestParam을 자동 적용

- 나머지는 @ModelAttribute 를 적용한다. 단, argument resolver로 지정해둔 타입은 제외. (이 내용은 나중에 언급됨)


HTTP 요청 메시지 - 단순 텍스트
이전 시간에 했던 요청 파라미터와 다르게 HTTP 메시지 바디를 통해 데이터 넘어오는 경우

@RequestParam, @ModelAttribute를 사용할 수 없다.

단, HTML Form 형식은 요청 파라미터로 인정된다. 

 

HTTP 메시지 바디의 데이터는 InputStream을 사용해서 직접 읽을 수 있다.

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 바이트코드를 UTF-8 변환

        log.info("messageBody={}", messageBody);
        
        response.getWriter().write("ok");
    }
}
확인은 postman으로 한다.



HttpServletRequest 에서 받아와서 처리하는 과정이 너무 길다.

 

스프링 MVC는 다음 파라미터를 지원한다.

InputStream(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회

OutputStream(Writer) : HTTP 응답 메시지의 바디에 직접 결과 출력

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 바이트코드를 UTF-8 변환

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }
이것도 처음에 비하면 과정이 간단히 생략되었을 뿐 사용방법은 흡사해서 계속 반복되는 불편함이 있다.

 

스프링 MVC는 더 나아가 메시지 컨퍼버라는 기능을 제공한다. (자세한 내용은 다음에 언급된다.)

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String body = httpEntity.getBody();
        log.info("messageBody={}", body);

        return new HttpEntity<>("ok");
    }
HTTP 메시지처럼 주고 받는다. 

 

HttpEntity

- HTTP header, body 정보를 편리하게 조회

- 요청 파라미터를 조회하는 기능과 관계없음 (@RequestParam, @ModelAttribute)

- 응답에도 사용 가능

- 메시지 바디 정보 직접 반환

- 헤더 정보 포함 가능

- view 조회X

 

HttpEntity를 상속받은 객체도 존재한다

RequestEntity

- HttpMethod, url 정보가 추가, 요청에 사용

 

ResponseEntity

- HTTP 상태 코드 설정 가능, 응답에서 사용

- return new ResponseEntity<String>("hello world", responseHeaders, HttpStatus.CREATED)

    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(RequestEntity<String> httpEntity) throws IOException {
        String body = httpEntity.getBody();
        log.info("messageBody={}", body);

        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }
 

스프링MVC 내부에서 HTTP 메시지 바디를 읽어서 문자나 객체로 변환하여 전달한다. 이때 HTTP 메시지 컨버터(HttpMessageConverter) 기능을 사용한다. (자세한 내용은 다음에)

 

@RequestBody

@RequestBody를 사용하면 HTTP 메시지 바디 정보를 편리하게 조회할 수 있다. 헤더 정보가 필요하면 HttpEntity나 @RequestHeader를 사용한다. 메시지 바디를 조회하는 기능은 @RequestParam, @ModelAttribute와는 관계없다. 

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String meesageBody) throws IOException {
        log.info("messageBody={}", meesageBody);

        return "ok";
    }
 

정리

- 요청 파라미터를 조회하는 기능 : @RequestParam, @ModelAttirubte

- HTTP 메시지 바디를 직접 조회하는 기능 : @RequestBody

 

@ResponseBody

- 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달 가능 (뷰를 사용하지 않는다.)

HTTP 요청 메시지 - JSON
JSON 형식 데이터 조회 방법이다.

 

서블릿 방식

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        response.getWriter().write("ok");
    }
}
 

@RequestBody 방식 - String

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }
생략을 했지만 반복되는 변환 과정이 존재한다.

 

@RequestBody 방식 - JSON -> 객체 자동 변환

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

 


 

@RequestBody 객체 파라미터

- 직접 만든 객체를 지정할 수 있다. @RequestBody HelloData helloData

 

HttpEntity, @RequestBody를 사용하면 HTTP 메시지 컨버터가 메시지 바디의 내용을 지정된 문자나 객체로 변환해준다.

JSON도 객체로 변환시켜주는데 V2 에서 했던 objectMapper.readValue(값, 객체)를 대신 처리해준다. (자세한 내용은 다음에)

 

@RequestBody는 생략 불가능하다. 생략하면 @RequestParam 이나 @ModelAttribute로 적용되어

HTTP 메시지 바디가 아니라 요청 파라미터를 처리하게 된다. (요청 파라미터 처리 기능은 메시지 바디 데이터를 인식 못한다)

 

명심할 것은 HTTP 요청 시에 content-type : application/json 인지 확인해야 한다. 그래야 HTTP 메시지 컨버터가 실행된다.

 

 

HttpEntity 방식

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException {
        HelloData data = httpEntity.getBody();
        log.info("username={}, age={}", data.getUsername(), data.getAge());

        return "ok";
    }
 

@RequestBody - 응답 방식

- 해당 객체를 HTTP 메시지 바디에 직접 넣을 수 있다

- 이 경우에도 HttpEntity 사용이 가능하다.

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) throws IOException {
        log.info("username={}, age={}", data.getUsername(), data.getAge());

        return data;
    }

 

정리

@RequestBody 요청

- JSON 요청 -> HTTP 메시지 컨버터 -> 객체

 

@RequestBody 응답

- 객체 -> HTTP 메시지 컨버터 -> JSON 응답

스프링에서 응답 데이터를 만드는 방법은 3가지다.

- 정적 리소스 : 웹 브라우저에서 정적인 HTML, css, js

- 뷰 템플릿 사용 :  웹 브라우저에 동적인 HTML 제공

- HTTP 메시지 사용 : HTTP 메시지 바디에 JSON 형식으로 데이터를 전달

 

정적 리소스
스프링 부트는 클래스패스의 다음 디렉토리에 있는 정적 리소스를 제공한다.

/static, /public, /resource, /META-INF/resources

 

정적 리소스 경로

src/main/resources/static


위와 같은 경로에 파일이 있으면 http://localhost:8080/basic/hello-form.html 로 접근이 가능하다.


정적 리소스에 접근이 된다.
 

뷰 템플릿
뷰 템플릿을 거쳐서 HTML이 생성되고, 뷰가 응답을 만들어서 전달한다.

스프링부트는 기본 뷰 템플릿 경로를 제공한다.

 

뷰 템플리 경로 : src/main/resource/templates 


@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello");

        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello");
    }
}
String을 반환하는 경우 - View or HTTP 메시지

@ResponseBody가 없으면 response/hello로 뷰 리볼버가 실행되어서 뷰를 찾고 렌더링 한다.

만약에 있으면 뷰 리졸버를 건너뛰고 HTTP 메시지 바디에 직접 response/hello 문자가 입력되어 반환된다.

 

@ResponseBody가 없고, 뷰 이름도 반환되지 않는다면?

스프링은 자체적으로 경로로 뷰 템플릿을 찾아서 반환된다. (명시적이 없어 비권장한다) 


경로로 뷰 템플릿이 반환된다.
HTTP 응답 - HTTP API, 메시지 바디에 직접 입력
HTTP API를 제공하는 경우에 HTML이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON 형식으로 데이터를 보낸다.

 

@Slf4j
@Controller
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("userA");
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("userA");
        return helloData;
    }
}
responseBodyV1 : HttpServletResponse 객체를 통해서 HTTP 메시지 바디에 응답 메시지를 전달한다.

responseBodyV2 : HttpEntity는 HTTP 메시지의 헤더, 바디 정보를 갖는데, 이를 상속받은 ResponseEntity는 HTTP 응답 코드를 설정할 수 있다.

responseBodyV3 : @ResponseBody를 사용하면 HTTP 메시지 컨버터를 통해 HTTP 메시지를 직접 입력한다.

 

responseBodyJsonV1 : ResponseEntity로 반환한다.

responseBodyJsonV2 : @ResponseBody 사용할 경우에 HTTP 응답 코드는 @ResponseStatus로 설정한다.

자료 아카이브
https://loopstudy.tistory.com/223
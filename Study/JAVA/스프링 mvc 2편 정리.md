# 스프링 mvc2편 정리

resources

- static
  - index.html은 스프링 부트가 자동으로 home으로 인식한다.

preference

- Build, Execution, Deployment

  - builld Tools
    - Build and run using, Run tests using을 IntelliJ IDEA로 변경
    - 그래들 빌드 시간 축소

- lombok plugin 설치 후 annotation processors 설정
  ![](/image/2022-01-02-15-43-56.png)

### 타임리프 특징

- 내츄럴 템플릿
- 타임리프는 순수 HTML을 최대한 유지하는 특징이 있다.
- 타임리프로 작성한 파일은 html을 유지해서 브라우저에서 바로 열린다. 서버를 통해 뷰템플릿을 거치면 추가적으로 변경된 데이터가 반영되어 보인다.
- JSP나 나머지들은 그대로 열면 깨짐
- 협업시 유리하다.
- 순수 HTML 유지, 뷰 템플릿 사용할 수 있는 타임리프 특징을 네츄럴 템플릿이라 한다.

- 스프링 통합 굉장히 잘되어 있다.
- 타임리프 사용 선언(사용되는 각 페이지의 상단 html에 선언)
  - <html xmlns:th="http://www.thymeleaf.org">

```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
```

#### 텍스트 - text, utext

```
타임리프는 기본적으로 html attribute를 사용해서 데이터 바인딩한다.

<span th:text="${data}"></span>

컨텐츠 영역 안에서(div와 같은 태그 내부에서) 출력하고싶을 때

<span>[[${data}]]</span>

```

#### escape

"<"">"와 같은 특수문자가 html entity로 바뀌어져서 나오게된다.

```
data="<b>spring</b> awesome";
<span th:text="${data}"></span>
```

기대값은 진한 글씨로 나오는 spring이지만
&lt;b&gt;로 나온다.
이렇게 html entity로의 변경 되는 것이 escape
이러한 변경을 막는것이 unescape이다.

```
data="<b>spring</b> awesome";
<span th:utext="${data}"></span> // u 추가
<span>[(${data})]</span> // 괄호 변경
```

escape를 사용하지 않아서 발생하는 수 많은 문제가 있다. 꼭 필요한 때만 unescape를 사용하자.

#### 변수 - springEL

```
<ul>Object
    <li>${user.username} =    <span th:text="${user.username}"></span></li>
    <li>${user['username']} = <span th:text="${user['username']}"></span></li>
    <li>${user.getUsername()} = <span th:text="${user.getUsername()}"></span></li>
</ul>
<ul>List
    <li>${users[0].username}    = <span th:text="${users[0].username}"></span></li>
    <li>${users[0]['username']} = <span th:text="${users[0]['username']}"></span></li>
    <li>${users[0].getUsername()} = <span th:text="${users[0].getUsername()}"></span></li>
</ul>
<ul>Map
    <li>${userMap['userA'].username} =  <span th:text="${userMap['userA'].username}"></span></li>
    <li>${userMap['userA']['username']} = <span th:text="${userMap['userA']['username']}"></span></li>
    <li>${userMap['userA'].getUsername()} = <span th:text="${userMap['userA'].getUsername()}"></span></li>

</ul>
```

##### 지역변수 선언

```
<h1>지역 변수 - (th:with)</h1>
<div th:with="first=${users[0]}"> // th:with로 지역변수 선언, 선언한 태그 내부에서만 사용가능하다.
    <p>처음 사람의 이름은 <span th:text="${first.username}"></span></p>
</div>
```

#### 기본 객체들

```
${#request}
${#response}
${#session}
${#servletContext}
${#locale}
```

- httpRequest는 요청이 끝나면 사라지지만 session은 브라우저 종료 전까지 남아있다.

#### 타임리프 유틸리티 객체

타임리프 유틸리티 객체
https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#expression-utility- objects

유틸리티 객체 예시
https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#appendix-b-expression- utility-objects

```
자바8 날짜
타임리프에서 자바8 날짜인 LocalDate , LocalDateTime , Instant 를 사용하려면 추가 라이브러리가 필요하다. 스프링 부트 타임리프를 사용하면 해당 라이브러리가 자동으로 추가되고 통합된다.
타임리프 자바8 날짜 지원 라이브러리 thymeleaf-extras-java8time
자바8 날짜용 유틸리티 객체 #temporals
<span th:text="${#temporals.forma(localDateTime, 'yyyy-MM-dd HH:mm:ss')}"></ span>
```

#### URL 링크

```
<a>th:href=@{/hello}</a>
@{/hello(param1=${param1}, param2=${param2})}
@{/hello/{param1}/{param2}(param1=${param1}, param2=${param2})}

```

#### 리터럴

리터럴은 소스 코드 상에 고정된 값을 말하는 용어.

타임리프에서 문자 리터럴은 항상 '(작은 따옴표)로 감싸야 한다.

`<span th:text="'hello'"></span>`

하지만 ''으로 항상 감싸는건 귀찮다.
그래서 공백없이 쭉 이어진다면 하나의 의미있는 토큰(리터럴)로 인지한다.
`<span th:text="hello"></span>` //이때 ''생략

오류
`<span th:text="hello world!"></span>` // 중간에 빈칸 있으니 오류 발생

`<span th:text="'hello world!'"></span>` // 작은 따옴표로 감싸서 오류 해결

```
<span th:text="'hello' + ' world!@'"></span>
<span th:text="'hello' + ${data}"></span>
<span th:text="|hello ${data}|"></span>
```

### 스프링 메시지

#### 스프링 mvc

messageSource.setBasenames("message", "error")
// 설정 파일의 이름을 지정한다.
// message.properties, error.properties를 읽는다.
// 국제화 기능은 message_en.properties 와 같이 이름 마지막에 언어 정보를 준다. 국제화 파일이 없으면 message.properties를 기본으로 사용
// 파일은 /resources/messages.properties에 위치
// 여러 파일 한번에 지정 가능

messageSource.defaultEncoding("utf-8");
// 보통 utf-8 사용

#### 스프링 부트

스프링 부트가 자동으로 MessageSource를 스프링 빈으로 등록한다

application.properties에
spring.messages.basename=message,config.i18n.messages
// resources 밑에 message 파일
// resources 밑에 config 밑에 i18n 밑에 messages 파일

messagesource를 스프링 빈으로 등록하지 않고, 스프링 부트와 관련된 별도 설정안하면 messages라는 이름으로 기본 등록(생략해도 기본으로 만듬)
이때 기본값은 spring.messages.basename=messages
국제화를 원하면 messages_kr, _en 등 파일만 만들어주면 된다.

#### 스프링의 국제화 메시지 선택

기본적으로 스프링은 Accept-Language를 활용하는 AcceptHeaderLocaleResover를 사용한다.

하지만 Locale 선택 방식을 변경할 수 있도록 LocaleResolver라는 인터페이스를 제공하므로
구현체를 변경할 수 도 있다.


### 검증

컨트롤러의 중요 역할 중 하나는 HTTP 요청이 정상인지 검증하는 것.

Safe Navigation Operator(js에서는 optional chaining)


```
<div th:if="${errors?.containsKey('globalError')}">
  <p class="field-error" th:text="${errors['globalError']}">전체 오류 메시지</p>
</div>
```

위와 같은 상황에서 Errors가 null로 들어왔을 때 null.containsKey를 사용하면 바로 npe가 터진다. 이같은 부분에 대한 대비로 springEL에서 null validation을 위한 ?. 연산자를 제공한다.

### BindingResult

* cmd + p를 통하면 필요한 인자 확인 가능

```
public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult,
RedirectAttributes redirectAttributes) {
```

위와 같은 상황에서 BindingResult는 항상 ModelAttribute 후의 인자로 와야 한다.

BindingResult의 addError 메서드의 인자는 ObjectError를 받게 되어 있다.
FieldError는 ObjectError의 자식이다.

타임리프에서는 #fields.globalError 등 여러 전역 객체에 접근이 가능하다.
th:errors 해당 필드에 오류가 있는 경우에 태그를 출력한다. th:if와 비슷하지만 편의기능.

### BindingResult2

BindingResult가 있으면 @ModelAttribute에 데이터 바인딩 시 오류가 발생해도 일단 컨트롤러가 호출된다.

```
BindingResult 가 없으면 400 오류가 발생하면서 컨트롤러가 호출되지 않고, 오류 페이지로
이동한다.
BindingResult 가 있으면 오류 정보( FieldError )를 BindingResult 에 담아서 컨트롤러를
정상 호출한다.
```

```
BindingResult에 검증 오류를 적용하는 3가지 방법
@ModelAttribute 의 객체에 타입 오류 등으로 바인딩이 실패하는 경우 스프링이 FieldError 생성해서
BindingResult 에 넣어준다.
개발자가 직접 넣어준다.
Validator 사용 이것은 뒤에서 설명

BindingResult 는 검증할 대상 바로 다음에 와야한다. 순서가 중요하다. 예를 들어서 @ModelAttribute Item item , 바로 다음에 BindingResult 가 와야 한다.
BindingResult 는 Model에 자동으로 포함된다.
```

BindingResult는 인터페이스이고 Error 인터페이스를 상속받는다. 하여 인자의 타입을 Error로 받을 수 도 있따. 하지만 이는 단순 오류 저장, 조회 기능만 제공하고 BindingResult는 이에 더해 추가적인 기능을 제공해서 사용이 더 용이하다.

### FieldError, ObjectError

#### FieldError 생성자

```
public FieldError(String objectName, String field, String defaultMessage);

public FieldError(String objectName, String field, @Nullable Object rejectedValue, boolean bindingFailure, @Nullable String[] codes, @Nullable Object[] arguments, @Nullable String defaultMessage)

파라미터 목록
objectName : 오류가 발생한 객체 이름
field : 오류 필드
rejectedValue : 사용자가 입력한 값(거절된 값)
bindingFailure : 타입 오류 같은 바인딩 실패인지, 검증 실패인지 구분 값
codes : 메시지 코드
arguments : 메시지에서 사용하는 인자
defaultMessage : 기본 오류 메시지
```

#### 오류 발생 시 사용자 입력 값 유지

```
new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1,000 ~
1,000,000 까지 허용합니다.")
```
사용자의 입력 데이터가 @ModelAttribute에 바인딩 되는 시점에 오류가 발생하면, 예를들어 다른 타입의 값이 들어오면 모델 객체에 사용자 입력 값을 유지하기 어렵다. 이를 위해 FieldError의 세번째 인자로 해당 값을 기억하게 한 뒤에 이를 활용한다.

```
th:field="*{price}"
타임리프의 th:field 는 일반적으로 모델 객체의 값을 사용하지만, 오류가 발생하면 FieldError 에서 보관한 값을 사용해서 값을 출력한다.
```

### 오류코드와 메시지 처리1

FieldError , ObjectError 의 생성자는 errorCode , arguments 를 제공하므로 messageSource에서 해당 인자를 사용할 수 있다.

#### errors 메시지 파일 생성

messages.properties를 그냥 사용보단 error.properties로 따로 관리하는게 좋다.

```
// application.properties
spring.messages.basename=messages,errors 

// 두개의 파일을인식한다. 아예 없으면 messages를 기본값으로 인식
// errors_en.properties 파일은 에러 국제화 파일로도 가능
```

```
bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));

codes : required.item.itemName 를 사용해서 메시지 코드를 지정한다. 메시지 코드는 하나가 아니라 배열로 여러 값을 전달할 수 있는데, 순서대로 매칭해서 처음 매칭되는 메시지가 사용된다.
arguments : Object[]{1000, 1000000} 를 사용해서 코드의 {0} , {1} 로 치환할 값을 전달한다.
```

#### rejectValue

rejectValue를 활용하여 addError의 인자로 넣는 수많은 리터럴들을 대체할 수 있다.

```
void rejectValue(@Nullable String field, String errorCode, @Nullable Object[] errorArgs, @Nullable String defaultMessage);

field : 오류 필드명
errorCode : 오류 코드(이 오류 코드는 메시지에 등록된 코드가 아니다. 뒤에서 설명할
messageResolver를 위한 오류 코드이다.)
errorArgs : 오류 메시지에서 {0} 을 치환하기 위한 값
defaultMessage : 오류 메시지를 찾을 수 없을 때 사용하는 기본 메시지

bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null)
```

* iter + TAB => 상단 배열에 대한 for문 자동 생성
* soutv + TAB => system.out.println 생성


reject()와 rejectValue()의 내부는 messageCodesResolver를 사용한다.

### 오류코드 관리전략

핵심은 구체적인 것에서 덜 구체적은것으로 메시지를 짜는게 좋다.




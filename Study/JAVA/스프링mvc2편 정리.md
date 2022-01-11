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

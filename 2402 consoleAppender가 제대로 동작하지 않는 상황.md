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

# Issue: consoleAppender가 제대로 동작하지 않는 상황

## 상황:
거의 같은 dependency 세팅을 가지고 있는 다른 프로젝트는 catalina.out으로 로그가 쌓이는데 다른 프로젝트는 안 쌓이고 있는 상황.
로컬에서는 로그가 잘 보임.

## 생각해낸 방안:

- multi module project라서 필요한 클래스가 제대로 Import 되지 않나?
- slf4j에서 뭔가 다른 설정이 있나?

## 방안: multi module project라서 필요한 클래스가 제대로 Import 되지 않나?

<br/>

실제로 consoleAppender의 필터링 클래스가 존재하지 않아서 실 사용중인 메인 모듈로 옮겼으나 되지 않음.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: slf4j에서 뭔가 다른 설정이 있나?

<br/>

```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/~~~~/webapps/f/WEB-INF/lib/slf4j-log4j12-1.6.6.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/~~~~/webapps/f/WEB-INF/lib/logback-classic-1.1.11.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]

SLF4J API is designed to bind with one and only one underlying logging framework at a time. If more than one binding is present on the class path, SLF4J will emit a warning, listing the location of those bindings.
```

SLF4J: Class path contains multiple SLF4J bindings.

해당 로그가 계속 떴는데 warn이라서 크게 의미를 안두고 있었다.
근데 혹시나해서 찬찬히 확인해보니 slf4j는 한가지 프레임워크만 바인딩할 수 있고 실제 바인딩 된건 log-back이 아니었다.

로컬에서는 아래로 바인딩이 됐고
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]

리모트 서버에서는 
SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]

이렇게 바인딩이 돼서 원하는 결과가 나오지 않고 있던 상황. 명령어를 역시 잘 봐야한다.

그래서 일단 사용하지 않는 slf4j-log4j12-1.6.6.jar를 dependency로 가지고 있는 lib들을 확인 후 exclusion, 혹은 사용하지 않는다면 제거했다.
아래 명령어도 가능한데 slf4j-log4j12가 얼마나 중첩된 dep인지까지 확인이 안돼서 mvn dependency:tree로 트리 전체에서 문자열 검색을 해도 되고 grep에서 해당 라인 주위를 보는 옵션을 넣어줘도 된다.

mvn dependency:tree | grep slf4j-log4j12

<br/>
<br/>
<br/>

        참조:
        https://www.slf4j.org/codes.html#multiple_bindings

<br/>

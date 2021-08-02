# Java 버전과 기능에 대한 가이드

https://dzone.com/articles/a-guide-to-java-versions-and-features 해당 글 번역, 정리

## What Java Version Should I Use?

- 특별한 제약이 없다면 Java 11(LTS)이나 Java 13을 써라.
- Android 개발자라면 Java 8 기능을 일부 사용할 수 있는 Java 7 환경에서 개발하거나, 아니면 Kotlin으로 개발해라.

## Why Are Companies Still Stuck on Java 8?

- maven, gradle 같은 빌드 툴이나 몇몇의 라이브러리들은 8버전 이상에서는 버그들이 있었고 이를 위한 업데이트가 필요했다. 지금까지도 특정 빌드 툴에서는 에러에 대한 경고를 띄워주기도 한다. 이는 사용자에게 해당 버전이 적합하지 않다는 느낌을 주게 되므로 높은 버전의 사용을 못하고 있다.


## Why Are Some Java Versions Called 1.X?

Java 8까지는 Java 1.8 같은 방식으로 부르기도 했지만, Java 9 이후로 Java 1.9 같은 표현은 없다.

## What Is the Difference Between Java Versions? Should I Learn a Specific One?

Java는 이전 버전과 매우 호환된다.
5~8 버전은 8~13 버전의 jvm에서 원활히 실행됨을 보증한다.


## What Is the Difference Between a JRE and a JDK?

자바 런타임 환경 (JRE)

JRE (Java Runtime Environment)는 Java 프로그래밍 언어로 작성된 애플릿 및 응용 프로그램을 실행하기위한 라이브러리, Java Virtual Machine 및 기타 구성 요소를 제공한다. 또한 두 가지 주요 배포 기술이 JRE에 포함되어 있다. Java Plug-in : 애플릿을 널리 사용하는 브라우저에서 실행할 수 있다. Java Web Start는 네트워크를 통해 독립형 응용 프로그램을 배포한다. 또한 엔터프라이즈 소프트웨어 개발 및 배포를위한 J2EE (Java 2 Platform, Enterprise Edition) 기술의 기반이기도 하다. JRE에는 애플릿 및 응용 프로그램 개발을위한 컴파일러 또는 디버거와 같은 도구 및 유틸리티가 포함되어 있지 않다.

JDK (Java 개발 키트)

JDK는 JRE의 상위 집합이며 JRE에있는 모든 항목과 애플릿 및 응용 프로그램 개발에 필요한 컴파일러 및 디버거와 같은 도구를 포함한다.

<img src="https://i.stack.imgur.com/AaveN.png">

## Java Distributions

Java 8부터 JDK 소스 코드는 실질적으로 OpenJDK 프로젝트 버전 한 벌만 존재한다.

이걸 빌드해서 Java SE 인증을 받고 자기네 브랜드를 붙여서 배포하는 벤더가 여럿 있다. 기능을 유틸리티를 추가하기도 한다.

Oracle이 빌드한 OpenJDK 빌드는 무료다. 하지만 이전 버전의 업데이트를 제공하지 않는다(14가 나오는 순간부터 13 빌드는 업데이트 안 함.)

OracleJDK는 장기적인 지원을 받을 수 있는 상용 빌드다. 개발용으로는 무료로 사용할 수 있다.

Amazon, MS, Redhat 등이 모인 단체에서 장기적인 업데이트까지 무료로 제공하는 빌드가 AdoptOpenJDK다.

OS 벤더가 제공하는 배포판을 써야 하는 상황이 아니라면 AdoptOpenJDK가 답이다.


## Java Features 8–13

### Java 8
    람다
    Stream API
    Method reference, repeating annotation, 인터페이스 default method

### Java 9
    List.of(), Set.of(), Map.of()
    Stream 메소드 추가: takeWhile(), dropWhile(), iterate(), …
    Optional#ifPresentOrElse()
    private 인터페이스 메소드
    try-with-resources 문 개선
    익명 클래스에 대한 다이아몬드 연산자
    JShell
    Jigsaw 모듈 시스템, 다중 릴리즈 JAR 파일

### Java 10
    로컬 변수 타입 추론: var 키워드

### Java 11
String 메소드 추가: isBlank(), lines(), strip(), …
File 메소드 추가: writeString(), readString(), …
java 명령으로 소스 파일 바로 실행
람다 파라미터에 대한 변수 타입 추론
정식 HttpClient
…

### Java 12
유니코드 11

### Java 13
새로운 스위치문(Java 12 이후 여전히 preview 기능)
멀티라인 문자열(preview 기능)

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

# Issue:

## 상황:

<br/>

## 알게된 부분 정리:

- +

<br/>

## 개념:

<br/>
  
  ```
    return wows
        .flatMap(wows -> Mono.just(wows.stream()
                .collect(toMap(wow -> wow.getWowNo().toString(), Function.identity()))));
  ```

  이런 코드에서 wow -> wow.getWowNo() 함수를 적용하려고 할 때 wow에 mouseOver를 해보면 type이 Object라고 뜨고 Cannot resolve method 'getWowNo' in 'Object' 라는 에러가 발생한다.

  이때 그래서 람다의 인자를 (Wow wow) -> wow.getWowNo()를 하게 되면 컴파일 에러가 사라지긴하는데..

  이거 다시 보니까 해당 코드에서 Mono가 리턴되는데 리턴 값을 Mono로 지정하지 않았다면. 그러니까

  리턴 타입을 Mono<리턴하려는 실제 참조값> 이 아닌 리턴하려는 실제 참조값으로 해놨다면
  이런 컴파일 에러가 발생한다. Mono로 리턴 타입을 바꾸면 컴파일 에러 발생하지 않음.
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

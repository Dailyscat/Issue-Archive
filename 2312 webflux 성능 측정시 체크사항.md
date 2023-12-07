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

# Issue: webflux 성능 측정 시 체크사항

## 상황:

<br/>

## 알게된 부분 정리:

- webflux 성능 측정 시 체크사항

<br/>

## 개념:

<br/>
log() 메서드 사용시 실제로 blocking I/O를 일으키기 때문에, 성능 저하가 쉽게 발생

map은 동기식 함수를 적용하여 Mono의 아이템을 변경하는데 사용하는 메서드, flatmap은 Async하게 Mono의 아이템을 변경하는데 사용하는 것. 즉, 비동기 처리 부분은 flatmap으로 동기처리 부분은 map 함수를 사용 그리고 너무 많은 map 함수의 조합은 연산마다 객체를 생성하므로 GC의 대상이 많아지는 단점도 존재

Reactive Redis는 Non-blocking I/O에 비동기로 동작해야 하는데 Non-blocking I/O는 Netty가 처리해주지만, 비동기로 동작해야 되는 부분은 map 메서드에 의해서 동기식으로 동작하기때문에 flatmap() 메서드로 실행되야함.

BlockHound는 blocking 코드를 찾아 주는 라이브러리 Reactor-core 3.3부터 내장되어 있고 blocking으로 가장 대표적인 메서드는 block, blockFirst, blockLast같은 메서드들이 있다. BlockHound는 이렇게 런타임에서 확인할 수도 있지만, 보통은 테스트 케이스를 작성할 때 사용하고, 테스트케이스 대상 코드에 blocking 코드가 안에 있는 지, 없는지 확인하는데 매우 유용

<br/>
<br/>
<br/>

        참조:
        https://www.youtube.com/watch?v=I0zMm6wIbRI

<br/>

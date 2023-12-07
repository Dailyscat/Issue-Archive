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

# Issue: reactor에서 Publishon, subscribeOn의 활용 되는 사례

## 상황:
데이터 소스를 발행할때와 구독할 때 어떨 때 다른 쓰레드로 처리하는게 각각 유용한지에 대해 토의 중 정리

<br/>

## 알게된 부분 정리:

- 외부 처리와 내부처리에 따라 다름.

<br/>

## 개념: 외부 처리와 내부처리에 따라 다름.

<br/>

publishOn()은 빠른 publisher와 느린 subscriber로 구성된 chain에 사용하고, subscribeOn()은 반대로 느린 publisher와 빠른 subscriber로 구성된 chain에 사용하는 게 적절함

일반적으로 내부 서비스 로직이 좀 더 빠르고 외부 의존성은 더 느리거나 IO blocking이 발생할 수 있음. 외부 의존성(웹서비스나 DB서버)에서 데이터를 읽어오거나 쓰는 경우에는 모두 별도의 스케쥴링을 적용하는 게 바람직함. 그렇지 않으면 main 쓰레드가 block되어 전체 서비스가 멈추기 때문임.

외부 의존성에 쓰는 경우에는 외부 의존성에 가까운 subscriber쪽 chain을 별도의 스케쥴링으로 관리할 수 있도록 분리하는 게 필요함. 여기에 적합한 것이 publishOn()

![정리](./image/2023-12-07-17-02-29.png)
<br/>
<br/>
<br/>

        참조:
        https://wiki.terzeron.com/Programming/Java/Reactor_Flux%EC%9D%98_publishOn_subscribeOn%EC%9D%84_%EC%9D%B4%EC%9A%A9%ED%95%9C_%EC%8A%A4%EC%BC%80%EC%A5%B4%EB%A7%81

<br/>


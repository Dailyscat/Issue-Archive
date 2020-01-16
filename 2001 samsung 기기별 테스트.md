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

# Issue: intent scheme을 활용하는 과정에서 간헐적으로 samsung 기기에서 문제가 생김

## 상황:

intent scheme을 활용하는 과정에서 간헐적으로 samsung 기기에서 문제가 생김 이를 위해 삼성 developer 사이트에서 기기를 원격으로 테스트

<br/>

## 생각해낸 방안:

- 원격으로 삼성 기기를 테스트 해볼 수 있는 사이트를 활용하여 intent 테스트

<br/>

## 방안: (성공)

<br/>

[삼성 디벨로퍼 사이트](https://developer.samsung.com/remotetestlab/rtlDeviceList.action?os=101)를 활용하여 galaxy5 부터 intent가 제대로 작동하는지를 확인할 수 있었다.

(1) java runtime이 실행될 수 있는 환경이어야 한다.
(2) ubuntu 라면 icedtea-netx 패키지를 활용하여 jlpt 파일을 실행시켜야 한다.

<br/>
<br/>
<br/>

        참조:
        https://askubuntu.com/questions/565016/how-to-run-a-jnlp-file-in-ubuntu-server

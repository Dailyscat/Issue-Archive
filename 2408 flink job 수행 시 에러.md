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

# Issue: flink job 수행 시 에러

## 상황: 
Caused by: org.apache.flink.shaded.netty4.io.netty.channel.AbstractChannel$AnnotatedConnectException: Connection refused: localhost/127.0.0.1:8081
Caused by: java.net.ConnectException: Connection refused
        at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)
        at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:716)
        at 

## 생각해낸 방안:

- flink cluster를 시작 후에 flink run 명령어 수행

## 방안: flink cluster를 시작 후에 flink run 명령어 수행

<br/>

flink/bin/start-cluster.sh 실행하여 flink cluster 실행.

<br/>
<br/>
<br/>

        참조:

<br/>

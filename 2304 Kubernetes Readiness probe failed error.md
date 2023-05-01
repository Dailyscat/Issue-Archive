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

# Issue: Kubernetes Readiness probe failed error

## 상황:Kubernetes Readiness probe failed error

<br/>

## 알게된 부분 정리:

- initialDelaySeconds 조절

<br/>

## 개념: initialDelaySeconds 조절

<br/>
  For anyone else here, if using helm to manage your deployments, you need to set initialDelaySeconds it in the deployments.yaml template in the /templates folder under livenessProbe.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/48540929/kubernetes-readiness-probe-failed-error

<br/>
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

# Issue: argoWf에서 배치 삭제시 나머지 리소스들은 수동으로 지워줘야함

## 상황:

Kubernetes 환경에서 ArgoCD를 통해 배포된 shopping-workflow-app-\*의 Batch를 제거하려고 한다. 단순히 설정 파일에서 삭제한다고 해서 리소스가 자동으로 제거되지 않으며, 이를 방치하면 의도치 않은 동작을 유발할 수 있다.
<br/>

## 알게된 부분 정리:

- 설정 파일에서 Batch를 제거해도, 이미 배포된 Kubernetes 리소스들은 남아있다.
  남아 있는 리소스들은 의도치 않은 동작을 유발할 수 있으며, Kubernetes 리소스를 낭비할 수 있다.

<br/>

## 개념:

<br/>
  ```
  kubectl delete cronworkflow/${applicationName}
kubectl delete secret/${applicationName}-secret
kubectl delete workflowtemplate/${applicationName}
kubectl delete workfloweventbinding/${applicationName}-event-consumer
kubectl delete cm/${applicationName}-config
  ```
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

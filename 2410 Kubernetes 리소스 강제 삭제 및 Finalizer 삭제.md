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

# Issue: Kubernetes 리소스 강제 삭제 및 Finalizer 삭제

## 상황:

Kubernetes에서 kubectl delete 명령어로 리소스를 삭제할 때, 리소스가 정상적으로 삭제되지 않거나 강제 삭제가 필요한 경우가 있다. 또한, 강제 삭제가 되지 않을 때는 finalizer를 수동으로 제거해야 할 때도 있다.
<br/>

## 알게된 부분 정리:

- 강제 삭제: kubectl delete의 --force 옵션을 사용하여 리소스를 강제 삭제할 수 있다.
- Finalizer: 리소스 삭제를 막고 특정 작업을 수행하기 위해 설정된 메타데이터로, 삭제가 되지 않을 때 이를 제거해야 할 수 있다.

<br/>
 
## 개념: 강제 삭제

<br/>
kubectl delete <resource_type>/<resource_name> --force --grace-period=0
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: Finalizer 삭제

<br/>
리소스 편집: kubectl edit 명령어를 사용하여 리소스를 편집한다.

kubectl edit <resource_type>/<resource_name>

Finalizer 제거: 편집 모드에서 metadata.finalizers 부분을 찾아 해당 항목을 삭제한다.

저장 및 종료: 편집을 완료하고 파일을 저장하여 종료한다.
<br/>
<br/>
<br/>

        참조:

<br/>

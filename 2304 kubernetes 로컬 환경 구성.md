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

# Issue: kubernetes 로컬 환경 구성

## 상황:

<br/>

## 알게된 부분 정리:

- +

<br/>

## 개념:

<br/>
  kubectl 설치
    brew install kubectl

  minikube 설치
    https://kubernetes.io/ko/docs/tutorials/hello-minikube/

	특정 터미널에서만 환경변수 설정
		export TEST_WOW=abc

	kube 클러스터 확인.
		kubectl config get-contexts
	원하는 kube 클러스터 사용.
		kubectl config use-context minikube

	minikube 클러스터 사용
		minikube start
		(optional) minikube start --memory=16384 --cpus=4 --kubernetes-version=v1.20.2 https://stackoverflow.com/questions/46234525/minikube-kubectl-connection-refused-did-you-specify-the-right-host-or-port

	helm template 커맨드로 template 생성
	helm template abc -f test.yaml -f wow.yaml -n 네임스페이스 > 생성 원하는 파일 이름.yaml

	minikube 구동 중 아래 에러 발생했을 때 
	Minikube: kubectl connection refused - did you specify the right host or port?
		minikube stop 
	만약 이대로도 해결 안되면
		minikube delete && minikube start 

	helm을 통해 만들어진 template에 맞는 namespace 생성
		kubectl create namespace test_wow

	istio를 사용한다면 아래 주소 확인하여 prerequisites 부터 시작
	https://istio.io/latest/docs/setup/install/helm/#prerequisites
	이때 helm install istiod istio/istiod -n istio-system --wait 해당 명령어가 아래 주소와 같은 에러를 발생시킬 수 있음
	https://github.com/istio/istio/issues/22677
	https://github.com/helm/helm/issues/3173
	https://stackoverflow.com/questions/51975069/helm-test-failure-timed-out-waiting-for-the-condition
	https://github.com/helm/helm/issues/3134
	https://github.com/istio/istio/issues/22677
	딱히 문제가 발생하진 않으니 skip해도 됨.

	template이 nodeSelector: type: "test" 를 쓴다면 클러스터에 node를 추가해줘야한다.
		kubectl get nodes --show-labels
		minikube node add test
		kubectl label nodes node1 type=test

	helm을 통해 만들어진 template을 사용해서 cluster에 배포
		kubectl apply -f ./~~.yaml

	kubectl 명령어를 통해 test_wow 네임 스페이스에 속한 모든 리소스 출력하여 pod 상태 확인
		kubectl get all -n test_wow

	이때 kubectl get all과의 차이
	-> get all은 해당 클러스터 전체에서의 리소스를 출력

	pending 상태가 지속되거나 에러가 발생하면 logs, describe 명령 통해 확인
		kubectl logs -n test_wow test_wow-v1-68fcf67c8b-jcrfg
		kubectl describe pod -n test_wow test_wow-v1-68fcf67c8b-jcrfg

	에러 해결 후 pod만 지워보거나 안되면 namespace 전체 삭제
		kubectl delete pod -n test_wow test_wow-v1-68fcf67c8b-jcrfg
		kubectl delete namespace test_wow

	로컬 브라우저에서 해당 클러스터에서 배포한 이미지가 잘 구동되었는지 확인하려면
		kubectl port-forward -n test_wow deployment/test_wow-v1 3000:3000
		https://stackoverflow.com/questions/75682427/probes-in-k8s-failed-with-404

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

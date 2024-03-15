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

# Issue: kube에서 외부 ip로 라우팅

## 상황:
kube 내부 resource에서 특정 user-agent에 한해서 외부 ip로 라우팅 필요한 상황

<br/>

## 알게된 부분 정리:

- dns에서부터 kube에서의 트래픽 라우팅 흐름
- 외부 라우팅을 위한 istio resource 적용
- serviceEntry의 addresses와 endpoints의 address 차이

<br/>

## 개념: dns에서부터 kube에서의 트래픽 라우팅 흐름

<br/>
  dns -> istio ingress gateway -> virtualService -> destinationRule -> service


  ```
    Istio Ingress Gateway:

  외부 클라이언트로부터 들어오는 트래픽은 먼저 Istio의 Ingress Gateway를 통과합니다. 이는 클라우드 프로바이더의 로드밸런서나 NodePort, LoadBalancer 서비스 타입을 사용하여 구성됩니다.
  Gateway는 외부 요청을 클러스터 내부의 서비스로 라우팅하기 위한 진입점 역할을 합니다.
  VirtualService:

  VirtualService는 특정 호스트에 대한 트래픽을 어떻게 라우팅할지 정의합니다. 특정 경로, 헤더, 요청 속성에 따라 다른 라우팅 규칙을 지정할 수 있습니다.
  예를 들어, 특정 사용자 에이전트에 따라 모바일 클라이언트용과 PC 클라이언트용 경로를 다르게 설정할 수 있습니다.
  VirtualService는 Istio의 Ingress Gateway와 연결되어 외부에서 들어오는 요청을 처리합니다.
  DestinationRule:

  DestinationRule은 특정 서비스 대상(host)에 대한 트래픽 정책을 정의합니다. 이 정책은 로드 밸런싱, 연결 풀링, 장애 조치(fault injection), TLS 설정과 같은 세부적인 트래픽 관리 기능을 포함할 수 있습니다.
  DestinationRule은 특정 서비스의 버전(subset)을 구분하고, 각 subset에 대한 트래픽 정책을 적용할 수 있습니다.
  Service (쿠버네티스 Service):

  쿠버네티스의 Service는 파드의 논리적인 집합에 대한 엔드포인트를 제공하고, 내부적인 DNS 엔트리를 통해 서비스 디스커버리를 가능하게 합니다.
  Service는 파드 셀렉터를 사용하여 특정 레이블을 가진 파드 그룹을 자동으로 선택하고, 이 파드들에 트래픽을 로드 밸런싱합니다.
  Service는 파드를 대표하는 안정적인 IP 주소와 포트를 제공하며, 파드가 죽고 새로 생성되더라도 서비스의 IP 주소와 포트는 변경되지 않습니다.
  ```
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: 외부 라우팅을 위한 istio resource 적용

<br/>

```
apiVersion: networking.istio.io/v1beta1
kind: VirtualService
metadata:
  name: "이름"
  namespace: 네임스페이스
spec:
  gateways:
    - "istio-system/istio-ingressgateway"
    - mesh
  hosts:
    - "원하는도메인"
    - 서비스네임.svc.cluster.local
  http:
  - name: "mobile"
    match:
    - headers:
        user-agent:
          regex: ".*Mobile.*"
    retries:
      attempts: 3
      perTryTimeout: "30s"
      retryOn: "gateway-error,connect-failure,refused-stream"
    route:
      - destination:
          host: 서비스네임.svc.cluster.local
          subset: "alpha-v1"
    timeout: "30s"
  - name: "pc"
    retries:
      attempts: 3
      perTryTimeout: "30s"
      retryOn: "gateway-error,connect-failure,refused-stream"
    route:
      - destination:
          host: serviceEntry에 명명한 외부 호스트 이름
    timeout: "30s"

apiVersion: networking.istio.io/v1beta1
kind: ServiceEntry
metadata:
  name: "서비스이름"
  namespace: istio-system
spec:
  hosts:
    - 외부 ip에 사용할 호스트 이름을 명명
  addresses:
    - 10.11.111.111 # 외부 서비스의 실제 IP 주소
  ports:
  - number: 80
    name: http
    protocol: HTTP
  - number: 443
    name: https
    protocol: HTTPS
  location: MESH_EXTERNAL
  resolution: STATIC
  endpoints:
  - address: 10.11.111.111
```



<br/>
<br/>
<br/>

        참조:
        https://kubernetes.io/docs/concepts/services-networking/service/
        https://github.com/google/re2/wiki/Syntax
        https://github.com/istio/istio/discussions/48405
        https://istio.io/latest/docs/reference/config/networking/virtual-service/#Headers
        https://istio.io/latest/docs/reference/config/security/conditions/
        https://github.com/istio/istio/issues/1246
        https://github.com/istio/istio/pull/10671
        https://stackoverflow.com/questions/11381673/detecting-a-mobile-browser

<br/>

## 개념: serviceEntry의 addresses와 endpoints의 address 차이

<br/>

Istio의 ServiceEntry 리소스에서 addresses와 endpoints의 address는 서로 관련된 항목이지만, 각각 다른 용도로 사용됩니다.

addresses: addresses 필드는 ServiceEntry가 적용될 IP 주소의 범위를 정의합니다. 이는 일반적으로 외부 서비스의 CIDR 블록을 나타내거나, 특정 IP 주소를 지정할 때 사용됩니다. 이는 매우 유용한 설정으로, Istio가 이 IP 범위로 나가는 트래픽을 인식하고 관리할 수 있게 합니다. 예를 들어, CIDR 블록을 사용하면 해당 블록 내의 모든 IP 주소를 외부 서비스로 간주할 수 있습니다.

endpoints의 address: endpoints 섹션의 address 필드는 ServiceEntry에 의해 정의된 서비스의 실제 네트워크 위치를 나타내며, 구체적인 IP 주소나 호스트명을 지정할 수 있습니다. 이는 Envoy 프록시가 트래픽을 실제로 전달해야 하는 목적지를 알려주는 역할을 합니다. endpoints 내에는 해당 서비스에 대한 여러 address 항목을 지정할 수 있으며, 이를 통해 로드 밸런싱과 같은 목적으로 다양한 목적지에 트래픽을 분산시킬 수 있습니다.

addresses에는 넓은 범위의 IP 또는 CIDR 블록을 지정하고, endpoints 내에는 그 범위 내의 특정 IP 주소를 지정할 수 있습니다.

간단히 말해, addresses는 외부 서비스로 간주할 IP 범위를 정의하는 데 사용되며, endpoints의 address는 실제 네트워크 위치를 나타냅니다. 이 구성을 통해 Istio는 해당 서비스로의 트래픽을 어떻게 라우팅할지 결정할 수 있습니다.

<br/>
<br/>
<br/>

        참조:
        https://istio.io/latest/docs/reference/config/networking/service-entry/

<br/>
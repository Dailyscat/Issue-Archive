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

# Issue: mkcert, nginx 활용하여 https로 로컬개발

## 상황:

특정 https 프로토콜로의 요청을 로컬에서 받아서 반환하도록 설정

<br/>

## 알게된 부분 정리:

- nginx를 프록시서버로 활용, mkcert로 해당 요청 url의 secure layer 인증서 확보

<br/>

## 개념: nginx를 프록시서버로 활용, mkcert로 해당 요청 url의 secure layer 인증서 확보

<br/>

```
/etc/hosts
127.0.0.1 원하는 도메인 네임

https://github.com/FiloSottile/mkcert
mkcert 원하는 도메인 네임

// simulator에서 사용하려면
For the certificates to be trusted on mobile devices, you will have to install the root CA. It's the rootCA.pem file in the folder printed by mkcert -CAROOT.

On iOS, you can either use AirDrop, email the CA to yourself, or serve it from an HTTP server. After opening it, you need to install the profile in Settings > Profile Downloaded and then enable full trust in it.

For Android, you will have to install the CA and then enable user roots in the development build of your app. See this StackOverflow answer.

// 인증서가 필요한 위치에서 "mkcert 도메인네임"을 사용하면 된다.
// mkcert -CAROOT 를 통해 루트 위치 찾을 수 있음

/usr/local/etc/nginx/
upstream 사용패스 {
    server 127.0.0.1:8085; // 구동되는 로컬 서버 포트
}

server {
    listen 80;
    listen 443 ssl;
    ssl_certificate   ~.pem;
    ssl_certificate_key ~key.pem;
    server_name  사용 서버 이름;

    location / {
        proxy_pass  http://사용패스/;
        proxy_set_header    Host            $host:$server_port;
        proxy_set_header    X-Real-IP       $remote_addr;
        proxy_set_header    X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header    X-Forwarded-Port $server_port;
    }
}
```

```
접속과정에서 로컬 컴퓨터의 hosts 파일을 항상 가장 먼저 확인합니다. 이 과정을 통해 우선적으로 접속 시킬 것인지 확인하는 과정이지요. 만약 로컬 컴퓨터에 hosts 파일이 없다면 DNS 서버를 검색합니다.

출처: https://studyforus.tistory.com/209 [Study For Us]
```

2022.03.27

키체인 접근 통하여 해당 mac에서 신뢰할 수 있는 인증서로 만들어야함.

1. command + space
2. 키체인 접근 오픈
3. mkcert 검색하여 원하는 인증서 신뢰 탭에서 항상 인증으로 설정

이때 자기 기기에 해당하는 mkcert를 다 만들어줘야한다.
공용파일로 하려고했더니 각 로컬 호스트에 따른 인증서가 만들어짐

<br/>
<br/>
<br/>

        참조:
        https://docs.nginx.com/nginx/admin-guide/web-server/reverse-proxy/
        https://nginx.org/en/docs/ngx_core_module.html
        https://studyforus.tistory.com/209
        https://github.com/JaeYeopHan/tip-archive/issues/33
        https://jgj1018.github.io/server/2017/02/11/server1.html
        https://chanhy63.tistory.com/19

<br/>

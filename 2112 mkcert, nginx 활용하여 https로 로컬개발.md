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

<br/>
<br/>
<br/>

        참조:
        https://studyforus.tistory.com/209

<br/>


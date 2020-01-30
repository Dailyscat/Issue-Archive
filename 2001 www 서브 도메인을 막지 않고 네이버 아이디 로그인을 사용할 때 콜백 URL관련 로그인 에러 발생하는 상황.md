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

# Issue: www 서브 도메인을 막지 않고 네이버 아이디 로그인을 사용할 때 콜백 URL관련 로그인 에러 발생하는 상황

## 상황:

사용자가 네이버 아이디 로그인을 시도했을 때 간헐적으로 Callback URL에 해당하는 팝업이 닫히지 않고 Cross Domain 관련 로그인 에러가 발생하는 상황

<br/>

## 생각해낸 방안:

- kakao 인앱브라우져에서 발생하는 에러를 회피하기 위해 intent를 사용한 부분에서 발생하는 에러인가 하여 회피
- Nginx 설정 중 www.도메인.com으로 들어오는 요청을 redirect

<br/>

## 방안: kakao 인앱브라우져에서 발생하는 에러를 회피하기 위해 intent를 사용한 부분에서 발생하는 에러인가 하여 회피 (실패)

<br/>

kakao 인앱브라우져에서 발생하는 에러를 회피하기 위해 intent scheme을 활용했던 부분이 있었는데, 이때 새창이 열리는 과정에서 생기는 에러인가하여 그 부분을 제거했는데 완벽한 해결책은 아니었다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: Nginx 설정 중 www.도메인.com으로 들어오는 요청을 redirect (성공)

<br/>

    server {
        if ($host = www.igogo.kr) {
            return 301 https://igogo.kr$request_uri;
        } # managed by Certbot


        if ($host = igogo.kr) {
           return 301 https://$host$request_uri;
        } # managed by Certbot


        listen 80;
        server_name igogo.kr www.igogo.kr;
        return 301 https://igogo.kr$request_uri;
    }


    server {
        listen 443;
        server_name igogo.kr www.igogo.kr;


        ssl on;
        // certbot 관련 ssl 로직
    }

위는 원래 설정인데 첫 server단의 조건문에서 host가 www로 올 때와 단순 도메인일 때 redirect가 발생하도록 설정해두었다. 근데 Block의 후반에 보면 80번 포트에서 redirect로 도메인으로 이동하게 해두어서 큰 의미가 없는 조건문이기는 하다. 문제는 위의 80번 포트로 들어오는 요청의 server block의 조건문이 Certbot의 영향으로 인해 거치지 않고 있었다.

그래서 두번째 server block이 의미하는 https에서만 유효한 웹 서버 block만 정상적으로 동작하고 있었다.

두번째 server block은 www 서브도메인에 대해 redirct 해주지 않았다. 이는 네이버 아이디로 로그인에서 오류를 촉발시켰는데, 이유는 네이버 아이디로 로그인에서 사용가능한 도메인은 `www.도메인.com` 이 아닌 단순 `도메인.com`이기 때문에 `www.도메인`이 호스트인 요청에 대해서 네이버 아이디로 로그인 시도는 Naver의 입장에서는 일종의 위반이 발생하는 것이었다. 그래서 Cross Domain 에러가 생겼던 것이었고..

이를 위해 www 호스트를 가진 요청에 대해 redirect를 해야했다.

    server {
        listen 443;
        server_name www.igogo.kr;

        ssl on;
        // certbot 관련 ssl 로직


            # redirects www to non-www.
            return 301 $scheme://igogo.kr$request_uri;

    }

위의 두 server block 중간에 위 코드를 삽입하여 www 도메인 요청에 대해 redirect를 시켜주었고 해결할 수 있었다.

<br/>
<br/>
<br/>

        참조:
        https://www.digitalocean.com/community/questions/configure-nginx-ssl-force-http-to-redirect-to-https-force-www-to-non-www-on-serverpilot-free-plan-using-nginx-configuration-file-only

<br/>

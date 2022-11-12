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

# Issue: nginx 설정 어디에 저장되어있는지 찾을 때

## 상황:
갑자기 오래돼서 기억 안날 때

<br/>

## 알게된 부분 정리:

- +

<br/>

## 개념:

<br/>
  
  ```
    $ nginx -t
    nginx: the configuration file /etc/nginx/nginx.conf syntax is ok
    nginx: configuration file /etc/nginx/nginx.conf test is successful

    $ nginx -V
    nginx version: nginx/1.11.1
    built by gcc 4.9.2 (Debian 4.9.2-10)
    built with OpenSSL 1.0.1k 8 Jan 2015
    TLS SNI support enabled
    configure arguments: --prefix=/etc/nginx --sbin-path=/usr/sbin/nginx --modules-path=/usr/lib/nginx/modules --conf-path=/etc/nginx/nginx.conf ...
  ```

  t 혹은 v 사용하여 확인

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/19910042/locate-the-nginx-conf-file-my-nginx-is-actually-using

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

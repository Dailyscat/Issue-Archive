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

# Issue: let's encrypt 갱신시 "/usr/lib/python3/dist-packages/virtualenv.py", line 2363 에러 shall script로 해결

## 상황:

letsencrypt는 보통 3개월이 무료라 이를 갱신해서 쓰면 영구적으로 사용이 가능하다. 근데 cron이 제대로 작동하지 않는 상황

<br/>

## 생각해낸 방안:

- virtualenv 문제
- locale 변경, 해당 쉘스크립트를 짜서 cron에서 이 스크립트를 실행하도록 설정

<br/>

## 방안: virtualenv 문제 (실패)

<br/>

굉장히 많은 사람들이 겪고 있는 문제다.

간단히 정리해보면 python 2.7에서는 잘 작동하지만 3버전에서는 locale 문제가 발생한다는 얘기다.

이는 virtualenv에서 발생하는데 원래의 system에서 작동하는 python 버전과 certbot이 설치하는 패키지가 의존하고 있는 python 버전이 virtualenv가 실행될 때 트러블이 발생하기 때문에 local error이 발생한다는 얘기.
그래서 나는 그냥 스크립트를 짜기로 했다.

<br/>
<br/>
<br/>

        참조:
        https://github.com/certbot/certbot/issues/2883

<br/>

## 방안: locale 변경, 해당 쉘스크립트를 짜서 cron에서 이 스크립트를 실행하도록 설정 (성공)

<br/>

1. “ssh 계정 ID@내부 IP 주소” 혹은 “ssh 계정 ID@도메인 주소” 를 입력하여 원격 접속한다.
2. vi run.sh

밑의 내용을 작성한다.

      export LC_ALL="en_US.UTF-8"
      export LC_CTYPE="en_US.UTF-8"
      ./letsencrypt/letsencrypt-auto renew --quiet --no-self-upgrade "/usr/sbin/service nginx restart"

3. chmod +x run.sh 명령어로 실행 권한을 부여한다.
4. ./run.sh 명령어로 테스트를 해본다.
5. 0 2,5 1 \* \* /root/run.sh 로 크론 지정을 해준다.

<br/>
<br/>
<br/>

        참조:
        https://www.cyberciti.biz/faq/run-execute-sh-shell-script/
        https://jdm.kr/blog/2
        https://jhnyang.tistory.com/68
        https://cjh5414.github.io/linux-crontab/

<br/>

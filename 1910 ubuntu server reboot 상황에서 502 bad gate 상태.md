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

# Issue: ubuntu server를 reboot 한 상황에서 프로덕트가 뜨지 않고 502 상태

## 상황:

server reboot을 했는데 nginx는 제대로 돌아가고 pm2 instance들도 작동하는 상황에서 프로덕트가 뜨지 않고 502가 발생하는 상황

<br/>

## 생각해낸 방안:

_실패_

- nginx pid 재생성
- 80번 포트를 사용하는 프로세스 kill
- 가상 호스트 재설정
- pm2 인스턴스 재시작, nginx 재시작
- nginx의 버퍼 사이즈 설정
- 기본 포트 80으로 설정, 최신, 옛날 문법 둘 다 적용

_성공_

- mongodb dbpath 재설정, background에서 계속 기동될 수 있도록 설정

<br/>

## 방안: nginx 설정 변경 (실패)

<br/>
  
+ nginx pid 재생성

nginx에서 실행되고 있는 master process를 못찾고 있다는 오류가 발생해서 pid를 삭제하고 nginx를 restart했다.

      https://www.nginx.com/resources/wiki/start/topics/tutorials/gettingstarted/
      http://blog.naver.com/PostView.nhn?blogId=rzip84&logNo=220309412335

- 80번 포트를 사용하는 프로세스 kill

  `fuser -k 80/tcp`

  `fuser -k 443/tcp`

      https://joonas.tistory.com/41
      https://jootc.com/p/201806261346

- 기본 포트 80으로 설정, 최신, 옛날 문법 둘 다 적용

  > server {
  > listen :80;
  > listen [::]:80;
  > }

      https://stackoverflow.com/questions/14972792/nginx-nginx-emerg-bind-to-80-failed-98-address-already-in-use

- 가상 호스트 재설정

  프록시를 사용하여 가상 호스트를 적용 중이 었기 때문에 symlink를 재설정 했다.

      https://stackoverflow.com/questions/40770818/502-gateway-error-with-meteor-browser-policy-http-connecting-to-s3
      https://extrememanual.net/10008#%EA%B0%80%EC%83%81%ED%98%B8%EC%8A%A4%ED%8A%B8_%EB%A3%A8%ED%8A%B8_%EB%94%94%EB%A0%89%ED%86%A0%EB%A6%AC_%EC%83%9D%EC%84%B1%EA%B3%BC_%ED%8D%BC%EB%AF%B8%EC%85%98
      https://twpower.github.io/50-make-nginx-virtual-servers

- pm2 인스턴스 재시작, nginx 재시작

  pm2 list
  pm2 stop
  pm2 restart

      https://sarc.io/index.php/nginx/60-nginx-start-stop-reload-script

* nginx의 버퍼 사이즈 설정

      https://developernote.com/2012/09/how-i-fixed-nginx-502-bad-gateway-error/

<br/>

## 방안: mongodb dbpath 재설정, background에서 계속 기동될 수 있도록 설정(성공)

<br/>

0. NIGNX log를 확인

   1. 우리의 경우는 502이기 때문에 요청자체를 제대로 받지 못하는 상태

1. pm2 자체의 배포 자동화를 사용하고 있었기 때문에 log를 보고 pm2 인스턴스들이 제대로 작동하고 있는지 확인

   1. 우리의 경우는 인스턴스들이 계속 실행이 되려고 1~2초 간격으로 커넥션을 요청했지만 게속 실패하고 있었음

1. pm2 문제가 아닌걸 확인하고 meteor 앱자체가 로컬에서 제대로 동작은 되는지를 확인
   1. 우리의 경우는 제대로 동작 자체가 되지 않았고 여기서 meteor 구성에서 문제가 있다는거를 확인할 수 있었음
1. meteor는 mongo와 react를 동시에 쓰는 framework 이기 때문에 전제는 mongodb server가 로컬에서 열려 있어야 했다.
1. 로컬에서 meteor 앱을 구동시킬 때는 env에 직접 포트와 url을 할당하여 실행했다.

   1. export ROOT_URL=https://igogo.kr
   2. env
   3. export PORT=5000

1. 4번 실행이 안됐고, mongo가 켜있는지를 확인하는 과정에서 mongo가 켜져있지 않을 걸 확인했다.
   1. 우리의 경우는 server가 reboot 되면서 mongo가 열리지 않았기 때문에 서버를 열어야 했고 이에 추가로 터미널을 끄면 몽고 서버도 같이 꺼지기 때문에 background에서도 계속해서 기동되도록 설정해야했다.
      1. set +H
         1. 이 과정은 mongo url 내에 !가 존재했기 때문에 필요했다.
      2. export MONGO_URL=mongodb://
      3. find / -name "mongo\*"
         1. mongodb 명령어를 쳐서 기동시켜야 하는 폴더를 찾아야 했다.
      4. cd /var/lib/mongodb
      5. mongod --dbpath=/var/lib/mongodb
         1. 제대로 된 dbpath를 설정해줬다. 이는 cd + tab 을 통하여 index, collection이 있는지 파악하여 저장된 문서가 있는지를 통해 확인했다.
      6. ps -ef |grep mongod
      7. mongod --dbpath=/var/lib/mongodb --fork --logpath=/var/log/mongodb/
         1. 이를 통해 터미널을 꺼도 몽고디비가 계속 작동하도록 설정했다.
         2. https://blog.canapio.com/31

<br/>

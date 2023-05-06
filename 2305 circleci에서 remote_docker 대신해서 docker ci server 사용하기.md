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

# Issue: circleci에서 remote_docker 대신해서 docker ci server 사용하기.md

## 상황:

<br/>

## 알게된 부분 정리:

- remote docker server 사용하는 yaml 작성

<br/>

## 개념: remote docker server 사용하는 yaml 작성

<br/>
  buildUI:
    docker:
      # the image here need to contain docker cli.
      - image: docker
    environment:
      DOCKER_HOST: "tcp://23.123.123322.123.12.31.121:2376"
      DOCKER_TLS_VERIFY: 1
      # root certificate: the content of `ca-cert.pem`.
      DOCKER_CA_CERT: |
        -----BEGIN CERTIFICATE-----
        -----END CERTIFICATE-----
      # client certificate: the content of `client-cert.pem`.
      DOCKER_CLIENT_CERT: |
        -----BEGIN CERTIFICATE-----
        -----END CERTIFICATE-----
      # client key: the content of `client-key.pem`.
      DOCKER_CLIENT_KEY: |
        -----BEGIN RSA PRIVATE KEY-----
        -----END RSA PRIVATE KEY-----
    steps:
      - checkout
      - run:
          name: Print Environment
          command: printenv
      - run:
          name: restore cert files
          command: |
            mkdir -p ~/.docker
            echo -n "$DOCKER_CA_CERT" > ~/.docker/ca.pem
            echo -n "$DOCKER_CLIENT_CERT" > ~/.docker/cert.pem
            echo -n "$DOCKER_CLIENT_KEY" > ~/.docker/key.pem
      - run:
          name: docker login
          command: docker login "harbor.com" --username "abc" --password "bac"
      - run:
          name: docker build
          command: docker build --build-arg PHASE=a --tag "harbor.com/abc:${CIRCLE_BRANCH}" ./abc
      - run:
          name: docker push
          command: docker push "harbor.com/abc:${CIRCLE_BRANCH}"
      - run:
          name: docker clear
          command: docker system prune -a -f
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: remote docker use in gradle

<br/>
docker {
    url = "tcp://2312312312312:2376"
    certPath = new File(projectDir, 'ci_cert')
    registryCredentials {
        url.set('harbor.com')
        username.set('aaa')
        password.set('aa')
    }
}
task dockerBuildImage(type: DockerBuildImage) {
    inputDir.set(file("."))
    images.add("harbor.com/${imageName}:${imageVersion}")
    buildArgs.put("PHASE", "${imageVersion}")
    noCache.set(true)
}

task dockerPushImage(type: DockerPushImage) {
    dependsOn dockerBuildImage

    images.add("harbor.com/${imageName}:${imageVersion}")
}
<br/>
<br/>
<br/>

        참조:
        https://bmuschko.github.io/gradle-docker-plugin/current/user-guide/#limitations

<br/>

## 개념: docker ci server 구성

<br/>
  https://docs.docker.com/engine/security/protect-access/#use-tls-https-to-protect-the-docker-daemon-socket
  해당 내용 따라가면 ci server에 tcp로 접근할 수 있도록 설정 가능.

  # docker mirror 커스텀 설정할 때

  mkdir -p /etc/docker
  cat <<EOF > /etc/docker/daemon.json
  {
    "registry-mirrors": ["https://docker-hub-mirror.com"]
  }
  EOF

  # docker reload
  kill -SIGHUP "$(pgrep dockerd)"
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: docker ci server clean

<br/>

//docker-prune 생성
cd /etc/cron.daily
sudo nano docker-prune
 
//docker-prune 내용 입력
#!/bin/bash
docker system prune -af  --filter "until=$((30*24))h"
 
//docker-prune 권한 설정
sudo chmod +x /etc/cron.daily/docker-prune
<br/>
<br/>
<br/>

        참조:
        https://confluence.curvc.com/pages/releaseview.action?pageId=109642597

<br/>
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

# Issue: sudoers 파일에 대한 내용과 편집 시 주의할 점 정리

## 상황:

라즈베리 파이 서버에 노트북을 접근하려고 user 이름을 생성하고
권한을 주는 과정에서 sudoers 파일에 대한 편집이 필요했고 그 과정에서 겪은 이슈와 해결과정 정리

<br/>

## 해결 과정:

- 일반사용자 계정생성
- sudoers 파일에 생성한 계정에 권한 설정

<br/>

## 과정1: 일반사용자 계정생성

<br/>

    $ sudo useradd -m -s /bin/bash wow
    $ sudo passwd wow

<br/>
<br/>
<br/>

        참조:

<br/>

## 과정2: sudoers 파일에 생성한 계정에 권한 설정

<br/>

    $ sudo cp /etc/sudoers /etc/sudoers.backup
    $ sudo vi /etc/sudoers

vi 파일 내부:

#This file MUST be ...
<br/>#
<br/>#

.....

wow ALL=(ALL) NOPASSWD: ALL

위 과정이 끝나고

    $ sudo reboot

이때 vim을 사용해 sudoers 파일을 사용할 때 주의 할 점

(1) 유저에 대한 정보를 작성할 때 오타가 나는걸 주의해야한다. 오타가 발생했을 때 backspace의 사용은 나중에 sudo명령어에 오류를 촉발시킬 확률이 굉장히 크다.

(2) 만약 sudo 명령어에서 오류가 났을 때 위의 backup 파일을 카피하여 뒤집어 쓴 후 다시 수정한다.

(3) 이때 backup파일을 copy하기 위해서는 root 계정으로 들어가서 진행해야 한다.

<br/>
<br/>
<br/>

        참조:
        https://blog.outsider.ne.kr/505

<br/>

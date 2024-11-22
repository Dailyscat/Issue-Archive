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

# Issue: ssh key 등록

## 상황:

<br/>

## 알게된 부분 정리:

- -

<br/>

## 개념:

<br/>

```
ssh key 생성하는 법

ssh-keygen -t rsa -b 4096 -C "git@git.~~~.com"
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub

생성된 키 복사해서 https://git.com/settings/ssh/new 에 등록
`제목`을 아무렇게나 하면되고 `Key type`는 `Authentication Key`이다.

잘 등록되었는지 확인하기

ssh -T git@git.~~.com

```

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

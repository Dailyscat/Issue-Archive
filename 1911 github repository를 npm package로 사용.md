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

# Issue: github repository를 npm package로 사용

## 상황:

npm을 갱신하지 않는 오픈소스를 사용하려고 하는데 버전관리를 같이 하고 싶어서 package.json을 활용하려고 시도

<br/>

## 생각해낸 방안:

- git을 사용하여 install 하고 package.json을 활용하여 버전관리

<br/>

## 방안: git을 사용하여 install 하고 package.json을 활용하여 버전관리 (성공)

<br/>
  
  > git+https://git@github.com/visionmedia/express.git
or this flavor if you need SSH:

> git+ssh://git@github.com/visionmedia/express.git
> <br/> > <br/> > <br/>

        참조:
        https://stackoverflow.com/questions/17509669/how-to-install-an-npm-package-from-github-directly

<br/>

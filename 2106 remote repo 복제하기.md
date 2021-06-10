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

# Issue: remote repo 복제하기

## 상황:

기존 리포가 multi module로 이루어져 있어서 admin 모듈만 따로 빼야하는 상황이라 repo를 분리.

<br/>

## 알게된 부분 정리:

- --bare 옵션과 --mirror 옵션을 통하여 작업

<br/>

## 개념:

<br/>

```
git clone --bare https://github.com/exampleuser/old-repository.git

cd old-repository
git push --mirror https://github.com/exampleuser/new-repository.git
```

<br/>
<br/>
<br/>

        참조:
        https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-on-github/duplicating-a-repository#mirroring-a-repository

<br/>

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

# Issue: pyenv 설치

## 상황:
회사 인프라의 kubectl client가 python 2.7만 지원하는데 3버전을 쓸일도 있어서 버전관리 필요했다.

<br/>

## 알게된 부분 정리:

- +

<br/>

## 개념:

<br/>
  https://github.com/pyenv/pyenv?tab=readme-ov-file#set-up-your-shell-environment-for-pyenv
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: BUILD FAILED (OS X 12.0.1 using python-build 2.2.3-1-g423de9ae): "C 에러 발생시

<br/>

```
softwareupdate --all --install --force
sudo rm -rf /Library/Developer/CommandLineTools
sudo xcode-select --install
```

혹시라도 xcode 시뮬레이터 모듈 다 없어지나했는데 그러지 않았음 다행..

<br/>
<br/>
<br/>

        참조:
        https://github.com/pyenv/pyenv/issues/2201

<br/>

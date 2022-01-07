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

# Issue: nodejs 버전 관리

## 상황: 기존에는 nvm으로 사용하고 있었는데 n 이라는 version manager도 있어서 정리.

<br/>

## 알게된 부분 정리:

- n

<br/>

## 개념: n

<br/>
nvm은 깔고나면 쉘에 따로 입력을 해주는 작업등이 필요한데 이건 필요없어서 좀 더 편리해보인다.
powerShell, bashShell은 따로 안된다.

  ```
    node -v   
    npm cache clean -f
    npm install -g n
    n 16.10
    node -v
  ```
<br/>
<br/>
<br/>

        참조:
        https://github.com/tj/n


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

# Issue: react에서 appsync sdk 호환에러, npm 특정 버전대 설치

## 상황:

react에서 appsync sdk를 사용하는데 제대로 작동하지 않는 상황

<br/>

## 생각해낸 방안:

- react-apollo 3.x버전에서는 sdk 제대로 동작하지 않음
- npm으로 2.x, 특정 버전 대 설치하기

<br/>

## 방안: react-apollo 3.x버전에서는 sdk 제대로 동작하지 않음 (과정)

<br/>

컨트리뷰터께서 친히 2.x버전대에서는 작동하지 않는다고 적어주셨다. 밑에는 opensource에서 모든 패키지가 제대로 동작하는것은 어려운 일이다 라는 느낌으로 2.x대를 사용하여 해결하라는 말도 덧붙여있다.

[이슈](https://github.com/awslabs/aws-mobile-appsync-sdk-js/issues/456#issuecomment-529053420)
<br/>
<br/>
<br/>

<br/>

## 방안: npm으로 2.x, 특정 버전 대 설치하기 (성공)

<br/>

> // for stable or recent

npm install express@3.X

> // For the last stable version

npm install -g npm@latest

> // For the most recent release

npm install -g npm@next

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/15890958/install-a-previous-version-of-a-package

<br/>

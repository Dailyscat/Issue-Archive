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

# Issue: cra에서 낮은 react-scripts 버전으로 인해 scss 사용할 수 없던 오류

## 상황:

sass 사용을 위하여 [cra의 document](https://create-react-app.dev/docs/adding-a-sass-stylesheet#docsNav)를 참조하여 적용하는 과정에서 script를 실행하면 prop-types에 관한 에러가 발생했고,
빌드 과정에서는
`Error: EMFILE: too many open files, open` 에러가 발생하여 빌드가 되지 않던 상황

<br/>

## 생각해낸 방안:

- nodemodules, package-lock.json 지우고 다시 npm i
- ab -n 6000 -c 2000 -k
- react-scripts 버전 업

<br/>

## 방안: nodemodules, package-lock.json 지우고 다시 npm i (실패)

<br/>

스크립트 자체가 실행은 됐다가, 또 안되는 경우가 있었다. 확실한 해답은 아니라고 생각했다.

prop-types, schedular 에러를 확인하고 다시 깔아보기도 했지만 안됐다.
<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: ab -n 6000 -c 2000 -k (실패)

<br/>

소켓 관련 에러라는 글을 몇개 보고 [1](https://ejnahc.tistory.com/263), [2](https://stackoverflow.com/questions/8965606/node-and-error-emfile-too-many-open-files)

시도해봤으나 되지 않았기도 했고, cra 관련 웹팩 설정에서 나오는 에러일 거기 때문에 큰 비중을 두지 않았다.
<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: react-scripts 버전 업 (성공)

<br/>

[cra의 scss document](https://create-react-app.dev/docs/adding-a-sass-stylesheet#docsNav)에 따르면 이는 react-scripts@2.0.0 이상의 버전에서만 작동하는 방법이었다.
(1) 나는 2018년에 전역으로 cra를 설치했었으니 dependencies가 구식이었고
(2) nvm을 사용하여 일을 하다보니 cra 공식문서에서 권하는 8.10보다도 낮은 노드 버전에서 npx를 실행하니 0.9 버전 이상의 react-scripts가 적용되어 실행될 수 없었다.

<br/>
<br/>
<br/>

        참조:
        https://create-react-app.dev/docs/getting-started
        https://stackoverflow.com/questions/48727922/how-to-upgrade-a-react-project-built-with-create-react-app-to-the-next-create-re
        https://bytearcher.com/articles/using-npm-update-and-npm-outdated-to-update-dependencies/
        http://jeonghwan-kim.github.io/2016/08/10/nvm.html
        https://create-react-app.dev/docs/adding-a-sass-stylesheet#docsNav

<br/>

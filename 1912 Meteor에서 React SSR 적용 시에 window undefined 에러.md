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

# Issue: Meteor에서 React SSR 적용 시에 window undefined 에러

## 상황:

Meteor에서 React SSR 적용 시에 window undefined 에러가 발생
<br/>

## 생각해낸 과정:

- Routes 코드 베이스에서 한줄 한줄 지워가며 에러가 발생하는 컴포넌트 확인
- server 측에서 클라이언트의 window 객체를 참조하지 못하므로 server에서 참조 가능한 Dom으로 대체

<br/>

## 방안: Routes 코드 베이스에서 한줄 한줄 지워가며 에러가 발생하는 컴포넌트 확인 (과정1)

<br/>

Routing 컴포넌트 자체에서 오류가 나는 건지 렌더링 되는 다른 컴포넌트 자체에서 오는건지를 몰라서 한참을 해매다가 한줄한줄 지워보며 현재 error가 발생하는 부분을 파악했다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: server 측에서 클라이언트의 window 객체를 참조하지 못하므로 server에서 참조 가능한 Dom으로 대체(과정2)

<br/>

나 뿐만이 ssr을 적용하려고 할 때 window api에 의존하는 프로덕트가 많았을 것이기 때문에 나같은 경우를 겪은 사람들이 꽤 될 것이라고 생각했다. JSDOM이라는 라이브러리가 있었고, 이를 이용하여 server side에서 window 객체를 참조할 수 있었다.

그 중에 match-media 등 지원하지 않는 api들도 있었는데 이는 polyfill을 활용하여 적용시켜 주었다.

<br/>
<br/>
<br/>

        참조:

    server side rendering, getting window is not defined error · Issue #57 · reactjs/react-chartjs
    https://github.com/reactjs/react-chartjs/issues/57

    "window is not defined" error when using server-side rendering · Issue #107 · hellosign/hellosign-embedded
    https://github.com/hellosign/hellosign-embedded/issues/107

    server side rendering, getting window is not defined error · Issue #57 · reactjs/react-chartjs
    https://github.com/reactjs/react-chartjs/issues/57

    v2.1.0 causing TypeError: Cannot read property 'JadeCompiler' of undefined · Issue #19 · meteorhacks/meteor-ssr
    https://github.com/meteorhacks/meteor-ssr/issues/19

    javascript - React JS Server side issue - window not found - Stack Overflow
    https://stackoverflow.com/questions/38951721/react-js-server-side-issue-window-not-found

    Getting started with React SSR | blog.jakoblind.no
    https://blog.jakoblind.no/getting-started-react-ssr/

    server-render | Meteor API Docs
    https://docs.meteor.com/v1.6/packages/server-render.html

    "window is not defined" error when using server-side rendering · Issue #107 · hellosign/hellosign-embedded
    https://github.com/hellosign/hellosign-embedded/issues/107

    Issues · purposeindustries/window-or-global
    https://github.com/purposeindustries/window-or-global/issues?q=is%3Aissue+is%3Aclosed

    javascript - React SSR: Document is not defined - Stack Overflow
    https://stackoverflow.com/questions/54256521/react-ssr-document-is-not-defined

    addEventListener not a function! · Issue #4 · purposeindustries/window-or-global
    https://github.com/purposeindustries/window-or-global/issues/4

    webmodules/get-window: Returns the `window` object from a DOM object
    https://github.com/webmodules/get-window

    Software Developer, Creative Mind and Strategist
    https://www.chrisvisser.io/

<br/>

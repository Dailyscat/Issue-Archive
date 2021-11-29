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

# Issue: node debugger 사용 정리

## 상황:
webpack 이슈를 겪으면서 node debugger를 주구장창 확인했었는데 해당 부분 정리

<br/>

## 알게된 부분 정리:

- node debugger 정리

<br/>

## 개념:

<br/>

1. node --inspect-brk 어쩌구/저쩌구/module.js
2. chrome://inspect/#devices 접속
3. 해당 타겟을 trace 하여 시작

보통 라이브러리를 디버깅 할 때 해당 프로젝트의 node_modules를 타고 들어가기 때문에 타겟이 되는 프로덕트로 타고 들어간다.

이때 --inspect-brk 옵션은 디버깅이 시작되기 전에 화면이 멈추도록 한다.


기본적으로 port는 9229 포트인데 다른 포트에서 열리게 할 수 도 있다.

```
// default 9229 port
node --inspect bin/www

// custom port
node --inspect=port bin/www
```

나는 webpack 빌드가 실행 될 때 발생하는 문제라서 node_modules 내부에 debugger를 적어서 확인했는데 아래와 같은 방법도 존재한다.

```
node --inspect something
chrome://inspect/#devices 접속
source
  Filesystem 탭
     +add folder to workspace
       해당 루트 폴더 선택

```


브라우저에서도 마찬가지이지만 100개 이상의 이터레이션이 발생하는 반복문의 경우는 디버깅이 힘들다. 이때 라인의 왼쪽 부분을 오른쪽 클릭하면 Conditional break point, 추가적인 코드 내용을 추가하여 로그를 볼 수 도 있다.

console.group, console.table 등을 활용하면 좀 더 효율적이게 디버깅할 수 있다.


<br/>
<br/>
<br/>

        참조:
        https://imcts.github.io/NODE-DEBUGGER/
        https://www.atatus.com/blog/11-best-tips-to-node-js-debugging-that-you-didnt-know/

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

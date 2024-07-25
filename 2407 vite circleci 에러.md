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

# Issue: vite circle ci 에러

## 상황:

#12 7.165 npm error Exit handler never called!
#12 7.165 npm error This is an error with npm itself. Please report this error at:
#12 7.165 npm error   <https://github.com/npm/cli/issues>
#12 7.166 npm error A complete log of this run can be found in: /root/.npm/_logs/2024-07-19T06_11_36_243Z-debug-0.log

<br/>

## 알게된 부분 정리:

- plugin-node-polyfills 문제

<br/>

## 개념:plugin-node-polyfills

<br/>

```
    nodePolyfills({
      include: ['http', 'https']
    } as PolyfillOptions)
```

위 옵션을 넣을 일이 있어서 넣고 보니 build시에 위에 같은 에러가 발생

추가로 node 버전 이슈도 존재하는것 같은데 나는 해당되진 않지만
가능하면 22.4.1 이상버전으로 뭘 작업을 해도 해야할듯


<br/>
<br/>
<br/>

        참조:
        https://github.com/npm/cli/issues/7666

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

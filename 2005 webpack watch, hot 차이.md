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

# Issue: webpack watch, hot 차이

## 상황: 환경 설정 중 어떤 차이가 있는지 확인

<br/>

## 생각해낸 방안:

- watch
- hot

<br/>

## 방안: watch

<br/>

#### webpack --watch

--watch 옵션은 작업 중인 파일의 코드가 변경되기를 기다리고 변경이 발생하면 다시 컴파일하여 빌드한 뒤 다시 변경을 기다린다.

만약 캐시 옵션이 활성화 되있다면 웹팩은 각각의 모듈을 캐싱해놓고 변화가 없다면 컴파일 시에 재사용한다.

#### webpack-dev-server

webpack-dev-server은 기본적으로 watch 옵션을 사용하고 있기 때문에 watch 옵션을 줄 필요가 없고 디스크에 build 파일을 생성하게 하지 않고 메모리에서 결과 파일을 유지하게 제공한다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: hot

<br/>
  
  #### webpack-dev-server --hot

해당 명령어는 `HotModuleReplacementPlugin`을 작동하게 하고 fullPage가 다시 컴파일 되게 하지 않고 해당 파일 중의 특정 컴포넌트만 리로드 되게 한다.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/38089785/webpack-watch-vs-hot-whats-the-difference

<br/>

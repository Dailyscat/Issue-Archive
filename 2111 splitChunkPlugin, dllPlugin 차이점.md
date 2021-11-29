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

# Issue: splitChunkPlugin dllPlugin 차이점

## 상황:
웹팩 실행 순서를 보다가 둘의 차이가 무엇인지 확인

<br/>

## 알게된 부분 정리:

- dllPlugin 정리
- splitChunkPlugin 정리
- 차이점

<br/>

## 개념: dllPlugin 정리

<br/>
  정의 
  
  ```
  The DllPlugin and DllReferencePlugin provide means to split bundles in a way that can drastically improve build time performance. The term "DLL" stands for Dynamic-link library which was originally introduced by Microsoft.
  ```

- dllPlugin은 기본적으로 한 웹팩 내부에서 다른 웹팩 설정을 사용하기 위한 방식이다.
- 윈도우에서 사용되는 Dynamic Link Library 방식을 차용한 것으로 윈도우에서 사용되는 .dll 파일처럼 외부 라이브러리를 미리 compile 해놓은 기법이다.
- 프로젝트에서 파일을 갱신할 때마다 웹팩이 번들링하는 범위를 줄일 수 있다.
- dllPlguin으로 만들어진 bundle을 dllReferencePlugin을 통해서 참조하여 사용한다.
- 

<br/>
<br/>
<br/>

        참조:
        https://webpack.js.org/plugins/dll-plugin/#dllreferenceplugin
        https://olaf.kr/2018/05/21/webpack4-dllplugin-configuration/

<br/>

## 개념: splitChunkPlugin 정리

<br/>
  정의

  ```
  Originally, chunks (and modules imported inside them) were connected by a parent-child relationship in the internal webpack graph. The CommonsChunkPlugin was used to avoid duplicated dependencies across them, but further optimizations were not possible.

  Since webpack v4, the CommonsChunkPlugin was removed in favor of optimization.splitChunks.

  ```

- 초기 청크가 아닌 사용하기 위해 불러오는 청크들이 타겟의 대상이 된다.
- 다른 엔트리에서 공용으로 사용되는 모듈 혹은 node_modules의 내부에 있는 모듈이 청크의 멤버가 된다.
- gzip 압축 전 20kb(webpack4는 30kb) 이상의 덩어리만이 청크가 될 수 있다.
- 초기에 해당 청크에 대한 요청이 30개 이하일 때 청크를 만든다.
- 비동기로 해당 청크에 대한 요청이 30개 이하일 때 청크를 만든다.
- cache group을 통해서 사용자 설정을 통한 청크들을 만들 수 있다.

<br/>
<br/>
<br/>

        참조:
        https://webpack.js.org/plugins/split-chunks-plugin/

<br/>

## 개념: 차이점

<br/>
  - splitChunkPlugin의 경우는 HMR가 일어날 때 컴파일이 계속 다시 일어난다.
  - dllPlugin은 이미 컴파일 되어 있는 상태이기 때문에 HMR이 일어나도 다시 컴파일 되지 않는다.

  - splitChunkPlugin의 경우는 사용자의 의도로, 런타임에 사용되는 모듈 중 공용으로 사용되는 청크들을 정리하기위해 주로 사용된다. 이때 사용자의 의도를 가지고 웹팩에서 자동적으로 분류한다.
  - dllPlugin의 경우는 사용자의 의도로, 자주 갱신되지 않는 모듈(framework, core module)을 빌드타임을 줄이기 위해 사용하는 경우가 많다. 이때 사용자의 의도로 웹팩을 사용하여 컴파일 한다.

  - 쓰임새와 용도가 매우 다르고 무엇보다 사용자의 의도로 분류가 되는지가 관건이라고 생각된다.
  
<br/>
<br/>
<br/>

        참조:

<br/>


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

# Issue: 2106 corejs, runtime 관련 내용 정리

## 상황:

공용 패키지를 만들면서 환경 설정 중 바벨 설정 관련 궁금한 사항을 찾아보다 알게된 부분 정리

<br/>

## 알게된 부분 정리:

- Core.js
- @babel/polyfill
- @babel/plugin-transform-runtime
- 개선된 폴리필
- core-js@3

<br/>

## 개념:

<br/>

### Core.js

2019년 3월 공개된 폴리필 라이브러리이다.
바벨 7.4 이상 버전과 core-js@3를 같이 사용해야 동작한다.

### @babel/polyfill

이 폴리필은 제너레이터 폴리필인 regenerator-runtime과 core-js를 합쳐서 만든 폴리필임.
전역공간에 폴리필을 채워 넣는 방식이기 때문에 전역공간이 오염되어 폴리필 충돌 가능성이 있다는 단점이 있음.
브라우저에서 이미 구현된 필요하지 않은 폴리필까지 전부 번들에 포함되어 번들 크기가 커지는 단점이 있음.
번들 크기는 커지지만, 실행될때는 브라우저에서 구현되지 않은 문법만 실행하기 때문에 빠르다는 장점이 있음. (최신 브라우저일 수록 더 빠름)
7.4버전에서 depeciated 되었음.

### @babel/plugin-transform-runtime

이 폴리필을 사용하면 바벨이 es6+의 문법들을 자체 구현한 함수로 트랜스파일링 한다. 덕분에 번들 크기가 작아지는 장점이 있으나, [1,2,3].includes와 같이 인스턴스의 메소드는 제대로 트랜스파일링 되지 않는 이슈가 있다.
또 하나의단점,
axios는 전역공간에 선언된 Promise가 있어야 제대로 동작하는데 이 플러그인은 트랜스파일링 과정에서 자체 구현된 함수로 변경되기 때문에 전역공간에 Promise를 채우지 않는다. 그래서 아래처럼 node_modules내부에 있는 axios가 런타임에 트랜스파일 될 수 있도록 웹팩 설정을 커스텀해줘야 한다. 문제는 이렇게 외부 모듈이 전역공간에 선언된 최신 객체를 필요로 할 경우 매번 webpack의 include 옵션에 포함시켜줘야 한다는 단점이 있다.

```
include: [
  /src\/js/,
  /node_modules\/axios/
],

```

이 런타임 폴리필 방식은 제한사항이 있으므로 완벽하게 폴리필을 넣어주고 싶은 경우 다른 방식을 사용해야 한다.

### 개선된 폴리필

### core-js@3

위에서 말했던 두가지 문제, @babel/polyfill의 전역공간 오염 문제와, 바벨 런타임 플러그인의 인스턴스 메소드 문제를 모두 해결했다.
한마디로, 완벽한 버전의 런타임 폴리필이라고 생각하면 쉬움.

        foo.includes("a");
        이런 코드가
        import _includesInstanceProperty from "@babel/runtime-corejs3/core-js-stable/instance/includes";
        _includesInstanceProperty(foo).call(foo, "a");

이렇게 트랜스파일링됨.

### 사용법

```
"presets": [
    ["@babel/preset-env", {
      "targets": {
        "browsers" : ["last 2 versions", "ie >= 11"]
      },
      "useBuiltIns": "usage",
      "corejs":3,
      "shippedProposals": true
    }]
  ],
```

이런식으로 @babel/plugin-transform-runtime 으로 트랜스파일링 된 코드가 런타임에 corejs3를 사용하게 만들어도 된다. 이 플러그인에는 기본적으로 regenerator-runtime이 들어있으므로 es7의 async와 es6의 generator를 옵션값 조절로 사용할 수 있게 만들어준다.

```
yarn add @babel/plugin-transform-runtime -D
yarn add @babel/runtime-corejs3

babel.rc

{
  "plugins": [
    [
      "@babel/plugin-transform-runtime",
      {
        "corejs": 3,
        "regenerator": true,
      }
    ]
  ]
}
```

이렇게 입력해주면 인스턴스 메소드 문제가 해결된 런타임 폴리필이 정상동작하며 전역공간을 오염시키지 않는다.

<br/>
<br/>
<br/>

        참조:
        https://babeljs.io/docs/en/babel-plugin-transform-runtime

<br/>

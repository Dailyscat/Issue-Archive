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

# Issue: preset-env, transform-rumtime 작동하는 방식

## 상황: 디버깅 중 좋은 글 발견해서 정리

<br/>

## 알게된 부분 정리:

- preset-env, transform-rumtime 작동하는 방식

<br/>

## 개념: preset-env, transform-rumtime 작동하는 방식


<br/>
  core-js는 core-js와 core-js-pure 두가지 패키지로 나뉜다.

core-js는 전역 폴리필로 사용하고 core-js-pure는 전역을 오염시키지 않는 폴리필로 사용한다.
간단히 예를들면

```
// core-js

import "core-js/stable/set";
// 이를 통해 global.Set으로 사용이 가능하다.

// core-pure-js

import Set from "core-js-pure/stable/set";
// 이를 통해 전역 오염을 막으면서 Set을 사용한다.
```

이때 preset-env를 useBuiltIns: 'usage'로 설정하고 corejs 옵션과 같이 사용할 때 promise를 사용한다면 아래와 같다.

```
"use strict";

require("core-js/modules/es.object.to-string");

require("core-js/modules/es.promise");

var p = new Promise();
```
여기서 "usage" 옵션은 바벨로 하여금 타겟 브라우저에 대해서 필요한 폴리필을 가져와서 주입하여 깨짐이 없이 사용하게끔 만든다.
여기서 중요한점은 어디서? 가져오느냐다.
위 코드를 보면 core-js에서 import하여 가져오고 있으며 이는 전역 오염을 발생시킨다.

그럼 같은 상황으로 transform-runtime을 사용한다고 가정해보자.

```
"use strict";

var _interopRequireDefault = require("@babel/runtime-corejs3/helpers/interopRequireDefault");

var _promise = _interopRequireDefault(require("@babel/runtime-corejs3/core-js-stable/promise"));

var p = new _promise["default"]();
```
이때 babel 패키지가 가지고 있는 소스코드는 없고 core-js-pure와 regenerator-runtime만 [디펜던시](https://github.com/babel/babel/blob/main/packages/babel-runtime-corejs3/package.json)로 가지고 있다. 이때 transform-runtime 플러그인의 역할은 core-js-pure 패키지에 있는 폴리필들을 node_modules로 옮기는 역할을 한다. 이를 통해 전역오염 없는 폴리필들을 사용할 수 있게된다.

#### @babel/transform-runtime을 사용했을 때 앱이 커지는 이유는?

@babel/transform-runtime 은 브라우저 타겟을 지정하지 않기때문에 해당 브라우저에서 사용이 가능해도 그냥 폴리필을 전부다 주입해버린다.

#### useBuiltIns: "usage" on @babel/preset-env 사용하는것이 왜 더작을 수 있나?

위에 얘기한대로 브라우저 타겟이 폴리필 없이 수행될 수 있다면 다 가지고 있지 않기 때문에 작다. 

#### useBuiltIns: "usage"와 corejs, babel/preset-env와 transform-runtime을 corejs false로 사용해도 되는가?

명확하지 않기 때문에 안된다.

```
async function f() {}

The transpiled code is this:

"use strict";

var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

var _regenerator = _interopRequireDefault(require("@babel/runtime/regenerator"));

require("regenerator-runtime/runtime");

var _asyncToGenerator2 = _interopRequireDefault(require("@babel/runtime/helpers/asyncToGenerator"));

function f() {
  return _f.apply(this, arguments);
}

function _f() {
  _f = (0, _asyncToGenerator2["default"])( /*#__PURE__*/_regenerator["default"].mark(function _callee() {
    return _regenerator["default"].wrap(function _callee$(_context) {
      while (1) {
        switch (_context.prev = _context.next) {
          case 0:
          case "end":
            return _context.stop();
        }
      }
    }, _callee);
  }));
  return _f.apply(this, arguments);
}
```
`var _asyncToGenerator2 = _interopRequireDefault(require("@babel/runtime/helpers/asyncToGenerator"));`

바벨은 runtime 패키지에서부터 헬퍼들을 받는다. 이 헬퍼들은 전역에 있는 일부 피쳐들을 사용한다. 위 코드에서는 @babel/runtime/helpers/asyncToGenerator가 promise를 사용한다. 이때 preset-env의 useBuiltIns가 usage이기 때문에 타겟 브라우저에 해당하는 폴리필들이 있으니 문제가 없을 것 같다. 하지만 바벨은 해당 설정에서 사용자의 app내에서 피쳐를 사용할 때만 폴리필을 추가하게 한다. 그래서 피쳐를 전역에서 사용할 수 없다. 피쳐를 앱 내에서 사용함으로써 헬퍼들을 트랜스파일하면 사용할 수 있지만 그것은 좋지않다. 예측할 수 없는 환경이 생겨버리기 때문이다. 만약 어쩔 수 없지 해당 설정을 유지해야 한다면 runtime-generator에서 core-js 옵션을 사용함으로써 전역오염 방지를 하는것이 좋다.


<br/>
<br/>
<br/>

        참조:
        https://github.com/babel/babel/issues/9853#issuecomment-619587386
        https://github.com/babel/babel/issues/9728#issuecomment-476617973
        https://github.com/babel/babel/issues/10121
        

<br/>

## 개념: transform-rumtime 플러그인 정리

<br/>
  - 바벨은 소스코드내에서 공통적으로 사용하는 함수같은 것들을 위해서 작은 헬퍼 함수를 사용한다. 
  - 이런 헬퍼 함수들은 다른 모듈에서도 사용되고 이때 불필요하게 중복되서 헬퍼함수들이 생성되는 경우가 있다.
  - transform-runtime은 이런 헬퍼함수들을 공통으로 참조할 수 있도록 해주어서 번들사이즈를 줄이도록 만든다.
  
<br/>
<br/>
<br/>

        참조:
        https://babeljs.io/docs/en/babel-plugin-transform-runtime#helper-aliasing
        https://babeljs.io/docs/en/babel-plugin-transform-runtime

<br/>

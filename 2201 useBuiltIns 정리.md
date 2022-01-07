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

# Issue: useBuiltIns 정리

## 상황:
바벨 관련 정리하다가 좋은글 발견하여 정리

<br/>

## 알게된 부분 정리:

- usage, entry, false 정리

<br/>

## 개념: usage, entry, false 정리 

<br/>
  entry 
  ```
  최상단에 import한 import "core-js/stable"을 타겟 브라우저에서 사용되는 폴리필로 전부 대체

  That means you need to add

import "core-js/stable";
import "regenerator-runtime/runtime";
to your entry point and these lines will be replaced by only required polyfills. When targeting chrome 72, it will be transformed by @babel/preset-env to

import "core-js/modules/es.array.unscopables.flat";
import "core-js/modules/es.array.unscopables.flat-map";
import "core-js/modules/es.object.from-entries";
import "core-js/modules/web.immediate";
  ```

  usage

```
타겟브라우저에 없는 피쳐만 자동으로 주입시켜준다.

이때 usage옵션은 사용하는 코드만 polyfill 대상으로 보기 때문에, 사용하는 node_modules의 dependency에서 polyfill이 적용되지 않은 코드가 있다면 에러가 발생할 수 있다.

또, 아래와 같은 코드에서 babel은 fooArrayOrObject가 string 인지 array 인지 판단할 수 없기 때문에 두 가지 polyfill을 모두 import 한다.

const set = new Set([1, 2, 3]);
[1, 2, 3].includes(2);
in browsers like ie11 will be replaced with

import "core-js/modules/es.array.includes";
import "core-js/modules/es.array.iterator";
import "core-js/modules/es.object.to-string";
import "core-js/modules/es.set";

const set = new Set([1, 2, 3]);
[1, 2, 3].includes(2);

```

false: that's the default value when no polyfills are added automatically.


) Do I need to install @babel/polyfill package and start my vendors.js with require("@babel/polyfill"); ?

Yes for environment prior to babel v7.4 and core-js v3.

TL;DR

No. Starting from babel v7.4 and core-js v3 (which is used for polyfilling under the hood) @babel/preset-env will add the polyfills only when it know which of them required and in the recommended order.

Moreover @babel/polyfill is considered as deprecated in favor of separate core-js and regenerator-runtime inclusions.

So using of useBuiltIns with options other than false should solve the issue.

Don't forget to add core-js as a dependency to your project and set its version in @babel/preset-env under corejs property.


<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/52625979/confused-about-usebuiltins-option-of-babel-preset-env-using-browserslist-integ
        https://so-so.dev/web/you-dont-know-polyfill/#babelpolyfill

<br/>


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

# Issue: npm -> yarn-berry로 migration 에러 정리

## 상황: 기술부채 청산(babel 최신화, 레거시 코드 제거), ci시에 빌드 속도 줄이기 등을 위해 적용

<br/>

## 에러해결 정리:

- Module not found: Error: Can't resolve '.."
- require.extensions is not supported by webpack. Use a loader instead
- ReferenceError: regeneratorRuntime is not defined
- was not found in '@babel/runtime-corejs3/core-js-stable/instance/for-each' (possible exports: __esModule)
- is not a function core-js-pure/internals/function-uncurry-this.js


<br/>

## 에러: Module not found: Error: Can't resolve '.."
## 에러: require.extensions is not supported by webpack. Use a loader instead


<br/>
  보통 node_modules에서 해당하는 디펜던시를 가져오게 되는 상황에서 발생

  ```
   resolve: {
            alias: {
                'dependency': path.resolve(__dirname, '.yarn/cache/dependency-123129/node_modules/handlebars/dist/dependency.min.js'),
            }
        },
  ```

  위와 같이 .yarn cache에 있는 파일을 바라보게 하거나, unplugged 폴더 내부에 있는 디펜던시 파일을 바라보게 하면 해결된다.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/45262265/require-extensions-is-not-supported-by-webpack-use-a-loader-instead
        https://github.com/handlebars-lang/handlebars.js/issues/1174

<br/>

## 에러: ReferenceError: regeneratorRuntime is not defined

<br/>
  기존 바벨이 7.4아래 일때는
  @babel/polyfill을 웹팩이 번들링할 때 babel-loader에서 트랜스파일 되도록 했는데
  이번에 작업을 하면서 바벨 7.16(latest)를 적용하다보니 저걸 사용할 수 가 없었다.
  설정을 바꿔야 했고 아래와 같이 바꿨다.
  ```
      "presets": [
        ["@babel/preset-env", {
            targets: {
                "browsers": ["last 2 versions", "not ie <= 7"]
            },
            modules: "cjs"
        }]
    ],
    "plugins": [["@babel/plugin-transform-runtime", {corejs: 3}]]
  ```
  이 과정에서 corejs를 @babel/runtime-corejs3를 사용하지 않고 preset-env에서 useBuiltin 옵션에서 최신 corejs로 3.20을 사용하려고 했는데
  에러가 발생하여서 runtime에 있는 플러그인으로 사용하도록 변경

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/53558916/babel-7-referenceerror-regeneratorruntime-is-not-defined
        https://stackoverflow.com/questions/52565118/webpack-babel-loader-need-babel-config-js-or-not

<br/>

## 에러: was not found in '@babel/runtime-corejs3/core-js-stable/instance/for-each' (possible exports: __esModule)

<br/>
  기존 레거시 라이브러리에서 esm(es module syntax)를 따라가지 못하는 부분에서 발생
  modules: "cjs"로 설정
```
    "presets": [
        ["@babel/preset-env", {
            targets: {
                "browsers": ["last 2 versions", "not ie <= 7"]
            },
            modules: "cjs"
        }]
    ],
```
<br/>
<br/>
<br/>

        참조:
        https://babeljs.io/docs/en/babel-preset-env
        https://github.com/babel/website/pull/1856
        https://stackoverflow.com/questions/53558916/babel-7-referenceerror-regeneratorruntime-is-not-defined
        https://github.com/babel/babel/issues/9853#issuecomment-619587386

<br/>

## is not a function core-js-pure/internals/function-uncurry-this.js

<br/>

preset-env의 종속성으로 
@babel-plugin-proposal-async-generator-functions
@babel-helper-define-polyfill-provider
두 가지가 있는데 해당 패키지들은 webpack4를 사용한다. 
webpack4는 모듈 빌드시 환경에 맞는 폴리필을 필요로해서 
내부에서 /buildin/*에 있는 폴리필을 가져와서 사용한다(링크)[https://github.com/webpack/webpack/tree/webpack-4/buildin]
해당 소스 내용은 대충 아래와 같다.
```
module.exports = function(module) {
	if (!module.webpackPolyfill) {
		module.deprecate = function() {};
		module.paths = [];
		// module.parent = undefined by default
		if (!module.children) module.children = [];
		Object.defineProperty(module, "loaded", {
			enumerable: true,
			get: function() {
				return module.l;
			}
		});
		Object.defineProperty(module, "id", {
			enumerable: true,
			get: function() {
				return module.i;
			}
		});
		module.webpackPolyfill = 1;
	}
	return module;
};
```

그리고 현재 어플리케이션에서 core-js를 사용하여 모듈이 빌드 되는 당시에 아래와 같은 흐름을 가진다.

```
So, when you require("core-js/..."),

core-js starts loading
core-js requires webpack/buildin
webpack/buildin starts loading
webpack/buildin requires core-js, because it's transpiled
core-js is alread loading -> module.exports already exists and it is an empty object (ERROR!)  
```

정리하면 webpack v4를 사용하고 있는 디펜던시들은 해당 패키지 내부 node_modules에 buildin/ 폴더를 가지고 있고
buildin 내부의 코드들은 이미 transpiled된 상태의 코드이기 때문에 로더를 통해 다시 컴파일 될 필요가 없다. 하지만 이때 컴파일을 시도하려고 하면
모듈로 또 컴파일이 되야 하고 바벨로더 입장에서는 폴리필로 사용되는 core-js를 동기적으로 불러오는 과정을 겪게 되고 이때 core-js는 또다시 buildin 내부의 코드를
부르려고 하기 때문에 제대로 가져오지 못한다는 얘기와 같아보인다. 

쨌든 buildin과 core-js에 대해 로더가 동작하지 않도록 설정하면 에러해결이된다.

추가로 babel과 corejs를 함께 사용할 때는 
https://github.com/zloirock/core-js#babelruntime
corejs 문서에 써있는 형태로 적용하는게 더 정확하다.


[바벨로더는 왜 웹팩의 일부분으로 사용되어야 하는가?](https://stackoverflow.com/questions/62102685/why-babel-loader-is-part-of-webpack-instead-babel-itself)

<br/>
<br/>
<br/>

        참조:
        https://github.com/zloirock/core-js/issues/743#issuecomment-571994042
        https://github.com/zloirock/core-js/issues/912
        https://github.com/zloirock/core-js/issues/743
        https://stackoverflow.com/questions/57361439/how-to-exclude-core-js-using-usebuiltins-usage/59647913#59647913
        https://webpack.js.org/loaders/babel-loader/
        https://github.com/zloirock/core-js/issues/743
        https://stackoverflow.com/questions/53558916/babel-7-referenceerror-regeneratorruntime-is-not-defined
        https://github.com/babel/babel/issues/13320

<br/>

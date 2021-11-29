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

# Issue: webpack 번들링 흐름 간단하게 정리

## 상황:
웹팩관련 이슈 겪으면서 웹팩 내부코드를 자세하게 보게 됐다. 어떤식으로 정리되는지 간단하게 정리

<br/>

<br/>

## 개념: 전체적인 흐름

<br/>
  1. 시작은 webpack.js에서 시작된다.

  ```
  	} else if (typeof options === "object") {
		options = new WebpackOptionsDefaulter().process(options);

		compiler = new Compiler(options.context);
		compiler.options = options;
		new NodeEnvironmentPlugin({
			infrastructureLogging: options.infrastructureLogging
		}).apply(compiler);
		debugger;
		if (options.plugins && Array.isArray(options.plugins)) {
			for (const plugin of options.plugins) {
				if (typeof plugin === "function") {
					plugin.call(compiler, compiler);
				} else {
					plugin.apply(compiler);
				}
			}
		}
		compiler.hooks.environment.call();
		compiler.hooks.afterEnvironment.call();
		compiler.options = new WebpackOptionsApply().process(options, compiler);
		debugger;
  ```
  compiler의 hook에 걸려있는 method들(environment, afterEnvironment)등을 호출하면서 compiler를 세팅한다.

  2. compiler run이 호출
  ```
  		this.hooks.beforeRun.callAsync(this, err => {
			if (err) return finalCallback(err);

			this.hooks.run.callAsync(this, err => {
				if (err) return finalCallback(err);

				this.readRecords(err => {
					if (err) return finalCallback(err);

					this.compile(onCompiled);
				});
			});
		});
  ```

  이때 compile 메소드가 호출되기전에 prod, development등 현재 빌드가 진행되는 환경, 혹은 그 환경에서만 필요한 미리 설정해둔 키 값들을 클로저로 두어 사용할 수 있게 설정하는 과정을 거친다.

  3. 컴파일 진행을 통해 현재 옵션과 context에서 만들어질 수 있는 normal module factory, context module factory를 만든다.

  4. 만들어진 compile 정보에 사용자가 설정한 dll, external 등 최적화 과정을 적용하기 위해 한번 더 compile 실행한다.

  dll 번들을 사용하고 있는지 확인하고 manifest를 분석 후 적용
  external에서 제외된 외부 라이브러리를 제거

  5. compile 정보를 통해 모듈들을 생성하고 해당 모듈들이 추후에 사용될 부분을 위해 훅을 건다.

  이때 최적화나 설정을 적용하기 위해 훅을 사용하여 closure로 compilation에 접근할 수 있도록 설정되어 있다.
  
  ```
  this.hooks.make 라면 this.hooks.make.call이 호출될 당시에 hooks.make.tap이 걸려 있는 부분이 전부 실행된다. 웹팩은 이때 필요한 정보를 클로져로 접근할 수 있도록 콜백패턴을 적용해두었다.
  ```

  6. this.hooks.make 훅을 통하여 엔트리 정보와 엔트리에서 적용될 모듈들을 파악한다.

  7. seal 과정을 통해서 chunk graph, split chunk 등 optimization 과정이 적용된다.

  8. afterCompile에서 각 런타임 청크에서 어떤 dep을 참조하는지 명명한다.
  
  

<br/>
<br/>
<br/>

        참조:

<br/>


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

# Issue: webpack과 VanillaJS로 라우팅 부분 이슈

## 상황:

spa를 위한 라우터를 만드는 과정에서 주소창에서 `/complete`을 쳤다고 가정했을 때 제대로 라우팅이 되지 않는 이슈

<br/>

## 생각해낸 방안:

- dev-server에 historyApiFallback 속성 true
- origin이 `file`일 때를 분류하여 다른 값 적용
- `#`을 넣고, hash값을 적용하여 라우팅

<br/>

## 방안: dev-server에 historyApiFallback 속성 true (실패)

<br/>

build된 html 파일에서는 작동되지 않았다.
<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: origin이 `file`일 때를 분류하여 다른 값 적용(실패)

<br/>

라우터 자체에서 file일 때를 분기하여 처리했다. 작동은 됐지만 근본적인 해결책이 아니었으며 pushState에서 hash관련 에러 발생
<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: `#`을 넣고, hash값을 적용하여 라우팅 (성공)

<br/>
  hash값을 기반으로 통일된 로직으로 라우팅이 작동하도록 설정했다.
  hashChange 이벤트에서 loadRoute 함수를 작동하도록 하여 설정했다.

```
    window.addEventListener("hashchange", () => {
    const hash = location.hash.split("#/").splice(1);
    this.loadRoute(...hash);
  });
```

근데 개인적으로 이 방식은 #!(해시뱅)을 필요로 하는 방식이라서 실제로 상용 프로덕트에서는 적용하기 좋지 않다.

그래서 react-router를 살펴보니 pushState기반의 라우팅 처리를 하고 있고 라우터로 전체 컴포넌트를 감싸서(wrapping) 해당 값에 따른 컴포넌트를 리턴해주는 방식이었다.

<br/>
<br/>
<br/>

        참조:
        L5] HashChange Event 해쉬 변경 이벤트 처리 PushState와 popState 메서드 :: 글쓰는 개발자

    https://writingdeveloper.tistory.com/219

    javascript - location.hash and history.replaceState - Stack Overflow
    https://stackoverflow.com/questions/37872018/location-hash-and-history-replacestate

    [정리] 러닝 리액트 11장 - React Router
    https://seungdols.tistory.com/769

    react-router/BrowserRouter.js at master · ReactTraining/react-router
    https://github.com/ReactTraining/react-router/blob/master/packages/react-router-dom/modules/BrowserRouter.js

    react-router/HashRouter.js at master · ReactTraining/react-router
    https://github.com/ReactTraining/react-router/blob/master/packages/react-router-dom/modules/HashRouter.js

    react-router/Router.js at master · ReactTraining/react-router
    https://github.com/ReactTraining/react-router/blob/master/packages/react-router/modules/Router.js

    react-router/RouterContext.js at master · ReactTraining/react-router
    https://github.com/ReactTraining/react-router/blob/master/packages/react-router/modules/RouterContext.js

<br/>

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

# Issue: SSR에서 Saga 초기 Data Fetching

## 상황: Server Side Rendering이 적용되야 하는 상황에서 초기 Data Fetching Generator 함수가 호출되야 하는 상황에서 이 Data Fetching을 기다리게 하려면 어떻게 해야할까라는 질문을 받았다.

```

async function createClientHTML({ path, url }) {
  const { store, runSaga } = configure();
  const task = runSaga(rootSaga);

  // 현재 경로에 필요한 Action을 dispatch하는 함수.
  // 위에서 보았던 `getUserById`를 호출하는 부분이다.
  store.dispatch({
    type: 'user/GET_USER_BY_ID',
    id: ctx.params.id,
  })

-------------------------------------------------------

  const html = await renderToString(
    <Provider store={store}>
      <StaticRouter context={context} location={url}>
        <App />
      </StaticRouter>
    </Provider>
  );

  return html;
}

function* getUserById(id) {
  // 1. getUserById API 호출
  const { user } = yield call(UserAPI.getUserById, id);

  // 2. API 호출 이후 context가 현재 함수를 호출한 곳으로 돌아간다.
  // 3. getUserById API 응답이 오면 다시 context를 얻게 되고
  //   아래 코드를 마저 실행한다.


  yield put({
    type: SET_USER,
    payload: {
      user,
    },
  });
}

```

`-------------------` 해당 부분에서 redux-saga가 `call (UserAPI.getUserById,id)`를 호출하면 아래 renderToString은 동기적으로 리턴값을 기다리지 않고 호출되게 된다.

<br/>

## 생각해낸 방안:

- renderToString의 상단에서 promise와 await를 활용하여 리턴 결과를 기다린다.
- saga에서 제공하는 `END`액션을 활용하여 적용

<br/>

## 방안: renderToString의 상단에서 promise와 await를 활용하여 리턴 결과를 기다린다.

<br/>

```
await new Promise((resolve, reject) => {
  resolve(api call 결과물)
})
```

방식으로 SSR에 한해 받은 결과값을 dispatch하여 store에 저장하는 helper 함수를 하나 만드는 방법.

후에 동기적으로 renderToString이 실행된다.

redux 스러운 처리는 아니지만 처리를 위한 방법이 될 수 있다.
<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: saga에서 제공하는 `END`액션을 활용하여 적용

<br/>

`task.done`은 redux의 태스크가 종류되었을 때 resolve 된다.

하지만 saga는 여러번의 action 요청을 처리하기 위하여 watcher함수를 만들어서 보통

```
while(true){

}
```

와 같은 방식으로 처리한다.

그러다보니 태스크는 끝나지않고 중간에 task.done은 resolve 되지 않는다.

이를 위해 `End`라는 빌트인 액션을 활용하여 처리할 수 있다.

`END`는 saga에게 action을 전달해주는 통로인 channel을 종료시키는 행동을 수행한다.

```
watcher함수에게 전달되는 액션은 모두 channel을 거치게 되는데, channel은 END Action을 받으면 더이상 watcher들에게 액션을 주지 않습니다.

결국 take하고 있는 Task들은 모두 종료된 것으로 처리됩니다. (resolve!)

참고로 END 로 인해 채널이 종료되더라도 take가 아닌 fork, call 등을 통해 실행중이었던 Task가 있다면 끝까지 실행됩니다. 따라서 우리가 fork를 통해 호출한 getUserById는 끝까지 수행될 것입니다.
```

즉 해당 요청에 대한 처리는 완전히 끝내되, action이 전달되는 channel을 종료시킴으로써 watcher함수의 역할이 중단되게 만든다.

<br/>
<br/>
<br/>

        참조:
        https://kwoncheol.me/posts/redux-saga-with-ssr/

<br/>

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

# Issue: trpc initTrpc.context 후에 api handler 노출하는 부분에서 자꾸 에러 발생

## 상황:

fetchRequestHandler, createTRPCOptionsProxy에 createContext하는 부분에서
둘다 lint 에러 발생시킴.
<br/>

## 알게된 부분 정리:

- 아직 해결 전

<br/>

## 개념: 아직 해결 전

<br/>

```
  ctx: () => {
    // Create a minimal mock of CreateNextContextOptions
    const mockCtx = {
      req: {
        headers: {}
      },
      res: {
        setHeader: () => {},
        status: () => ({ end: () => {} })
      }
    } as any;
    return createContext(mockCtx);
  },
```

일단 이런식으로 mock 객체 만들어서 넘기고 있는데 https://github.com/trpc/trpc/discussions/3559 메인테이너도 비슷한 방식을 추천하기도 했고.. 근데 문서가 context 문서, migration 문서 두 부분에 조각조각 나있는데 둘이 내용이 달라서 뭐가 맞는지 모르겠고, 둘다 적용해봐도 타입에러 발생한다;
좀 더 확인 필
<br/>
<br/>
<br/>

        참조:
        https://github.com/trpc/trpc/blob/main/examples/next-big-router/src/pages/api/trpc/%5Btrpc%5D.ts

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

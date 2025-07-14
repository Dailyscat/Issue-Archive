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

# Issue: Query data cannot be undefined. Please make sure to return a value other than undefined from your query function. Affected query key

## 상황:
서버 컴포넌트 페이지를 기반으로 trpc 적용하여 작업하는데에서 자꾸 위 에러 발생
<br/>

## 알게된 부분 정리:

- await prefetch

<br/>

## 개념: await prefetch

<br/>
    trpc의 server component 적용 문서 https://trpc.io/docs/client/tanstack-react-query/server-components#using-your-api 에는 분명히 예제가 아래와 같은데
    
    ```
        void queryClient.prefetchQuery(
            trpc.hello.queryOptions({
            /** input */
            }),
        
          );
    ```

    여기서 자꾸 Query data cannot be undefined.가 나서 이것 저것 별거 다 해보다가 
    혹시나해서 prefetch 하는 부분 지우고 해보니 에러 안나와서 이것저것 보다가
    void 없애고 await하니까 제대로 작동 ㅠㅠ
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

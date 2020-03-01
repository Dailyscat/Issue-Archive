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

# Issue: Context API와 Typescript를 함께 사용할 때 createContext 관련 이슈

## 상황:

<br/>

## 방안: 타입 단언 (성공)

<br/>
 문서와 다른 예제들을 참고했을 때, 보통의 상황에서는 createContext를 호출할 때 빈 객체 값을 주고 provider의 props로 실제 사용하는 객체를 넘겨줘도 실행이 됐습니다. 하지만 Typescript와 함께 사용하다보니 처음 호출할 때 부터 사용하는 state의 타입을 지정해줘야 했습니다. 이를 위해 provider에서 사용하는 속성들을 interface화 하여 이를 타입 단언을 활용하여 빈 객체와 함께 호출하도록 설정하고 후에 provider 컴포넌트에서 실제로 사용하는 상태를 적용하여 상태 관리를 진행할 수 있었습니다.
<br/>
<br/>
<br/>

        참조:
        https://ko.reactjs.org/docs/context.html#contextconsumer
        https://react.vlpt.us/using-typescript/04-ts-context.html
        https://react.vlpt.us/mashup-todolist/02-manage-state.html#
        https://velog.io/@velopert/typescript-context-api
        https://www.toptal.com/react/react-context-api
        https://stackoverflow.com/questions/55133907/react-with-typescript-type-is-missing-the-following-properties-from-type
        https://github.com/Microsoft/TypeScript-React-Starter/issues/155
        https://github.com/DefinitelyTyped/DefinitelyTyped/pull/20987
        https://www.daleseo.com/react-context/
        https://velog.io/@velopert/tdd-with-react-testing-library
        https://medium.com/lunit-engineering/context-api%EA%B0%80-redux%EB%A5%BC-%EB%8C%80%EC%B2%B4%ED%95%A0-%EC%88%98-%EC%9E%88%EC%9D%84%EA%B9%8C%EC%9A%94-76a6209b369b

<br/>

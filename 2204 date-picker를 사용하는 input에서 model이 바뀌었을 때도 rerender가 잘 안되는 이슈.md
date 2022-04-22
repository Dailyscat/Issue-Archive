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

# Issue: date-picker를 사용하는 input에서 model이 바뀌었을 때도 rerender가 잘 안되는 이슈

## 상황: 
인풋 모델을 바꾸는데도 인풋이 잘 변경되지 않고 빈칸으로 뜨거나 이전의 값을 계속 가지고 있음. 즉 리렌더가 안됨.

## 생각해낸 방안:

- :key 값의 변화를 통해 vue에게 리렌더가 필요하다고 알려줌.

## 방안: :key 값의 변화를 통해 vue에게 리렌더가 필요하다고 알려줌.

<br/>

key에 변화가 되는 값을 매핑함으로써 개발자는 vue에게 해당 컴포넌트가 특정 값, 데이터에 연결되어 있음을 알린다.

리액트에서 리스트 렌더링 시에 키 값을 넣어서 최적화를 하는 방법과 유사하다.

<br/>
<br/>
<br/>

        참조:
        https://michaelnthiessen.com/force-re-render/

<br/>

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

# Issue: antd의 expandedRowRender 속성에 rowkey가 없으면 모든 속성을 제대로 사용할 수 없는 이슈

## 상황:

expandedRowRender 속성을 사용할 일이 있었는데 속성이 제대로 동작하지 않음

<br/>

## 생각해낸 방안:

- antd 자체의 codepen에서 테스트하여 차이점 파악
- rowkey를 주지 않으면 각 요소를 구분할 수 없을거라고 생각하고 이처럼 요소요소 자체의 기능을 위해서는 rowkey가 필요할거라고 생각하여 rowkey 삽입

<br/>

## 방안: antd 자체의 codepen에서 테스트하여 차이점 파악 (과정)

<br/>

큰 차이점이 없었다.
<br/>
<br/>
<br/>

        참조:
        https://codepen.io/pen/?&editable=true&editors=001

<br/>

## 방안: rowkey를 주지 않으면 각 요소를 구분할 수 없을거라고 생각하고 이처럼 요소요소 자체의 기능을 위해서는 rowkey가 필요할거라고 생각하여 rowkey 삽입 (성공)

<br/>

React의 동작 방식에 집중을 하여, list는 key를 주는 것을 권장하는 부분에 중점을 두고 생각했다. 뿐만아니라 어떤 방식으로든 요소를 구분해야 할것이라고 생각이들어서 rowkey를 주니 해결이 됐다.

가장 큰 문제는 이와 같은 설명이 antd 문서에는 전혀 명시되어 있지 않다 ㅠ 그래서 이슈를 올렸다ㅠㅠ
<br/>
<br/>
<br/>

        참조:
        https://github.com/ant-design/ant-design/issues/15028

<br/>

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

# Issue: antd의 expandedRowRender가 특정 컴포넌트에서만 열리지 않는 Bug

## 상황:

메모기능을 작성하면서 expandedRowRender 속성을 사용해야 했는데, 이게 유독 admin의 User 단, 즉 부모, 튜터 단에서는 열리지가 않았다. 다른 요청 단과는 코드도 다를게 없는 상태.

<br/>

## 생각해낸 방안:

- 테이블의 각 행에 onClickRow속성이 활성화 되어 있어서 eventBubbling으로 인한 하위 클릭 이벤트가 실행되지 않는가를 확인
- 이전에 겪었던 rowKey 속성의 부재로 인한 이슈를 바탕으로 rowKey가 제대로 들어가고 있는지 확인

<br/>

## 방안: 테이블의 각 행에 onClickRow속성이 활성화 되어 있어서 eventBubbling으로 인한 하위 클릭 이벤트가 실행되지 않는가를 확인 (실패)

<br/>
  행에 행 자체를 클릭하면 새로운 모달이 뜨는 메소드가 존재했어서 expandedRowRender를 추가하면 생기는 + 버튼의 클릭 메소드가 상위 이벤트로 인해 막혀있나 확인을 해봤다.

그 메소드를 꺼도 동작하지 않았고, Table을 컴포넌트로 만들어서 공통적으로 사용하고 있었기 때문에 다른 Table을 사용하는 페이지에서 확인을 해봐도 여전히 User단에서만 열리지 않는 상태

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: 이전에 겪었던 rowKey 속성의 부재로 인한 이슈를 바탕으로 rowKey가 제대로 들어가고 있는지 확인 (성공)

<br/>

2월 달 쯤에 expandedRowRender 속성과 관련하여 issue를 올린적이 있는데 그때의 이슈는 각 행에 rowKey 속성을 주지 않으면 table에서 제공되는 모든 메소드가 제대로 동작하지 않는다는 이슈였다.

이때의 기억을 살려서 혹시나 하고 rowKey를 살펴보았는데, rowKey 속성자체는 들어가고 있었지만 그 속성을 던져주는 datasource 내부의 key값으로 설정되는 속성의 구조가 문제였다.

User에서의 객체 구조는 예를들어 datasource.\_id를 확인하여 각 행에 대한 id를 확인하는데
다른 요청, 프로그램, 신청서 단은 datasource.\_id.\_str 의 방식으로 id를 확인하고 있었다.

<br/>
<br/>
<br/>

        참조:

<br/>

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

# Issue: 자식에서 margin이 먹지 않을 때

## 상황: 자식 div에서 margin을 주는데 먹지않음.

<br/>

## 알게된 부분 정리:

- collapsing margin(마진 겹침 현상) 현상

<br/>

## 개념: collapsing margin 현상

<br/>
  마진상쇄 발생 3가지 상황

  1. 인접 형제 박스 간 상하 마진이 겹칠 때
    겹쳐진 두 마진 값을 비교해, 더 큰 마진 값으로 상쇄해 렌더링합니다. 만약 겹쳐진 두 값이 동일할 경우, 중복을 상쇄해 렌더링합니다.
    ![](/image/2022-05-05-22-39-08.png)

  2. 빈 요소의 상하 마진이 겹칠 때

    '빈 요소' 란 높이(height)가 0인 상태의 블록 요소를 말합니다.

    height / min-height / padding / border 등 상하로 늘어나는 프로퍼티 값을 명시적으로 주지 않았거나
    내부에 Inline 콘텐츠가 존재하지 않는 요소
    이 경우 위와 아래를 가르는 경계가 없으므로, 자신의 상단 마진의 값과 하단 마진의 값을 비교해 더 큰 값으로 상쇄합니다. 만약 겹쳐진 두 값이 동일할 경우, 중복을 상쇄합니다. 특히 빈 요소와 인접 박스들 간에 마진 겹침이 일어나는 구조에서는 다음과 같이 상쇄가 
    여러 번 발생하게 됩니다.
    
![](/image/2022-05-05-22-40-07.png)

   3. 부모 박스와 첫 번째(마지막) 자식 박스의 상단(하단) 마진이 겹칠 때
    마진이란 콘텐츠 간의 간격이고, 간격을 벌리기 위해서는 경계를 필요로 합니다. 브라우저는 부모 박스와 첫 번째(마지막) 자식 박스 간의 경계를 그 사이에 있는 border / padding / inline 콘텐츠 유무로 판단합니다. 앞에 설명했던 빈 박스의 마진 상쇄 현상과는 조금 다르게, 이미 명시적으로 height / min-height 값을 줬더라도 이번 경우에선 신경 쓰지 않습니다.

    따라서 부모와 첫 번째(마지막) 자식 사이에 inline 콘텐츠(텍스트 등)가 없거나, 상단(하단)에 명시적으로 padding 또는 border 값을 주지 않았다면 마진이 겹치게 됩니다. 이때, 자식 요소의 마진이 더 크든 작든 상관없이 상쇄된 마진은 부모 박스의 바깥으로만 렌더링이 됩니다. 😨

    3-1. 부모 박스와 첫 번째 자식 박스의 상단 마진이 나란히 겹칠 때

![](/image/2022-05-05-22-41-11.png)

    3-2. 부모 박스와 마지막 자식 박스의 하단 마진이 나란히 겹칠 때

![](/image/2022-05-05-22-41-32.png)

    다음과 같이 부모 박스 상단(하단)에 padding 또는 border 값을 주어 벽을 만들어주는 것이 안전합니다. 이렇게 하면 의도했던 대로 첫 번째(마지막) 자식 요소를 배치할 수 있습니다.

![](/image/2022-05-05-22-41-51.png)

    마진 상쇄 규칙 적용
        마진 상쇄는 인접한 두 박스가 온전한 block-level 요소일 경우에만 적용됩니다.
        (inline, inline-block, table-cell, table-caption 등의 요소는 block-level이 아닙니다.)
        마진 값이 0이더라도 상쇄 규칙은 적용됩니다.
        좌우 마진은 겹치더라도 상쇄되지 않습니다.
        마진 상쇄 규칙 예외
        다음과 같은 상황에서는 인접 요소 간 마진 상쇄가 일어나지 않습니다.

        박스가 position: absolute 된 상태
        박스가 float: left/right 된 상태 (단, clear 되지 않은 상태)
        박스가 display: flex 일 때 내부 flexbox item
        박스가 display: grid 일 때 내부 grid item

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/1762539/margin-on-child-element-moves-parent-element
        https://stackoverflow.com/questions/1878997/child-elements-with-margins-within-divs
        https://www.w3.org/TR/CSS2/box.html#collapsing-margins

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

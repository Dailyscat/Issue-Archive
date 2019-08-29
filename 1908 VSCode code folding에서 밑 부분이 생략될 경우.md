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

# Issue: VSCode code folding 상황에서 밑 부분이 생략될 경우

## 상황:

vscode의 왼편의 - 버튼을 클릭해서 코드를 각 div별 혹은 block 별로 접어 놓을 경우 밑 부분이 생략되어서 주석처리를 해야될 때 제대로 되지 못하는 경우
<br/>

## 생각해낸 방안:

- vs code code folding three dot skip으로 검색

<br/>

## 방안: vs code code folding three dot skip으로 검색 (성공)

<br/>

해결 : "editor.foldingStrategy": "indentation"

auto를 주게 되면 언어 관련 접기 전략을 사용하기 때문에
의도한 대로 접히지 않는 경우가 많은 걸 확인했다.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/49744464/vscode-code-folding-error

<br/>

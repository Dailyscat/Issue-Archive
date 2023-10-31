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

# Issue: vscode에서 snippet 추가하여 생산성 향상하기

## 상황:
clean architecture를 적용하는 과정에서 보일러플레이트 코드가 생기다보니
이 부분에 대한 추가가 꽤나 번거롭기도하고 하나 추가 후에 일이 점점 늘어지는 것 같다고 생각이 들었다.
이 부분에 대해서 코드 스니펫 추가.

<br/>

## 알게된 부분 정리:

- 스니펫 추가 정리

<br/>

## 개념: 스니펫 추가 정리

<br/>
  1. `.vscode/snippets.code-snippets` 파일 추가
  2.  [snippet 변환 사이트](https://snippet-generator.app/?description=&tabtrigger=&snippet=&mode=vscode)에서 스니펫 관련 문법을 활용하여 작성

  ```
  ${TM_FILENAME_BASE} 해당 파일 이름
  ${TM_FILENAME_BASE/(.*)/${1:/capitalize}/} 해당 파일이름의 앞 글자만 대문자로
  `데이터 페칭 문제 발생: stateCode = \\${stateCode}, msg = \\${msg}, resn = \\${resn}` 템플릿 리터럴 문법을 사용시에는 달러사인에 대하여 escape 처리 해줘야 한다.
  ```
<br/>
<br/>
<br/>

        참조:
        https://code.visualstudio.com/docs/editor/userdefinedsnippets

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

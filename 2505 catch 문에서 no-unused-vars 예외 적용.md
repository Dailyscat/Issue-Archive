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

# Issue: catch 문에서 no-unused-vars 예외 적용

## 상황:

        "argsIgnorePattern": "^_",
        "varsIgnorePattern": "^_",
        두개는 원래 있었는데 catch 문에서의 인자가 _ prefix를 줘도 제대로 안돼서 찾아보니 따로 설정해줘야했다.

<br/>

## 알게된 부분 정리:

- -

<br/>

## 개념:

<br/>

```
    "@typescript-eslint/no-unused-vars": [
      "warn", // or "error"
      {
        "argsIgnorePattern": "^_",
        "varsIgnorePattern": "^_",
        "caughtErrorsIgnorePattern": "^_"
      }
    ]

```

caughtErrorsIgnorePattern를 따로 적용해야 catch 문에서의 error 인자에 예외 룰 적용 가능.
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

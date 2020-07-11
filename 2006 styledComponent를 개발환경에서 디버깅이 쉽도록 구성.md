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

# Issue: styledComponent를 개발환경에서 디버깅이 쉽도록 구성

## 상황:

해쉬된 클래스네임으로 인해 브라우저상에서의 디버깅이 힘든 부분을 해결하려는 상황

<br/>

## 생각해낸 방안:

- `babel-plugin-styled-components` 적용
- +

<br/>

## 방안: `babel-plugin-styled-components` 적용(성공)

<br/>

- 설치
  - npm install --save-dev babel-plugin-styled-components
- babel 설정파일 추가

```
{
"plugins":  ["babel-plugin-styled-components"]
}
```

<br/>
<br/>
<br/>

        참조:

        https://blog.woolta.com/categories/1/posts/198

<br/>

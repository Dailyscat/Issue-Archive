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

# Issue: markdown-pdf 사용시 한글 폰트 깨짐 에러

## 상황:

md 파일을 pdf로 바꾸느라 익스텐션을 사용했는데 한글이 깨지는 에러

<br/>

## 생각해낸 방안:

- 이전 linux 문제와 같을 것이라고 생각하고 extension의 폰트 설정 바꾸기

<br/>

## 방안: (성공)

<br/>

다른 사람들도 같은 이슈를 겪을 것 같아서 [이슈](https://github.com/yzane/vscode-markdown-pdf/issues/148)를 날렸당.

Situation:
Korean word are not properly printed.

ex:
간 => 가ㄴ, 같 => 가ㅌ

solution:
(1) Markdown-pdf: The path of Styles in vscode must be modified.
(2) `"markdown-pdf.styles": [ "/yours!!!/markdown-pdf.css" ],`
(3) The contents of markdown-pdf.cs are the same as the code.

```
body {
  font-family: "Segoe WPC", "Segoe UI", "SFUIText-Light", "HelveticaNeue-Light", sans-serif;
}
```

In other words, "Droid Sans Fallback" must be deleted.

_p.s_
For directories, the absolute path starting at /home is recommended for the Linux user.

<br/>
<br/>
<br/>

        참조:
        https://github.com/yzane/vscode-markdown-pdf/issues/148

<br/>

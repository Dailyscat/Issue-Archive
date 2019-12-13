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

# Issue: React에서 textarea의 value 값 중 enter(/n)값을 인식하여 뷰가 나오도록 처리

## 상황:

React에서 textarea의 value 값 중 enter 값을 인식하여 뷰가 나오도록 처리하는 상황

<br/>

## 생각해낸 방안:

- 정규표현식을 활용하여 "\n"을 `<br>` 태그로 replace
- dangerouslySetInnerHTMl 사용하여 처리
- "\n"을 split하여 react 내부에서 map을 활용하여 배열로 처리

<br/>

## 방안: 정규표현식을 활용하여 "\n"을 `<br>` 태그로 replace (실패)

<br/>

    문자열.replace(/(?:\r\n|\r|\n)/g, '<br />');

이를 활용하려고 했지만 react 자체에서는 XSS방지를 위하여 문자열 내부 태그를 인식하지않고 문자열 그대로 렌더링한다.

<br/>
<br/>
<br/>

        참조:
        http://naminsik.com/blog/3282

<br/>

## 방안: dangerouslySetInnerHTMl 사용하여 처리 (성공)

<br/>

사용할 수 있고 어떻게 보면 가장 직관적인 방법이지만 취약점을 굳이 만들 필요가 없고, 단순하게 br 태그만이 필요했기 때문에 다른 방법을 선택하게 됐다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: "\n"을 split하여 react 내부에서 map을 활용하여 배열로 처리 (성공)

<br/>

    {
      data.split('\n').map( line => {
        return (<span>{line}<br/></span>)
      })
    }

[벨로퍼트님 블로그](https://velopert.com/1896)를 참조했다.

<br/>
<br/>
<br/>

        참조:
        https://velopert.com/1896

<br/>

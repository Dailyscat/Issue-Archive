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

# Issue: img 태그에서 rendering 된 이미지가 inline 속성이기 때문에 발생하는 에러

## 상황:

<img src="./image/img 태그에 이미지가 렌더링 될때 생기는오류.png">

이미지를 감싸는 div의 background에 색을 줬을 때 감싼 div가 더 커지는 이유

<br/>

## 생각해낸 과정:

- div 내에 기본적인 padding이 있을 수 도 있어서 css reset
- 이미지가 처음 이미지 태그에서 렌더링 될 때 inline 속성으로 렌더링되어서 글자처럼 취급

<br/>

## 방안: div 내에 기본적인 padding이 있을 수 도 있어서 css reset (과정1)

<br/>

https://meyerweb.com/eric/tools/css/reset/

기본적으로 운영체제의 영향을 받을 수 도 있기 때문에 css reset을 실행했지만 안됐다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: 이미지가 처음 이미지 태그에서 렌더링 될 때 inline 속성으로 렌더링되어서 글자처럼 취급 (과정2)

<br/>
  
    By default, an image is rendered inline, like a letter.
    It sits on the same line that a, b, c and d sit on.
    There is space below that line for 
    the descenders you find on letters like f, j, p and q.
    You can adjust the vertical-align of the image 
    to position it elsewhere (e.g. the middle) or 
    change the display so it isn't inline.

기본적으로 이미지는 inline으로 렌더되고 이는 글자처럼 취급이 된다. 그래서 이를 포함한 div는 이미지 보다 큰 규격을 갖게 된다 여기서 나오는 2px 차이였다.

여러가지 방법이 있는데

1. 이미지의 vertical-align을 middle

2. 이미지의 display 속성을 "block"으로 처리

3. 이미지를 감싼 div의 line-height를 0으로 처리하는 방법이었다.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/5804256/image-inside-div-has-extra-space-below-the-image

<br/>

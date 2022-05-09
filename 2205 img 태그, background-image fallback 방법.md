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

# Issue: img 태그, background-image fallback 방법

## 상황:
기본 이미지 필요로 하는 상황

<br/>

## 알게된 부분 정리:

- img src에 있는 이미지가 500일 때, background-image가 500일 때

<br/>

## 개념: img src에 있는 이미지가 500일 때, background-image가 500일 때

<br/>
  
  ```
  <img src="noimg.jpg" onerror="this.src='https://s.pstatic.net/static/www/img/uit/2019/sp_search.svg';"/>
  ```

  ```
  div{   
     background-image: url('http://placehol/1000x1000'), url('http://placehold.it/500x500');
     background-repeat:no-repeat;
     background-size: 100%;
     height:200px;
     width:200px;
    }
  ```


<br/>
<br/>
<br/>

        참조:
        https://zxchsr.tistory.com/16
        https://www.w3schools.com/tags/ev_onerror.asp

        https://stackoverflow.com/questions/37588017/fallback-background-image-if-default-doesnt-exist

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

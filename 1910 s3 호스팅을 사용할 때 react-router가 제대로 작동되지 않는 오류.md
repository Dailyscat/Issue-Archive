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

# Issue: s3 호스팅을 사용할 때 react-router가 제대로 적용되지 않는 오류

## 상황:

react-router를 사용하여 push 등의 이벤트가 호출되어 주소가 바뀔 때, 404가 발생한다.

<br/>

## 생각해낸 방안:

- 정적 호스팅 설정에서 Error document에 index.html 설정하기

<br/>

## 방안: 정적 호스팅 설정에서 Error document에 index.html 설정하기 (성공)

<br/>
  
  검색을 통해 Tricky한 방법을 찾았다.

s3 버켓의 주소는 그 폴더 자체를 바라보는데 url 자체가 바뀌면 폴더 내부의 자원을 바라보니까 호스팅 자체가 안되는거다. 이때 간단하게 Error 발생에 해당하는 문서를 줄 때 index.html을 주면 제대로 작동한다.

<img src="https://i.stack.imgur.com/qTd8l.png">
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/51218979/react-router-doesnt-work-in-aws-s3-bucket

<br/>

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

# Issue: Local Path의 이미지를 canvas로 그려내지 못하는 이슈

## 상황:

accessory akinator 만드는 과정에서 image.src를 하고 onload에 이벤트를 주는 과정에서 그림이 그려지지를 않아서 관련 이슈를 찾아봤던 상황

<br/>

## 생각해낸 방안:

- https에 저장된 이미지들을 로딩
- canvas의 security rule에 의하여 로컬이미지 그려지지 않음

<br/>

## 과정: https에 저장된 이미지들을 로딩 (실패)

<br/>
  웹에서 사용하고 있는 이미지 url을 가져와서 사용해보니 제대로 그려졌고 로컬쪽에서 사용하는 이미지가 문제임을 확인했다.
  그래서 local에서의 canvas 사용과 관련한 이슈가 있는지를 확인했다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: canvas의 security rule에 의하여 로컬이미지 그려지지 않음

(성공)

<br/>

> If a canvas is allowed to draw local files to itself then it could potentially draw a file that is on your local drive (private to you), get its imageData, and upload that file to a server, effectively stealing the image. We can't have that, so the "local files break origin-clean" rule is in place.

역: 간단하게 canvas에 로컬 이미지 파일을 허용하면 canvas를 사용하여 사용자의 컴퓨터 내부에서 쉽게 파일들을 훔칠 수 있고 잠재적인 위험이 있기 때문에 이를 막아놨다는 얘기

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/10187306/canvas-todataurl-throws-security-exception-despite-image-being-local
        https://dzone.com/articles/understanding-html5-canvas

<br/>

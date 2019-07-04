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

# Issue: mobile의 햄버거 버튼 클릭 했을 때 sider ux를 개선

## 상황: mobile view의 햄버거 버튼을 클릭 했을 때 sider가 튀어나오는 것이 깜짝깜짝 놀라는 경험을 주는 것 같아서 다른 방식으로 사이더가 나오도록 퍼블리싱해야하는 상황

<br/>

## 생각해낸 방안:
+ html 구조와 css를 아예 새로 만들자.
+


<br/>


## 방안: html 구조와 css를 아예 새로 만들자. (성공)
<br/>
  
  <img src="./image/mobile의 slider ux 개선 전.gif">
  <img src="./image/mobile의 slider ux 개선 후.gif">

  첫 이미지는 개선 전이미지이고,
  두번째 이미지는 개선 후 이미지이다.

  첫번째 이미지를 보면 햄버거 버튼을 클릭했을 때 순간적으로 검은화면이
  전체에 퍼지며 사용자에게 깜짝 하는 느낌을 주곤 했다.

  이를 없애고 사이더를 마치 서랍을 여는 듯한 느낌으로 부드러움을 주려고 생각했다.

  두번째 이미지를 보면 클릭했을 때 fadeIn과 faedOut의 느낌을 살려서 
  다시금 리퍼블리싱 한 것을 알 수 있다.

  이전 코드는 <br> 
    div <br>
    div <br>
    위 처럼 div 두개를 사용하여 <br>
    첫번 째 div에 transform: translateX( 100vw ); <br>
    두번 째 div에 transform: translateX( 200px ); <br>
    이렇게 주고 transition이 없이 만들어서 
    각각이 state와 className을 이용하여 클릭할 때마다 새로 생기게 하여 깜짝깜짝 놀라는 ux를 만들었다.

  개선 후 코드는 <br> 
    div <br>
    &nbsp;&nbsp;&nbsp; div <br>
    &nbsp;&nbsp;&nbsp; div <br>
    위 처럼 div 3 개를 사용하여 맨 위의 div는 parent의 역할로 두고
    아래 div들 중 첫번 째 div는 stackingContext를 이용하기위해
    검은색 화면을 담당하게 만들고
    두번째 div는 사이더 내부의 컨텐츠를 담당하게 만든다.

        .forSliderBackground.--show {
            opacity: 0.92;
            transform: translateX( 0 );
        }
        .sider-content.--show {
            transform: translateX( 45vw );
        }
<br>
    클릭되기 전 원소의 css 값에 transition들을 추가하여 부드럽게 여닫는 부분을 완성했다.

<br/>
<br/>
<br/>

        참조:

<br/>


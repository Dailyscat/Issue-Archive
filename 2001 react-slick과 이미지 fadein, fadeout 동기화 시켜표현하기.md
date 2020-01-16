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

# Issue: react-slick과 별도로 이미지 fadein, fadeout 동기화 시켜표현하기

## 상황:

react-slick 자체에 fadein, fadeout 기능이 있긴한데 기획에서의 주문은 slick은 옆으로 넘길 수 있는 슬라이드 자체 기능이 있어야하고 이미지만 그에 따라 변경이 되어야 하는 상황.

<br/>

## 생각해낸 방안:

- slick의 beforeChange 함수를 사용해서 state에 따라 변경
- slick의 afterChange 함수와 image를 prerender 하여 사용하고, 이를 z-index, opacity, transition을 활용하여 fadein, out을 자연스럽게 구현

<br/>

## 방안: slick의 beforeChange 함수를 사용해서 state에 따라 변경 (실패)

<br/>

template string을 사용하여 slideIndex가 바뀔 때마다 image를 바꿔주는 방식을 처음에 사용해봤는데,

    `../image/image${this.state.slideIdx}.png`

대충 위와 같은식인데 이 방법의 가장 큰 단점은 State 변화에 따른 Rendering + 이미지를 불러왔을 때의 새로운 Loading이다. 슬라이드를 할 때 beforeChange함수가 실행되고, state가 바뀌고, 렌더링되고 한 div에서 이미지를 불러오다보니 슬라이드자체가 느려지고 이미지가 확 바뀌는 굉장히 오류가 많아보이는 UI가 발생됐다. 그때의 UI를 커밋해놨어야했는데.. 어쨌든 너무 구렸다.

여기서 base64로 인코딩하여 좀 더 빠른 로드라면 가능할까? 도 시도해보고, 다른 여러 방식들을 차용해봤지만 근본적으로 preload가 필수적이었다.

<br/>
<br/>
<br/>

<br/>

## 방안: slick의 afterChange 함수와 image를 preload 하여 사용하고, 이를 z-index, opacity, transition을 활용하여 fadein, out을 자연스럽게 구현 (성공)

<br/>

(1) afterChange 함수를 활용하여 slider가 바뀐 후의 처리가 되도록 하여 slide 될 때의 버벅임을 없앴다.
(2) image가 미리 렌더링 되어 있는 상태에서 위치만 바꿔주는.. 이미지 스프라이트의 아이디어를 차용하여 absolute와 z-index를 사용하여 구성했다.
예를들면

    <div>
      <img/>
      <img/>
      <img/>
      <img/>
    </div>

위와 같은 구조에서
각각의 이미지의 css 속성을 아래와 같이 주었다.

    position: absolute;
    width: 100%;
    min-height: 370px;
    background-repeat: no-repeat;
    background-size: cover;
    opacity: 0.2;
    transition: opacity 2.3s ease-out;

결국엔 각각 image는 위의 html 구조로 인하여 처음부터 로딩이 되어있는 상태고, 이를 absolute를 통하여 미리 로딩이 되어 있는 상태에서 뒤에서 앞으로 겹쳐져 있다. 그리고 state에 따른 className을 동적으로 주어서 z-index와 opacity를 자연스러운 효과와 딜레이 시간을 활용하여 faedin,out을 구성할 수 있었다.

<img src="./image/슬라이더&#32;트랜지션&#32;적용.gif"/>
<br/>
<br/>
<br/>

        참조:

        html - How much faster is it to use inline/base64 images for a web site than just linking to the hard file? - Stack Overflow

        https://stackoverflow.com/questions/1574961/how-much-faster-is-it-to-use-inline-base64-images-for-a-web-site-than-just-linki

        Preloading images with JavaScript - Stack Overflow
        https://stackoverflow.com/questions/3646036/preloading-images-with-javascript

        Preloading CSS Images - Stack Overflow
        https://stackoverflow.com/questions/1373142/preloading-css-images

        css - Does "display:none" prevent an image from loading? - Stack Overflow
        https://stackoverflow.com/questions/12158540/does-displaynone-prevent-an-image-from-loading

<br/>

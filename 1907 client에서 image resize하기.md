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

# Issue: client에서 image resize.

## 상황: 트래픽이 올라감에 따라 대역폭이 올라가는데 이미지 업로드시 resize가 안되어 올라가던 상황이라 resize가 시급했다.

<br/>

## 생각해낸 방안:
+ library 사용하기
+ canvas에 새로 그려서 저장하기


<br/>

## 방안: library 사용하기 (실패)
<br/>

  cropper.js, pica 등을 사용하려고 고려했다.

  하지만 둘다 사용하지 않았는데 이유를 정리해봤다.

  #### pica
  + 플랫폼 특성상 ie를 사용하시는 분들도 분명히 있는데, ie 11에 대한 지원하지 않음
  + 다른 옵션들이 필요하지 않았기 때문에 사용하지 않음

  #### cropper.js
  + resize 특화 툴이 아닌 crop을 위한 툴이기 때문에 쓸데 없는 용량을 차지한다는 생각
  + 문서에 사용 되는 resize 메소드는 정확한 면적이 전제 되고 나서야 사용할 수 있는 메소드.
  + 다른 옵션들이 필요하지 않았기 때문에 사용하지 않음

<br/>

두 라이브러리 다 공통된 blob 객체를 사용하는 형식이었고 마찬가지로 전달되는 image는 file 객체였기 때문에 blob객체를 사용하여 resize하는 방법을 찾는 방향으로 틀었다.

    File 객체는 특정 종류의 Blob이며, Blob을 사용할 수 있는 모든 맥락에서 사용할 수 있습니다.

<br/>
<br/>
<br/>

        참조:
        https://developer.mozilla.org/ko/docs/Web/API/File
        https://stackoverflow.com/questions/27553617/convert-blob-to-file
        https://developer.mozilla.org/en-US/docs/Web/API/Blob
        https://github.com/nodeca/pica


<br/>

## 방안: canvas에 새로 그려서 저장하기  (성공)
<br/>
  과정 정리

  #### (1) file 객체에서 url 만들기
  file 객체에서 url을 만들고 image태그의 src로 연결하여 로드되도록 해주는게 첫번째 였다.
  이때 createObjectURL과 FileReder.readAsDataUrl 중 어떤 것으로 url을 생성할 것인지를 고민했는데, 결론적으로는 createObjectURL을 사용했다.

  이유로는 동기이며, 메모리 사용이 base64 기반의 fileReader보다 적었기 때문이다. 위와 같은 이유로 더 빠르고 효율적이라고 생각했다.
  <a href="https://stackoverflow.com/questions/31742072/filereader-vs-window-url-createobjecturl">
  참고
  </a>
  사용 후 revoke메소드를 사용하여 브라우저에서 이 파일에 대한 참조를 유지하지 않도록 해제 시켰다.(메모리관점)

  #### (2) canvas를 사용하여 그려주기
  canvas에 어떻게 사진을 그리게 해주는지가 두번째 였다.
  비율이 깨지지 않아야 하고, 어떤 압축을 사용해야하는지가 고민거리였다.
  비율 문제는 [drawImage에 scaling이 내장되어있다는]("https://stackoverflow.com/questions/6752366/resizing-photo-on-a-canvas-without-losing-the-aspect-ratio") 의견을 참고하여 codepen에서 테스트를 해봤는데 제대로 작동하지 않아서 [spec](https://html.spec.whatwg.org/multipage/canvas.html#drawing-rectangles-to-the-bitmap)을 확인해보니 그림을 담을 보관 요소의 w,h가 제대로 잡혀있을 경우에 scale이 작동한다고 적혀있었다.

  전체적으로 canvas를 이용해 image를 리사이즈 하는 과정은
  canvas 요소를 만들고, getContext(2d)메소드를 사용하여 리턴된 context에 drawImage를 사용하여 그린다.

  이 과정을 따르는 도중에 이미지가 제대로 중간으로 가지 않던 문제가 발생했다.

  이는 canvas자체의 w,h가 잡혀있지 않은 상태에서 오는 오류였다. 처음에는 비율을 제대로 잡아주지 않아서 그런건가 하여 이것저것 시도를 했다.


  #### (3) toBlob의 ie 11 호환시키기

  polyfill을 넣어서 처리해야했는데, 처음 toBlob의 polyfill로 확인한게 이 [함수](https://github.com/Financial-Times/polyfill-library/blob/master/polyfills/HTMLCanvasElement/prototype/toBlob/polyfill.js)였다. 그런데 이 함수를 index.html의 스크립트 태그 사이에 넣는게 약간 내 맘에 들지 않았고 cdn을 찾다가 polyfill.io로 가서 찾아서 넣을 수 있었다.

  굳이 cdn을 사용하지 않고, 이 함수를 업로드가 실행 될 때 useragent를 사용해서 ie일 때만 작동하게 해야하는지에 대한 고민이 남아있다.

  #### (4) image가 max값 보다 적을 때, 클때 구분하여 함수 호출

  업로드 하는 이미지가 max로 둔 값보다 적을 때는 max값 까지 키워지는게 아니라 단순히 다시 그리게 해야했다.

  이를 위해 작성한 알고리즘이다.

    if( img.width > maxWidth || img.height > maxHeight ) {
        if( !maxHeight ) {
            ratio = maxWidth / img.width;
            width = img.width * ratio || 0;
            height = img.height * ratio || 0;
        } else if ( maxWidth && maxHeight ) {
            ratio = Math.min(maxWidth / img.width, maxHeight / img.height);
            width = img.width * ratio || 0;
            height = img.height * ratio || 0;
        }
    } else {
        width = img.width || 0;
        height = img.height || 0;
    }

  #### (5) png로? jpeg로?

  보통 의견은 사진은 jpeg 아이콘은 png 이다.
  kb 단위로 따지지만 png 자체는 바탕이 투명이라 jpeg보다 3배정도의 크기를 가지고 있었다.
  하지만 jpeg는 바탕이 검은색 혹은 다른 색으로 적용이 되어서 혹시 나중에 문제가 될까 고민이 됐다.
  결국엔 용량의 관점에서 큰 차이는 아니지만 서비스의 확장성으로 봤을 때 수kb차이도 나중엔 다 돈이 될 것이라고 생각하여 jpeg로 갔다.

  여기에 추가로 질문을 하다가 얻은 인사이트가 있다.

  [imageSprite](http://tcpschool.com/css/css_basic_imageSprites)
  [chromakeys](https://www.w3.org/2013/chroma/)
  [Using SVG to Shrink Your PNGs](https://peterhrynkow.com/how-to-compress-a-png-like-a-jpeg/)



<br/>
<br/>
<br/>

        참조:
        (1)
        https://developer.mozilla.org/ko/docs/Web/API/FileReader/readAsDataURL
        https://developer.mozilla.org/ko/docs/Web/API/URL/createObjectURL
        https://stackoverflow.com/questions/31742072/filereader-vs-window-url-createobjecturl
        https://developer.mozilla.org/ko/docs/Web/API/URL/revokeObjectURL
        https://developer.mozilla.org/en-US/docs/Web/API/File/Using_files_from_web_applications

        (2)
        https://stackoverflow.com/questions/23733787/canvas-drawimage-is-not-working-properly-on-image-with-css-height-and-width
        https://developer.mozilla.org/ko/docs/Web/API/CanvasRenderingContext2D/drawImage
        https://zocada.com/compress-resize-images-javascript-browser/
        https://stackoverflow.com/questions/6752366/resizing-photo-on-a-canvas-without-losing-the-aspect-ratio
        https://stackoverflow.com/questions/49759386/resize-image-in-the-client-side-before-upload

        (3)
        https://webclub.tistory.com/270
        http://hacks.mozilla.or.kr/2014/12/an-easier-way-of-using-polyfills/
        https://github.com/Modernizr/Modernizr/wiki/HTML5-Cross-Browser-Polyfills

        (5)
        https://foxcg.com/362
        https://stackoverflow.com/questions/8371510/canvas-reduces-imagesize-of-jpeg-but-why
        https://stackoverflow.com/questions/16906144/transparent-background-in-jpeg-image


<br/>


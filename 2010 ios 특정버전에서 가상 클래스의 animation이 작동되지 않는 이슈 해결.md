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

# Issue: ios 특정버전(13.5)에서 가상 클래스의 animation이 작동되지 않는 이슈

## 상황: 마크업 페이지에서는 잘 나오는 animation이 프로덕트에서 시작이 안되는 이슈

## 생각해낸 방안:

- 가상클래스에 애니메이션을 적용했을 때 가지고 있는 영역(width, height 등)이 없으면 애니메이션이 실행되지 않는다는 글을 보고 영역 주입
- browser에서 reflow가 트리거 되도록 실행

## 방안(실패): 가상클래스에 애니메이션을 적용했을 때 가지고 있는 영역(width, height 등)이 없으면 애니메이션이 실행되지 않는다는 글을 보고 영역 주입

<br/>
width, height 값 주입, content에 글자 주입, 빈칸 주입
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/49269614/why-does-reflow-need-to-be-triggered-for-css-transitions

<br/>

## 방안(성공): browser에서 reflow가 트리거 되도록 실행

<br/>

렌더트리에서 적용만 안되는 것이기 때문에 reflow가 발생했을 때 애니메이션이 정상적으로 작동되는 것을 확인하여
document.querySelector('.outlink_bridge .button_gradient').style.display='none'; document.querySelector('.outlink_bridge .button_gradient').offsetHeight; document.querySelector('.outlink_bridge .button_gradient').style.display='block';
해당 코드로 강제로 reflow를 발생시키려고 했으나 $(window).load의 콜백, setTimeout으로도 reflow가 일어나지 않았습니다.(inspector에서는 해당 코드로 리플로우가 발생)
5. querySelector, setTimeout은 동작했으나 console.log는 찍히지 않는 것으로 보아 server 단에서 렌더링이 이루어질 때 위의 세 코드가 적용되었고 브라우저에서 js가 실행되지 않는 현상으로 생각되었지만 이는 확실하지는 않습니다. 캐시를 전부 지우고 페이지를 처음에 로드할 때 보통 생기는 현상이었고 bridge 페이지를 한번 들어갔다 나오면 브라우저 자체에서 렌더트리에 관한 캐시를 가지고 있기 때문에 해당 현상은 일어나지 않았으므로 아마 위에 언급한 것과 같이 서버에서 만들어져서 렌더트리 형성 과정에서 어떤 문제가 있었을 것이라고 추측합니다.
6. 해당현상 해결을 위해 렌더링 트리가 그려진 후 button 노드를 동적으로 삽입하는 코드로 일단 대체해두었습니다.

정리하면 문제되는 부분은

pseudo class에 애니메이션을 준 부분이 특정 ios에서 호환되지 않는 부분이 있다.
처음 로드 될 때 js 파일 내부의 web에서만 이용하는 api가 불리지 않는다.
입니다.
참고로

ftl에 버튼 마크업을 넣어두고 해당 버튼 노드를 deep 카피하여
원래 있던 버튼 노드를 삭제 후 카피한 버튼 노드를 넣어보는 시도를 해봤지만 원래도 잘 동작하는 환경들에서 progress bar가 움직이는게 가다가 다시 처음부터 시작하게 되어서 삭제하였습니다.

setTimeout을 사용하지 않으면 render 전에 그려지기 때문에 reflow가 발생하지 않기 때문에 소용이 없습니다.

Bridge directive에 코드를 넣어보려 하다가 마크업 자체를 구성하는 코드라고 생각되어 ftl에서 발생시켰습니다.

서버사이드에서 렌더링 될 때 after pseudo 클래스에서 준 애니메이션이 동작되지 않는 문제로 판단되어
기존에 after pseudo 클래스에서 준 애니메이션을 새로 만든 정적 요소에 줌으로써 해결했습니다.


<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/3485365/how-can-i-force-webkit-to-redraw-repaint-to-propagate-style-changes
        https://stackoverflow.com/questions/16050914/css-animation-doesnt-restart-when-resetting-class
        https://stackoverflow.com/questions/37035335/css-transition-fails-on-jquery-load-callback
        https://css-tricks.com/restart-css-animation/
        https://stackoverflow.com/questions/38327542/css-transition-doesnt-work-on-pseudo-element
        https://stackoverflow.com/questions/31794070/css-transition-on-a-pseudo-element-doesnt-work-the-second-time
        https://stackoverflow.com/questions/43219242/how-to-edit-text-style-with-javascript

<br/>
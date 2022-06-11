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

# Issue: content-visibility 정리

## 상황:
content-visibility,  contain-intrinsic-size 간단 정리

<br/>

## 알게된 부분 정리:

- css contain
- content-visibility
- contain-intrinsic-size

<br/>

## 개념: css contain

<br/>

/* 키워드 값 */
contain: none;      /* 격리없이 평범하게 렌더링함 */
contain: strict;    /* style을 제외한 모든 격리 규칙을 적용함 */
contain: content;   /* size와 style을 제외한 모든 격리 규칙 */
contain: size;      /* 요소의 크기를 계산할 때 자손의 크기는 고려하지 않아도 됨을 나타냄 */
contain: layout;    /* 요소 외부의 어느 것도 내부 레이아웃 영향을 주지 않음 */
contain: style;     /* 요소 자신과 자손 외에도 영향을 주는 속성이라도 그 영향 범위가 자신을 벗어나지 않음 */
contain: paint;     /* 요소가 화면 밖에 위치할 경우 당연히 그 안의 자손도 화면 안에 들어오지 않을 것이므로 브라우저는 그 안 요소 고려하지 않음 */ 

/* 다중 값 */
contain: size paint;
contain: size layout paint;

/* 전역 값 */
contain: inherit;
contain: initial;
contain: unset;
 
브라우저는 ie 및 사파리는 지원하지 않습니다.
 
contains CSS 속성을 사용하면 작성자가 요소와 해당 내용이 가능한 한 나머지 문서 트리와 독립적임을 나타낼 수 있습니다. 
이를 통해 브라우저는 전체 페이지가 아닌 DOM의 제한된 영역에 대해 레이아웃, 스타일, 페인트, 크기 또는 이들의 조합을 다시 계산할 수 있으므로 명백한 성능 이점을 얻을 수 있습니다.
 
float 속성을 사용하였을 때 레이아웃이 간섭받는 상황이 왔을 때도 유용하게 사용할 수 있습니다.
 

img {
  float: left;
  border: 3px solid black;
}

article {
  border: 1px solid black;
  contain: content;
}
브라우저가 이해할 수 있도록 contain content 속성을 통해서 하위 트리만 계산하고 바깥은 고려하지 말라고 알려줍니다.

추후에 브라우저 지원 범위가 확대되면 사용해볼 수 있을 것 같습니다. 
 

<br/>
<br/>
<br/>

        참조:
        https://frontdev.tistory.com/?page=14
        https://developer.chrome.com/blog/css-containment/
        css-tricks.com/lets-take-a-deep-dive-into-the-css-contain-property/
        developer.mozilla.org/en-US/docs/Web/CSS/contain        

<br/>


<br/>

## 개념: content-visibility

<br/>
  content-visibility는 UserAgent가 layout, painting을 포함한 요소의 렌더링 작업을 필요로할 때까지 생략할 수 있도록 합니다. 콘텐츠의 대부분이 화면 밖에 있을 때, content-visibility을 활용해서 렌더링을 생략하게 되면 사용자의 초기 로드 시간이 훨씬 빨라진다.

  브라우저 최적화는 적절한 값이 지정되어야 효과가 있으므로 어떤 값을 써야할지 어려울 수 있습니다. 어떤 값이 가장 효과적인지 직접 적용하면서 확인하거나 또는 다른 속성인 content-visibility을 사용하여 자동으로 적용할 수 있습니다. content-visibility는 최소한의 노력으로 최대 성능 향상을 이룰수 있게 합니다.

    content-visibility 속성에는 여러 값이 있지만, auto는 즉시 성능 향상을 제공하는 값입니다. content-visibility: auto는 layout, style and paint Containment의 효과가 있습니다. 요소가 화면 밖이면(선택되었거나 포커스를 받는 것과 같이 사용자 액션과 관련이 없는 경우) size Containment 효과도 얻습니다.(요소의  painting과 hit-testing도 중지합니다.)

    이것이 무엇을 의미할까요? 바로 요소가 화면 밖에 있는 경우 자손 요소를 렌더링 하지 않습니다. 브라우저는 요소의 내용을 고려하지 않고 크기를 결정합니다. 자손 요소는 styling, layout과 같은 대부분의 렌더링 작업을 생략합니다.

    요소가 뷰포트에 접근하면 브라우저는 더 이상 size containment를 유지하지 않고 사용자가 볼 수 있도록 렌더링 작업(painting과 hit-testing)을 진행합니다.


-- content-visibility를 다 적용하는게 좋은가? 에 대한 답변
https://stackoverflow.com/questions/70416821/can-i-use-content-visibility-on-all-elements
<br/>
<br/>
<br/>

        참조:
        https://wit.nts-corp.com/2020/09/11/6223
        https://github.com/WICG/display-locking/blob/main/explainers/contain-intrinsic-size.md
        https://wicg.github.io/display-locking/sample-code/contain-intrinsic-size-examples.html
        

<br/>

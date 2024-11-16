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

# Issue: html content를 height에 기반하여 복수의 이미지로 만들었을 때 중간이 짤리는 이슈

## 상황:

1000이라는 기준을 잡고 html을 복수개로 나누어서 이미지로 만들었는데
중간에 사진이 있을 경우에 그 부분이 실선으로 나누어지는 이슈

## 생각해낸 방안:

- html 의 children 수를 기반으로 이미지를 만들어서 저장하도록 설정

## 방안:

<br/>

```
const previewElement = div;
const childElements = Array.from(previewElement.children) as HTMLElement[];
const canvasSegments = [];
let currentOffset = 0;

while (currentOffset < childElements.length) {
    element 수를 세어서 기준을 만듬.
  let totalSegmentHeight = 0;
  const segmentLimit = Math.min(currentOffset + 3, childElements.length);
  3개 정도로 기준을 잡고 작성하도록

 elements
  for (let index = currentOffset; index < segmentLimit; index++) {
    totalSegmentHeight += childElements[index].offsetHeight;
  }
  현재 자식의 height를 세서 한 덩어리의 이미지의 height를 설정

  // Create a canvas for the current segment of child elements
  const canvas = await toCanvas(previewElement, {
    width: previewElement.scrollWidth,
    height: totalSegmentHeight,
    backgroundColor: 'white',
    cacheBust: true,
    style: {
      transform: `translateY(-${childElements[currentOffset].offsetTop}px)`
    }
  });
  offsetTop으로 현재 el 까지의 y를 잡아서 이미지를 캡쳐하도록 설정

  canvasSegments.push(canvas);
  currentOffset = segmentLimit;
}
```

<br/>
<br/>
<br/>

        참조:

<br/>

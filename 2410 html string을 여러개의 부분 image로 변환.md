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

# Issue: html string을 여러개의 부분 image로 변환

## 상황:

html string을 부분 이미지로 변환해서 배열로 만들어야함.

<br/>

## 알게된 부분 정리:

- 해당 html을 특정 기준의 height로 잘라서 캡쳐

<br/>

## 개념: 해당 html을 특정 기준의 height로 잘라서 캡쳐

<br/>

```
// 전체 높이(totalHeight)를 기준으로 세그먼트 높이(segmentHeight)만큼씩 반복문을 돌림.
for (let offset = 0; offset < totalHeight; offset += segmentHeight) {

  // previewElement를 캔버스로 변환함. 변환할 때, 현재 오프셋만큼 Y축으로 이동하여
  // 특정 부분만을 캡처할 수 있도록 transform 스타일을 적용함.
  // 캔버스의 높이는 segmentHeight와 남은 높이(totalHeight - offset) 중 최소값을 사용하여
  // 마지막 세그먼트가 남은 높이보다 클 경우에도 문제없이 캡처할 수 있도록 함.
  const canvas = await toCanvas(previewElement, {
    width: width, // 캔버스의 너비를 설정함.
    height: Math.min(segmentHeight, totalHeight - offset), // 캔버스의 높이를 설정함.
    style: {
      transform: `translateY(-${offset}px)` // Y축으로 offset만큼 위로 이동하여 해당 부분을 캡처함.
    }
  });

  // 생성된 캔버스를 배열에 추가함.
  canvasArray.push(canvas);
}

// 모든 캔버스에 대해 비동기적으로 처리하여 이미지 URL을 얻음.
const imageUrls = await Promise.all(
  canvasArray.map(async (canvas) => {
    // 캔버스를 Blob으로 변환함. toBlob은 콜백 함수를 통해 Blob을 반환하므로 Promise로 감싸 비동기 처리함.
    const blob = await new Promise<Blob | null>((resolve) => canvas.toBlob(resolve));

    // Blob이 존재하면 이미지 저장소에서 해당 Blob의 URL을 얻음.
    if (blob) {
      return imageRepository.getOurUrl(blob);
    }

    // Blob이 존재하지 않을 경우 null을 반환함.
    return null;
  })
);
```

이때 중요한건 이미지가 다 렌더링 되어 있을 때를 가정한다.

그래서 아래 코드로 html의 img 상태를 확인한다.

```
export async function generateTempDiv(htmlContent: string) {
  const div = document.createElement('div')
  div.className = 'preview'
  div.innerHTML = htmlContent
  document.body.appendChild(div)

  await waitForImagesToLoad(div)
  return div
}

function waitForImagesToLoad(element: HTMLElement) {
  const images = element.querySelectorAll('img')
  const promises = Array.from(images).map((img) => {
    return new Promise((resolve) => {
      if (img.complete) {
        resolve(true)
      } else {
        img.onload = resolve
        img.onerror = resolve
      }
    })
  })
  return Promise.all(promises)
}
```

<br/>
<br/>
<br/>

        참조:
        https://www.npmjs.com/package/html-to-image

<br/>

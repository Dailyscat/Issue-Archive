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

# Issue: html string에서 img태그에 third party domain url을 대체

## 상황:

<br/>

## 알게된 부분 정리:

- canvas를 통해서 다시 그린 뒤 해당 url을 replace

<br/>

## 개념:

<br/>

```
  // imgTags를 배열로 변환하여 각 img 요소에 대해 반복문을 돌림.
  for (const img of Array.from(imgTags)) {
    const src = img.src; // 이미지의 src 속성을 가져옴.

    // 이미지의 src가 'https://aaa'로 시작하지 않는 경우에만 처리함.
    if (!src.startsWith('https://aaa')) {
      // 이미지를 캔버스로 변환함. 여기서 캔버스의 크기를 이미지의 크기로 설정함.
      const canvas = await toCanvas(img, {
        width: img.width, // 캔버스의 너비를 이미지의 너비로 설정함.
        height: img.height // 캔버스의 높이를 이미지의 높이로 설정함.
      });

      // 캔버스를 JPEG 형식의 Blob으로 변환함. 품질은 0.9로 설정함.
      const blob = await new Promise<Blob | null>((resolve) => canvas.toBlob(resolve, 'image/jpeg', 0.9));

      // Blob이 존재하면 새로운 이미지 URL을 얻어 img의 src에 설정함.
      if (blob) {
        const newImageUrl = await imageRepository.getOurImageUrl(blob);
        img.src = newImageUrl;
      }
    }
  }

```

<br/>
<br/>
<br/>

        참조:
        https://www.npmjs.com/package/html-to-image

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

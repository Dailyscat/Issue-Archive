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

# Issue: 다른 origin의 image url을 브라우저에서 canvas로 만들려고 할 때 cors 발생

## 상황:

다른 사이트의 image url을 blob로 만들어서 내 도메인의 image url로 만들려고 하는 과정에서 타 사이트의 image url이 cors를 허용하지 않으면 canvas, blob로 만드는 과정을 브라우저에서 허용하지 않는 이슈 발생

## 생각해낸 방안:

- server에서 이미지 요청하여 bytes로 만든 후 처리

## 방안:

<br/>

```
val restTemplate = RestTemplate()
val imageBytes = restTemplate.getForObject(url, ByteArray::class.java)
val nonNullImageBytes = imageBytes ?: ByteArray(0)

```

<br/>
<br/>
<br/>

        참조:
        https://github.com/bubkoo/html-to-image/issues/40
        https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/crossorigin
        https://github.com/bubkoo/html-to-image/blob/master/src/util.ts

<br/>

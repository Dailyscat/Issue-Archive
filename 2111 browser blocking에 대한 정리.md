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

# Issue: 번들이 작게 많은 것이 무조건 좋은가?

## 상황:
chunk를 작게 많이 만들어서 불러오는것이 무조건 좋은가에 대한 궁금함으로 찾아보는 중 

<br/>

## 알게된 부분 정리:

- 브라우저의 최대 요청 개수가 있기 때문에 이는 지연을 발생시킨다.

<br/>

## 개념: 브라우저의 최대 요청 개수가 있기 때문에 이는 지연을 발생시킨다.

<br/>

크롬의 경우 같은 도메인에서는 최대 6개의 연결을 제한, 다른 도메인에서는 최대 4개의 연결을 제한한다. 즉 같은 서버에서 어떤 자원을 얻을 때(js, css, image, etc) 최대 6개의 자원만 동시에 요청을 보내고 다운로드를 할 수 있으며 다른 도메인도 마찬가지다.

이때 번들을 필요 이상으로 작게 만들어서 여러개의 작은 번들을 요청한다면 이는 더 중요한 번들을 받아오는 데에 지연을 발생시키는 요소가 될 수 있다. 물론 defer와 async 설정 등 최적화 옵션을 넣을 수 있고 6개의 요청이 끝나고 다음 6개의 자원을 동시에 받는 등이 가능할 수 있지만 일반적인 경우에는 한번에 하나의 요청과 다운로드가 끝나고 다시 재개되는 형식이 주를 이룬다. Opera, IE10, 11, & 12 등은 한번에 한 호스트에서 가능한 연결의 개수를 조절할 수 있지만 이는 서버성능에도 좋지않고 다른 문제를 야기할 수 있다(ddos)

하지만 http2를 사용한다면 얘기가 달라진다.
http2는 하나의 요청만을 사용하지만 http/2의 multiplexing 프로토콜을 통해서 거의 병렬 요청에 대한 제한 없이 다운로드가 가능하다.
이러한 부분에서는 최대한 작은 번들이 효율적이다.

이때 http2는 서버에는 또 다른 문제를 야기할 수 있다. 기존에는 최대 6개의 요청이 하나의 리소스만을 가져가기 때문에 해당 상황만을 두고 가용영역을 설정해둔 서버가 http2를 적용했을 때 병렬로 리소스를 요청하게 되기 때문에 cpu문제가 발생할 수 있고 서버에 과부하가 발생하게 할 수 있다.

  
<br/>
<br/>
<br/>

        참조:
        https://blog.bluetriangle.com/blocking-web-performance-villain
        https://stackoverflow.com/questions/36835972/is-the-per-host-connection-limit-raised-with-http-2
        https://stackoverflow.com/questions/36517829/what-does-multiplexing-mean-in-http-2/36519379
        https://www.chrisclaxton.me.uk/chris-claxtons-blog/webpack-chunksplitting-deepdive
        https://www.lucidchart.com/techblog/2019/04/10/why-turning-on-http2-was-a-mistake/
        https://brainbackdoor.tistory.com/113

<br/>


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

# Issue: siteLink 방식으로 검색결과가 보이도록 유도.

## 상황:
구글 검색 결과가 [siteLink방식](https://www.google.com/search?q=%EB%82%98%EC%9D%B4%ED%82%A4&oq=%EB%82%98%EC%9D%B4%ED%82%A4&aqs=chrome..69i57.1474j0j7&sourceid=chrome&ie=UTF-8)으로 나오길 원하는 상황

<br/>

## 생각해낸 방안:
+ react-helmet 추가하여 SEO 처리
+ SSR
+ ld+json 스크립트 추가


<br/>

## 방안: react-helmet 사용하여 SEO 처리 (실패)
<br/>

  react-helmet을 사용하여 SEO처리를 하려 했지만 현재는 index.html에
  태그를 추가하는 것만으로도 가능하다고 생각되어서 일단 패스

  추가로 나중에 클래스 마다 og 태그를 사용하여 주소만 주었을 때도 원하는 사진이 뜨도록 설정하려고 할 때 SSR후, helmet으로 설정하는 부분이 필요할 것 같다.


<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/50193960/react-helmet-facebook-open-graph

<br/>

## 방안: SSR (실패)
<br/>

  meteor와 react자체를 다 생각하며 서버사이드 렌더링 작업을 해야했다. 생각보다는 자료가 많은 편이어서 도입을 많이 생각해 봤지만, 여러 자료를 찾아봤을 때 갑자기 클라이언트에서 사용하고 있던 library가 작동하지 않는다던가, 플러그인 관리를 조금 더 신경써서 해야하는 부분이 있어서 현재 상황에선 적합하지 않는다고 생각했다.

<br/>
<br/>
<br/>

        참조:


<br/>

## 방안: ld+json 스크립트 추가 (추후 확인 예정)
<br/>

  애초에 sitelink가 포함된 검색결과는 구글 봇 자체가 사용자에게 편리할 것이라 생각되는 링크를 자동으로 판단하여 구성하여 표현하는 방식이라고 한다.
  이 부분 때문에 일단 위의 두 가지를 조금 미뤄두었고, 타 사이트들을 참고하며
  ld+json을 스크립트태그로 추가하는 방식을 적용했다.

  ld+json 참고 리포
  [1](https://publicwww.com/websites/%22%40type%5C%22%3A%5C%22SiteNavigationElement%5C%22%22/)
  [2](https://github.com/JayHoltslander/Structured-Data-JSON-LD)
  [3](https://www.hindustantimes.com/)
  [4](https://indianexpress.com/)




<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/12491102/what-is-the-correct-use-of-schema-org-sitenavigationelement
        https://searchengineland.com/schema-markup-structured-data-seo-opportunities-site-type-231077
        https://developer.mozilla.org/ko/docs/Web/HTML/Global_attributes/itemprop
        https://blog.outsider.ne.kr/1214



<br/>


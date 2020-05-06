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

# Issue: React에서 SEO위한 웹사이트 사이트맵 만들기

## 상황:

SEO를 위해 Sitemap을 적용해야 하는 상황

<br/>

## 생각해낸 방안:

- 사이트맵 생성기를 통하여 만들기
- React 사이트맵 생성 라이브러리를 통하여 사이트맵 생성
- robot.txt 생성

<br/>

## 방안: 사이트맵 생성기를 통하여 만들기 (실패)

<br/>

[사이트1](http://www.check-domains.com/sitemap/index.php)

[사이트2](http://www.web-site-map.com)

spa라서 그런지 제대로 사이트맵이 생성되지 않았다.
<br/>
<br/>
<br/>

        참조:
        https://wookoa.tistory.com/376

<br/>

## 방안: React 사이트맵 생성 라이브러리를 통하여 사이트맵 생성 (성공)

<br/>

1. react-router-sitemap 라이브러리 설치
2. sitemapRoutes.js 파일 생성

   - 실제 라우팅을 위해 실제 어플리케이션과 동일하지만 사이트맵을 위한 파일을 만들었다.

3. sitemapGenerator.js 파일 생성

   - cli에서 생성해주기 위하여 사이트맵 생성을 위한 js 파일 생성

4. sitemapGenerator.js 파일 실행
   - 필요한 dev dependency를 설치
   - sitemap이라는 스크립트 명령어 추가하여 변경사항이 바뀔 때마다 작동할 수 있도록 한다.
   - `"sitemap": "babel-node path/to/your/sitemaGenerator.js"`
5. 배포 과정에 sitemap 생성 추가
   - `"predeploy": "npm run sitemap"`

-. 구글 웹마스터 도구에 사이트맵 등록: http://wookoa.tistory.com/377

-. 네이버 웹마스터 도구에 사이트맵 등록: http://wookoa.tistory.com/179

<br/>
<br/>
<br/>

        참조:
        https://medium.com/@wj9304/react-%EB%A6%AC%EC%95%A1%ED%8A%B8-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8-%EC%82%AC%EC%9D%B4%ED%8A%B8%EB%A7%B5-%EB%A7%8C%EB%93%A4%EA%B8%B0-1116ff8b6c2a

<br/>

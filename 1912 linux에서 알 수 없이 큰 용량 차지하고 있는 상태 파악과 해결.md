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

# Issue: linux에서 알 수 없이 큰 용량 차지하고 있는 상태 파악과 해결

## 상황:

ubuntu의 용량이 점점 없어지고 있어서 구동에도 문제가 생기는 일이 많았다. 프로젝트를 따로 구축하고 용량을 잡아먹을 일을 한게 없어서 확인하다가 디스크 용량을 확인해보니 현재 프로덕트의 프로젝트 폴더에서 거의 8기가 정도를 먹고 있어서 문제를 파악하던 과정

<br/>

## 과정: 현재 디렉토리의 1depth 이하 디렉토리의 disk usage 판단(과정1)

<br/>

선행되었던 문제는 프로덕트 폴더의 용량을 gui에서 단순히 속성으로 들어가서 파악했을 때, 200mb정도여서 이제껏 프로덕트가 문제라고 생각하고 있지 않았다.

disu usage를 확인하는 과정에서 .meteor 프로젝트에서 발생하는 걸 파악했고 들어가보니 bundling-cache와 plugin-cache에서 거진 3g정도를 가지고 있는 것을 확인했다.

    du -hs -> 현재 디렉토리 용량 확인

    du -hs * -> 현재 폴더의 1depth 하위 디렉토리들의 용량표기

    du -hsx * | sort -rh | head -n 10 -> 현재 폴더에 있는 폴더 및 파일 중에서 용량이 큰 것 순으로 10개 보기

<br/>
<br/>
<br/>

        참조:
        https://webisfree.com/2018-01-24/linux-%EB%94%94%EB%A0%89%ED%86%A0%EB%A6%AC-%EC%82%AD%EC%A0%9C%ED%95%98%EA%B8%B0-rmdir
        https://ra2kstar.tistory.com/135
        https://zetawiki.com/wiki/%EB%A6%AC%EB%88%85%EC%8A%A4_%EB%94%94%EB%A0%89%ED%86%A0%EB%A6%AC_%EC%9A%A9%EB%9F%89_%ED%99%95%EC%9D%B8_du
        https://m.blog.naver.com/PostView.nhn?blogId=kby88power&logNo=220179372327&proxyReferer=https%3A%2F%2Fwww.google.com%2F

<br/>

## 과정: Meteor의 bundle-cache, plugin-cache clean (과정2)

<br/>

    meteor npm i -g  meteor-cleaner
    meteor clean-package-cache

    meteor 번들러와 plugin 캐시가 구버전에서 쌓이는 이슈가 있었어서 최근 버전에서는 cleaner자체가 내장되어 있는데 회사 product의 경우 1.6(현재1.8)이라서 cleaner를 설치하고 해결해주었다.

    별도로 bundler-cache는 따로 지우라는 말이 있어서 나는 따로 지워줬고, meteor 실행과 동시에 다시 깔렸다.

<br/>
<br/>
<br/>

        참조:
        https://github.com/meteor/meteor/pull/8687
        https://forums.meteor.com/t/clean-up-your-meteor-package-cache/38471

<br/>

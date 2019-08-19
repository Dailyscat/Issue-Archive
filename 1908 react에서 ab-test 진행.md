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

# Issue: react에서 ab-test 진행

## 상황:

워크샵 텍스트를 변경하는 과정에서 ab-test 진행

<br/>

## 생각해낸 방안:

- google optimize를 활용한 ab test
- @marvelapp/react-ab-test 라이브러리를 활용한 abtest

<br/>

## 방안: google optimize를 활용한 ab test (실패)

<br/>

일단 spa를 완전히 지원하지 않는다. ssr이 지원되면 text같은 것들을 감지하고 그 자체 텍스트를 optimize에서 바꿔서 탐지가 되는데, 그게 이루어지지 않는다.

여러번 테스트를 해보고, 기존에 사용하고 있던 gtm 모듈을 떼서 실험도 해보았지만 제대로 테스트가 진행되지 않음..

커뮤니티자체에서 issue도 있고, 해결이 안되는 사람도 있었고 현재 베타기 때문에 며칠간의 시도 끝에 다른 방향으로 진행하기로 결정..

<br/>
<br/>
<br/>

        참조:
        https://brunch.co.kr/@hj-kang/8
        https://www.laneterralever.com/blog/set-google-optimize-ab-testing#gref
        https://www.clickinsight.ca/blog/installing-google-optimize-the-right-way-with-ga-and-gtm
        https://datadriven.design/how-to-install-google-optimize-and-run-a-b-tests-on-your-website-data-driven-daily-tip-209/
        https://medium.com/@sarimhaq/a-simple-guide-to-running-a-b-tests-with-google-optimize-part-1-9a4b527bf2d4
        https://marketlytics.com/blog/install-google-optimize-via-google-tag-manager
        https://developers.caffeina.com/simple-a-b-testing-with-google-optimize-93cd2c24cd4

<br/>

## 방안: @marvelapp/react-ab-test 라이브러리를 활용한 abtest (성공)

<br/>

- ab test를 위한 db 구조 설계
- ab test를 위한 api 설계
- 들어오는 사용자, 클릭율 집계

      <Menu.Item key="/workshop">
        <Experiment ref="experiment" name="workshopTitle">
          <Variant name="스튜디오 클래스">스튜디오 클래스</Variant>
          <Variant name="같이 클래스">같이 클래스</Variant>
          <Variant name="체험 클래스">체험 클래스</Variant>
        </Experiment>
      </Menu.Item>

위와 같은 방식으로 진행했고 localstorage를 활용하여 한번 클릭한 고객의 정보는 중첩되지 않도록 설정

<br/>
<br/>
<br/>

        참조:
        https://www.w3schools.com/js/js_cookies.asp
        https://jonathanmh.com/use-ios-safari-localstorage-sessionstorage-private-mode/
        https://dev.to/rdegges/please-stop-using-local-storage-1i04

<br/>

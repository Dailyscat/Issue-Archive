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

# Issue: ios safari에서 scrollTo시에 글자 잘려보이는 상황

## 상황: chrome은 괜찮은데 safari에서 scrollTo를 했을 때 borderbox를 벗어나는 상황

<br/>

## 알게된 부분 정리:

- ios safari에서 overflow: hidden 관련 에러 존재


<br/>

## 개념: ios에서 사용하는 safari의 경우 overflow가 html{overflow:hidden} 로 주지 않았을 때 버그 존재

<br/>
ios에서 사용하는 safari의 경우 overflow가 html{overflow:hidden} 로 주지 않고
내부에 있는 element에 속성을 주게 되면 먹히지 않는 에러 존재
해당 에러 때문에 overflow가 먹히지 않은 상태에서 scrollTop을 하니까
padding: 0 6px 20px;가 존재하는 border box에
해당 css 속성에서 bottom에 적용되는 20px 만틈 scrollTop이 되어버려서 글자가 짤리는 에러
<br/>
<br/>
<br/>

        참조:

<br/>


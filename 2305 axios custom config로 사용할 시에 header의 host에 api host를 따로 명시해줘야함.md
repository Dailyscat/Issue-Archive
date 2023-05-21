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

# Issue: axios custom config로 사용할 시에 header의 host에 api host를 따로 명시해줘야함

## 상황: axios를 custom해서 사용하는대 local에서는 api와 통신이 되는데 http로 떠있는 서버에 api 요청은 제대로 이행되지 않음.

<br/>

## 알게된 부분 정리:

- next.js 서버, 즉 node에서 axios 요청 날릴 때 header의 호스트가 api쪽 오리진으로 설정되어야함.

<br/>

## 개념: next.js 서버, 즉 node에서 axios 요청 날릴 때 header의 호스트가 api쪽 오리진으로 설정되어야함.

<br/>
  이는 http 1.1 스펙인데 axios에서 기본 설정으로 override가 되지 않는 것으로 보임.
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

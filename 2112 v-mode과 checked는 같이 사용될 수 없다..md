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

# Issue: v-mode과 checked는 같이 사용될 수 없다.

## 상황:
v-model에 data를 그대로 바인딩 해서 사용되는 상황에서
:checked는 다른 조건을 통해서 적용되야 하는 상황


<br/>

## 알게된 부분 정리:

- 같이사용할 수 없다. beforeMount 훅에서 data의 조건을 주어서 적용하여 바인딩 되도록 설정

<br/>

## 개념: 같이사용할 수 없다. beforeMount 훅에서 data의 조건을 주어서 적용하여 바인딩 되도록 설정


<br/>
  
  ```
  v-model will ignore the initial value, checked, or selected attributes found on any form elements. It will always treat the Vue instance data as the source of truth. You should declare the initial value on the JavaScript side, inside the data option of your component.
  ```

<br/>
<br/>
<br/>

        참조:
        https://vuejs.org/v2/guide/forms.html

<br/>

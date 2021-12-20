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

# Issue: new Date를 반복문 내부에서 선언해서 사용할 때 주의해야할 부분

## 상황:
오늘을 기점으로 before, after 함수를 사용해야하는 상황인데
이 오늘이 리스트에 있는 원소만큼 만들어져도 이슈가 없는지 확인

<br/>

## 알게된 부분 정리:

- 성능상으로는 이슈가 없지만 GC가 염려되긴 한다.

<br/>

## 개념:

<br/>

  ```
  System.currentTimeMillis() :: 290 
  new Date() :: 291 
  Calendar.getInstance().getTime() :: 12176

  출처: https://www.crocus.co.kr/1636 [Crocus]
  ```

currentTimeMillis를 사용하는게 gc 염려가 없어보인다.

<br/>
<br/>
<br/>

        참조:
        https://www.crocus.co.kr/1636

<br/>

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

# Issue: js의 sort 사용 시 주의점

## 상황:

알고리즘 문제를 푸는데 edge case에서
넘어가지 못하던 상황

<br/>

## 생각해낸 방안:

- sort에서 파생된 문제

<br/>

## 방안: sort에서 파생된 문제 (성공)

<br/>
  기본적으로 sort의 default 값은 알파벳순, 오름차순인데 Numbers에서는 제대로 동작하지 않는다. 숫자 원소의 정렬을 제대로 하기 위해서는 무조건 함수를 넣어서 작동시켜야 한다.

위와 같은 동작을 하는 이유는 sort 자체의 동작 원리 때문인데,
sort는 숫자를 정렬할 때 내부 원소들을 일단 string으로 처리한 후,
ascii 문자 순서로 정렬하게 된다.

이 과정에서 숫자 상으로는 크지만 ASCII 문자 순서 상으로는 적은 것들이 원하는대로 적용되지 않는다.

      var nums = [10, 5, 40, 25, 100, 1];
      nums.sort();
      //[1, 10, 100, 25, 40, 5]

위와 같은 결과가 도출된다.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/3423394/algorithm-of-javascript-sort-function

<br/>

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

# Issue: 2107 object literals로 array 생성하는 방법 정리

## 상황: 가끔 하드코딩 해야할 때가 있을 때 배열 생성은 어떤 방법이 제일 간편, 좋은가를 고민하게 되서 정리

## 개념:

<br/>
  ```
JDK2
List<String> list = Arrays.asList("one", "two", "three");

JDK7
//diamond operator
List<String> list = new ArrayList<>();
list.add("one");
list.add("two");
list.add("three");

JDK8
List<String> list = Stream.of("one", "two", "three").collect(Collectors.toList());

JDK9
// creates immutable lists, so you can't modify such list
List<String> immutableList = List.of("one", "two", "three");

// if we want mutable list we can copy content of immutable list
// to mutable one for instance via copy-constructor (which creates shallow copy)
List<String> mutableList = new ArrayList<>(List.of("one", "two", "three"));

```
<br/>
<br/>
<br/>

      참조:
      https://stackoverflow.com/questions/13395114/how-to-initialize-liststring-object-in-java

<br/>

## 개념:

<br/>
개념에 대한 내용
<br/>
<br/>
<br/>

      참조:

<br/>
```

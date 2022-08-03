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

# Issue: java Map 인스턴스 생성

## 상황:
custom type map 클래스를 만들어서 관리해아하는 상황

<br/>

## 알게된 부분 정리:

-  LegacyMap<K, V> implements Map<K, V>, Serializable 

<br/>

## 개념:  LegacyMap<K, V> implements Map<K, V>, Serializable 

<br/>
  Map 인터페이스를 구현하여 사용
   이때 아래 방식 차용.

   ```
    Map<String,String> temp = new HashMap<String,String>(){{
        put(colName, data);
    }};
   ```
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/11714449/java-creating-instance-of-a-map-object

<br/>

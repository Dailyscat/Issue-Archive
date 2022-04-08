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

# Issue: 2204 spring redisTemplate 다건 삭제 정리

## 상황:
다건 삭제 필요하여 redisTemplate 이용하여 api 생성

<br/>

## 알게된 부분 정리:

- redis template의 ZSetOperations.remove 메소드 사용하면서 알게된 내용 정리

<br/>

## 개념: redis template의 ZSetOperations.remove 메소드 사용하면서 알게된 내용 정리

<br/>
  ZSetOperations.remove는 애초에 단건, 다건  삭제 전부 활용가능한 메소드

  ```
    remove(K key, Object... values)
    Remove values from sorted set.
  ```

  위에서 Object...는 java5에서 나온 varargs 라고 한다.
  
  ```
  This way to express arguments is called varargs, and was introduced in Java 5.
You can read more about them here.
  ```

이런 형태의 인자를 충족하기 위해서는 
```
List<WorldLocation> locations = new ArrayList<>();

.getMap(locations.stream().toArray(WorldLocation[]::new));
```

0408 업데이트.
그냥 Array를 넘겨주면 됌 Object...를 너무 복잡하게 생각. 
참조형형태의 iterator면 가능하다.

```
locations.stream().map(location -> location.toString).toArray()

Set<String> redisKeys = template.keys("samplekey*"));
// Store the keys in a List
List<String> keysList = new ArrayList<>();
Iterator<String> it = redisKeys.iterator();
while (it.hasNext()) {
       String data = it.next();
       keysList.add(data);
}

```


위와 같이 줌으로써 가능하다.
<br/>
<br/>
<br/>

        참조:
        https://docs.spring.io/spring-data/redis/docs/current/api/org/springframework/data/redis/core/RedisTemplate.html
        https://docs.spring.io/spring-data/redis/docs/current/api/org/springframework/data/redis/core/ZSetOperations.html#remove-K-java.lang.Object...-
        https://www.codetd.com/ko/article/6517626
        https://stackoverflow.com/questions/16674023/proper-terminology-for-object-args
        https://stackoverflow.com/questions/9863742/how-to-pass-an-arraylist-to-a-varargs-method-parameter
        https://stackoverflow.com/questions/19098079/how-to-get-all-keys-from-redis-using-redis-template

<br/>

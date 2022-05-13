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

# Issue: RedissonSpringCacheManager, RedisCacheManager 발생하는 이슈 정리

## 상황:
RedissonSpringCacheManager, RedisCacheManager 발생하는 이슈 정리

<br/>

## 알게된 부분 정리:

- 두 캐시매니저에서 @Cacheable, @CacheEvict가 동작하는 방식이 다름
- redisInsight에서 키 관련 검색
- @Cacheable에서 Avoid storing null via '@Cacheable(unless="#result == null")' or configure RedisCache to allow 'null' via RedisCacheConfiguration. 에러 

<br/>

## 개념: 두 캐시매니저에서 @Cacheable, @CacheEvict가 동작하는 방식이 다름

<br/>
  @Cacheable에서
  RedissonSpringCacheManager을 통해 SEL(spring expression language)로 만들어진 키값과 RedisCacheManager에서 같은 과정을 통해 만들어진 키 값이 다르다.

  전자는 앞에 ASCII코드(x04>\)와 같은 값이 붙고 후자는 @Cacheable을 통한 저장 자체에 어떤 문제가 있다(시리얼 라이즈를 잘 지정해줘도 저장되지 않음). 이로 인해 다른 매니저에서 같은 키를 통한 접근이 불가.

  @Cacheable은 null관련 에러가 발생하고 저장이 되어도 redis 인터페이스에 맞지 않은 에러 관련 class가 통째로 저장되어 버린다.

  결론은 두개를 따로 쓰면서 @Cacheable로 같은키를 찾는건 안됨.


참고: redis에서 자바 map 형태로 저장할 때 serialize 필요. 
https://stackoverflow.com/questions/13215024/why-is-data-getting-stored-with-weird-keys-in-redis-when-using-jedis-with-spring
https://stackoverflow.com/questions/39324717/spring-boot-caching-with-redis-key-have-xac-xed-x00-x05t-x00-x06
https://stackoverflow.com/questions/37753927/is-it-possible-to-customize-serialization-used-by-the-spring-cache-abstraction
https://stackoverflow.com/questions/63317251/redisson-client-serialization-issue
https://github.com/redisson/redisson/wiki/4.-data-serialization

<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: redisInsight에서 키 관련 검색

<br/>

1. `*찾는 키*` 와 같이 검색해보는게 좋다.
2. 1처럼 찾으면 중괄호로 감싸진 키도 나오는데 이는 레디스에서 따로 저장하는 부분.

<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: @Cacheable에서 Avoid storing null via '@Cacheable(unless="#result == null")' or configure RedisCache to allow 'null' via RedisCacheConfiguration. 에러 

<br/>

```
@Cacheable 어노테이션에 unless = "#result == null" 속성을 추가하라는 말인데 나는 분명 전역적으로 null 을 캐싱하지 않겠다고 했는데 왜 이런 에러가 발생하는지 궁금해졌고 몇가지 테스트를 통해 에러가 발생하는 이유를 알 수 있었다.

 

나는 RedisCacheManager에 disableCachingNullValues() 이 null 캐싱을 비활성화 하는 것이기 때문에 null value 가 넘어오면 그냥 캐싱을 하지 않겠구나 라고 생각했는데 RedisManager에서는 null 을 단순히 비활성화 하는데에서 끝나지 않고 Exception을 발생시켜 버린다. 

 

때문에 정상적인 처리를 위해서는 아예 null value에 대해 캐싱 자체를 시도하지 않아야 하고 null value 에 대해 아예 캐싱 자체를 시도하지 않도록 하는 것이 @Cacheable 어노테이션에 unless = "#result == null" 속성을 정의하는 것이다.

 

이는 서버에서 포트를 생각하면 된다. RedisCacheManager 가 곧 서버인 셈이고 서버에서는 null 포트를 비활성화한 상태인 것이다. 그럼에도 클라이언트에서 해당 포트로 연결을 시도할 경우 access denied 에러를 리턴한다.

 

그런데 어차피 캐싱을 시도하는 @Cacheable 를 통해 null value 캐싱 여부를 결정하는 거라면 RedisCacheManager의 disableCachingNullValues() 설정은 필요없는 거 아닌가? 그건 또 아니다. 아예 null value 를 존재하지 않게 하려면 RedisCacheManager.disableCachingNullValues() 를 통해 null 캐싱 자체를 비활성화하고 명시적으로 예외를 발생시켜주는 것이 좋다.
```

<br/>
<br/>
<br/>

        참조:
        https://findmypiece.tistory.com/105

<br/>
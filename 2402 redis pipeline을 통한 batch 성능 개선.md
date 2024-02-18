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

# Issue: redis pipeline api를 통한 batch 성능 개선

## 상황:
getRecentData()는 Map<String, Object> 반환

이때 반환되는 데이터 중

zSetOperations .add를 통해

zset으로 타입을 변환해서 redis에 저장해야할 상황이 있고 

memberRedisTemplate.opsForValue().set을 통해

바로 저장해야할 상황이 있다.

<br/>

beta 약 천건 대상

## 개념: 성능이 잘 나오지 않던 버전

<br/>

```
@Bean
public ItemWriter<entity> redisItemWriter() {
    ///
                 if(zset 타입으로 저장이 필요한 상황) {
                    archive.getRecentData().forEach((key, value) -> {
                        if(archive.getKeyName().contains("특정키워드")) {
                            ZSetOperations<String, String> zSetOperations = stringMemberRedisTemplate.opsForZSet();
                            zSetOperations
                                    .add(archive.getKeyName(), key.toString(), Double.valueOf(value.toString()));
                        } else {
                            ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
 
                            zSetOperations
                                    .add(archive.getKeyName(), Long.valueOf(key.toString()), Double.valueOf(value.toString()));
                        }
                        redisTemplate.expire(archive.getKeyName(), 180, TimeUnit.DAYS);
                    });
                } else {
                    redisTemplate.opsForValue().set(archive.getKeyName(), jpaMapToJsonConverter.convertToDatabaseColumn(archive.getRecentData()), 180, TimeUnit.DAYS);
                }
 
    ///
}
```


소요시간: 4 ~ 5분

zset 타입은 다건저장을 지원하지 않는걸로 보여서 

zset 타입 저장이 필요한 상황은 제외하고

json을 바로 저장하는 상황에서만

valueOps.multiSet(keyValueMap)을 통해 다건 저장



하지만 시간은 비슷하게 걸림.

이유로는 아래 코드가 돌면서 expire를 설정해줘야 해서 결국 redis api를 한번씩은 호출하기 때문에 동일함

expire를 필요로 하기 때문에 없앨 수 는 없었음

 keyValueMap.keySet().forEach(key ->
redisTemplate.expire(key, 180, TimeUnit.DAYS)
);


```
@Bean
public ItemWriter<entity> redisItemWriter() {
    return new ItemWriter<entity>() {
        private int count = 0;
 
        @Override
        public void write(List<? extends entity> items) throws Exception {
        ///
            Map<String, String> keyValueMap = new HashMap<>();
            for (entity archive : items) {
 
                if(zset 타입으로 저장이 필요한 상황) {
 
                    archive.getRecentData().forEach((key, value) -> {
                        if(archive.getKeyName().contains("my_searchKeywords_EXTERNAL_JP")) {
                            strZSetOperations
                                    .add(archive.getKeyName(), key.toString(), Double.valueOf(value.toString()));
                            redisTemplate.expire(archive.getKeyName(), 180, TimeUnit.DAYS);
 
                        } else {
                            zSetOperations
                                    .add(archive.getKeyName(), Long.valueOf(key.toString()), Double.valueOf(value.toString()));
                            memberRedisTemplate.expire(archive.getKeyName(), 180, TimeUnit.DAYS);
                        }
                    });
                } else {
                    String jsonValue = jpaMapToJsonConverter.convertToDatabaseColumn(archive.getRecentData());
                    keyValueMap.put(archive.getKeyName(), jsonValue);
                }
            }
            if (!keyValueMap.isEmpty()) {
 
                ValueOperations<String, Object> valueOps = memberRedisTemplate.opsForValue();
                valueOps.multiSet(keyValueMap);
 
                keyValueMap.keySet().forEach(key ->
                        memberRedisTemplate.expire(key, 180, TimeUnit.DAYS)
                );
            }
        ///
        }
    };
}
```

<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: 성능 개선 버전

zset의 경우도 tuples로 한번에 다건 저장할 수 있는걸 확인하여 단건 저장에서 다건 저장되도록 개선.

그리고

redis의 단건 저장이든 다건 저장이든 network i/o를 통해 진행되고

이때 저장은 레디스 내에서 반복문을 돌며 여러 리스트에 push를 하는 모델이기 때문에 네트워크 요청을 시작한 후 응답을 받는 데 걸리는 시간(RTT)으로 인해 오버헤드가 생긴다.

이런 부분을 개선하기 위해 http pipelining을 활용할 수 있는 redis의 Pipleline api 중 spring-data-redis에서 지원하는 executePipelined를 사용해 redis에 연결 후 모든 redis command가 파이프라인으로 처리되도록 설정했다.

<br/>

```
   @Bean
   public ItemWriter<entity> redisItemWriter() {
       return new ItemWriter<entity>() {
           @Override
           public void write(List<? extends entity> items) throws Exception {
               List<Object> results = redisTemplate.executePipelined(new SessionCallback<Object>() {
                   @Override
                   public Object execute(RedisOperations operations) throws DataAccessException {
                       ZSetOperations<String, String> zSetOps = operations.opsForZSet();
 
                       long forLoopStartTime = System.currentTimeMillis();
 
                       for (entity archive : items) {
                           if(zset 타입으로 저장이 필요한 상황) {
                               Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
                               archive.getRecentData().forEach((key, value) -> {
                                   double score = Double.valueOf(value.toString());
                                   tuples.add(new DefaultTypedTuple<>(key, score));
                               });
                               if(!archive.getRecentData().isEmpty()) {
                                   zSetOps.add(archive.getKeyName(), tuples);
                                   operations.expire(archive.getKeyName(), 180, TimeUnit.DAYS);
                               }
                           } else {
                               String jsonValue = jpaMapToJsonConverter.convertToDatabaseColumn(archive.getRecentData());
                               keyValueMap.put(archive.getKeyName(), jsonValue);
                               operations.opsForValue().set(archive.getKeyName(), jpaMapToJsonConverter.convertToDatabaseColumn(archive.getRecentData()), 180, TimeUnit.DAYS);
                           }
                       }
                       return null;
                   }
               });
           }
       };
   }
```

실제 리얼 배치 적용시 시간


처음 10,000건 처리시
Processed 10,000 items, time taken: 4522 ms

그다음 10,000건
Processed 10,000 items, time taken: 799 ms

이후 평균 1초를 유지하다 시간이 가면서 평균 1초씩 늘어서 마지막의 10,000건 처리시엔 3.6초
Processed 10,000 items, time taken: 3629 ms

총 7,625,874 건 처리했으며 약 24분 소요.
executed in 24m9s588ms



<br/>
<br/>
<br/>

        참조:
        https://redis.io/docs/manual/pipelining/
        https://velog.io/@leehyeonmin34/dambae200-redis-pipeline
        https://tjdrnr05571.tistory.com/7
        https://randro.tistory.com/3

<br/>

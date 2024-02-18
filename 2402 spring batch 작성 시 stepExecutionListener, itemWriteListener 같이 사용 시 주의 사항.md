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

# Issue: spring batch 작성 시 stepExecutionListener, itemWriteListener 같이 사용 시 주의 사항

## 개념:

chunk 메소드 호출 이후에 호출되는 리스너에는 stepExecutionListener를 대상으로 하는

리스너가 없고 Object 타입을 받는 리스너만 존재한다.

그러다보니 아래 Bad와 같은 형태로 코드를 짜면 에러는 나지 않지만 

stepExecutionListener가 호출되지는 않는다.

각 스텝에 대한 리스너를 필요로 한다면 get()호출 이후 리스너를 호출해야한다.

<br/>
 
 ```
 // Bad
@Bean
public Step updateRedisStep() {
    return stepBuilderFactory.get("updateRedisStep")
            .<MyRecentArchive, MyRecentArchive>chunk(1000)
            .reader(jpaItemReader())
            .writer(redisItemWriter())
            .listener(itemWriteListener())
            .listener(stepExecutionListener())
            .build();
}    
 
 
// Good
@Bean
public Step updateRedisStep() {
    return stepBuilderFactory.get("updateRedisStep")
            .listener(stepExecutionListener())
            .<MyRecentArchive, MyRecentArchive>chunk(1000)
            .reader(jpaItemReader())
            .writer(redisItemWriter())
            .listener(itemWriteListener())
            .build();
}
 ```

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

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

# Issue: retryTemplate 활용하여 재호출 로직 설정

## 상황: 배치잡 중 하나에서 간헐적으로 호출이 실패해서 어떤 방식의 처리가 가장 효율적일까를 생각하다가 일단 큰 문제는 아니었고 간헐적 배치 실패이기 때문에 retry를 넣어보자로 결론

## 생각해낸 방안:

- retryTemplate 활용

## 방안: 

<br/>

```
retryTemplate.execute(context -> {
    if (context.getRetryCount() > 0) {
        log.warn("retry occur. retryCount : {}", context.getRetryCount());
    }
    return restTemplate.exchange(uriComponents.toString(), HttpMethod.PUT, new HttpEntity<>(subList), Object.class);
});
```

<br/>
<br/>
<br/>

        참조:

<br/>

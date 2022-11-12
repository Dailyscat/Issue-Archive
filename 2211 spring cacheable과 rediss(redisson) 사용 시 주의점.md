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

# Issue: spring cacheable과 rediss(redisson) 사용 시 주의점

## 상황: 

단일서버에서는 상관없는데 lb를 붙여 여러 서버를 사용할 때 @cachable을 사용하여 레디스에 저장, 조회, 소멸 되는 부분이 제대로 작동하지 않음.


## 생각해낸 방안:

- key 값 명시

## 방안: 

<br/>

```
@Cacheable(value = CacheNames.abc)
	public boolean abc(code code, Long id)
```
방식일 때 spring cache에서 parameter를 가지고 simpleKey라는 객체로 래핑하는데 이때 멀티서버일 때 이슈를 발생. 

아마도 SimpleKey 객체가 레디스에 캐시에 매핑되는 부분에서 class이름에 어떤 postPrefix로 붙은 메모리 이름이나 이런 것들이 추가되어 다른걸로 추정된다.

key option을 명시해줘서 이슈를 피할 수 있었다. key option을 붙이면 simpleKey가 아닌 string으로 매핑되어 이슈 발생하지 않음.

소스 내부를 파악해봐도 spring의 simpleKey에서는 이슈가 없어보이고 redisson에서 저장할 때 이슈가 있을 것 같은데 열심히 찾아보다가 일이 바빠서 일단 스킵


<br/>
<br/>
<br/>

        참조:

<br/>

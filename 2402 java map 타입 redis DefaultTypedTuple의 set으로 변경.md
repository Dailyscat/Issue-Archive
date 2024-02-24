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

# Issue: java map 타입 redis DefaultTypedTuple의 set으로 변경


## 개념: java map 타입 redis DefaultTypedTuple의 set으로 변경
기존에 Redis에 sorted sets로 저장된걸
mysql에 map으로 백업 저장 해두었음.

이를 다시 불러와서 사용해야하고 score에 따라 정렬(저장된 순서로)이 필요.

string, score(double) 타입으로 저장된 map타입을
redis tuple로 저장할 필요가 있어서 변경.

<br/>

```
    Map<String, Object> map
    map.entrySet().stream()
                        .map(entry -> {
                            String value = entry.getKey();
                            double score = ((Number) entry.getValue()).doubleValue();
                            return new DefaultTypedTuple<>(value, score);
                        })
                        .collect(Collectors.toSet());

```

<br/>
<br/>
<br/>

        참조:

<br/>

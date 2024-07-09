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

# Issue: pinia 사용시 반응형 변수를 return 할 때 주의사항

## 상황:
컴포저블 함수를 통해서 스토어에서 리턴된 변수를 사용하는 과정에서 
해당 변수의 반응성이 살아있지 않음. 그래서 공식문서 확인.
<br/>

## 알게된 부분 정리:

- 스토어에서 리턴하여 특정 변수를 사용하려고 할때 storeToRefs 함수 활용

<br/>

## 개념:
아래와 같이 사용 방법에 주의 사항이 있고
그 밑에 저장소에서 디스트럭처링을 확인하니까 이해가갔다.
react에서처럼 return된 변수가 반응성을 그대로 가지고 있을거라고만 생각했는데 
pinia는 달랐음.

<br/>

```
<script setup>
import { useCounterStore } from '@/stores/counter'
const store = useCounterStore()
// ❌ 반응성을 깨뜨리기 때문에 작동하지 않습니다.
// `props`에서 디스트럭처링하는 것과 동일합니다.
const { name, doubleCount } = store
name // 언제나 "Eduardo"
doubleCount // 언제나 0

setTimeout(() => {
  store.increment()
}, 1000)

// ✅ 이것은 반응적일 것입니다
// 💡 또한 `store.doubleCount`로 직접 사용할 수도 있습니다.
const doubleValue = computed(() => store.doubleCount)
</script>

```


<br/>
<br/>
<br/>

        참조:
        https://pinia.vuejs.kr/core-concepts/#using-the-store
        https://pinia.vuejs.kr/core-concepts/#destructuring-from-a-store

<br/>
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

# Issue: vue router history mode, hash mode difference

## 상황: spring, vue 환경 구성하는 중 두 라우터의 차이가 무엇인지 확인

<br/>

## 알게된 부분 정리:

- 차이점 정리

<br/>

## 개념: 차이점 정리

<br/>

Hash Mode

내부적으로 전달되는 실제 URL 앞에 해시 문자(#)를 사용한다. 이때 바뀌는 url은 일반적으로 주소창에서 발생하는 get 요청이 발생하지 않는다. 이는 온전히 csr에서만 사용되는 것이 바람직하다.

History Mode

일반적으로 사용되는 url의 모양을 쓸 수 있다.
하지만
history 모드에서 특정 url(https://example.com/user/id)로의 요청은 서버의 특별한 설정이 있지 않다면 404를 발생시킨다.

이때 필요한 서버의 특별 설정은 엔드포인트/로 들어오는 모든 요청에 대하여 index.html을 제공하도록 설정한다.

이때 오류인 요청에 대해서도 index.html을 제공할 수 있고 이는 문제를 일으킬 수 있기 때문에 
```
const router = createRouter({
  history: createWebHistory(),
  routes: [{ path: '/:pathMatch(.*)', component: NotFoundComponent }],
})
```

위의 코드로 대체해볼 수 있다.


<br/>
<br/>
<br/>

        참조:
        https://next.router.vuejs.org/guide/essentials/history-mode.html#html5-mode
        https://cokes.tistory.com/125
        https://stackoverflow.com/questions/41962712/spring-resourcehandler-pattern-fails-for-index-page

<br/>


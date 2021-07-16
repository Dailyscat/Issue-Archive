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

# Issue: ios에서 sticky를 사용했을 때 노이즈 발생 이슈

## 상황: ios에서 sticky를 적용한 메뉴가 간헐적으로 스크롤링 시에 깜빡이는 이슈가 있었다.

## 생각해낸 방안:

- sticky 제거 후 scroll이 될 때 특정 지점에서 fixed로 제어하도록 처리

## 방안: sticky 제거 후 scroll이 될 때 특정 지점에서 fixed로 제어하도록 처리

<br/>

```
    position: fixed;
    top: calc(44px + constant(특정 변수) + 50px);
    left: auto;
    right: auto;
    width: 100%;
    box-sizing: border-box;
```

<br/>
<br/>
<br/>

        참조:

<br/>

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

# Issue: gif가 적용된 background 이미지가 aos 9 버전 이하에서 노이즈 일으키는 현상 해결

## 상황:

aos 9 이하 버전에서 background image로 적용된 gif 파일이 특정 시점에 노이즈를 일으킴

## 생각해낸 방안:

- 다른 레이어로 분리하여 노이즈 영역을 줄임

## 방안:

<br/>

```
  -webkit-perspective: 1000;
  perspective: 1000;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden
```

perspective css 속성을 줌으로써 background image가 적용되는 레이어를 분리하고
stacking context를 이루도록 하였다.

현재 [Cumulative Layout Shift (CLS)](https://web.dev/cls/?utm_source=devtools)를 야기하는 부분이 프론트에 있어서 일단 해당 영역 조정만으로도 큰 효과를 볼 수 있었다.

<br/>
<br/>
<br/>

        참조:
        https://web.dev/cls/?utm_source=devtools

<br/>

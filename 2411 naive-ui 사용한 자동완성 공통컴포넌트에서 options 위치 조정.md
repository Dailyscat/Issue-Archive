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

# Issue: naive-ui 사용한 자동완성 공통컴포넌트에서 options 위치 조정

## 상황:

1. autoComplete 컴포넌트의 options의 너비는 input을 따라가는데 이를 조절할 수 있는 방법이 api로 제공되지 않음. 그리고 autoComplete에 class를 줘도 그 밑에 options는 modal로 떠버리고 부여한 class에 의존하지 않음.
2. placement라는 attr로 조절할 수 있는 방법을 찾았지만 여러 페이지에서 중첩된 컴포넌트로 엮여있어서 prop 드릴링으로 조절 & 각 페이지의 style 스트립트에서 각자 다조절이 필요
3. 왼쪽과 오른쪽 창 사이드에서 input을 늘리면 짤리는 이슈.

<br/>

## 생각해낸 방안:

- getBoundingClientRect를 통해 현재 options가 창 밖으로 나가 있는지 아닌지 확인 후 이동.

<br/>

## 방안:

<br/>

```
options에 변화가 있으면 자동완성 인풋 아래에 options가 생기기 때문에 로직을 watch 내부에 작성
watch(options, (options) => {
  showSuggestions.value = true

  setTimeout(() => {
    모든 microTask queue가 비워지고 나서, 즉 렌더링에 필요한 로직이 다 끝난 이후에 실행해야해서 setTimeout으로 task queue로 던지도록 설정.

    const nodes = document.querySelectorAll('[v-placement="parent"]')
    const optionsParent = nodes[nodes.length - 1]
    naive ui에서 방식이 계속 options에 해당하는 parent를 body 내부에 생성하는 방식이라서 가장 최근에 생긴 options에서 이벤트 부여하도록 설정
    const inputWidth = (autoCompleteRef.value as any).$el.getBoundingClientRect().width

    if (inputWidth > 200) {
        input이 너무 짧을 때만 class를 부여해서 조절하도록.
      return
    } else {
      ;(optionsParent as HTMLElement).classList.add('adjust-width')
    }

    if (optionsParent) {
      const { left, right } = optionsParent.getBoundingClientRect()

      const windowWidth = window.innerWidth

      if (left < 0) {
        // 왼쪽 경계를 넘어갔을 때
        ;(optionsParent as HTMLElement).style.left = `${Math.abs(left) + 10}px`
      } else if (right > windowWidth) {
        // 오른쪽 경계를 넘어갔을 때
        ;(optionsParent as HTMLElement).style.left = `-${right - windowWidth + 10}px`
      }

      10은 padding으로 주었음.
    }
  }, 0)
})

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

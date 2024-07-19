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

# Issue: vue3에서 props를 destructure 하면 반응성을 잃음

## 상황:

vue 작업 중 props를 destructure 해서 사용하는데 값이 업데이트 되어도 컴포넌트가 다시 렌더링되지않음;

<br/>

## 알게된 부분 정리:

- vue는 props 객체 자체가 반응형 속성을 갖고 있기 때문에 destructure하면 새로운 변수로 참조값이 생성되기 때문에 반응성 잃음.

<br/>

## 개념: vue는 props 객체 자체가 반응형 속성을 갖고 있기 때문에 destructure하면 새로운 변수로 참조값이 생성되기 때문에 반응성 잃음.

<br/>
  암튼 그래서 두가지 방법이 있다고 해서 봤는데

  ```
    <script setup lang="ts">  
    import { computed } from 'vue'

    const props = defineProps<{ name: string }>()

    // ✅ do this: access props using `props.name`
    const greeting = computed(() => {  
    return 'Hello ' + props.name ?? 'unknown'
    })
    </script>

    <template>  
    <p>
        My name is: {{ name }}
        {{ greeting }}
    </p>
    </template>  
  ```

  ```
    <script setup lang="ts">  
    import { computed, toRefs } from 'vue'

    const props = defineProps<{ name: string }>()

    // ✅ do this: `name` stays reactive and updates accordingly when changed in the parent component
    const { name } = toRefs(props)

    const greeting = computed(() => {  
    return 'Hello ' + name.value ?? 'unknown'
    })
    </script>

    <template>  
    <p>
        My name is: {{ name }}
        {{ greeting }}
    </p>
    </template>  
  ```

  둘 다 만족 스럽진 않다. 결국엔 인스턴스 참조하는 코드를 하나씩 다 써줘야해서.
  근데 vite에서 이걸 해결하는 설정이 있었다.

```
Vite
// vite.config.js
export default {
  plugins: [
    vue({
      script: {
        propsDestructure: true
      }
    })
  ]
}
vue-cli
Requires vue-loader@^17.1.1

// vue.config.js
module.exports = {
  chainWebpack: (config) => {
    config.module
      .rule('vue')
      .use('vue-loader')
      .tap((options) => {
        return {
          ...options,
          reactivityTransform: true
        }
      })
  }
}
```

굿
<br/>
<br/>
<br/>

        참조:
        https://futurestud.io/tutorials/vue-js-3-how-to-destructure-props-in-composition-api
        https://github.com/vuejs/rfcs/discussions/502
<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

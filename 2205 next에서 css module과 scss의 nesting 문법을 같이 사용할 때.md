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

# Issue: next에서 css module과 scss의 nesting 문법을 같이 사용할 때

## 상황:
사실 nesting 문법때문에 scss를 쓰는데 next에서 빌트인으로 지원하는 css module은 궁합이 약간 안맞는다. 이를 슬기롭게 해결하는 방법 두가지 정리

<br/>

## 알게된 부분 정리:

- 해결방법 정리

<br/>

## 개념: 해결방법 정리

<br/>
  1.
  
  ```
      .scss
      
      .filter-items{
        .filter-item{
            &__title {
            font-size: 3rem;
            span:first-child{
                background: red}
            }
        }
      }

      .tsx

      <div className={scss["filter-items"]}>
        <div className={scss["filter-item"]}>
            <div className={scss["filter-item__title"]}>
            <span>test 1</span><span>test 2</span>
            </div>
        </div>
      </div>

  ```

  2.

  ```
  .test{ // name of module
    color:pink;
        [class~="test2"]{ // target child class
        color: blue
        }
    }
  ```

<br/>
<br/>
<br/>

        참조:
        https://github.com/vercel/next.js/discussions/27772
        https://github.com/vercel/next.js/discussions/19417#discussioncomment-549788

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

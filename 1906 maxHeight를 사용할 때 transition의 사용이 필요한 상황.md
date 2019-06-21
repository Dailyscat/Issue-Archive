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

# Issue: maxHeight를 사용할 때 transition의 사용이 필요한 상황

## 상황:

더보기, 접기 ui가 작동될 때 확확 접히고 펴지는 상황

<br/>

## 생각해낸 방안:
+ transition을 maxHeight에 사용하여 부드러운 닫기, 열기가 작동되도록 구성
+


<br/>

## 방안: transition을 maxHeight에 사용하여 부드러운 닫기, 열기가 작동되도록 구성 (과정, 성공)
<br/>
  
   처음에는 min-height 속성을 사용하고 있었어서 min-height에 transition을 사용했었는데 max-height 속성으로 사용하게 됐다. 이유는

    (1) content 자체의 height가 정해져 있었어야 했어서  
    (2) min-height를 1000px로 줬을 때 어떤 액션이 일어났을 때 눈에 보이지 않아도 1000px까지 height가 늘어나는 부작용이 있었다.

overflown 된 컨텐츠에 한해서 지켜져야 하는 height를 초기 max-height로 주고 

        .text {
          overflow: hidden;
          max-height: 0;
          transition: max-height 0.5s cubic-bezier(0, 1, 0, 1);

          &.full {
            max-height: 1000px;
            transition: max-height 1s ease-in-out;
          }
        }
와 비슷한 코드를 작성하고 cubic-bezier를 조절하여 부드러운 열고 닫기를 구현하였다.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/16028878/animating-max-height-with-css-transitions

<br/>


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

# Issue: ios8+ safari에서 getItem 안되는 오류

## 상황:

로그인 실패시에 에러가 발생하면 에러에 해당하는 부분에서 alert나 popcorn 메세지를 줬어야 하는데

    (1) this.state = {
          message: window.localStorage.getItem( MESSAGE_KEY )
        };
    (2) keyBox[MESSAGE_KEY]= "이메일 또는 비밀번호가 일치하지 않습니다.";

아래와 같은 방식으로 state가 변경되는 방식으로 구현되어 있었다.

일단 setState로 처리되지 않았던 점이 특이했다.

<br/>

## 생각해낸 방안:
+ getItem의 safari 오류
+


<br/>


## 방안: getItem의 safari 오류 (성공)
<br/>

  이전에도 getItem을 사용하다가 safari에서 작동되지 않던 경험을 했던 적이 있었다.

  밑의 참조 글과 같이 ios8+ 이상부터 set, getItem 메소드를 제어할 수 없게 되었음을 알 수 있다.

  setState로 바꿔주면서 해결했다.

<br/>
<br/>
<br/>

        참조: https://stackoverflow.com/questions/30048009/html5-localstorage-setitem-is-not-working-on-ios-8-safari-mobile

<br/>


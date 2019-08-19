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

# Issue: ESLint, Prettier를 Meteor에 적용하기

## 상황:

시니어 개발자가 합류하기로 한 상황에서 코드스타일을 통일해야할 필요성을 느껴서
<br/>

## 생각해낸 방안:

- Meteor에 최적화된 eslint plugin 깔고 적용
- React와 함께 사용하는 최적화된 eslint plugin 적용

<br/>

## 과정1: Meteor에 최적화된 eslint plugin 깔고 적용

<br/>
  Meteor 자체에서 조금 다르게 돌아가는 부분이 있을 까 싶어서 meteor eslint plugin을 찾아봤더니 있었다. 문서를 확인해보니 Template api를 사용하는 유저를 위한 셋업이었고, react를 적용해서 사용하는 나같은 사용자들에게는 맞지 않았다.

<br/>
<br/>
<br/>

        참조:
        https://github.com/dferber90/eslint-plugin-meteor
        http://killerchip.net/2018/08/20/2018-08-20-meteor-react-eslint/

<br/>

## 과정2: React와 함께 사용하는 최적화된 eslint plugin 적용

<br/>
  여러 룰을 살펴보았다.

[첫번째 룰](https://forums.meteor.com/t/eslint-rules-for-meteor-airbnb-style/41126/2)
[두번째 룰](https://gist.github.com/lehtu/92fb231a3e794c20293609f6fb37037f)
[세번째 룰](http://killerchip.net/2018/09/22/2018-09-22-prettier-config/)

개중에서 Meteor api를 쓸 때 충돌이 날 수 있는

      "no-underscore-dangle": [
        "error",
        {
          "allow": [
            "_id",
            "_ensureIndex"
          ]
        }
      ],
      "import/extensions": 0,
      "import/no-unresolved": ["error", {
        "ignore": ["^meteor/", "^/"]
      }],
      "func-names": 0,
      "prefer-arrow-callback": 0,
      "meteor/no-session": 0

몇가지와, prettier에서 tab과 bracketSpacing semi콜론 등등을 추가해주었다.

추가로 husky를 적용하여 precommit 할 수 있도록 적용했고,

[prettier 전체 파일 적용](https://github.com/prettier/prettier-vscode/issues/321) 커맨드를 통하여 전체적인 코드 스타일을 통일 해주었다. 레거시까지 내가 다 쓴게 되어서 좀 찜찜하지만.... 얼른 고쳐나가야겠다.

<br/>
<br/>
<br/>

        참조:
        https://medium.com/@joshuacrass/javascript-linting-and-formatting-with-eslint-prettier-and-airbnb-30eb746db862
        http://killerchip.net/2018/09/22/2018-09-22-prettier-config/
        https://medium.com/quick-code/how-to-integrate-eslint-prettier-in-react-6efbd206d5c4
        https://github.com/amandakelake/blog/issues/59
        https://medium.com/quick-code/how-to-integrate-eslint-prettier-in-react-6efbd206d5c4
        https://velog.io/@velopert/eslint-and-prettier-in-react

<br/>

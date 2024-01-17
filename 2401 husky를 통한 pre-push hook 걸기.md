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

# Issue: husky를 통한 pre-push hook 걸기

## 상황: docker image를 구울때 next 빌드시에 ts type validation에 계속 걸리는 이슈가 있어서 git hook을 계속 넣으려고 생각은 하고 있었는데 어쨌든 빌드를 한번씩 해야하는 딜레이가 생겨서 안넣고 있었다가 이번에 push 훅으로 넣음.

<br/>

## 알게된 부분 정리:

- husky install, pre-push 훅 생성

<br/>

## 개념: husky install, pre-push 훅 생성

<br/>
  husky 가이드를 보고 생성하면 되고
  
  git add .husky/pre-push 를 통해 생성한다.

  ```
    #!/usr/bin/env sh
    . "$(dirname -- "$0")/_/husky.sh"

    cd ./front-ui && npm run lint && npm run build
  ```

  이때 .git 폴더의 하위에서 husky를 사용하려고 하니까 에러가 발생되는 부분때문에 cd 명령어 필요했다.

  그리고 "lint": "eslint --fix --ext .js,.jsx,.ts,.tsx .", lint 명령어를 통해서 eslint에서 fix할 수 있는 부분에 대해서는 수정될 수 있도록 선언했다.

<br/>
<br/>
<br/>

        참조:https://typicode.github.io/husky/getting-started.html

<br/>
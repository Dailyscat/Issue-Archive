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

# Issue: gh-page로 storybook 배포


## 알게된 부분 정리:

- storybook static만 gh-page로 배포.

<br/>

## 개념:

<br/>

https://github.com/settings/tokens

들어가서 `workflowUpdate GitHub Action workflows`의 체크박스 체크


```

.package.json
```
{
  "scripts": {
    "build-storybook": "storybook build -o ../../storybook_static"
  }
}
```

.github/workflows
// deploy-storybook.yml
name: Deploy Storybook to GitHub Pages

on:
  push:
    branches:
      - main  # main 브랜치에 푸시될 때마다 실행

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v2

      - name: Set up Node.js
        uses: actions/setup-node@v2
        with:
          node-version: '20.14.1'

      - name: Install dependencies
        run: npm install

      - name: Build Storybook
        run: npm run build-storybook

      - name: Deploy to GitHub Pages
        uses: peaceiris/actions-gh-pages@v3
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: ./storybook-static

```

repo의 /settings/pages으로 들어가서 생성된 페이지의 /storybook_static으로 접근하면 들어가게된다.

<br/>
<br/>
<br/>

        참조:

<br/>

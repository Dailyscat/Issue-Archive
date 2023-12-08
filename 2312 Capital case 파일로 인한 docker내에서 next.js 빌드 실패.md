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

# Issue: Capital case 파일로 인한 docker내에서 next.js 빌드 실패

## 상황: 로컬에서는 되는데 remote docker ci 서버에서는 빌드가 안된다.


## 생각해낸 방안:

- 실제 파일이 있는지 없는디 dockerfile 내에서 확인
- 혹시 alias 문제인가?
- docker ci server prune으로 클린
- git global 설정 변경
- mv를 통해 실제 git에서 파일 변경

## 방안: 실제 파일이 있는지 없는디 dockerfile 내에서 확인

<br/>

```
Failed to compile.

Type error: Cannot find module '@/domain/abc/Abc' or its corresponding type declarations
```

에러 발생하여 dockerfile내에서 파일 존재와 이름 확인

RUN ls -al src
RUN test -f src/domain/Abc/Abc.ts && echo "Abc.ts exists" || echo "Abc.ts does not exist"
RUN cat src/domain/Abc/Abc.ts

파일이 없고 abc.ts 파일만 존재, 하지만 source에는 존재했음.

<br/>
<br/>
<br/>

        참조:

<br/>


## 방안: 혹시 alias 문제인가?

<br/>

  webpack: (config, { isServer }) => {
    config.resolve.alias['~'] = path.join(__dirname, 'src');
    return config;
  }


ts.config
  {
  "compilerOptions": {
    "forceConsistentCasingInFileNames": false,
  }
}
  등등 적용해봤지만 아님.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/63109383/file-name-differs-from-already-included-file-name-only-in-casing-vetur1149
        https://stackoverflow.com/questions/76676456/next-js-v13-in-docker-does-not-respect-path-alias-but-works-locally
        https://stackoverflow.com/questions/76173896/error-resolving-path-alias-when-dockerizing-nextjs-13-app
<br/>


## 방안: docker ci server prune으로 클린

<br/>

docker system prune -af  --filter "until=$((30*24))h"

이것도 아님.

<br/>
<br/>
<br/>

        참조:

<br/>



## 방안: git global 설정 변경

<br/>

git config core.ignorecase false

내 로컬에서도 실행해보니 differ 가 떠서 commit했으나 빌드실패
혹시나 해서 원격서버에도 적용해봤으나
Type error: Already included file name '/app/src/domain/order/Abc.ts' differs from file name '/app/src/domain/order/abc.ts' only in casing.
둘다 위 에러 발생. 근데 에러 내용이 같은 파일 이름이 있다는걸로 보여서 아예 git에서 잡고 있는 파일을 mv로 변경해보려고 시도.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/6899582/i-change-the-capitalization-of-a-directory-and-git-doesnt-seem-to-pick-up-on-it
        https://stackoverflow.com/questions/54114916/file-name-differs-from-already-included-file-name-only-in-casing

<br/>


## 방안: mv를 통해 실제 git에서 파일 변경

<br/>

```
$ git mv somename tmpname
$ git mv tmpname SomeName
```

git mv를 통해 파일 이름 자체를 바꿔주니 제대로 성공 ㅠㅠ 이때 --force를 붙여줘야한다 안붙여주면 fatal: destination exists, source=somename, destination=SomeName 이런 에러 발생.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/6899582/i-change-the-capitalization-of-a-directory-and-git-doesnt-seem-to-pick-up-on-it
        https://stackoverflow.com/questions/10523849/how-do-you-change-the-capitalization-of-filenames-in-git
        

<br/>


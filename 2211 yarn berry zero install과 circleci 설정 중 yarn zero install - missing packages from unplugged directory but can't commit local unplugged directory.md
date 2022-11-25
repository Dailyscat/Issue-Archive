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

# Issue: yarn berry zero install과 circleci 설정 중 yarn zero install - missing packages from unplugged directory but can't commit local unplugged directory

## 상황:
yarn zero install - missing packages from unplugged directory but can't commit local unplugged directory
관련 에러
<br/>

## 알게된 부분 정리:

- intelliJ에서 Unversioned Files에 설치한 패키지가 남아 있어서 커밋되지 않음.

<br/>

## 개념: intelliJ에서 Unversioned Files에 설치한 패키지가 남아 있어서 커밋되지 않음.


<br/>
    circle ci를 로컬에서 돌렸을 때 이슈가 나서 좀 의아했는데 아마 github origin을 바라보고 작동하는 것 같기도 하고 git commit된 부분까지를 보고 작동하는 것 같기도 한데(후자 일 것 같다) 여튼 패키지 커밋해주니 이슈 해결.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/70767390/yarn-zero-install-missing-packages-from-unplugged-directory-but-cant-commit-l
        https://yarnpkg.com/features/zero-installs#how-do-you-reach-this-zero-install-state-youre-advocating-for

<br/>

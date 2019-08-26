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

# Issue: git stash 다른 브랜치에 적용, git stash clean 후 복원

## 상황:

협업 중 stash를 해놓고 작업하다가 git 이 꼬인 상황

<br/>

## 과정:

- git stash 다른 브랜치에 적용
- git stash clean 후 복원

<br/>

## 과정: git stash 다른 브랜치에 적용

<br/>

1. make changes
2. git stash save
3. git branch xxx HEAD
4. git checkout xxx
5. git stash pop

#### Shorter:

1.  make changes
2.  git stash
3.  git checkout -b xxx
4.  git stash pop
    <br/>
    <br/>
    <br/>

            참조:
            https://stackoverflow.com/questions/6925099/git-stash-changes-apply-to-new-branch

<br/>

## 과정: git stash clean 후 복원

<br/>

#### 잘못해서 git stash clean을 했을 때

     git fsck --unreachable | grep commit | cut -d ' ' -f3 | xargs git log --merges --no-walk --grep=WIP

    git의 기본 메커니즘에 따르면 복원할 수 있는 방법은 없지만
    repo에 만약 남아있다면
    이 방법이 될 수 도 있다.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/32517870/how-to-undo-git-stash-clear

<br/>

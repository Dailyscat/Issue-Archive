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

# Issue: git stash 명령어를 사용했지만 제대로 stash 되지 않음

## 상황:

git stash로 진행중인 작업을 잠시 멈추고 다른 일을 했어야 했는데, 새로 작성한 파일이 들어가지 않아서 git이 꼬였던 상황

<br/>

## 진행 과정:

- stash의 untracked, unstaged, staged file 알기
- git stash --all 명령어로 새로 만든 파일까지 추적되도록 설정

<br/>

## 과정1: stash의 untracked, unstaged, staged file 알기

<br/>
  Tracked

- 현재 repository의 관리 대상
- 스냅샷에 포함되어 있는 파일
- Unmodified
- Modified
- Staged(커밋하면 저장소에 기록 즉 git add .를 한 상태)

Untracked

- 위 Unmodified, Modified, Staged 파일을 제외한 나머지 파일
- 기존 repository에 저장되어 있던 파일 외 새로 생성한 파일
- 처음 저장소를 clone하면 모든 파일은 Tracked, Unmodified 상태가 된다.
  <br/>
  <br/>
  <br/>

          참조:
          https://git-scm.com/book/ko/v1/Git%EC%9D%98-%EA%B8%B0%EC%B4%88-%EC%88%98%EC%A0%95%ED%95%98%EA%B3%A0-%EC%A0%80%EC%9E%A5%EC%86%8C%EC%97%90-%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0

<br/>

## 과정2: git stash --all 명령어로 새로 만든 파일까지 추적되도록 설정

<br/>
  스냅샷, 즉 repository에서 Tracked하고 있는 파일들만 git stash 명령어를 통해 저장이 가능하다. 새로 만들어졌거나, .gitignore 파일에서 참조하는 파일은 tracked되지 않는다.
  이전 버전에서는 *git stash --include-untracked* 를 사용하여 추적되지 않는 파일을 관리했지만, 18년 5월 후에
  git stash -all을 사용하여 추적되지 않던 파일도 가능하도록 했다.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/835501/how-do-you-stash-an-untracked-file
        https://git-scm.com/book/ko/v1/Git-%EB%8F%84%EA%B5%AC-Stashing
        https://suwoni-codelab.com/git/2018/04/06/Git-stash/
        https://wayhome25.github.io/git/2017/04/05/git-05-stash/

<br/>

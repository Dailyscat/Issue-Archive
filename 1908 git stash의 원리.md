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

# Issue: git stash 구조 파악

## 상황:

stash 이슈 처리 중 좀 더 깊게 알아보았다.

<br/>

<br/>

## git stash란?

<br/>

stash는 기본적으로 두개의 commit을 포함하고 있다. 하나는 i for the index / staging-area contents 즉 기존에 add를 통해 올려져 있는, 이미 존재하는 파일들의 영역에 대한 commit, 그리고 하나는 위의 이미 존재하는 영역에 대한 변경 사항들이다.

    ...--o--o--o   <-- branch (HEAD)
              |\
              i-w   <-- stash

이때 git stash apply --index 명령어를 통하여 이미 스테이징 된 파일들도 함께 적용시킬 수 있다.

    If you omit --index, git stash apply completely ignores the i commit.

이때 --index를 생략하면 스테이징 된 파일은 무시된다.

여기서 특별하게 세개의 commit을 포함하는 경우도 있는데, 이는 --include-untracked or --all or -u 명령어를 사용해서 새로 생성된 파일들에 대한 stash를 의도한 명령의 경우이다.

    ...--o--o--o   <-- branch (HEAD)
              |\
              i-w   <-- stash
                /
              u

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/51275777/why-does-git-stash-pop-say-that-it-could-not-restore-untracked-files-from-stash
        https://wayhome25.github.io/git/2017/05/16/git-07-stashing-cleaning/

<br/>

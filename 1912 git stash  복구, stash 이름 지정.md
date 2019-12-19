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

# Issue: git stash 복구, 이름 지정

## 상황:

git stash 해놓은 게 삭제된 상황
후에 이를 명확히하기 위하여 이름을 지정하려는 상황
<br/>

## 생각해낸 방안:

- git stash 해놓은 게 삭제된 상황
- stash를 명확히하기 위하여 이름을 지정

<br/>

## 방안: git stash 해놓은 게 삭제된 상황

<br/>

1. 일단 삭제한 해쉬를 \$함께 입력하여 복구해본다.
   1. > git stash apply \$stash_hash
   2. > git branch recovered \$stash_hash (다른 브랜치에 삭제된 stash 적용)
2. 삭제 된 해쉬를 찾는다.
   1. > git fsck --no-reflog | awk '/dangling commit/ {print \$3}'

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/89332/how-to-recover-a-dropped-stash-in-git

<br/>

## 방안: stash를 명확히하기 위하여 이름을 지정

<br/>

1. > git stash save "my_stash"
2. > git stash push -m "message"

1번은 depricate 되었지만 사용은 가능하다.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/11269256/how-to-name-and-retrieve-a-stash-by-name-in-git

<br/>

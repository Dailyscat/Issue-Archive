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

# Issue: stash 이름과 함께 저장

## 상황:

<br/>

## 알게된 부분 정리:

- +

<br/>

## 개념:

<br/>
  
```
git stash push -m "my_stash"
Where "my_stash" is the stash name.

Some more useful things to know: All the stashes are stored in a stack. Type:

git stash list
This will list down all your stashes.

To apply a stash and remove it from the stash stack, type:

git stash pop stash@{n}
To apply a stash and keep it in the stash stack, type:

git stash apply stash@{n}
Where n is the index of the stashed change.

Notice that you can apply a stash and keep it in the stack by using the stash name:

git stash apply my_stash_name
```

<br/>
<br/>
<br/>

        참조:

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

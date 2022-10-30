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

# Issue: unable to start container process: unable to apply cgroup configuration: cannot enter cgroupv2

## 상황: 

## 생각해낸 방안:

- There is another workaround in the related Docker Desktop issue that doesn't require using an old Docker Desktop.

Edit "$HOME/Library/Group Containers/group.com.docker/settings.json" and update deprecatedCgroupv1 to true.

This requires Docker Desktop 4.6.0 or newer.

## 방안: 

<br/>

<br/>
<br/>
<br/>

        참조:
        https://github.com/CircleCI-Public/circleci-cli/issues/672

<br/>

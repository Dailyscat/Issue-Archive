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

# Issue: archive contains more than 65535 entries.

## 상황: 

Cause: archive contains more than 65535 entries.

To build this archive, please enable the zip64 extension.
See: https://docs.gradle.org/8.2/dsl/org.gradle.api.tasks.bundling.Zip.html#org.gradle.api.tasks.bundling.Zip:zip64

## 생각해낸 방안:

- shadowJar 태스크에 zip64 property 추가.

## 방안: shadowJar 태스크에 zip64 property 추가.

<br/>

파일이 65535개를 넘거나 4gb를 넘으면 압축 필요.


```
shadowJar {
    zip64 true
}

```

<br/>
<br/>
<br/>

        참조:
        https://github.com/GradleUp/shadow/issues/107#issuecomment-66461675

<br/>

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

# Issue: gradle 파일에서 command로 디버깅

## 상황:
gradle 파일에서 command로 디버깅

<br/>

## 알게된 부분 정리:

- checkDirectories

<br/>

## 개념:checkDirectories

<br/>
  디렉토리를 보고 싶었음.

  task checkDirectories {
    doLast {
        def process = new ProcessBuilder('sh', '-c', 'cd front-ui && pwd && ls -al').directory(project.rootDir).start()
        process.inputStream.eachLine { line -> println line }
        process.waitFor()
    }
  }

  그리고 필요한 task가 실행되기 전에 아래 명령을 통해서 실행되도록 설정.
    dependsOn checkDirectories
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

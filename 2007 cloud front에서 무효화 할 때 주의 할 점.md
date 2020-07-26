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

# Issue: 2007 cloud front invalidation시 주의할 점

## 상황: 기존 invalidation 이력을 통해 똑같이 invalidation을 하려 했지만 제대로 작동하지 않음

<br/>

## 생각해낸 방안:

- 하위 디렉토리 무효화를 위해 /타겟/\*을 통해 무효화
- /타겟\*과 같은 용법을 통하여 하위 디렉토리까지 전부 무효화 하도록 설정

<br/>

## 방안: 하위 디렉토리 무효화를 위해 /타겟/\*을 통해 무효화(실패)

<br/>

/타겟/하위타겟/하하위타겟 의 path를 무효화 하기 위해 기존과 같이 /타겟/\*을 실행하여 무효화를 진행했지만 제대로 되지 않았다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: /타겟\*과 같은 용법을 통하여 하위 디렉토리까지 전부 무효화 하도록 설정 (성공)

<br/>

/타겟/\*과 같은 path는 특정 디렉토리의 모든 파일만을 무효화 한다. 즉 서브 디렉토리는 가만히 있게 된다.

특정디렉토리와 하위 디렉토리의 모든 파일의 무효화를 위해서는 /타겟\*과 같이 설정해줘야 정상적인 무효화가 동작한다.

하지만 최대한 특정 path에 대해 자세하게 표현하여 처리하는 것이 좋다.

<br/>
<br/>
<br/>

        참조:
        https://docs.aws.amazon.com/ko_kr/AmazonCloudFront/latest/DeveloperGuide/Invalidation.html#invalidation-specifying-objects-paths

<br/>

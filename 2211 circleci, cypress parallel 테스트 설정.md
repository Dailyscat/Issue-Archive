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

# Issue: circleci, cypress parallel 테스트 설정

## 상황:
여러 테스트를 주기적으로 실행하기 때문에 시간이 오래걸림. 이를 위해 병렬 테스트 적용

<br/>

## 알게된 부분 정리:

- orb 대신 vanilla로 설정 적용

<br/>

## 개념: orb 대신 vanilla로 설정 적용

<br/>

    multirepo 설정에서 orb를 사용하려고 했으나 working_directory 설정도 잘 안먹히고 이슈가 많았었음. 그래서 패스

    vanilla로 아래와 같이 적용
    병렬 적용 잘됨.

    ```
    jobs:
  test:
	docker:
	  - image: cimg/node:16.10.0
	parallelism: 2
	  steps:
        - checkout
		- run: 
          name: Integration tests
          command: |
            TEST_FILES="$(circleci tests glob "cypress/integration/**/*.js" | circleci tests split --split-by=timings)"
            npm run cypress --spec ${TEST_FILES//$'\n'/','}
    ```



<br/>
<br/>
<br/>

        참조:
        https://docs.cypress.io/guides/continuous-integration/circleci#Additional-Examples
        https://enrq.me/dev/2021/10/04/cypress-parallel-in-circleci/
        https://github.com/artsy/force/blob/main/.circleci/config.yml#L219-L235

<br/>

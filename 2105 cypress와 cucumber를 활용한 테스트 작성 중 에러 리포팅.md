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

# Issue: cypress와 cucumber를 활용한 테스트 작성 중 에러 리포팅

## 상황:
라인쇼핑의 스냅과 카테고리 관련 페이지에 대한 테스트 작성

<br/>

## 알게된 부분 정리:

- 특정 에러를 무시하고 테스트가 진행되도록 설정하는 법
- 모바일에서의 테스트를 위한 설정
- cucumber에서 describe하는 텍스트에서 파라미터를 받으려면 ``(backtick)으로 처리해야한다.


<br/>

## 개념: 특정 에러를 무시하고 테스트가 진행되도록 설정하는 법

<br/>
        Cypress.on('uncaught:exception', (err, runnable) => {
            // returning false here prevents Cypress from
            // failing the test
            if() {
                return false;
            }
        })
<br/>
<br/>
<br/>

        참조: https://stackoverflow.com/questions/53845493/cypress-uncaught-assertion-error-despite-cy-onuncaughtexception

<br/>

## 개념: 모바일에서의 테스트를 위한 설정

<br/>

    cypress.json 파일에

        "userAgent":"Mozilla/5.0 (Linux; Android 6.0.1; SM-G532G Build/MMB29T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.83 Mobile Safari/537.36",

    를 부여하여 테스트

<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: cucumber에서 describe하는 텍스트에서 파라미터를 받으려면 ``(backtick)으로 처리해야한다.

<br/>
        Then(`파라미터의 값이 {string}이어야 한다.`, (parameter) => {
            expect(parameter)
        })

    Then 안에서 파라미터를 쓰려면 '파라미터' 로 처리

<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: Error: Step implementation missing

<br/>

    package.json에

        "cypress-cucumber-preprocessor": {
            "step_definitions": "cypress/integration/features/step_definitions/**/"
        }

    위와 같은 코드 추가하여 cucumber 전처리기가 작동해야 될 폴더를 지정해주어야 한다.
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: cucumber와 cypress를 함께 사용할 때 cy.intercept를 사용하는 부분은 한 블럭에서 다 처리가 되어야 한다.

<br/>

        When(`요청을 보냈다면`, () => {
            cy.intercept({
                url: 'https://abcde/wow*',
                query: {
                    gggo: "2"
                }
            }).as('req');
            cy.get(".class").children().first().next().click();
            cy.wait('@req');
        })

위와 같은 방식이 When 절 블럭안에서 다 처리되어야 한다.

xhr 요청의 응답코드가 필요했는데 cy.wait이 작동하지 않았는데 이 부분은 cypress 버전 문제였고 7.2.0에서 resolve되어서 버전 업데이트를 했고 그래도 wait이 작동하지 않아서 확인해보니 원래는
When절에서 click하고 Then절에서 wait후 요청 코드를 확인했는데 이 부분이 호환이 안되서 한 블럭안에서 처리해보니 됐다.


<br/>
<br/>
<br/>

        참조:
        https://github.com/cypress-io/cypress/releases/tag/v7.2.0

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

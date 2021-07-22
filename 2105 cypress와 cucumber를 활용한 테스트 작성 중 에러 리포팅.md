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
- intercept와 wait을 사용할 때 주의점
- cucumber를 사용할 때는 테스트 문서에 괄호 ")"를 사용하면 안된다.

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

## 개념: intercept와 wait을 사용할 때 주의점

<br/>

```
let conunt = 0;
When(호호, (merchant) => {
    cy.intercept({
        pathname: '/a/b',
        query: {
            url: "*",
        },
    }).as(`zz_${count}`)
    cy.get(':nth-child(1) > .product_box > .button_group > .link').click({force: true});
})
Then(히히, (asp) => {
    cy.wait(`@zz_${count}`).then(interception => {
        count++;
    })
})
```

이때 count값이 없어도 `대체적`으로 잘 작동하긴하지만 이전의 alias를 인식하여 잘못된 결과를 도출해내는 경우가 있었다. alias를 리셋하는 방법을 찾아봤지만 딱히 없음

<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: cucumber를 사용할 때는 테스트 문서에 괄호 ")"를 사용하면 안된다.

<br/>

괄호가 있으면 에러를 발생시킨다.

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

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

# Issue: mongodb mongoshell의 update 사용 시 주의 점(ex. db.collection.update)

## 상황:

db.collection.update 사용 상황

<br/>

## 생각해낸 방안:

- db.collection.update는 Operator Expressions가 필요하다.

<br/>

## 방안: db.collection.update는 Operator Expressions가 필요하다. (성공)

<br/>

    The modifications to apply. Can be one of the following:

    Update document

      - Contains only update operator expressions.

    Replacement document
      - Contains only <field1>: <value1> pairs.

    Aggregation pipeline
      - Consists only of the following aggregation stages:

        - $addFields and its alias $set
        - $project and its alias $unset
        - $replaceRoot and its alias $replaceWith.

이때 Replacement document에서

단순하게
{
a: "wow"
}
를 주고 기존 문서에서 a라는 값이 "wow"로 바꾸기를 기대했는데 \$set으로 연산자를 함께 주어야 바뀌는것을 확인했다.

<br/>
<br/>
<br/>

        참조:

<br/>

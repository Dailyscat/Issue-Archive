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

# Issue: $in과 $all을 잘못사용하여 생긴 오류

## 상황:

objectId를 배열에 저장해두고 그 배열 내부에 있는 해당 id들을
사용하여 원하는 클래스들을 가져와야 하는 상황
<br/>

## 생각해낸 방안:

- array operator인 \$all을 사용하여 해당하는 document 가져오기
- \$in을 사용하여 배열 내부에 있는 요소에 해당하는 document 가져오기

<br/>

## 방안: array operator인 \$all을 사용하여 해당하는 document 가져오기(실패)

<br/>
  예전에 한번 스쳐지나가듯 봤던 query 내용이라서 all이라는 것이 뭔가 다 가져온다는 느낌이라서 제대로 내용을 확인하지 않고 사용했던 것이 화근이었다. 제대로 작동하지 않아서 내용을 살펴보니

      { tags: { $all: [ "ssl" , "security" ] } }

이 쿼리는

      { $and: [ { tags: "ssl" }, { tags: "security" } ] }

아래 쿼리와 같다. 즉 특정 필드의 배열에 쿼리의 요소들이 존재하는 document들만 가져오는 것이었다.

공식 문서를 잘보자..
<br/>
<br/>
<br/>

        참조:
        https://docs.mongodb.com/manual/reference/operator/query/all/

<br/>

## 방안: \$in을 사용하여 배열 내부에 있는 요소에 해당하는 document 가져오기 (성공)

<br/>

    db.inventory.find( { qty: { $in: [ 5, 15 ] } } )

원한 쿼리가 이거였다.

즉 배열내부의 아이디들을 forEach, map방식으로 돌면서 teacherProgram에 저장되어 있는 document중 해당하는 document만 가져와서 리턴해주는 쿼리였다.
<br/>
<br/>
<br/>

        참조:
        https://docs.mongodb.com/manual/reference/operator/query/in/

<br/>

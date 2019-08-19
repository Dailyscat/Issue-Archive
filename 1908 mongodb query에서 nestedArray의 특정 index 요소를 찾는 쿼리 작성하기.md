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

# Issue: nestedArray에서 특정 index 요소를 수정해야하는 mongodb query 짜기

## 상황:

신원 인증 기능을 만들어야 하는 상황에서
기존 스키마는 단순히 array안에 Object를 받고 있었다.
이때 이 array내부의 특정 Object내부 필드를 수정, 혹은 삽입을 해야 했는데 굉장히 까다로웠다.

ex:
[{state:"wait"},{state:"approve"},{state:"wait"}]

<br/>

## 생각해낸 과정:

- \$elemMatch 쿼리를 통해 array내부에서 해당하는 field를 찾고 update
- \$ operator를 통해 해당 object의 field 수정
- object를 만들고 아예 기존의 object를 갈아끼우기

<br/>

## 방안: object를 만들고 아예 기존의 object를 갈아끼우기(성공)

<br/>

       queryObj["academics." + idx] = subject;
     TeacherHomes.update(
       {
         userId: userId
       },
       {
         $set: queryObj
       },
       { upsert: true },
       (err, result) => {
         console.log(err, result);
       }
     );

mongodb에서 특정 idx에 접근하여 바꿀 수 있는 방법이 없고,
위와 같은 방식의 쿼리에서 특정 idx에 접근하려면 "academics.4"와 같은 방식으로 접근해야하기 때문에 미리 객체를 만든 후 접근할 수 있도록 했다.

위와 같은 방식으로 academics라는 배열의 특정 idx에 갈아끼울 object를 할당하고 \$set operator를 사용했다. 이때 upsert는 target의 위치에 똑같은 객체가 존재하면 수정, 없다면 새로 창조하게끔 만드는 optional 인자이다.

set: `If the field does not exist, $set will add a new field with the specified value, provided that the new field does not violate a type constraint. If you specify a dotted path for a non-existent field, $set will create the embedded documents as needed to fulfill the dotted path to the field.`

=> object의 필드 자체를 수정하는 연산자

addToSet: `$addToSet only ensures that there are no duplicate items added to the set and does not affect existing duplicate elements. $addToSet does not guarantee a particular ordering of elements in the modified set.`
=> 기존의 arrapy 내부에 원하는 array를 중복없이 삽입하는 연산자

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/52294572/mongodb-addtoset-to-deep-nested-array-of-object/52295993
        https://jira.mongodb.org/browse/SERVER-831
        https://docs.mongodb.com/manual/reference/operator/update/addToSet/?searchProperty=current&query=%24%24
        https://docs.mongodb.com/manual/reference/operator/update/push/
        https://stackoverflow.com/questions/10773172/what-am-i-doing-wrong-with-set-and-inc-in-update

<br/>

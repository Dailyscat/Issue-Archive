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

# Issue: 특정 상태의 유저를 검색하는 쿼리를 사용하여 데이터 가져오기

## 상황:

대기 상태의 유저들을 검색하여 list를 가져오고 뿌려줄 수 있는 api 작성
<br/>

## 생각해낸 방안:

- field내의 value 값을 검색하여 원하는 유저들을 가져오도록 api 작성

<br/>

## 방안: field내의 value 값을 검색하여 원하는 유저들을 가져오도록 api 작성 (성공)

<br/>

    let waitApprovalTeacherHome = TeacherHomes.find({
     $or: [
       {
         academics: {
           $elemMatch: { approvalState: { $regex: /^waitApproval/ } }
         }
       }
     ]

}).fetch();

<br/>
<br/>
<br/>

        참조:
        https://docs.mongodb.com/manual/reference/operator/query/elemMatch/

<br/>

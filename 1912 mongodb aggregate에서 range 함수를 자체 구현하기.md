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

# Issue: mongodb aggregate에서 range 함수를 자체 구현하기

## 상황:

현재 product의 mongodb 버전이 3.2를 사용하고 있는 상태라 range 함수를 사용할 수 없어서 fullcalendar에서 현재 보이는 날짜에 해당하는 범위의 document를 가져와야 하는 상황

<br/>

## 생각해낸 방안:

- $match, $and, $gte, $lte 를 사용하여 범위 만들기

<br/>

## 방안: $match, $and, $gte, $lte 를 사용하여 범위 만들기

<br/>

    {
        $project: {
          parentUserId: 1,
          ....
        }
      },
      {
        $match: {
          $and: [
            {
              startDate: {
                $gte: startDateInCalendar
              },
              endDate: {
                $lte: endDateInCalendar
              }
            }
          ]
        }
      },
      {
        $lookup: {
          from: ...,
          localField: ...,
          foreignField: "_id",
          as: ...
        }
      },
      {
        $lookup: {
          from: ...,
          localField: ...,
          foreignField: "_id",
          as: ...
        }
      }
    ]);

1. 처음에는 \$lookup의 from에 적어야 하는 목표 db를 script상에서 객체로 존재하는 schema를 참조하는 방식을 생각했는데, 이는 에러가 발생한다. 어차피 처음에 db를 참조하여 들어가기 때문에 collection 이름을 string으로 주어도 참조가 가능하다.

2. 3.6 버전에서는 lookup 자체에서 참조하는 각 db에서 필요한 속성만을 pipeline 속성을 활용하여 project를 임베딩하여 최종 결과를 낼 수 있는데, 3.2...를 사용하는 현재 프로덕트에서는 불가능하여 전체를 사용하는 방식으로 일단 구현해놓고 버전 업 후 바꿀 예정이다.

3. project와 group의 [차이](https://stackoverflow.com/questions/53134973/what-is-the-actual-difference-between-project-and-group)

   간단하게 project의 목적은 결과물에서 어떤 속성이 포함되는지 안 포함되는지에 중점을 두고 있고,
   group은 각 속성이 어떻게 계산되어 표현될지에 중점을 두고 있다.

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/25436630/mongodb-how-to-find-and-then-aggregate/25437434
        https://docs.mongodb.com/manual/reference/operator/aggregation/range/
        https://docs.mongodb.com/v3.2/reference/operator/aggregation/project/
        https://stackoverflow.com/questions/49427425/mongodb-aggregate-one-collection-into-another-collection-for-a-given-document
        https://stackoverflow.com/questions/53710203/project-in-lookup-mongodb
        https://code-maven.com/slides/mongodb/mongodb-aggregation-example-11
        https://stackoverflow.com/questions/37549019/combining-group-and-project-in-mongodb-aggregation-framework

<br/>

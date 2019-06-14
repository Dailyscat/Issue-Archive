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

# Issue: 기존 db에 데이터와 그에 따른 schema 추가

## 상황:

클래스 요청을 저장하는 collection의 document들에 새로운 field를 추가했어야했고, 그에 따른 schema도 추가해줬어야 했다.

<br/>

## 생각해낸 방안:
+ db 자체에서 db를 참조하여 다른 collection의 정보를 받고 그 정보를 가공하여 저장해보자.
+


<br/>

## 방안: db를 참조하여 collection을 사용하는건 가능했지만 save되지 않았다.  (실패)
<br/>
  
    db.teacherprograms.find({}).map((cur) => {
        let requestedPrograms = db.requestprograms.find({"programId": cur._id}).toArray();
        if( requestedPrograms.length > 0 ) {
            requestedPrograms.forEach((request) => {
                request.classOriginFee = Number(cur.fee);
        });
      }
    });
​  위의 코드가 원래의 client 환경이라면 바로 저장이됐을 테지만 db 자체에서 사용할 때는 save, update, insert 등의 메소드를 최종적으로 사용해줬어야 했다.
​​

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/9711529/save-subset-of-mongodb-collection-to-another-collection
        https://stackoverflow.com/questions/19822871/how-to-update-a-collection-based-on-another-collection-in-mongodb
<br/>

## 방안:  (성공)
<br/>

    db.teacherprograms.find({}).map((cur) => {
        let requestedPrograms = db.requestprograms.find({"programId": cur._id}).toArray();
        if( requestedPrograms.length > 0 ) {
           requestedPrograms.forEach((request) => {
                 request.classOriginFee = Number(cur.fee);
                 db.requestprograms.save(request);
                 //save 메소드 추가
           });
        }
    });
​
    save 메소드를 쓴 이유는 save 메소드자체가 document가 _id필드를 가지고 있다면 insert()로 작동하고 없다면 update()로 작동하기 때문에 어떠한 변수를 염두에 두고 save 메소드를 사용하기로 했다.
​​
<br/>
<br/>
<br/>

        참조:
        https://docs.mongodb.com/manual/reference/method/db.collection.save/

<br/>


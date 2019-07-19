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

# Issue: 기존 db에 저장되어 있던 url의 png문자를 jpg로 대체

## 상황:
기존 db에 저장되어 있던 png가 적혀있는 url을 jpg로 대체하여 다시 저장해야 하는 상황

<br/>

## 생각해낸 방안:
+ text, search query operator 사용
+ or, and 쿼리 오퍼레이터와 정규표현식을 사용하여 includes 기능 적용한 쿼리 작성


<br/>

## 방안: text, search query operator 사용 (실패)
<br/>

  인덱싱이 되있지 않음으로 쿼리 오퍼레이터를 사용할 수 없었음.
<br/>
<br/>
<br/>

        참조:

<br/>

## 방안: or, and 쿼리 오퍼레이터와 정규표현식을 사용하여 includes 기능 적용한 쿼리 작성 (성공)
<br/>

    db.edufessionals.find({})
    .forEach((cur, idx) => {
      cur.tutorClassSet.forEach((classDescription, index) => {
            cur.tutorClassSet[index].classThumbNailImageUrl = classDescription.classThumbNailImageUrl.split("png").join("jpg");
      })

      db.edufessionals.save(cur);
    });​​
    ​
    db.notices.find({"content" : {$regex : ".*png.*"}})
    .forEach((cur, idx) => {
      let content = cur.content;
      cur.content = content.split("png").join("jpg");

      db.notices.save(cur);
    });​
    ​
    db.teacherprograms.find({"description" : {$regex : ".*png.*"}})
    .forEach((cur, idx) => {
      let description = cur.description;
      cur.description = description.split("png").join("jpg");

      db.teacherprograms.save(cur);
    });​
    ​
    db.teacherprograms.find({"images" : {$regex : ".*png.*"}})
    .forEach((cur, idx) => {
      cur.images.forEach((imgUrl, index) => {
            cur.images[index] = imgUrl.split("png").join("jpg");
      })

     db.teacherprograms.save(cur);
    });​​
    ​
    db.users.find({ $and: [{"roles":"teacher"},{"profile.imageUrl" : {$regex : ".*png.*"}}] })
    .forEach((cur, idx) => {
      let imageUrl = cur.profile.imageUrl;
      cur.profile.imageUrl = imageUrl.split("png").join("jpg");

      db.users.save(cur);
    });​
    ​
    db.users.find({$and:[{"roles":"teacher"},{$or:[{"profile.contract.licensePhoto": {$regex : ".*png.*"}},{"profile.contract.bankAccountPhoto": {$regex : ".*png.*"}}]}]})
    .forEach((cur, idx) => {
      if(cur.profile.contract.licensePhoto.includes("png")){
        let photoUrl = cur.profile.contract.licensePhoto;
        cur.profile.contract.licensePhoto = photoUrl.split("png").join("jpg");
    ​
     } else if (cur.profile.contract.bankAccountPhoto.includes("png")) {
        let photoUrl = cur.profile.contract.bankAccountPhoto;
        cur.profile.contract.bankAccountPhoto = photoUrl.split("png").join("jpg");
     }

     db.users.save(cur);
    });​
​



<br/>
<br/>
<br/>

        참조:
        https://docs.mongodb.com/manual/reference/operator/query/and/
        https://stackoverflow.com/questions/10610131/checking-if-a-field-contains-a-string
        https://stackoverflow.com/questions/40806871/full-text-search-in-node-js-with-mongoose


<br/>


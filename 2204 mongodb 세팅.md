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

# Issue: mongodb server 세팅

## 상황:

<br/>

## 알게된 부분 정리:

- mongodb server 세팅
- mongoose의 model과 documents 차이

<br/>

## 개념: mongodb server 세팅

<br/>
  mac에서 세팅(home brew 설치 추천)
  https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-os-x/

  brew services start mongodb-community@5.0 을 통해서 macos Service로 프로세스 구동 가능

  linux에서 세팅(home brew 설치 추천)
  https://www.mongodb.com/docs/manual/administration/install-on-linux/

  mongodb uri 세팅
  https://www.mongodb.com/docs/manual/reference/connection-string/

  
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: mongoose의 model과 documents 차이

<br/>

mongoose의 models는 정의한 스키마의 생성자고, 해당 models의 인스턴스는 document라고 불린다. models는 몽고디비에 저장된 documents를 생성, 읽기에 대한 책임을 가진다.

mongoose.mode()을 호출하면 몽구스는 모델을 컴파일한다.

```
const schema = new mongoose.Schema({ name: 'string', size: 'string' });
const Tank = mongoose.model('Tank', schema);
```

이때 Tank라는 인자는 tanks라는 소문자, 복수형으로 변하여 몽고디비 컬렉션이 생성된다.


<br/>
<br/>
<br/>

        참조:
        https://mongoosejs.com/docs/models.html

<br/>

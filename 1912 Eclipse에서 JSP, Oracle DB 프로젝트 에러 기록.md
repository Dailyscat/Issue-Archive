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

# Issue: Eclipse에서 JSP, Oracle DB 프로젝트 에러 기록

## 상황:

Eclipse에서 JSP, Oracle DB 프로젝트 에러 기록

<br/>

#### 1.

    6 행: ORA-12899: "SYS"."BOOK"."BOOKNAME" 열에 대한 값이 너무 큼(실제: 22, 최대값: 20)

​
해결:
​

alter table Book modify(bookname varchar(60));
​

#### 2.

id 자동으로 +1 값 설정되게
(SELECT NVL(MAX(REGION_ID)+1,0) FROM COUNTRIES))​
​
​

#### 3.

out.println(word);
--> 사용하여 디버깅
​

#### 4.

contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
--> 사용하여 한글사용 되도록 설정
​

https://installed.tistory.com/entry/12-JSP-%EA%B0%84%EB%8B%A8%ED%95%9C-%EC%98%88%EC%A0%9C-%EB%AA%A9%EB%A1%9D-%EC%9E%85%EB%A0%A5-%EC%88%98%EC%A0%95-%EC%82%AD%EC%A0%9C​
​

​

#### 5.

INSERT INTO Customer(bookid, bookname, publisher, price) VALUES (1, '축구의 역사', '굿스포츠', 7000)
​

#### 6.

​
일정한 도메인을 유지하고 싶어서 수정, 삭제 후 reload를 통하여
삭제와 수정에 대한 내용을 안보여주려고 했는데, 이 때문에 수정과 삭제 쿼리가 제대로 실행되지 않았다.
​

#### 7.

SELECT \* FROM Customer, Orders WHERE (1) Orders.bookid = 5 AND (2) Customer.custid = ( SELECT Orders.custid FROM Orders WHERE Orders.bookid=5 )
​​

1. (2)에서 custid만 넣었을 때 column이 애매하다는 에러가 났다.
   ​
   이는 Customer.custid로 해결했다.
   ​

2. ​​(1)이 없이 (2)만 쿼리에 포함되어 있을 때는 모든 order 목록에 (2)에서 걸러진 customer에 해당하는 사람의 이름과 나이가 전부다 포함되어서 나왔다.
   ​
   이는 (1)을 추가하여서 Orders.bookid를 포함하는 조건을 주어서 해결했다.
   ​

참고:

http://blog.naver.com/PostView.nhn?blogId=pyj721aa&logNo=221466664622&categoryNo=0&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView​
​
https://insomniachaos.tistory.com/28​
​
​

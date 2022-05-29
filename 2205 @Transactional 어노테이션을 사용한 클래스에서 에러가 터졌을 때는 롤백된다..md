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

# Issue: @Transactional 어노테이션을 사용한 클래스에서 에러가 터졌을 때는 롤백된다.

## 상황:
트랜잭션을 전체 클래스에 넣어서 제어하는데 이때 트랜잭션이 필요 없는 함수를 호출시에 에러가 발생했다.
이때 롤백관련 에러가 발생, 이에대한 정리

<br/>

## 알게된 부분 정리:

- @Transactionl에서 Exception이 터지면 롤백 마크를 한다.

<br/>

## 개념: @Transactionl에서 Exception이 터지면 롤백 마크를 한다.

<br/>

```
Could not commit JPA transaction; nested exception is 
    javax.persistence.RollbackException: Transaction marked as rollbackOnly
```

내부를 잘 정리한글을 참조.
여튼 트랜잭션 내부에서 exception 처리는 최대한 익셉션을 잡아내지 않는 방향으로 가는것이 좋다.

<br/>
<br/>
<br/>

        참조:
        https://techblog.woowahan.com/2606/

<br/>

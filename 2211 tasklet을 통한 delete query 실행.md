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

# Issue: tasklet을 통한 delete query 실행

## 상황:
기존 작성한 삭제 배치에서 굳이 paging으로 나누어서 할 필요가 없다는 리뷰를 받고 tasklet으로 변경

<br/>

## 알게된 부분 정리:

- 16분 -> 1초

<br/>

## 개념: 16분 -> 1초

<br/>



  ```
      repository
          // delte, update 같은 경우 아래 어노테이션 필요
          @Transactional
          @Modifying
          @Query("delete from Data where updateDate < ?1")
          //Delete query method returning either no result (void) or the delete count.
          Integer deleteBatch(LocalDateTime now);



        private Tasklet deleteBatch() {
        return (contribution, chunkContext) -> {
            log.info("삭제 시작");
            LocalDateTime before6Month = LocalDateTime.now().minusMonths(6L);
            // 삭제 된 수는 integer로 리턴됨.
            Integer deletedRow = repository.deleteBatch(before6Month);
            log.info("삭제된 tn : " + deletedRow.toString());
            return RepeatStatus.FINISHED;
        };
    }
  ```

<br/>
<br/>
<br/>

        참조:
        https://docs.spring.io/spring-batch/docs/current/reference/html/step.html
        https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
        https://stackoverflow.com/questions/64164282/why-do-i-need-an-itemreader-in-my-job-step-if-i-only-need-to-delete-rows-using-i
        https://ykh6242.tistory.com/entry/Spring-%EB%8D%B0%EC%9D%B4%ED%84%B0-JPA-%EC%BF%BC%EB%A6%AC-%EB%A9%94%EC%86%8C%EB%93%9C-%EA%B8%B0%EB%8A%A5
        https://gimmesome.tistory.com/204
        https://jojoldu.tistory.com/336
        https://stackoverflow.com/questions/39796849/spring-repository-get-number-of-deleted-rows

<br/>

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

# Issue: spring batch에서 deleteAll 적용

## 상황:
배치 프로세스에서 deleteAll 적용을 어떻게 해야할지 고민

<br/>

## 알게된 부분 정리:

- repositoryItemWriter
- jpaItemWriter
- listener에서 deleteAll
- itemWriter의 write 메소드 오버라이드 하여 처리

<br/>

## 개념: repositoryItemWriter

<br/>
  
  ```
        RepositoryItemWriter repositoryItemWriter = new RepositoryItemWriter<>();
        repositoryItemWriter.setRepository();
        repositoryItemWriter.setMethodName();
  ```

  삭제시 단건 커밋하여 불가.
<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: jpaItemWriter

<br/>
  
  ```
        JpaItemWriter jpaItemWriter = new JpaItemWriter();
        jpaItemWriter.setEntityManagerFactory();
  ```
  삭제시 단건 커밋하여 불가.
<br/>
<br/>
<br/>

        참조:
        
        https://jojoldu.tistory.com/339
        https://stackoverflow.com/questions/35406503/how-to-delete-objects-in-itemwriter

<br/>

## 개념: listener에서 deleteAll

<br/>
  deleteAll 자체는 잘 작동하지만
  writer가 작동하는 순간 에러를 뱉어냄.
  이유는 processor -> writer로 넘어가는 중간에 처리가 되어 버리기 때문에
  writer에서 처리할 아이템이 없음.
  writer를 없이 구동해보기도 했으나 내부 로직 상 에러를 던지게 되어 있음.
  https://jojoldu.tistory.com/347의 흐름 참조.

  https://sadcode.tistory.com/47에서처럼 processor에서 적용 해보려고도 했으나 동일.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/65844852/spring-batch-itemwritelistener-and-skiplistener-doesnt-work-hand-in-hand
        https://stackoverflow.com/questions/67692017/how-to-know-if-it-is-the-last-item-in-itemprocessor-of-springbatch
        https://hanseom.tistory.com/210
        https://jojoldu.tistory.com/347

<br/>

## 개념: itemWriter의 write 메소드 오버라이드 하여 처리

<br/>

단건 삭제, 커밋이 40분 정도 걸렸다면 deleteAll은 3분의 1 가까이 시간 줄일 수 있었음.
  
  ```
  public class MyCompositeItemWriter<T> implements ItemWriter<T> {
 
    @Override
      public void write(List<? extends T> items) throws Exception {
            jpaRepository.deleteAll(items)
    }


  }
  ```

<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/63296065/how-to-override-spring-batch-compositeitemwriter-manage-transaction-for-delegate
        https://jojoldu.tistory.com/339

<br/>

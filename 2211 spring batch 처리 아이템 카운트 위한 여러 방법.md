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

# Issue: spring batch 처리 아이템 카운트 위한 여러 방법 정리

## 상황: https://howtodoinjava.com/spring-batch/records-count-example/

<br/>

## 개념:

<br/>
 
 ```
   public class ItemCountItemStream implements ItemStream {
 
  public void open(ExecutionContext executionContext) throws ItemStreamException {
  }
 
  public void update(ExecutionContext executionContext) throws ItemStreamException {
    System.out.println("ItemCount: "+executionContext.get("FlatFileItemReader.read.count"));
  }
 
  public void close() throws ItemStreamException {
  }
}


@Autowired
private JobBuilderFactory jobBuilderFactory;
 
@Autowired
private StepBuilderFactory stepBuilderFactory;
 
@Bean
public Job readCSVFilesJob() {
  return jobBuilderFactory
      .get("readCSVFilesJob")
      .incrementer(new RunIdIncrementer())
      .start(step1())
      .build();
}
 
@Bean
public Step step1() {
  return stepBuilderFactory
      .get("step1")
      .<Employee, Employee>chunk(1)
      .reader(reader())
      .writer(writer())
      .stream(stream())
      .build();
}
 
@Bean
public ItemCountItemStream stream() {
  return new ItemCountItemStream();
}
 ```



 ```
 public class ItemCountListener implements ChunkListener {
   
  @Override
  public void beforeChunk(ChunkContext context) {
  }
 
  @Override
  public void afterChunk(ChunkContext context) {
     
    int count = context.getStepContext().getStepExecution().getReadCount();
    System.out.println("ItemCount: " + count);
  }
   
  @Override
  public void afterChunkError(ChunkContext context) {
  }
}


@Autowired
private JobBuilderFactory jobBuilderFactory;
 
@Autowired
private StepBuilderFactory stepBuilderFactory;
 
@Bean
public Job readCSVFilesJob() {
  return jobBuilderFactory
      .get("readCSVFilesJob")
      .incrementer(new RunIdIncrementer())
      .start(step1())
      .build();
}
 
@Bean
public Step step1() {
  return stepBuilderFactory
      .get("step1")
      .<Employee, Employee>chunk(1)
      .reader(reader())
      .writer(writer())
      .listener(listener())
      .build();
}
 
@Bean
public ItemCountListener listener() {
  return new ItemCountListener();
}

 ```


<br/>
<br/>
<br/>

        참조:

<br/>

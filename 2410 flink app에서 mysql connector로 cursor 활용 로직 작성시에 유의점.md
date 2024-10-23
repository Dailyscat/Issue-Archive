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

# Issue: flink app에서 mysql connector로 cursor 활용 로직 작성시에 유의점

## 상황:

Flink의 MySQL Connector를 사용하여 데이터베이스에서 데이터를 읽어올 때, 대용량 데이터를 처리하면서 cursor를 활용하려고 했는데 메모리 문제가 발생했다.
<br/>

<br/>

## 개념:

<br/>

아래 코드 작성해서 진행해봤는데

```
@Override
    public void snapshotState(FunctionSnapshotContext context) throws Exception {
        checkpointedState.clear();
        checkpointedState.add(lastCursorPosition);
    }

    @Override
    public void initializeState(FunctionInitializationContext context) throws Exception {
        ListStateDescriptor<Integer> descriptor = new ListStateDescriptor<>("lastCursorPosition", Integer.class);
        checkpointedState = context.getOperatorStateStore().getListState(descriptor);

        if (context.isRestored() && checkpointedState != null) {
            // Checkpoint에서 복구된 경우
            for (Integer state : checkpointedState.get()) {
                lastCursorPosition = state;
            }
        }
```

ResultSet.TYPE_SCROLL_SENSITIVE와 ResultSet.TYPE_SCROLL_INSENSITIVE는 ResultSet을 스크롤 가능하게 만들어주지만, 대용량 데이터에서는 메모리 문제를 일으킬 수 있다.
이 옵션들은 내부적으로 데이터를 캐싱하기 위해 Vector를 사용하며, 자바의 가비지 컬렉터가 이를 제대로 처리하지 못해 메모리 사용량이 증가한다.

rs.absolute() 사용하려면 scrollable 해야해서 그냥 TYPE_FORWARD_ONLY인 resultSet으로 사용. 복구를 걷어냄.

<br/>
<br/>
<br/>

        참조:
        https://blog.naver.com/kdeun00/80160454280
        https://forums.oracle.com/ords/apexds/post/oracle-jdbc-thin-driver-memory-leak-in-scrollable-result-se-0536
        https://stackoverflow.com/questions/2091659/behaviour-of-resultset-type-scroll-sensitive
        https://m.blog.naver.com/json2811/90173104613
        https://stackoverflow.com/questions/45499934/calling-absolute-on-a-resultset-that-is-not-set-to-type-scroll-sensitive-or-in
        https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html#absolute-int-
        https://stackoverflow.com/questions/43026386/if-resultset-is-not-scrollable-then-absolute-function-should-not-work

<br/>

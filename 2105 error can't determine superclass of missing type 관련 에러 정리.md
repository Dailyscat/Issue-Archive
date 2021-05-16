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

# Issue: error can't determine superclass of missing type 에러 로그 발생

## 상황: 운영의 문제는 아니지만 로컬에서 구동 시 error can't determine superclass of missing type 에러 로그가 발생해서 확인

## 생각해낸 방안:

- aspectj 관련 설정 변경

## 방안: aspectj 관련 설정 변경


<br/>
```
<!DOCTYPE aspectj PUBLIC "-//AspectJ//DTD//EN" "http://www.eclipse.org/aspectj/dtd/aspectj.dtd">

 <aspectj>
     <weaver options="-Xlint:ignore">
         <include within="oracle.jdbc..*"/>
     </weaver>
 </aspectj>
```

import 하는 부분에서 나는 에러인데, runtime에서는 나는 에러도 아니고 intelliJ와 maven을 같이 쓰는 부분에서 나는 에러인 것 같아서 무시하도록 했다.
<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/10582631/aspectj-error-cant-determine-superclass-of-missing-type

<br/>

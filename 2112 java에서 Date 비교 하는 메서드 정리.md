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

# Issue: java에서 Date 비교하는 메서드 정리

## 상황: date를 비교하여 분기처리 해야하는 요구사항이 있어서 찾아보았고 정리

<br/>

## 알게된 부분 정리:

- 주로 사용되는 메서드 정리

<br/>

## 개념: 주로 사용되는 메서드 정리

<br/>
  
  ```
  if(!historyDate.after(todayDate) && !futureDate.before(todayDate)) {
    /* historyDate <= todayDate <= futureDate */ 
  }

  if (date1.after(date2)) {
    System.out.println("Date1 is after Date2");
  }

  if (date1.before(date2)) {
      System.out.println("Date1 is before Date2");
  }

  if (date1.equals(date2)) {
      System.out.println("Date1 is equal Date2");
  }

  date1.compareTo(date2);

  Calendar cal1 = Calendar.getInstance();
  Calendar cal2 = Calendar.getInstance();
  cal1.setTime(date1);
  cal2.setTime(date2);

  if (cal1.after(cal2)) {
      System.out.println("Date1 is after Date2");
  }

  if (cal1.before(cal2)) {
      System.out.println("Date1 is before Date2");
  }

  if (cal1.equals(cal2)) {
      System.out.println("Date1 is equal Date2");
  }
  ```



<br/>
<br/>
<br/>

        참조:
        https://stackoverflow.com/questions/2592501/how-to-compare-dates-in-java

<br/>

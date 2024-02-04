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

# Issue: python 스크립트에서 한글사용했을 때 발생하는 오류 해결


<br/>

## 개념:

<br/>
  스크립트 파일에서 한글을 단순 사용만 할 수 있게 하려면 아래와 같이 사용
  ```
    #-*-coding:utf-8-*-
  ```

  유니코드로 디코딩 후 다시 인코딩하여 utf-8로 인코딩된걸 사용
  ```
  korean_str = korean_str.decode('cp949').encode('utf-8')
  ```

  default encoding을 변경
  python2 는 ascii
  python3 는 utf-8

  ```
  import sys
  print(sys.getdefaultencoding())

  ```


<br/>
<br/>
<br/>

        참조:
        https://hummingat.tistory.com/entry/%ED%95%9C%EA%B8%80-encoding

<br/>


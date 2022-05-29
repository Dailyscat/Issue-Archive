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

# Issue: 데몬으로 실행하는 fluentd의 설정파일을 변경했을 때 로그 발생

## 상황:

fluentd 설정 파일을 변경하고 sudo systemctl start fluentd 적용하는데 

```
Warning: The unit file, source configuration file or drop-ins of fluentd changed on disk. Run 'systemctl daemon-reload' to reload units.
```
이 워닝과 제대로 설정한 fluentd처럼 작동하지 않는 이슈

<br/>

## 알게된 부분 정리:

- systemctl daemon-reload 는 데몬 실행 시 꼭 필요하다.

<br/>

## 개념: systemctl daemon-reload 는 데몬 실행 시 꼭 필요하다.

<br/>
  현재 대상 서버들에서는 이게 생각보다 엄청 오래걸려서 에러가 있나했는데 중간에 멈추면 제대로 동작하지 않음 꼭 기다리다보면 결국 적용된다.
<br/>
<br/>
<br/>

        참조:
        https://pybo.kr/pybo/question/detail/551/

<br/>

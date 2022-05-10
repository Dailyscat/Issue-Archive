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

# Issue: vue에서 datePicker 사용시 date() parameter must be one of ... 에러

## 상황: 
cell을 클릭할 때마다 render가 되게하여 초기화 시켰는데
렌더 될 때 마다 해당 에러 발생

## 생각해낸 방안:
- 거슬러 올라가서 에러 발생 원인 파악 후 원인 해결

## 방안: 거슬러 올라가서 에러 발생 원인 파악 후 원인 해결

<br/>

```
TypeError: date() parameter must be one of [null, string, moment or Date]
    at Object.picker.date (bootstrap-datetimepicker.js?5644:1524:1)
    at VueComponent.mounted (vue-bootstrap-datetimepicker.js?133f:203:1)
    at callHook (vue.js?ba4c:2921:1)
    at Object.insert (vue.js?ba4c:4152:1)
    at invokeInsertHook (vue.js?ba4c:5947:1)
    at VueComponent.patch [as __patch__] (vue.js?ba4c:6166:1)
    at VueComponent.Vue._update (vue.js?ba4c:2670:1)
    at VueComponent.updateComponent (vue.js?ba4c:2788:1)
    at Watcher.get (vue.js?ba4c:3140:1)
    at Watcher.run (vue.js?ba4c:3217:1)
```

이유는 date-picker가 인터페이스로 제공하는 @dp-change에서 들어오는 date인자가 초기에 false로 들어온다. 위에 에러에서 말한 파라미터 타입이 아니기 때문에 발생.

false를 제외하여 적용 해주었다. 근데 자기들이 제공하는 인자인데 이렇게 에러나는게 좀 이상했다. 레거시라서 그럴 수 도..


<br/>
<br/>
<br/>

        참조:

<br/>

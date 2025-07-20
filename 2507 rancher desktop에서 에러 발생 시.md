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

# Issue: rancher desktop에서 에러 발생 시

## 알게된 부분 정리:

- 정리

<br/>

## 개념: 정리

<br/>

1. 일단 강제 종료하여 rancher re launch.
2. 그래도 안되면 factory reset

```
/Applications/Rancher\ Desktop.app/Contents/Resources/resources/darwin/bin/rdctl factory-reset
```

3. factory reset도 실패하면 stop 후 lima vm 제거

```
/Applications/Rancher\ Desktop.app/Contents/Resources/resources/darwin/lima/bin/limactl stop 0
/Applications/Rancher\ Desktop.app/Contents/Resources/resources/darwin/lima/bin/limactl delete 0
```

4. reinstall

~/Library/Application Support/rancher-desktop, ~/.rd
~/Library/Preferences/io.rancherdesktop.\*.plist.

<br/>
<br/>
<br/>

        참조:

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

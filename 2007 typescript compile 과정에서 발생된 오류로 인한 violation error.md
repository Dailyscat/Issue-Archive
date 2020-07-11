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

# Issue: typescript compile 과정에서 발생된 오류로 인한 violation error

## 상황:

컴포넌트팀이 만들어놓은 공통 컴포넌트를 사용 하는 상황에서 웹에서는 에러가 뜨지 않지만 앱에서는 violation error가 뜨면서 크래시 나는 상황

<br/>

## 생각해낸 과정:

- 제대로 컴파일 되도록 설정

<br/>

## 방안: 제대로 컴파일 되도록 설정 (성공)

<br/>

```

    const text = <TextMSmall color={colColorName}>{children}</TextMSmall>

    if(this.props.onTitleIconPress) {
	const icon = this.renderIcon(this.props.icon)
	return <>{text} {icon}</>
}
```

위와 같은 ts코드에서 {text} {icon} 사이에 있는 빈칸이 text값인 " "로 컴파일되어 js 코드로 변환이 되었었다. app에서는 text값을 적을 수 있는 태그가 제한되어 있는데 이를 놓쳐서 만들어진 컴포넌트였기 때문에 위와 같은 에러가 발생했던 것이다.

아찔한 버그였다..
<br/>
<br/>
<br/>

        참조:

<br/>

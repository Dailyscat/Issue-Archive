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

# Issue: offsetTop 관련하여 offsetParent, parentNode 차이 정리

## 상황: jquery의 offset().top을 대체를 위한 함수를 구현하려고 하는 과정에서 알게된 점 정리

<br/>

## 알게된 부분 정리:

- parentNode와 offsetParent 차이
- display:none인 요소에서 offsetParent는 Null


<br/>

## 개념: parentNode와 offsetParent 차이

<br/>

  
  ```
    The HTMLElement.offsetParent read-only property returns a reference to the element which is the closest (nearest in the containment hierarchy) positioned ancestor element. 

    offsetParent is the closest parent that has position:relative or position:absolute or the body of the page. parentNode is the direct parent, regardless of position.
  ```
<br/>
<br/>
<br/>

        참조:
        https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/offsetParent
        https://stackoverflow.com/questions/17974859/difference-between-offsetparent-and-parentelement-or-parentnode

<br/>

## 개념: display:none인 요소에서 offsetParent는 Null

<br/>
  display:none인 요소에서 offsetParent는 Null이기 때문에
  해당 요소에서 offsetTop을 가져올 수 없다. 가져와도 0이다.(기준이되는 offsetParent가 없기 때문에)

  이때 jquery의 .offset()은 가져올 수 가 있는데 아래와 같이 getBoundingClientRect 함수를 통해서 윈도우 기준으로 top에서 해당 요소의 위치를 파악한다 이후 pageYOffset(scrollY)값을 더해서(스크롤링 된 높이만큼) top 값을 리턴한다. 실제로 의미 있는 top 값이다.

  하여 실제로 의미있는 값을 위해서는, 그리고 요소의 Display가 none일 때를 대비하여 아래 함수와 같이 top 값을 가져오는 것이 낫다.

  ```
  		// Return zeros for disconnected and hidden (display: none) elements (gh-2310)
		// Support: IE <=11+
		// Running getBoundingClientRect on a
		// disconnected node in IE throws an error
  		if ( !elem.getClientRects().length ) {
			return { top: 0, left: 0 };
		}

		// Get document-relative position by adding viewport scroll to viewport-relative gBCR
		rect = elem.getBoundingClientRect();
		win = elem.ownerDocument.defaultView;
		return {
			top: rect.top + win.pageYOffset,
			left: rect.left + win.pageXOffset
		};
  ```

<br/>
<br/>
<br/>

        참조:
        https://developer.mozilla.org/en-US/docs/Web/API/Window/pageYOffset
        https://developer.mozilla.org/en-US/docs/Web/API/Element/getClientRects
        https://ohgyun.com/569
        https://stackoverflow.com/questions/6777506/offsettop-vs-jquery-offset-top
        https://stackoverflow.com/questions/44172651/difference-between-getboundingclientrect-top-and-offsettop/44173056
        https://stackoverflow.com/questions/21917023/jquery-difference-between-offset-top-and-element-offsettop
        https://stackoverflow.com/questions/42356461/offsettop-property-on-element-with-display-none-is-always-0

<br/>
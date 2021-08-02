# js로 만드는 mvc

## mvc?

mvc는 잘 알려진 아키텍쳐 패턴으로 mvvm을 사용하는 앵귤러도 이를 참조하고 있으며, 잘 알려준 서버사이드 렌더링 프레임워크들에서도 사용하고 있다.

모델은 우리의 상태를 위한 컨테이너이며 이 안에서 비즈니스, 도메인 로직들이 수행된다.

뷰는 사용자에게 보여지는 화면을 얘기한다.

컨트롤러는 모델과 뷰 사이에서의 중개자로 사용되며 몇몇 주요하지 않은 어플리케이션 상태를 저장하기도 한다.

MVC 모델은 모델과 뷰 계층이 직접적으로 coupling 되는 것을 피하기위해 컨트롤러 계층을 두어 관리한다.

예를들어 커머스 사이트를 새단장할 때 모델은 바뀌지 않지만 뷰가 바뀌는 경우가 대다수다. 또 같은 모델을 두고 사용자에 따라 다른 뷰가 보일 수 도 있다. 위와 같은 상황에서 직접적인 coupling은 모델과 뷰를 같이 바뀌도록 만든다.

이 같은 상황을 컨트롤러가 막아준다. 그렇다면 뷰가 바뀌면 컨트롤러가 바뀌어야 하는 것인가 하는 의문이 생길 수 있지만 이 같은 이유에서라도 컨트롤러 계층 로직은 매우 간단해야한다. 적어도 모델을 바꾸는 것보다는 훨씬 쉽게.

<img src="https://miro.medium.com/max/1084/1*eG2DYVb_HozJ6RboNZZbQg.png">

## view

이전부터 사용된 비 브라우저 지향 GUI들은 보통 전체 스타일들이 이미 작성되어 있는 컴포넌트들을 코드에 추가하여 사용하곤 했다.(JAVA Swing) 이 같은 특성은 클래스 내에서 View 오브젝트를 완전히 추상하여 사용할 수 있게 했다.

반면 vanilla JS의 적용은 브라우저 지향적으로 사용할 수 있게 되었고 HTLM과 DOM을 활용하여 HTML 그 자체를 DOM 셀렉터를 가지고 있는 하나의 View Class로 사용할 수 있게 되었다.

<img src="https://miro.medium.com/max/738/1*WGd7u4QzBjeT7mXFgBYp9w.png">

```
<!DOCTYPE html>
<html>
<head>
 <meta charset=”utf-8">
 <meta name=”viewport” content=”width=device-width”>
 <title>MVC Experiment</title>
 <script src="./mvc-script.js"></script>
</head>
<body>
 <h1 id=”heading”></h1>
</body>
</html>
```

위 코드는 html인데 여기서 "id"를 가지고 있는 h1 엘레멘트를 DOM 셀렉터를 통해 가질 수 있다. 이를 View 클래스로 나타내본다면 아래와 같다.

```
function View(controller){
    this.controller = controller;
    this.heading = document.getElementById(‘heading’);
}
```

## Model

```
function Model(){
  this.heading = "Hello";
}
```

위 코드는 방금 view에서 잡은 heading의 값을 저장하고 있다. 이는 동시에 우리가 heading에 어떤 값을 할당할지를 의미하기도 한다.

컨트롤러는 뷰의 이벤트에 따라 모델을 조작할 수 있기 때문에 이 모델은 controller로 주입하여 사용될 것이다.

## Interface EventListener

DOM과 Web Api는 몇가지 암시적인 인터페이스가 존재하고 보통 이들은 EventListener와 관련이 있다.

이러한 EventListener를 노드에 추가할 때 많은 사람들이 callback 함수를 사용하는데 이는 익명함수 이거나 자기 자체를 호출하는 함수이다.

## Controller

컨트롤러는 EventListener 인터페이스(동일한 목적 하에 동일한 기능을 수행하게끔 강제하는 것(메소드 구현이 필수)이 바로 인터페이스의 역할이자 개념)를 사용한다. 그리고 이는 우리 View에서의 DOM Events를 감지하기 위해서 리스너로 등록하여 사용한다.
그래서 컨트롤러는 세가지 메소드를 가져야 한다.

```
function Controller(model){
  var self = this;
  this.model = model;

//EVENTLISTENER INTERFACE
  this.handleEvent = function(e){
    e.stopPropagation();
    switch(e.type){
      case "click":
        self.clickHandler(e.target);
        break;
      default:
        console.log(e.target);
    }
  }

//GET MODEL HEADING
  this.getModelHeading = function(){
    return self.model.heading;
  }

//CHANGE THE MODEL
  this.clickHandler = function(target){
    self.model.heading = 'World';
    target.innerText = self.getModelHeading();
  }
}
```

컨트롤러에는 현재는 click 하나밖에 없지만 다른 메소드들을 위임받은 switch case 기반으로 handleEvent 메소드가 존재한다. 만약 이벤트 타입이 click이면 모델은 바뀐다 그리고 clickHandler 함수의 인자로 들어오는 target 파라미터, 즉 element(node)는 모델을 반영하여 변화한다.

```
function View(controller){
    this.controller = controller;
    this.heading = document.getElementById(‘heading’);
    this.heading.innerText = controller.getModelHeading();
    this.heading.addEventListener('click', controller);
}
```

위처럼 컨트롤러 인자를 받고 모델의 초기 상태를 innerText로 채울 수 있다.

## js파일에서 mvc를 적용

```
function main(){
  var model = new Model();
  var controller = new Controller(model);
  var view = new View(controller);
}
```

위 방식에서 모델은 view를 모르고 뷰 또한 모델을 알지 못하지만 컨트롤러가 둘 사이에서 중개인 역할을 할 수 있기 때문에 view에서 모델을 참조하는게 가능하고, 모델은 컨트롤러 내부에서 참조되어 있다.

이후 main함수를 호출하면 우리는 Hello와 Click를 확인해볼 수 있다.

위 같은 간단한 동작은 코드 몇줄로도 가능하지만 MVC 패턴을 적용함으로써 우리는 컨트롤러 내부에서 입력(클릭)과 다른 이벤트들을 들을 수 있고 모델을 업데이트 하거나 저장을 할 수 있다.

```
참조:
https://medium.com/@patrickackerman/the-observer-pattern-with-vanilla-javascript-8f85ea05eaa8
```

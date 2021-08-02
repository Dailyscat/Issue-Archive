# Visitor 패턴

여러가지 얘기가 있다.

- 알고리즘을 작동하는 오브젝트와 알고리즘을 분리 할 수있는 행동 설계 패턴
- 객체의 구조와 기능을 분리시키는 패턴
- 요소 클래스의 실행 알고리즘을 변경하여 사용하기 위한 행동 패턴
- 데이터 구조와 연산을 분리하여 데이터 구조의 원소들을 변경하지 않고 새로운 연산을 추가 할 수 있습니다. 새로운 연산을 추가하려면 새로운 방문자를 추가하기만 하면 됩니다.
- 다양한 객체에 새로운 기능을 추가해야 하는데 캡슐화가 별로 중요하지 않은 경우

정리해보면 객체의 모델링을 따로하고 해당 객체(모델)를 사용하는 클래스(visitor)를 구현하여 모델이 되는 객체를 기반으로 필요한 기능을 구현하여 사용하는 행동 설계 패턴이다.

<img src="https://www.tutorialspoint.com/design_pattern/images/visitor_pattern_uml_diagram.jpg">

아래 코드는 A,B 컴포넌트에서 visitor 클래스가 접근하게 하여 두 컴포넌트의 모델을 참조하여 새로운 기능을 적용할 수 있음을 보여주는 코드이다.

```
function Interface() {

  this.implements = function(obj) {

    // this는 인터페이스, obj는 인터페이스를 implements한 클래스

    var notImplementMethod = [];

    for(var method in this) {

      if(method !== 'implements') {

        var proto = obj.__proto__;

        while(proto) {

          // obj.__proto__ 객체가 method를 가지고 있지 않으면

          if(!Object.hasOwnProperty.call(proto, method)) {

            proto = proto.__proto__;

            if(proto === null) {

              notImplementMethod.push(method);

            }

          }

          else {

            break;

          }

        }

      }

    }

    if(notImplementMethod.length > 0) {

      throw new Error(obj.__proto__.constructor.name + " 클래스의 "

      + notImplementMethod.join() + " 메서드가 구현되지 않았습니다.");

    }

  }

}

​

function Component(){

  if (this.constructor === Component){

    throw new Error(this.constructor.name

      + ' 인터페이스는 객체를 생성할 수 없습니다.');

  }

  return (function(){

    // 인터페이스 정의 메서드

    var method = {

      accept : function(visitor){},

    };

    // 인터페이스의 implements 메서드를 method객체에 이식한다.

    Interface.call(method);

    return method;

  })();

};

​

function AComponent() {

  Component().implements(this);

  this.name = "AComponent";

}

AComponent.prototype.accept = function(visitor) {

  visitor.visitAComponent(this);

}

AComponent.prototype.execute = function() {

  console.log("AComponent의 execute 메서드 실행");

}

AComponent.prototype.getName = function() {

  return this.name;

}

​

function BComponent() {

  Component().implements(this);

  this.name = "BComponent";

}

BComponent.prototype.accept = function(visitor) {

  visitor.visitBComponent(this);

}

BComponent.prototype.execute = function(visitor) {

  console.log("BComponent의 execute 메서드 실행");

}

BComponent.prototype.getName = function() {

  return this.name;

}

​

function Visitor(){

  if (this.constructor === Component){

    throw new Error(this.constructor.name

      + ' 인터페이스는 객체를 생성할 수 없습니다.');

  }

  return (function(){

    // 인터페이스 정의 메서드

    var method = {

      visitAComponent : function(component){},

      visitBComponent : function(component){},

    };

    // 인터페이스의 implements 메서드를 method객체에 이식한다.

    Interface.call(method);

    return method;

  })();

};

​

function AVisitor() {

  Visitor().implements(this);

}

AVisitor.prototype.visitAComponent = function(component) {

  console.log("AVisitor는 " +component.getName()+ " 의 execute 메서드를 실행" )

  component.execute();

}

AVisitor.prototype.visitBComponent = function(component) {

  console.log("AVisitor는 " +component.getName()+ " 의 execute 메서드를 실행" )

  component.execute();

}

​

function BVisitor() {

  Visitor().implements(this);

}

BVisitor.prototype.visitAComponent = function(component) {

  console.log("BVisitor는 " +component.getName()+ " 의 execute 메서드를 실행" )

  component.execute();

}

BVisitor.prototype.visitBComponent = function(component) {

  console.log("BVisitor는 " +component.getName()+ " 의 execute 메서드를 실행" )

  component.execute();

}

​

function Client() {}

Client.prototype.test = function(){

  var components = [

    new AComponent(), new BComponent()

  ];

  var aVisitor = new AVisitor();

  for(var component of components) {

    component.accept(aVisitor);

  }

  console.log("------------------------");

  var bVisitor = new BVisitor();

  for(var component of components) {

    component.accept(bVisitor);

  }

}

​

new Client().test();

// 결과 출력

// AVisitor는 AComponent 의 execute 메서드를 실행

// AComponent의 execute 메서드 실행

// AVisitor는 BComponent 의 execute 메서드를 실행

// BComponent의 execute 메서드 실행

// ------------------------

// BVisitor는 AComponent 의 execute 메서드를 실행

// AComponent의 execute 메서드 실행

// BVisitor는 BComponent 의 execute 메서드를 실행

// BComponent의 execute 메서드 실행
```

    참조:
    Javascript Visitor 패턴 : 네이버 블로그
    https://blog.naver.com/PostView.nhn?blogId=mycho&logNo=221867357787&categoryNo=0&parentCategoryNo=0&viewDate=¤tPage=1&postListTopCurrentPage=1&from=postList&userTopListOpen=true&userTopListCount=30&userTopListManageOpen=false&userTopListCurrentPage=1

    Thinking Different :: 방문자 패턴 (Visitor Pattern)
    https://copynull.tistory.com/146

    Kunoo :: [행위 패턴] Visitor pattern (비지터 패턴)
    https://kunoo.tistory.com/entry/%ED%96%89%EC%9C%84-%ED%8C%A8%ED%84%B4-Visitor-pattern-%EB%B9%84%EC%A7%80%ED%84%B0-%ED%8C%A8%ED%84%B4

    12 방문자 패턴 (Visitor Pattern)
    https://lktprogrammer.tistory.com/58

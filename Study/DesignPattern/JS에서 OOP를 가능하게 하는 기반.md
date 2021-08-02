# JS에서 OOP를 가능하게 하는 기반

## 기본적으로 OOP를 하기 위해서는 3가지 구성요소가 필요

- 클래스(Class)
- 객체(Object)
- 메서드(Method)

### 클래스

- ES5의 Class 문법을 사용하는 방법
- 함수를 작성하여 new 키워드를 통해 return된 객체를 사용하는 방법

위의 두 방법은 어떻게 보면 같은 방법이라고 볼 수 있다. ES5의 Class 문법 자체가 JS 함수를 Class 문법과 비슷하게 사용할 수 있게 해놓은 Syntactic Sugar 이기 때문이다.

[참조](https://jeong-pro.tistory.com/120)

    function TV(brand, model, price) {

    this.brand = brand;

    this.model = model;

    this.price = price;

    this.turnOn = function() {

        console.log("[" + this.brand + "(" + this.model + ") TV] turn on");

    };

    this.turnOff = function() {

        console.log("[" + this.brand + "(" + this.model + ") TV] turn off");

    };

    this.volumeUp = function() {

        console.log("[" + this.brand + "(" + this.model + ") TV] volume up");

    };

    this.volumeDown = function() {

        console.log("[" + this.brand + "(" + this.model + ") TV] volume Down");

    };

    }

### 객체

이때의 객체는 Class의 Instance를 말하며 보통 new 키워드를 통해 생성자 함수를 호출하여 Instance를 생성할 수 있으며 이때의 Instance는 개별적인 속성과 메서드를 가진다.

    var samsungTv = new TV("Samsung", "S-001", "1000001");

    var lgTv = new TV("LG", "L-001", "1000000");

    console.log("[SamsungTv 가격] : " + samsungTv.price); // [SamsungTv 가격] : 1000001

    console.log("[LGTv] 가격] : " + lgTv.price); // [LGTv] 가격] : 1000000

### 메서드

클래스로부터 생성된 객체를 제어하는데 사용한다.

    samsungTv.turnOn();             // [Samsung(S-001) TV] turn on

    samsungTv.volumeUp();       // [Samsung(S-001) TV] volume up

    samsungTv.volumeDown(); // [Samsung(S-001) TV] volume Down

    samsungTv.turnOff();            // [Samsung(S-001) TV] turn off

    lgTv.turnOn();                         // [LG(L-001) TV] turn on

    lgTv.volumeUp();                   // [LG(L-001) TV] volume up

    lgTv.volumeDown();            // [LG(L-001) TV] volume Down

    lgTv.turnOff();                       // [LG(L-001) TV] turn off

### 추상화

공통의 속성이나 기능을 묶어서 이를 클래스로 정의하는 것

## OOP의 특징 세가지

- 캡슐화
- 상속
- 다형성

### 캡슐화

캡슐화는 데이터(변수)와 데이터를 제어하는 방법(함수)를 결합시켜 놓은 것으로 객체의 데이터를 외부에서 직접 접근하지 못하도록 하고 특정 메서드를 통해서만 이를 제어하게 한다.

JS에서는 public, private만 제어가 가능하고 protected는 제어할 수 없다.

    function TV(brand, model, price) {

      var brand = brand;

      var model = model;

      var price = price;

      this.turnOn = function() {

        console.log("[" + brand + "(" + model + ") TV] turn on");

      };

      this.turnOff = function() {

        console.log("[" + brand + "(" + model + ") TV] turn off");

      };

      this.volumeUp = function() {

        console.log("[" + brand + "(" + model + ") TV] volume up");

      };

      this.volumeDown = function() {

        console.log("[" + brand + "(" + model + ") TV] volume Down");

      };

      this.getPrice = function() {

        return price;

      }

    }


    var samsungTv = new TV("Samsung", "S-001", "1000001");

    var lgTv = new TV("LG", "L-001", "1000000");

    console.log("[SamsungTv 가격] : " + samsungTv.price); // [SamsungTv 가격] : undefined

    console.log("[LGTv] 가격] : " + lgTv.getPrice());          // [LGTv] 가격] : 1000000

samsungTV 객체에서 price를 직접 접근하려 했으나 값을 가져오지 못했고, lgTV에서는 메서드를 호출하여 값을 가져올 수 있었다.

이는 this를 통해 설정한 메서드는 값에 접근할 수 있음으로써 public의 접근을 가능하게 한다고 볼 수 있고, var를 통한 변수 선언은 직접 접근할 수 없음으로써 private의 접근이라고 볼 수 있다.

### 상속

자식 클래스가 부모 클래스의 기능을 받아서 사용하는 것.

JS는 프로토타입 기반 언어이고 프로토타입은 JS 객체의 상속과 밀접한 연관이 있다.

> Prototype Chain

> JS의 모든 함수는 prototype 속성을 가진다. 그리고 모든 객체는 `__proto__`(상위 객체의 prototype) 속성을 가진다.

> 생성자 함수 A에서 생성된 Instacne a가 있을 때 어떤 속성을 a에서 찾으려고 할 때 a에 그 속성이 없다면 `__proto__`(상위 객체의 prototype)에 접근하여 그 속성이 있는지 검사해보고 있다면 그 속성을 사용하고 없다면 다시 그 상위 객체의 `__proto__`를 확인하여 해당 속성을 검색하는 Javascript가 속성을 찾는 방법을 말한다. 이때 모든 프로토타입 체인이 검사 되고 브라우저가 더이상 찾는 `__proto__`가 없을 때 undefined를 리턴한다.

#### 객체를 생성하는 방식

객체를 생성하는 방법은

1.  `Object.create`
2.  var d = {};
3.  var d = new Object();
4.  생성자 함수를 사용하는 방식

        function EX (name, age) {
            this.name = name;
            this.age = age;
        }

        var ex1 = new Ex("eungyu", 12);

        var ex2 = enw Ex("gungyu", 123);

#### prototype 체인을 활용한 상속

1. 부모클래스의 생성자 생성
2. 자식 클래스에서 부모 클래스 생성자 호출
3. 프로토타입 상속 및 생성자 보정

##### 부모클래스의 생성자 생성

      function TV(brand, model, price) {

      this.brand = brand;

      this.model = model;

      this.price = price;

      }

      TV.prototype.turnOn = function() {

      console.log("[" + this.brand + "(" + this.model + ") TV] turn on");

      };

      TV.prototype.turnOff = function() {

      console.log("[" + this.brand + "(" + this.model + ") TV] turn off");

      };

      TV.prototype.volumeUp = function() {

      console.log("[" + this.brand + "(" + this.model + ") TV] volume up");

      };

      TV.prototype.volumeDown = function() {

      console.log("[" + this.brand + "(" + this.model + ") TV] volume Down");

      };

      TV.prototype.getPrice = function() {

      return this.price;

      }

##### 자식 클래스의 생성자에서 부모 클래스의 생성자 호출

클래스에서 Instance가 생성되는 시점에서 this는 생성되는 Instance이기 때문에 이를 활용해서

    function SamsungTv(model, price) {

      this.brand = "Samsung";

      TV.apply(this, [this.brand, model, price]);

    }

##### 프로토타입 상속 및 생성자 보정

부모 클래스에서 전해 내려오는 메소드를 활용하기 위해서 자식 클래스의 프로토타입에 부모 클래스의 prototype을 위임한다.

      SamsungTv.prototype = Object.create(TV.prototype);

      SamsungTv.prototype.constructor = SamsungTv;

즉 모든 인스턴스는 `__proto__`를 통해 프로토타입 체인을 가지고 상위객체의 속성을 사용하기 때문에 SamsungTV의 Instance는 TV의 prototype을 `__proto__`를 통해 활용할 수 있다.

#### 다형성

1. 오버라이딩
2. 오버로딩
3. 부모 클래스로의 형 변환
4. 인터페이스로의 형 변환

##### 오버라이딩

      function Car(brand, model, price) {
      this.brand = brand;

      this.model = model;

      this.price = price;

      }

      Car.prototype.turnOn = function() {

      console.log("[" + this.brand + "(" + this.model + ") TV] turn on");

      };

      function Tesla(model, price) {
      this.brand = "Tesla";
      Car.apply(this,[this.brand, model, price])
      }

      Tesla.prototype = Object.create(Car.prototype);
      Tesla.prototype.constructor = Tesla;

      Tesla.prototype.turnOn = function () {
      console.log(this.brand + "시동 걸렸습니다.")
      }

      var teslaInstance = new Tesla("royal", 1000000
      );
      teslaInstance.turnOn();

##### 오버로딩

JS는 오버로딩을 지원하지 않는다. 동일한 이름의 함수를 여러개 선언하면 맨 마지막에 선언 된 함수만 인식하게 된다. 추가로 변수와 함수 선언식을 동일한 이름으로 할당 했을 때

      var d = 123;

      function d () {}

d의 값은 123이다.

오버로딩은 지원하지 않지만 함수 내에서 분기하여 인자에 따른 기능을 처리할 수 는 있다.

      참조:
      https://blog.naver.com/PostView.nhn?blogId=mycho&logNo=221841926107&categoryNo=0&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postList&userTopListOpen=true&userTopListCount=30&userTopListManageOpen=false&userTopListCurrentPage=1

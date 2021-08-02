### JWT와 OAuth 차이점

#### JWT와 OAuth 차이점

둘을 비교하는는 것은 흔한 질문이지만 맞지 않는 접근이다.

JWT가 사과라면 OAuth는 사과 상자라고 볼 수 있는데, 다시말해 JWT는 Token의 한 형식이고, OAuth는 하나의 Framework이다.

여기서 framework라는 이유는

- 토큰을 요청하는 데 사용할 수 있어야하는 요청 및 응답의 순서와 형식만 있다.
- 각기 다른 시나리오에서 어떤 방식으로 권한 부여 유형을 얘기할지를 정한다.

그리고 JWT는 이러한 framework에서 발생하는 산출물로 볼 수 있다.

하지만 사람들이 비교하는 이유에 대해서 좀 더 살펴보면 auth framework을 통해 나온 산출물, 즉 `OAuth Bearer token`(이하 OAuth token)과 단순한 JWT는 분명한 차이가 있다.

일단 OAuth token은 모호한 토큰, 즉 어떠한 사용자의 정보 등과 같은 중요한 정보가 있는 명확한 정보를 가지고 있는 토큰이 아니다. 그래서 이 OAuth token을 사용하는 사용자(여기서는 개발자라고 생각할 수 있다)가 이 토큰이 가지고 있는 정보에 대해서 아는 바가 전혀 없다. 이 모호한 토큰, OAuth token이 가지고 있는 정보는 일련의 랜덤한 문자열인데 이는 일종의 pointer이다. 즉 OAuth Framework로 들어가서 해당 정보가 저장되어 있는 주소를 확인할 수 있는 인식표정도가 될 것이다. 물론 이때의 토큰은 JWT의 유형을 가질 수 도 있다.

반대로 JWT는 포인터는 물론 모호한 토큰도 아니다. JWT는 명확하게 정보를 가지고 있다. 토큰의 크기는 300~ 500 byte.. 그 보다도 더 커질 수 도 있으며 가지고 있는 속성 정보에 따라 이는 달라질 수 있다.

사람들은 JWT가 스스로 유효성 검사를 한다고 얘기하는데, 이는 사용자가(이때도 개발자) 이 토큰의 내부 속성정보를 활용하여 유효성 검사를 할 수 있음을 얘기한다. 물론 이는 취약점을 가지고 있다고 생각할 수 도 있다.

#### JWT란?

Json Web Token의 약자로 전자 서명 된 URL-safe (URL로 이용할 수있는 문자로만 구성된)의 JSON이다.

이는 속성정보(claim)를 JSon 데이터 구조로 표현한 토큰으로 RFC7519표준이다.

보통 JWT는 서버와 클라이언트 간 정보를 주고 받을 때 Http 리퀘스트 헤더에 JSON 토큰을 넣은 후 서버는 별도의 인증 과정 없이 헤더에 포함되어 있는 JWT 정보를 통해 인증한다.

이때 사용되는 JSON 데이터는 URL-safe 하도록 URL에 포함될 수 있는 문자만으로 만든다.

##### JWT 토큰 구성

<img src="https://i2.wp.com/www.opennaru.com/wp-content/uploads/2018/08/JWT_Stacks.png?fit=1200%2C300">

header는 토큰의 타입과 해시 암호화 알고리즘으로 구성되어 있다. 첫째는 토큰의 유형, 둘째는 HMAC, SHA256과 같은 해시 알고리즘을 나타낸다.

payload는 토큰에 담을 클레임 정보(간단하게 말해서 토큰에 넣고 싶은 정보를 json 형태로)를 포함하고 있다. payload에 담는 정보의 한 조각을 클레임이라고 부르고, 이는 name/ value 한 쌍으로 이루어져 있다. 토큰에는 여러개의 클레임을 넣을 수 있으며 이는 등록된(registered), 공개(public), 비공개(private) 클레임으로 세 종류가 있다.

signature는 secret key를 포함하여 암호화되어 있다.

##### JWT 작동 방식

###### OAuth와 함께 사용할 때

<img src="https://i2.wp.com/www.opennaru.com/wp-content/uploads/2018/08/Microservice-With-OAuth.png?fit=3000%2C2250">

기존의 토큰 방식 인증은 다이어그램에 표시된 것처럼 토큰은 이후의 모든 서비스 호출에 사용된다.
서비스를 받기 위해서는 토큰의 유효성을 확인하여 세부 정보를 쿼리해야한다.
참조에 의한 호출(By Reference) 형태로 모든 서비스는 항상 상호 작용할 때 다시 접속해야한다.

##### URL-safe란?

`Base 64 Encoding with URL and Filename Safe Alphabet` 형식 주로 base64url 인코딩으로 불린다. 사용되는 Table이 다르며 기술적으로 적용되는 방식은 동일하다.

##### ASCII Encoding(부호화)

목적: ASCII 방식으로 text를 인코딩할 때 text를 일련의 Bytes로 변경하는것

##### base64 Encoding(부호화)

목적: base64 방식으로 data를 인코딩할 때 일련의 Bytes(data)를 text(ASCII TEXT DATA)로 변환하는것

이는 임의의 3byte(octets)를 변환하여 표현 되도록 설계되었고 대소문자를 분리하여 표현하지만 그 의미를 인간이 알아볼 수 있도록 설계되어지진 않았다.

base64는 왼쪽에서 오른쪽으로 인코딩되며 각각의 6비트 그룹은 Base64 Tabel의 index(색인)를 가리킨다.

여기서 65번째 문자인 "="은 특수한 처리를 하도록 설계되어 있는데, 간단하게 데이터의 끝을 얘기한다고 생각하면 된다.

만약 입력된 바이트(8bit)가 하나라면 출력 중 두 개만이 사용되고 나머지 둘은 "="으로 패딩되며, 입력된 바이트가 둘이라면 출력 중 세 개 만이 사용되고 나머지 하나는 "="으로 패딩되게 된다.

<img src="https://t1.daumcdn.net/cfile/tistory/1445B33B4FD8338B35">

위의 결과를 보면 a라는 문자 하나를 넣었을 때는 YQ==으로 base64 table에 없는 '=' 문자가 추가된 것을 알 수 있다. '='은 bit수를 맞춰주기 위해 0으로 채워주는 패딩이라는 것이다.

##### 부호화란?

정보의 형태나 형식을 표준화나 보안, 처리 속도 향상, 저장 공간 절약 등을 위해 다른 형태나 형식으로 변환하는 처리, 혹은 처리방식

##### base64가 나온 계기

컴퓨터는 0과 1을 다루지만 사람들은 이미지나 글자를 사용하여 이를 활용하려한다. 이를 위해 컴퓨터는 이미지나 글자 데이터를 0과 1로 인코딩하여 보내고 이를 다시 디코딩하여 사용하는 방식을 택했다.

각 character 마다 7비트를 활용하여 인코딩하는 방식의 ASCII가 표준으로 채택됐지만 이는 많은 컴퓨터가 bytes로 binary 데이터를 저장하는 8비트 방식을 채택하면서 데이터를 전달하는 방식에 적합해지지 않았고, 몇몇 시스템은 굉장히 중요한 비트를 삭제하기도 했으며 심지어는 각각의 운영체제마다 인코딩이 처리되는 마지막 라인이 수정되는 현상도 발생했다.

이를 해결하기 위해 base64 인코딩이 알려졌다. base64 인코딩은 임의의 바이트를 손상없이 전송하기에 안전한 바이트로 인코딩할 수 있는 방식이었다. 다만 단점은 메시지를 인코딩했을때 길이가 증가한다는점이다. 3 바이트의 데이터마다 4개의 ASCII 문자로 인코딩된다.

안전하게 문자를 보내기 위해

1.  UTF-8과 같은 방식으로 인코딩을 하고 이를 Base64 인코딩을 통해 ASCII로 인코딩된 안전한 텍스트 문자열로 인코딩이 가능하다.

2.  Base64는 결과 이진 데이터를 ASCII로 인코딩 된 안전한 텍스트 문자열로 인코딩한다.

example:

    I wish to send a text message with two lines:

    Hello
    world!

    If I send it as ASCII (or UTF-8) it will look like this:

    72 101 108 108 111 10 119 111 114 108 100 33

    //위에서 말했듯 시스템상에서 10번째 혹은 13번째 character가 수정될 때가 있다.

    The byte 10 is corrupted in some systems so we can base 64 encode these bytes as a Base64 string:

    SGVsbG8sCndvcmxkIQ==
    Which when encoded using ASCII looks like this:

    83 71 86 115 98 71 56 115 67 110 100 118 99 109 120 107 73 61 61

추가 설명:

     "Man" is base-64 encoded as "TWFu". 3 bytes -> 4 bytes.
     It's because the input is allowed to be any of the 2^8 = 256 possible bytes, whereas the output only uses 2^6 = 64 of them (and =, to help indicate the length of the data).
     8 bits per quartet of output are "wasted", in order to prevent the output from containing any "exciting" characters even though the input does.

즉 Man이 base64 인코딩 되면 TWFu가 되고 이는 character가 하나 더 추가된 결과를 이끌어내는데, 이유는 input은 256개의 수를 나타낼 수 있도록 허용하지만 output은 64개의 수를 나타내도록 했기 때문에 여기서 "="(padding)문자를 활용하여 data의 bit길이를 확인하고 4번째 캐릭터를 나타내게 된다. 물론 입력이 출력에 입력하지 않은 문자가 포함되지 않도록하기 위해 출력 결과에서 마지막 8 비트는 폐기된다.

##### 간단한 base64 인코딩 과정

1. 이진 데이터는 연속된 24비트의 청크(3byte)로 배열된다.
2. 각각의 24비트 덩어리는 6비트씩 4개의 그룹으로 짝지어진다.
3. 각각의 6비트 그룹은 base64의 형식으로 부호화되고, 이는 3byte가 4개의 character로 나타나도록 설정된다. 이때 3개의 인풋이 4개의 인풋으로 출력되기 때문에 33%의 오버헤드가 발생한다고도 볼 수 있따.
4. 흥미롭게 같은 문자여도 그 문자가 있는 각자의 위치에 따라 다르게 인코딩되어 변환된다.
5. 결과를 받은 사람은 원래의 메세지를 갖기 위해 4,3,2,1의 순서로 디코딩해야한다.

전자 서명은 JSON의 변조를 체크할 수 있게 되어 있다. 속성정보(Claim)를 JSON 구조로 표현한 토큰이다.

      참조

      //JWT와 OAuth 차이
      https://community.apigee.com/questions/21139/jwt-vs-oauth.html

      //base64, base64url 참고
      https://ko.wikipedia.org/wiki/%EB%B2%A0%EC%9D%B4%EC%8A%A464
      https://stackoverflow.com/questions/3538021/why-do-we-use-base64
      https://tools.ietf.org/html/rfc4648#section-5
      https://ko.wikipedia.org/wiki/%EC%98%A5%ED%85%9F_(%EC%BB%B4%ED%93%A8%ED%8C%85)
      http://www.opennaru.com/opennaru-blog/jwt-json-web-token/
      https://bbolmin.tistory.com/46

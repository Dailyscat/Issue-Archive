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

# Issue: lombok의 @builder 어노테이션이 적용된 vo에서 setter custom하게 적용

## 상황:

빌더 패턴으로 작성된 코드가 있었는데 코드 리팩토링 과정에서 변수를 변경하다가 이전 파라미터도 호환되게 적용하기 위해서
@builder 어노테이션으로 작성된 vo에 변경이 필요했다.

## 생각해낸 방안:

- builder가 적용되는 단계에서 custom하게 setter를 적용했다.

## 방안: builder가 적용되는 단계에서 custom하게 setter를 적용했다.

<br/>

```
@Builder
public class User {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private String username;

    private String password;

    public static class UserBuilder {
        public UserBuilder password(String password) {
            this.password = ENCODER.encode(password);
            return this;
        }
    }
}
```

<br/>
<br/>
<br/>

        참조:
        https://www.baeldung.com/lombok-builder-custom-setter
        https://stackoverflow.com/questions/42379899/use-custom-setter-in-lomboks-builder

<br/>

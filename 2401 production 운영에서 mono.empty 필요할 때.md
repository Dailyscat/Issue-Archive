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

# Issue: production 운영에서 mono.empty 필요할 때

## 상황:
mono.empty가 어떤 상황에서 필요한지 정리

<br/>

## 알게된 부분 정리:

- 특정 요청이 매핑된 컨트롤러에서의 쓰임
- 에러처리 로직에서 특정 에러 발생했을때 에러 로깅 후 실행 흐름은 정상적으로 진행하려고 할때

<br/>

## 개념:

<br/>

  ```
  @RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 아이템 삭제 처리
    @DeleteMapping("/{id}")
    public Mono<Void> deleteItem(@PathVariable String id) {
        // ItemService는 삭제 처리 후 Mono<Void>를 반환하도록 구현됩니다.
        return itemService.deleteItem(id)
                .doOnSuccess(aVoid -> {
                    // 삭제 처리가 성공했을 때, 필요한 로직을 실행합니다.
                    System.out.println("Item with id " + id + " deleted.");
                }).then(Mono.empty()); // 클라이언트에게는 아무 것도 반환하지 않습니다.
    }
}

@Service
public class ItemService {
    public Mono<Void> deleteItem(String id) {
        // 데이터베이스에서 아이템 삭제하는 로직을 구현하고 완료 신호를 위해 Mono<Void> 반환
        // 예시로 바로 Mono.empty()를 반환하겠습니다.
        return Mono.empty();
    }
}
  ```

<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: 에러처리 로직에서 특정 에러 발생했을때 에러 로깅 후 실행 흐름은 정상적으로 진행하려고 할때

<br/>

  ```
  import reactor.core.publisher.Mono;

public class UserService {

    // 유저 정보를 가져오는 메소드
    public Mono<User> getUser(String userId) {
        return findUserInDatabase(userId)
                .onErrorResume(e -> {
                    // 특정 에러가 발생했을 때 로깅하고 Mono.empty() 반환
                    if (e instanceof UserNotFoundException) {
                        System.err.println("User not found, continue without throwing: " + e.getMessage());
                        return Mono.empty();
                    }
                    // 다른 에러의 경우, 이를 상위로 전파
                    return Mono.error(e);
                });
    }

    // 예제용 데이터베이스 조회 메소드
    private Mono<User> findUserInDatabase(String userId) {
        // ... 데이터베이스 조회 로직 ...
        return Mono.just(new User()); // 실제 구현에선 데이터베이스에서 유저를 찾아 반환
    }

    // 유저 정보를 나타내는 클래스
    class User {
        // 유저 정보 관련 속성들...
    }

    // 에러처리 - 유저를 찾지 못했을 때 발생하는 예외
    class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
  ```

<br/>
<br/>
<br/>

        참조:

<br/>

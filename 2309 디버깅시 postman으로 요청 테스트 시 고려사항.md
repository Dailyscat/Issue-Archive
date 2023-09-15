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

# Issue: 디버깅시 postman으로 요청 테스트 시 고려사항

## 상황:
browser에서 post 요청시 next trpc레이어를 통하여 axios로 spring server에 요청을 보내는데. 첨부하는 body data가 spring controller의 @RequestBody에 도통 담기지 않음. 이를 해결하기위해 postman으로 요청했을 때는 제대로 요청이 날아감.
결국 문제는 trpc middleware에서 ua 확인을 위해 덮어씌도록 header를 설정한 로직 때문이었는데 이때 테스트를 좀 더 완벽하게 디테일 하나하나 같도록 했으면 금방 알아냈을 것 같다.

<br/>

## 알게된 부분 정리:

- postman의 header와 client에서 날리는 header가 동일한가?
- param, body로 보내는 내용이 동일한가?


<br/>

## 개념: postman의 header와 client에서 날리는 header가 동일한가?

<br/>
  해당 요청을 받는 spring 서버에서 아래 코드를 통해 확인한다.

    ```
    @Component
    public class LoggingWebFilter implements WebFilter {

        private static final Logger log = LoggerFactory.getLogger(LoggingWebFilter.class);

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
            ServerHttpRequest request = exchange.getRequest();

            // Log all headers
            HttpHeaders headers = request.getHeaders();
            headers.entrySet().forEach(entry -> {
                String headerName = entry.getKey();
                List<String> headerValues = entry.getValue();
                System.out.println("Header Name: " + headerName + ", Header Values: " + headerValues);
            });

            return chain.filter(exchange);
        }

    }
    ```

    쓸데없는 header 정보를 overRoad하여 보내는 부분에서 생각지못한 이슈가 생길 수 있다.

<br/>
<br/>
<br/>

        참조:

<br/>

## 개념: param, body로 보내는 내용이 동일한가?

<br/>
  이번 이슈는 param, body로 보내는 내용의 content-length 값이
  browser에서 보내는 byte와 axios에서 보내는 byte가 달라서 발생되는 이슈였다.
  이를 체크하고 헤더까지 확인했으면 좀 더 빠르게 확인이 가능했을 것.
<br/>
<br/>
<br/>

        참조:

<br/>

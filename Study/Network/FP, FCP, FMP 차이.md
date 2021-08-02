`아카이빙:https://stackoverrun.com/ko/q/11612130`

# FP, FCP, FMP 차이

- First Paint: 첫번째 픽셀이 스크린에 페인팅 되었을 때(시간)를 얘기한다. 예를들어 background-color가 적용되는 순간을 얘기한다.

- First Contentful Paint: DOM에 속해있는 컨텐츠 조각이 스크린에 페인팅 되는 순간(시간)을 얘기한다. 예를들어 한개 혹은 몇개의 문단의 텍스트, 혹은 이미지 등을 얘기한다.

- First Meaningful Paint: 브라우저가 유저가 관심있어 할 만한 컨텐츠를 페인트하여 나타냈을 때까지의 시간을 얘기한다. 이는 페이지의 구성에 달려있다.

FP, FCP의 경우에는 크롬의 timing API로 추적이 가능하며 GA 같은 도구로 리포팅이 가능하다.

FMP의 경우는 FMP가 의미하는 순간을 브라우저 API가 추적할 수는 없다. 일반적으로 FMP가 의미하는 순간의 측정은 Hero Elements가 정의됨으로써 구분이 가능하다. 즉 사용자가 메인으로 사용하게될 컨텐츠(Hero elements)가 페인팅 되는 순간이 바로 FMP가 의미하는 순간과 같다. 현재로써는 DOM의 특정 element가 페인팅 되는 순간을 알 수 있는 방법은 없다. Lighthouse나 WebPageTest는 단지 렌더링이 발생할 때 가장 큰 레이아웃의 변화를 가지는 엘레멘트를 hero Element로 추정하고 FMP를 추정할 수 있을 뿐이다.

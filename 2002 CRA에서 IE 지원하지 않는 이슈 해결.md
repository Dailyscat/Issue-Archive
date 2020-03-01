# Issue: create-react-app에서 IE를 지원하지 않는 이슈

## 상황:

현재(2/23) 기준으로 react-scripts 3.4를 활용하면 IE를 서포트하지만 그전엔 안되었어서 이를 해결해야 했다.

<br/>

## 생각해낸 방안:

- "react-scripts": "3.3.0-next.62"
-

<br/>

## 방안: (성공)

<br/>

- 이 부분을 인지하고 있었으며 현재는 업데이트가 되었는지 확인 - `"react-scripts"` 버전 변경 - `"react-app-polyfill"` 설치 - `package.json`의 `browserslist`의 `development` 배열에 `">0.2%"`를 추가하여 개발환경에서 IE 브라우저도 포함하도록 설정. - `index.tsx`파일에 - `import "react-app-polyfill/ie11";` - `import "react-app-polyfill/stable";` - 위와 같이 import 하여 호환이 가능하도록 설정.
  <br/>
  <br/>
  <br/>

          참조:

<br/>

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

# Issue: next에서 특정 페이지의 경우 body에 클래스를 부여해야할 때

## 상황: 각 페이지의 어떤 상태에 따라 body에 클래스를 부여해줘야 하는 상황

<br/>

## 알게된 부분 정리:

- _document 파일 커스텀

<br/>

## 개념: _document 파일 커스텀

<br/>
  ```
  import cla from 'classnames';
  import Document, {
  Html,
  Head,
  Main,
  NextScript,
  DocumentContext,
  DocumentInitialProps,
} from 'next/document'
 
class MyDocument extends Document {
  static async getInitialProps(
    ctx: DocumentContext
  ): Promise<DocumentInitialProps> {
    const originalRenderPage = ctx.renderPage
 
    // Run the React rendering logic synchronously
    ctx.renderPage = () =>
      originalRenderPage({
        // Useful for wrapping the whole react tree
        enhanceApp: (App) => App,
        // Useful for wrapping in a per-page basis
        enhanceComponent: (Component) => Component,
      })
 
    // Run the parent `getInitialProps`, it now includes the custom `renderPage`
    const initialProps = await Document.getInitialProps(ctx)
 
    return initialProps
  }
 
  render() {
    // eslint-disable-next-line no-underscore-dangle
    const props = this.props?.__NEXT_DATA__?.props?.pageProps;
    const bodyClassName = cla({
      wow: true,
      and: props?.isAndroid
    });
    return (
      <Html lang="en">
        <Head />
        <body>
          <Main />
          <NextScript />
        </body>
      </Html>
    )
  }
}

사용하는 페이지에서

```
export async function getServerSideProps(context: GetServerSidePropsContext) {
  const ssg = await ssgHelper(context);
  return {
    props: {
      trpcState: ssg.dehydrate(),
      screenName,
      isAndroid: isAndroid(context.req.headers['user-agent'])
    }
  };
}
```

단점은 서버사이드렌더링시에만 해당 부분을 적용할 수 있고 사실 클라에서 어떤 상태에 따라 부여되야 한다면 그건 훅으로 처리해야할 부분이다.


export default MyDocument
  ```
<br/>
<br/>
<br/>

        참조:
        https://nextjs.org/docs/pages/building-your-application/routing/custom-document

<br/>

## 개념:

<br/>
  개념에 대한 내용
<br/>
<br/>
<br/>

        참조:

<br/>

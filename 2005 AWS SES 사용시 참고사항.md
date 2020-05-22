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

# Issue: AWS SES 사용시 참고사항

## 상황:

해커톤에서 SES 사용을 했는데, 사용하면서 초기 사용시에 알고있으면 좋을 상황을 정리

<br/>

## 생각해낸 방안:

- To, Cc, Bcc
- 이메일 템플릿 생성 시 주의할점
- .env 파일을 활용하여 aws 관련 설정 저장
  <br/>

## 방안: To, Cc, Bcc

<br/>
  To: 수신인
  Cc: 참조(눈에 보이는)
  Bcc: 숨은 참조

이걸 몰라가지고 처음에 이게 뭔가 하고 헤맸다.
<br/>
<br/>
<br/>

        참조:
        https://m.blog.naver.com/PostView.nhn?blogId=somienglish&logNo=220990988427&proxyReferer=https:%2F%2Fwww.google.com%2F

<br/>

## 방안: 이메일 템플릿 생성 시 주의할점

<br/>

```
var params = {
  Template: {
    TemplateName: 'AuthPictureTemplate',
    SubjectPart: '{{name}}님 인증해주세요!',
    HtmlPart: `<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>HTML Email Template</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <style type="text/css">
        /* GENERAL STYLE RESETS */
        body, #bodyTable { width:100% !important; height:100% !important; margin:0; padding:0; }
        #bodyTable { padding: 20px 0 30px 0; background-color: #ffffff; }
        img, a img { border:0; outline:none; text-decoration:none; }
        .imageFix { display:block; }
        table, td { border-collapse:collapse; }
      </style>
    </head>
    <body>
      <!-- OUTERMOST CONTAINER TABLE -->
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="bodyTable">
        <tr>
          <td>
            <table border="" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td>
            <table border="" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                <!--[if mso]>
                <v:roundrect xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w="urn:schemas-microsoft-com:office:word" href="https://google.com" style="height: 40px; v-text-anchor: middle; width:200px;" arcsize="50%" strokecolor="#1caeba" fillcolor="#2bcae3">
                    <w:anchorlock/>
                    <center style="color:#147e94;font-family:sans-serif;font-size:13px;font-weight:bold;">Button</center>
                  </v:roundrect>
                <![endif]-->
                <a  href="http://www.sestest.com"
                    style="background-color: #2bcae3; border: 1px solid #1caeba; border-radius: 20px; color: #147e94; display: inline-block; font-family: sans-serif; font-size: 13px; font-weight: bold; line-height: 40px; text-align: center; text-decoration: none; width: 200px; -webkit-text-size-adjust: none; mso-hide: all;">인증하러 가기</a>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td>
            <table border="" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                <td></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </body>
    </html>`,
    TextPart: 'wow'
  }

};
```

호환성을 위해 table로 레이아웃을 짜는게 좋고, jsbin이나 어떤 playground를 통해 확인하는 뷰와 이메일에서 보이는 뷰가 다르기 때문에 여러 템플릿에 관한 아티클을 찾아보는게 좋다.

aws 자체에서는 템플릿 생성과 삭제가 지원되지 않기 때문에 cli로 구동시키거나 코드내부에서 명령을 실행해야한다.

```
// ses.createTemplate(params, function(err, data) {
// if (err) {
// console.log(err, err.stack);
// } else {
// console.log(data);
// }
// });
```

<br/>
<br/>
<br/>

        참조:
        https://webdesign.tutsplus.com/ko/articles/build-an-html-email-template-from-scratch--webdesign-12770
        https://foodchain.tistory.com/64
        https://medium.com/@yunkimoon/%ED%86%B5%EC%9D%B4%EB%AF%B8%EC%A7%80%EB%8A%94-%EA%B7%B8%EB%A7%8C-html%EB%A1%9C-%EB%89%B4%EC%8A%A4%EB%A0%88%ED%84%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0-d49799aac427

<br/>
<br/>

## 방안: .env 파일을 활용하여 aws 관련 설정 저장

<br/>

```

import dotenv from 'dotenv'

dotenv.config();

```

<br/>
<br/>
<br/>

        참조:
        https://velog.io/@public_danuel/process-env-on-node-js

<br/>
```

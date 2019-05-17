# Issue: 회사 프로덕트의 ssl인증 갱신이 필요한 상황

해결 방법은 두가지가 있었다.

```
(1) comodo라는 회사의 1년짜리 ssl인증을 받아 갱신한다
(2) Let's Encrypt라는 비영리 단체의 90일 짜리 ssl 인증을 받아 갱신한다.
```

회사의 상황은 반년 이내에 aws로 migration이 예정되있던 상태라서 1년까지의 인증이 필요 없었으며, 개인적으로 Let's Encrypt는 무료이기 때문에 시간이 조금 더 걸리더라도 Let's Encrypt의 ssl 인증 방식을 택하기로 했다.


아키텍쳐는 ubuntu 16.04 server에 nginx를 사용하고, 그 안에서 경로를 설정하여 SSL인증을 거치는 방식이었으며,
[아웃사이더님의 포스팅](https://blog.outsider.ne.kr/1178)을 보고 따라하던 중 이 도메인이 사용하고 있는 도메인이 맞는지 확인하는 과정에서 문제가 생겼다.


nginx의 server 블록에 location 블록을 만들고, let's encrypt에서 요청하는 경로를 설정하고 그 경로에 파일을 만들었는데 아무리 해도 그 경로를 인식하지를 못했다.


    * 첫번째 디버깅으로는 nginx의 location을 설정하는 방법(alias와 root)이 잘못되었나 하여 고쳐보았다.

        ex)
            `http://example.com/images/something/somepath/myfile.png` 의 경로 요청일 때

            location /images/something/ {
                alias /var/www/something/;
            }

            => 실제 경로는 /var/www/something/sompath/myfile.png

            location /images/something/ {
                root /var/www/something/;
            }

            => 실제 경로는 /var/www/something/images/something/somepath/myfile.png

        참조한 사이트:
        >http://kwonnam.pe.kr/wiki/nginx/location


그런데 root, alias 둘 다 써보면서 각각의 경로로 설정해봐도 도통 요청을 제대로 받지를 못하고 404가 떴다. 더군다나 몇번 시도를 해보다가 이 제대로된 도메인인지를 확인하는 과정이 한시간에 5번인가의 제한이 있는걸 하다가 알게 되어서 중간 중간 쓸데 없는 시간을 보내게 됐다. 

    참조 사이트: 
    >https://letsencrypt.org/docs/rate-limits/



## 어쨌든 계속 시도를 해보다가 현재는 적절한 도메인임을 인증하는게 중요하다고 생각되어서 생각을 바꿔서 다른 시도를 해보기로 했다.


    ```
    (1) meteor를 빌드하여 생긴 build 폴더내부에 certbot에서 요청을 보내는 경로를 사용하여 nginx에 위에서 했던 방법으로 경로를 설정한다. 
    이 방법은 meteor의 특성상 어떤 source의 요청이 왔을 때, public 폴더로 가서 찾게 되는 방식을 이용하려고 한건데, 이미 build된 폴더 내부에서는 public를 새로 만들어줘도 가지를 않았다. 

    (2) 애초에 빌드 전 public폴더 내부에 요청이 온 경로에 해당하는 폴더와 파일을 만들고 빌드한다.
    이 방법으로 해당하는 루트에서 파일을 확인할 수 있게 됐다.
    ```

인증프로세스 후, 만들어 지는 파일 중 두가지만 경로 설정을 해도 실행은 되는데 보안을 위해서 세번째 dhparam.pem 2048 파일도 설정하여 비트 암호화를 했다.

이 과정을 끝으로 ssl 설정을 마무리 했고, 1~2분 정도 지나고 브라우저 단에서도 ssl 인증이 확인됐다. 그리고 프로덕트는 약 90일간의 인증 기간을 받게 되었다.

물론 메일로 갱신기간에 대해 알림이 오지만 90일 이라는 기간이 너무 짧아서 조금 더 찾아보니 crontab을 이용하여 갱신의 자동화를 이용할 수 있는 기능이 있어서 찾아보았다.

**그런데 나 같은 경우 meteor 내부에 적절한 도메인인지에 대한 내용을 작성한 뒤에 빌드를 해서 요청을 받아야 하는 script를 넣어줘야 자동화가 될 수 있는데, 그 과정이 꽤나 번거로왔다.**

----

무엇보다 아웃사이더님의 블로그를 참조했을 때, 

``` 
(1) nginx를 사용하기 때문에 추가적인 설정은 nginx에 개별적으로 넣어야 한다는 맥락에서 `letsencrypt-auto certonly --manual` 명령어로 실행을 했기 때문에 letsencrypt -auto의 renew 명령어를 실행할 수 없었다.
(2) (1)와 같은 이유에서 갱신에 대한 포스트에서 갱신이 되질 않아서, 그냥 다시 재발급을 하는 로직을 따랐다.
```

  * letsencrypt-auto certonly --manual => letsencrypt에서 제공하는 플러그인을 사용하지 않는다는 의미
  * renew => letsecrypt에서 제공하는 인증서 갱신 명령어

경로 설정에 애를 먹었고, 삽질을 하다 결국 어찌어찌 인증은 했지만 갱신이 안된다는 부분을 보고 고민을 하다가 나중을 위하여 재 설정을 하기로 생각했다.

참고한 사이트:
>https://serverfault.com/questions/750902/how-to-use-lets-encrypt-dns-challenge-validation
>https://community.letsencrypt.org/t/cant-renew-the-certification-with-error-an-authentication-script-must-be-provided-with->manual-auth-hook-when-using-the-manual-plugin-non-interactively/67216/2




## 검색을 해보다가 letsencrypt에서 만든 자동화 툴인 certbot을 사용하자고 맘을 먹었다.



 **이유는** 
   * 기존의 letsencrypt에서 만든 자동화툴
   * nginx에 대한 플러그인도 마련되어 있음
   * 명령어 한 줄로 갱신 자동화 가능
   * 참고할 수 있는 article이 많았음
 
 참고한 사이트:
 >https://letsencrypt.readthedocs.io/en/latest/using.html#renewing-certificates
 >https://twpower.github.io/44-set-free-https-by-using-letsencrypt
 >http://blog.kimgihong.com/devlog/AWS_EC2_letsencrypt_SSL
 >https://doc.owncloud.com/server/admin_manual/installation/letsencrypt/nginx.html
 >https://m.blog.naver.com/PostView.nhn?blogId=itperson&logNo=220853849351&proxyReferer=https%3A%2F%2Fwww.google.com%2F
 >https://realsangil.github.io/2018/10/31/letsencrypt_wildcard_certification_renew#%ED%95%9C%EB%B2%88%EC%97%90-%EB%90%A0%EB%A6%AC%EA%B0%80-%EC%97%86%EC%A7%80
 


 [twpower라는 블로그](https://letsencrypt.readthedocs.io/en/latest/using.html#renewing-certificates)의 프로세스를 참고했고, 정말 얼마 걸리지 않게 쉽게 인증을 받을 수 있었다.......

 여기서 주의해야할 점은 *certbot-auto*와 *letsencrypt-auto*는 동일한 프로그램이라는 점이다.
 
 `Until May 2016, Certbot was named simply letsencrypt or letsencrypt-auto, depending on install method. Instructions on the Internet, and some pieces of the software, may still refer to this older name.`
 
 같은 기능을 하지만 certbot이란 이름으로 바뀌게 된것이고, 인터넷에 letsencrypt로 되어있는 문서의 역할을 동일하게 할 수 있으니 참조할 수 있다.
 
 ##갱신
 
 
 **0 14,19 1 * * /절대경로/letsencrypt-auto renew --quiet --no-self-upgrade --post-hook "/절대경로/service nginx restart"**
 매달 1일 한국시간으로 11시와 새벽 4시에 인증서가 갱신될 수 있는 상태인지를 확인하고 그 후 nginx를 다시 시작하도록 crontab에 명령을 예약하여 주었다.
 
 >--no-self-upgrade 플래그를 사용하면 Certbot이 사용자의 개입 없이 자체 업그레이드하지 않는다.위 옵션이 없으면 y/n 입력받기 위해 대기하다 끝난다.
 >--quiet : 로그 출력 안함
 
 
 
 참조한 사이트:
 >https://swiftcoding.org/lets-encrypt-renew
 >https://swiftcoding.org/lets-encrypt-auto-renew
 >http://riseshia.github.io/2016/10/16/certbot-let-s-encrypt.html
 >https://nosmoke.cctoday.co.kr/2487 -> 명령어에 대한 설명도 잘 되어있다.
 >https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/SSL-on-an-instance.html 
 
 
 
 
 
 

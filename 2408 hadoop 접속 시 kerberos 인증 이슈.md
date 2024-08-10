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

# Issue: hadoop 접속 시 kerveros 인증 이슈

## 상황: 
copyToLocal: failure to login: using ticket cache file: FILE:/tmp/krb5cc_p12311231 javax.security.auth.login.LoginException: java.lang.IllegalArgumentException: Illegal principal name test@ABC.AA: org.apache.hadoop.security.authentication.util.KerberosName$NoMatchingRule: No rules applied to test@ABC.AA

## 생각해낸 방안:

- /etc/hadoop/conf/core-site.xml의 hadoop.security.auth_to_local 규칙 수정

## 방안: /etc/hadoop/conf/core-site.xml의 hadoop.security.auth_to_local 규칙 수정

<br/>

keberos 주체를 로컬사용자로 매핑 즉 해당 RULE에 해당하는 도메인 가진 사람을 커버로스 주체로 매핑.

```
<configuration>
    <property>
        <name>hadoop.security.authentication</name>
        <value>kerberos</value>
    </property>

    <property>
        <name>hadoop.security.authorization</name>
        <value>true</value>
    </property>

    <property>
        <name>hadoop.security.auth_to_local</name>
        <value>
            RULE:[1:$1@$0](.*@ABC.AA)s/@.*//
            DEFAULT
        </value>
    </property>
</configuration>

```


<br/>
<br/>
<br/>

        참조:

<br/>

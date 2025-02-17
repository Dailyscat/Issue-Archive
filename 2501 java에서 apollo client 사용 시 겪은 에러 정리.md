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

# Issue: 2501 java에서 apollo client 사용 시 겪은 에러 정리

## 상황:

java, maven과 함께 apollo client 사용해야함.

apollo client는 kotlin으로 작성되어 있고 java support는 하지만
실제로 같이 사용하기 위해서는 apollo-client-maven-plugin까지 제대로 support해야함.

즉 apollo client jvm runtime이 apollo-client-mavne-plugin에서 잘 지원이 되어야하는데
나래비만 세워봐도 지옥이 시작될 것으로 보임.

일단 팀에서 사용하는 apollo client는 대체로 3.8.x라서 확인해봤는데

https://www.apollographql.com/docs/kotlin/v3/advanced/java
이 부분에서 ApolloClient를 만들고 execute() 하는 과정에서
java에서 이렇게 사용해보려고하니 execute()에 코루틴 객체를 인자로 받는 이상한 컴파일 에러가 있었음.
<generateKotlinModels>false</generateKotlinModels>가 기본 값인데도
뭔가 제대로 되지 않음..

```
 apolloClient.query(LaunchListQuery()).execute()

```

일단 그래서 다시 apollo 문서를 찾아보다 v4에 java client를 지원하고 interceptor 예제도 있길래 한번 써보려고 시도
https://apollographql.github.io/apollo-kotlin-java-support/welcome.html
잘되는듯 했고 apollo-client-mavne-plugin도 붙는가 싶었는데
schema.graphqls, dddd.graphql
파일을 컴파일 시에 읽어서 클래스로 만드는 과정에서 컴파일에러 발생.
mvn dependency:tree로 확인해보니
apollo-client-mavne-plugin은 아직 apollo runtime 4를 지원하지 않고 apollo3 dependency를 사용하고 있음.
그래서 다른 런타임으로 돌아가니 컴파일 에러 발생.

그래서 v4에서 지원하는 java client 디펜던시에 exclusion을 넣어서 runtime 버전만 3.8.x로 내려보려고 시도.
아래 에러 발생
retryOnError를 받아주는 부분이 호출이 안되니 뭐 어쩌구 저쩌구 안됨.

```
com.apollographql.apollo3.api.ApolloRequest$Builder.retryOnError(Ljava/lang/Boolean;)Lcom/apollographql/apollo3/api/ApolloRequest$Builder;
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1055)

```

다른 방법 찾다가 apollo-client-mavne-plugin에서 apollo v2를 지원하길래 이 방법 시도.
https://github.com/aoudiamoncef/apollo-client-maven-plugin/tree/support/apollo-2
https://www.apollographql.com/docs/kotlin/v2/essentials/get-started-java
오 역시 옛날걸로 해야하나해서 시도해보니 확실히 잘 되긴 되고 클라이언트 작성까지 잘돼서 요청 시도했는데
아래 에러 발생

```
duplicated field
```

분명히 요청은 가는데 에러가 오는거라서 서버쪽에서 어떨 때 에러가 오는지 확인을 해보니
graphQl 서버에서 validation하는 코드에 부분이 있었음. 이 부분을 수정해달라고 할 수 도 있었지만
graphQl 서버에서 document에서 관용적으로 제안하는 validation인 것 같아서 모호했음.
그래서 확인해보니 apollo-client-mavne-plugin에서 컴파일시에 스키마와 쿼리에 맞게 클래스를 생성하는 과정에서
뭔가 이슈가 있어보임. 이 이슈가 v2때는 문제가 아니었지만 v3부터는 문제인걸로 확인됨.

그래서 apollo-client-mavne-plugin에서 제공하는 config를 custom할 수 있는 방법을 제공해서 이것저것 해보는데
아래 이슈 발생

```
pollo-client-maven-plugin:4.0.5:generate failed.: NullPointerException -> [Help 1]
```

https://github.com/aoudiamoncef/apollo-client-maven-plugin/issues/57

config 수정 param 넣는데 npe 발생, maintaniner는 v3.x에 매진해야하서 수정어려움이라고 답변.

총체적난국이라 그냥 포기할까하다가 코틀린 코드가 jvm에서 도는데 kotlin 코드를 못사용할리가 없고 그리고 예전에 공부할 때
코틀린 코드 부분적으로 사용할 수 있다고 얘기한게 생각남.

그래서 다시 3.8.x로 회귀.

```
<dependency>
    <groupId>com.apollographql.apollo3</groupId>
    <artifactId>apollo-runtime</artifactId>
    <version>3.8.2</version>
</dependency>
<dependency>
      <groupId>com.apollographql.apollo3</groupId>
      <artifactId>apollo-api-jvm</artifactId>
      <version>3.8.2</version>
</dependency>
 <dependency>
    <groupId>com.apollographql.apollo3</groupId>
    <artifactId>apollo-runtime-jvm</artifactId>
    <version>3.8.2</version>
</dependency>
```

코틀린 쓰기 위한 디펜던시

```
<dependency>
    <groupId>org.jetbrains.kotlin</groupId>
    <artifactId>kotlin-stdlib-jdk8</artifactId>
    <version>1.8.22</version>
</dependency>
<dependency>
    <groupId>org.jetbrains.kotlinx</groupId>
    <artifactId>kotlinx-coroutines-core</artifactId>
    <version>1.8.0</version>
</dependency>

<!-- Kotlin Maven Plugin -->
<plugin>
    <groupId>org.jetbrains.kotlin</groupId>
    <artifactId>kotlin-maven-plugin</artifactId>
    <version>1.8.22</version>
    <extensions>true</extensions>
    <executions>
        <execution>
            <id>compile</id>
            <phase>compile</phase>
            <goals>
                <goal>compile</goal>
            </goals>
            <configuration>
                <sourceDirs>
                    <sourceDir>${project.basedir}/src/main/kotlin</sourceDir>
                </sourceDirs>
            </configuration>
        </execution>
        <execution>
            <id>test-compile</id>
            <phase>test-compile</phase>
            <goals>
                <goal>test-compile</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

```
@file:JvmName("ApolloBlockingUtils")
package com.example.apollo.blocking

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse

import kotlinx.coroutines.runBlocking

object ApolloBlockingUtils {
    @JvmStatic
    fun fetch(
        client: ApolloClient,
        ids: List<Long>
    ): example. example = runBlocking {
        val response = client.query(example(ids)).execute();

        if (response.hasErrors()) {
            throw RuntimeException("GraphQL errors: ${response.errors}")
        }

        val data = response.data
            ?: throw RuntimeException("No data returned. Possibly partial or null")

        return@runBlocking data.example
    }
}

```

코드 자체는 동작을 하는데 컴파일 에러 발생

main/kotlin
main/java
로 컴파일하게 해놨는데

코틀린 컴파일 플러그인이 자꾸 kotlin 소스를 찾지를 못함.

아마 java compile과 kotlin compile의 순서가 뭔가 이슈를 만드는 것 같아서

```
            <plugin>
                <groupId>org.jetbrains.kotlin</groupId>
                <artifactId>kotlin-maven-plugin</artifactId>
                <version>1.8.22</version>
                <extensions>true</extensions>
                <executions>
                    <execution>
                        <id>compile</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>compile</goal>
                        </goals>
                        <configuration>
                            <sourceDirs>
                                <sourceDir>${project.basedir}/src/main/kotlin</sourceDir>
                            </sourceDirs>
                        </configuration>
                    </execution>
                    <execution>
                        <id>test-compile</id>
                        <phase>test-compile</phase>
                        <goals>
                            <goal>test-compile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.2</version>
                <configuration>
                    <encoding>UTF-8</encoding>
                    <source>1.8</source>
                    <target>1.8</target>
                    <!--<optimize>true</optimize> -->
                </configuration>
                <executions>
                    <!-- Replacing default-compile as it is treated specially by Maven -->
                    <execution>
                        <id>default-compile</id>
                        <phase>none</phase>
                    </execution>
                    <!-- Replacing default-testCompile as it is treated specially by Maven -->
                    <execution>
                        <id>default-testCompile</id>
                        <phase>none</phase>
                    </execution>
                    <execution>
                        <id>java-compile</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>compile</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>java-test-compile</id>
                        <phase>test-compile</phase>
                        <goals>
                            <goal>testCompile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

default-compile을 none으로 처리해서 실행이 안되게 하고
kotlin이 compile단계에서 먼저 빌드하게 하고
그 다음 java-compile이 빌드되게 해봤더니
실행됨.

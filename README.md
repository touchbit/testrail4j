# TestRail4J

Lightweight java HTTP-client for the TestRail API.

Http clients (feign) are represented by the `BaseTestRailClient` interface for each type of model annotation.   
Annotated jackson2 and gson models are generated using the jsonschema2pojo plugin from json schemas (see `schema` directory).   
To generate models, simply build the project `mvn clean package`.

## Modules 

* core (testrail4j-core) - basic implementations for clients.
* feign4jackson2 (jackson2-feign-client) - jackson2 annotated client interface
    ```xml
    <dependencies>
        <groupId>org.touchbit.testrail4j</groupId>
        <artifactId>jackson2-feign-client</artifactId>
    </dependencies>
    ```
* model4jackson2 (jackson2-api-model) - jackson2 annotated models
    ```xml
    <dependencies>
        <groupId>org.touchbit.testrail4j</groupId>
        <artifactId>jackson2-api-model</artifactId>
    </dependencies>
    ```
* model4gson (gson-api-model) - gson annotated models
    ```xml
    <dependencies>
        <groupId>org.touchbit.testrail4j</groupId>
        <artifactId>gson-api-model</artifactId>
    </dependencies>
    ```

## Usage

1. Add repository
    ```xml
    <project>
       ...
       <repositories>
           <repository>
               <id>touchbit.org.repository</id>
               <url>https://touchbit.org/repository/</url>
           </repository>
       </repositories>
       ...
    </project>
    ```

2. Add the necessary feign client
    ```xml
    <project>
       ...
       <dependency>
           <groupId>org.touchbit.testrail4j</groupId>
           <artifactId>jackson2-feign-client</artifactId>
           <version>0.0.1</version>
       </dependency>
       ...
    </project>
    ```

3. Build feign client implementation and call the necessary method
    ```java
    import org.touchbit.testrail4j.jackson2.feign.client.TestRailClientBuilder;
    import org.touchbit.testrail4j.core.BasicAuthorizationInterceptor;
    import org.touchbit.testrail4j.jackson2.model.Case;
    
    public class Example {
        public static void main(String[] a) {
            TestRailClient client = TestRailClientBuilder
                    .build(new BasicAuthorizationInterceptor("user", "pass"), "http://localhost");

            Case testCase = client.getCase(1L);
            assert testCase.getId() == 1L;
        }
    }
    ```

## Contributing

For update version use `make ver <version>`. For example `make ver 0.0.1`.

[TestRail API (v2) documentation](http://docs.gurock.com/testrail-api2/start)

[Online json to schema converter](https://www.liquid-technologies.com/online-json-to-schema-converter)
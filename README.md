# TestRail4J

Lightweight java HTTP-clients for the TestRail API.

Full documentation is available on the [readthedocs](https://testrail4j.readthedocs.io/en/master/)

##### Briefly

* Add repository and necessary feign client
```xml
<repository>
   <id>touchbit.org.repository</id>
   <url>https://touchbit.org/repository/</url>
</repository>
...
<dependency>
   <groupId>org.touchbit.testrail4j</groupId>
   <artifactId>jackson2-feign-client</artifactId>
   <version>0.0.1</version>
</dependency>
```

* Build feign client implementation and call the necessary method
```java
public class Example {
    public static void main(String[] a) {
        TestRailClient client = TestRailClientBuilder
                .build(new BasicAuthorizationInterceptor("user", "pass"), "http://localhost");
        client.getCase(1L);
    }
}
```

##### [Contributing](https://testrail4j.readthedocs.io/en/master/CONTRIBUTING/index.html)

##### [License](https://testrail4j.readthedocs.io/en/master/LICENSE/index.html)
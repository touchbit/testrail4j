# TestRail4J [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.touchbit.testrail4j/parent/badge.svg?style=plastic)](https://mvnrepository.com/artifact/org.touchbit.testrail4j)

![TestRail](https://img.shields.io/badge/TestRail-v5.6.0.3856-blue.svg?style=plastic) ![java](https://img.shields.io/badge/Java-8-blue.svg?style=plastic)


Lightweight java HTTP-clients for the TestRail API.

Full documentation is available on the [testrail4j.readthedocs.io](https://testrail4j.readthedocs.io/en/master/)

##### Briefly

* Add repository and necessary feign client
```xml
<dependency>
   <groupId>org.touchbit.testrail4j</groupId>
   <artifactId>jackson2-feign-client</artifactId>
   <version>${testrail4j.version}</version>
</dependency>
```   
or
```xml
<dependency>
   <groupId>org.touchbit.testrail4j</groupId>
   <artifactId>gson-feign-client</artifactId>
   <version>${testrail4j.version}</version>
</dependency>
```   

* Build feign client implementation and call the necessary method
```java
public class Example {
    public static void main(String[] a) {
        TestRailClient client = TestRailClientBuilder.build("user", "pass", "http://localhost");
        client.getCase(1L);
    }
}
```
# About

Lightweight java HTTP-clients for the TestRail API.

A feign HTTP-clients are represented by the `TestRailClient` interface for each type of model annotation.   
Annotated jackson2 and gson models are generated using the jsonschema2pojo plugin from json schemas.   
To generate models, simply build the project `mvn clean package`.

## Modules
#### Clients
* **jackson2-feign-client** - `jackson2` annotated interface for the `feign` http-client   
```xml
<dependency>
    <groupId>org.touchbit.testrail4j</groupId>
    <artifactId>jackson2-feign-client</artifactId>
</dependency>
```
* **gson-feign-client** - `gson` annotated interface for the `feign` http-client   
```xml
<dependency>
    <groupId>org.touchbit.testrail4j</groupId>
    <artifactId>gson-feign-client</artifactId>
</dependency>
```

#### Models
* **testrail4j-schema** - module for generating models from JSON schemes.   
* **jackson2-api-model** - `jackson2` annotated models   
```xml
<dependency>
    <groupId>org.touchbit.testrail4j</groupId>
    <artifactId>jackson2-api-model</artifactId>
</dependency>
```
* **gson-api-model** - `gson` annotated models   
```xml
<dependency>
    <groupId>org.touchbit.testrail4j</groupId>
    <artifactId>gson-api-model</artifactId>
</dependency>
```

## Usage

* Add the necessary feign client
```xml
<dependency>
   <groupId>org.touchbit.testrail4j</groupId>
   <artifactId>jackson2-feign-client</artifactId>
   <version>0.0.1</version>
</dependency>
```

* Build feign client implementation and call the necessary method
```java
import org.touchbit.testrail4j.jackson2.gson.feign.TestRailClientBuilder;
import org.touchbit.testrail4j.core.BasicAuth;
import org.touchbit.testrail4j.jackson2.model.Case;

public class Example {
    public static void main(String[] a) {
        TestRailClient client = TestRailClientBuilder.build("user", "pass", "http://localhost");

        Project project = client.addProject("name", "announcement", true, 3);
        Section section = new Section()
                .withName(UUID.randomUUID().toString())
                .withDescription(UUID.randomUUID().toString());
        Section section = client.addSection(section, project.getId());
        Case caze = new Case()
                .withTitle("test_20190101201312")
                .withPriorityId(CRITICAL.getId())
                .withSuiteId(section.getSuiteId())
                .withRefs("JIRA-123")
                .withTypeId(ACCEPTANCE.getId())
                .withTemplateId(TEST_CASE_TEXT.getId())
                .withEstimate("1m 45s")
                .withCustomPreconds("withCustomPreconds")
                .withCustomSteps("withCustomSteps")
                .withCustomExpected("withCustomExpected")
                .withCustomStepsSeparated(null)
                ;
        Case caze = client.addCase(caze, caze.getSectionId());
        System.out.println(caze.getId());
    }
}
```

## Developer versions
For "dev" versions that are not yet available in the maven central add a repository
```xml
<project>
   <repositories>
       <repository>
           <id>touchbit.org</id>
           <url>https://touchbit.org/repository/</url>
       </repository>
   </repositories>
</project>
```

## Dependency tree
* [testrail4j-core](dependencies/testrail4j-core.html)
* [jackson2-feign-client](dependencies/jackson2-feign-client.html)
* [jackson2-api-model](dependencies/jackson2-api-model.html)
* [gson-feign-client](dependencies/gson-feign-client.html)
* [gson-api-model](dependencies/gson-api-model.html)
# About

Lightweight java HTTP-clients for the TestRail API.

A feign HTTP-clients are represented by the `BaseTestRailClient` interface for each type of model annotation.   
Annotated jackson2 and gson models are generated using the jsonschema2pojo plugin from json schemas (see `schema` directory).   
To generate models, simply build the project `mvn clean package`.

## Modules 

* **feign4jackson2** (jackson2-feign-client) - `jackson2` annotated interface for the `feign` http-client
```xml
<dependencies>
    <groupId>org.touchbit.testrail4j</groupId>
    <artifactId>jackson2-feign-client</artifactId>
</dependencies>
```
* **model4jackson2** (jackson2-api-model) - `jackson2` annotated models
```xml
<dependencies>
    <groupId>org.touchbit.testrail4j</groupId>
    <artifactId>jackson2-api-model</artifactId>
</dependencies>
```
* **model4gson** (gson-api-model) - `gson` annotated models
```xml
<dependencies>
    <groupId>org.touchbit.testrail4j</groupId>
    <artifactId>gson-api-model</artifactId>
</dependencies>
```
* **core** (testrail4j-core) - basic implementations for http-clients.
* **util** (testrail4j-util) - utility solutions for data processing.

## Dependency
```text
org.touchbit.testrail4j:jackson2-feign-client:jar:0.4.0
├─ org.touchbit.testrail4j:testrail4j-core:jar:0.4.0:compile
├─ org.touchbit.testrail4j:testrail4j-util:jar:0.4.0:compile
├─ org.touchbit.testrail4j:jackson2-api-model:jar:0.4.0:compile
│  ├─ com.fasterxml.jackson.core:jackson-annotations:jar:2.9.7:compile
│  └─ org.apache.commons:commons-lang3:jar:3.8.1:compile
├─ io.github.openfeign:feign-core:jar:10.1.0:compile
├─ io.github.openfeign:feign-jackson:jar:10.1.0:compile
│  └─ com.fasterxml.jackson.core:jackson-databind:jar:2.9.6:compile
│     └─ com.fasterxml.jackson.core:jackson-core:jar:2.9.6:compile
└─ org.slf4j:slf4j-api:jar:1.7.25:compile
```

## Usage

* Add repository
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

* Add the necessary feign client
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

* Build feign client implementation and call the necessary method
```java
import org.touchbit.testrail4j.jackson2.feign.client.TestRailClientBuilder;
import org.touchbit.testrail4j.core.BasicAuth;
import org.touchbit.testrail4j.jackson2.model.Case;

public class Example {
    public static void main(String[] a) {
        TestRailClient client = TestRailClientBuilder
                .build(new BasicAuthorizationInterceptor("user", "pass"), "http://localhost");

        Project project = client.addProject("name", "announcement", true, 3);
        Section section = new Section()
                .withName(UUID.randomUUID().toString())
                .withDescription(UUID.randomUUID().toString());
        Section section = client.addSection(section, project.getId());;
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
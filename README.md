# TestRail4J [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.touchbit.testrail4j/parent/badge.svg?style=plastic)](https://mvnrepository.com/artifact/org.touchbit.testrail4j)
![TestRail](https://img.shields.io/badge/TestRail-v5.6.0.3856-blue.svg?style=plastic)

Java HTTP-client for the TestRail API.

A feign HTTP-clients are represented by the `TestRailClient` interface for each type of model annotation.
Annotated jackson2 and gson models are generated using the jsonschema2pojo plugin from the json schemas.

Full documentation is available on the [testrail4j.readthedocs.io](https://testrail4j.readthedocs.io/en/master/)

## Requirements
* Java 8+
* [Open feign core](https://mvnrepository.com/artifact/io.github.openfeign/feign-core) 10.1+

## Usage
TestRail4J represented by two client modules with gson and Jackson2 models respectively.
This is done for the convenience of the end user, so as not to use unnecessary dependencies
in the project if one of the presented models is already used in the project.

Add the client to the pom.xml file. For example:   
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

Build client using `TestRailClientBuilder` and call the necessary method
```java
public class Example {
    public static void main(String[] a) {
        TestRailClient client = TestRailClientBuilder
                                    .build("user", "pass", "http://localhost");

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
                .withCustomStepsSeparated(null);
        
        Case caze = client.addCase(caze, caze.getSectionId());
        System.out.println(caze.getId());
    }
}
```

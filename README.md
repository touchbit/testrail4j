# TestRail4J [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.touchbit.testrail4j/parent/badge.svg?style=plastic)](https://mvnrepository.com/artifact/org.touchbit.testrail4j) [![TestRail](https://img.shields.io/badge/TestRail-v5.6.0.3856-blue.svg?style=plastic)](https://www.gurock.com/testrail) [![GitLab](https://img.shields.io/badge/Source-GitLab-blue.svg?style=plastic)](https://gitlab.com/TouchBIT/testrail4j)
   
[![master](https://gitlab.com/TouchBIT/testrail4j/badges/master/build.svg)](https://gitlab.com/TouchBIT/testrail4j/pipelines) [![readthedocs](https://readthedocs.org/projects/testrail4j/badge/?version=master)](https://testrail4j.readthedocs.io) [![alert_status](https://touchbit.org/sonar/api/project_badges/measure?project=org.touchbit.testrail4j%3Atestrail4j&metric=alert_status)](https://touchbit.org/sonar/dashboard?id=org.touchbit.testrail4j%3Atestrail4j) [![ncloc](https://touchbit.org/sonar/api/badges/measure?key=org.touchbit.testrail4j%3Atestrail4j&metric=ncloc&blinking=true)](https://touchbit.org/sonar/component_measures?id=org.touchbit.testrail4j%3Atestrail4j&metric=ncloc) [![coverage](https://touchbit.org/sonar/api/badges/measure?key=org.touchbit.testrail4j%3Atestrail4j&metric=coverage&blinking=true)](https://touchbit.org/sonar/component_measures?id=org.touchbit.testrail4j%3Atestrail4j&metric=coverage) [![tests](https://touchbit.org/sonar/api/badges/measure?key=org.touchbit.testrail4j%3Atestrail4j&metric=tests&blinking=true)](https://touchbit.org/sonar/component_measures?id=org.touchbit.testrail4j%3Atestrail4j&metric=ncloc) [![test_success_density](https://touchbit.org/sonar/api/badges/measure?key=org.touchbit.testrail4j%3Atestrail4j&blinking=true&metric=test_success_density)](https://touchbit.org/sonar/component_measures?id=org.touchbit.testrail4j%3Atestrail4j&metric=tests)

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

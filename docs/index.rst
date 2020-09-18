TestRail4J |TestRail4J| |Java|
==============================

.. |TestRail4J| image:: https://maven-badges.herokuapp.com/maven-central/org.touchbit.testrail4j/parent/badge.svg?style=plastic
    :target: https://mvnrepository.com/artifact/org.touchbit.testrail4j

.. |Java| image:: https://img.shields.io/badge/Java-8%2B-blue

Java HTTP-client for the `TestRail API`_.

.. _TestRail API: https://www.gurock.com/testrail/docs/api

A feign HTTP-clients are represented by the `TestRailClient` interface for each type of model annotation.
Annotated jackson2 and gson models are generated using the jsonschema2pojo plugin from the json schemas.

Usage
-----

TestRail4J represented by two client modules with gson and Jackson2 models respectively.
This is done for the convenience of the end user, so as not to use unnecessary dependencies
in the project if one of the presented models is already used in the project.

Add the `client`_ to the pom.xml file. For example jackson2:

.. _client: modules.html

.. code:: xml

    <dependency>
       <groupId>org.touchbit.testrail4j</groupId>
       <artifactId>jackson2-feign-client</artifactId>
       <version>${testrail4j.version}</version>
    </dependency>

Build client using `TestRailClientBuilder` and call the necessary method

.. code:: java

    import org.touchbit.testrail4j.jackson2.gson.feign.TestRailClientBuilder;
    import org.touchbit.testrail4j.core.BasicAuth;
    import org.touchbit.testrail4j.jackson2.model.Case;

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
            Case caze = client.addCase(caze, section);
            System.out.println(caze.getId());
        }
    }

Restrictions
------------

`TestRailClient#addCaseField(TRCaseField)`
""""""""""""""""""""""""""""""""""""""""""

The returned object for the method of creating a new test case custom field
is not available until the correction of the `defect`_

.. _defect: https://discuss.gurock.com/t/bug-api-different-types-of-returned-data-for-case-fields-configs/10598

Table of Contents
-----------------

.. toctree::

    customisation
    modules
    dependencies
    LICENSE
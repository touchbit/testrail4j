TestRail4J |CI/CD| |MavenCentral| |ReadTheDocs| |AlertStatus| |Coverage|
=====================================================================

.. |CI/CD| image:: https://github.com/touchbit/testrail4j/workflows/CI%2FCD/badge.svg?style=plastic
    :target: https://github.com/touchbit/testrail4j/actions?query=CI%2FCD

.. |MavenCentral| image:: https://maven-badges.herokuapp.com/maven-central/org.touchbit.testrail4j/parent/badge.svg?style=plastic
    :target: https://mvnrepository.com/artifact/org.touchbit.testrail4j

.. |ReadTheDocs| image:: https://readthedocs.org/projects/testrail4j/badge/?version=master
    :target: https://testrail4j.readthedocs.io

.. |AlertStatus| image:: https://sonarcloud.io/api/project_badges/measure?project=org.touchbit.testrail4j%3Atestrail4j&metric=alert_status
    :target: https://sonarcloud.io/dashboard?id=org.touchbit.testrail4j%3Atestrail4j

.. |Coverage| image:: https://sonarcloud.io/api/project_badges/measure?project=org.touchbit.testrail4j%3Atestrail4j&metric=coverage&blinking=true
    :target: https://sonarcloud.io/component_measures?id=org.touchbit.testrail4j%3Atestrail4j&metric=coverage

Java HTTP-client for the TestRail API.

TestRail4J represented by two client modules with gson and Jackson2 models respectively. This is done for the convenience of the end user, so as not to use unnecessary dependencies in the project if one of the presented models is already used in the project.

Full documentation is available on the `testrail4j.readthedocs.io`_.

.. _testrail4j.readthedocs.io: https://testrail4j.readthedocs.io/en/master/

Requirements
------------

* Java 8+
* `Open feign core`_ 11+

.. _Open feign core: https://mvnrepository.com/artifact/io.github.openfeign/feign-core

Briefly
-------

* Add repository and necessary feign client

.. code:: xml

    <dependency>
       <groupId>org.touchbit.testrail4j</groupId>
       <artifactId>jackson2-feign-client</artifactId>
       <version>${testrail4j.version}</version>
    </dependency>

or

.. code:: xml

    <dependency>
       <groupId>org.touchbit.testrail4j</groupId>
       <artifactId>gson-feign-client</artifactId>
       <version>${testrail4j.version}</version>
    </dependency>

* Build client using `TestRailClientBuilder` and call the necessary method

.. code:: java

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

Restrictions
------------

`TestRailClient#addCaseField(TRCaseField)`
""""""""""""""""""""""""""""""""""""""""""

The returned object for the method of creating a new test case custom field
is not available until the correction of the `defect`_

.. _defect: https://discuss.gurock.com/t/bug-api-different-types-of-returned-data-for-case-fields-configs/10598
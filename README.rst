TestRail4J |TestRail4JMavenCentral| |TestRail| |GitLabSource|
=============================================================

.. |TestRail4JMavenCentral| image:: https://maven-badges.herokuapp.com/maven-central/org.touchbit.testrail4j/parent/badge.svg?style=plastic
    :target: https://mvnrepository.com/artifact/org.touchbit.testrail4j

.. |TestRail| image:: https://img.shields.io/badge/TestRail-v6.*.*-blue.svg?style=plastic
    :target: https://www.gurock.com/testrail

.. |GitLabSource| image:: https://img.shields.io/badge/Source-GitLab-blue.svg?style=plastic
    :target: https://gitlab.com/TouchBIT/testrail4j

|MasterPipeline| |ReadTheDocs| |AlertStatus| |ncloc| |Coverage|

.. |MasterPipeline| image:: https://gitlab.com/TouchBIT/testrail4j/badges/master/build.svg
    :target: https://gitlab.com/TouchBIT/testrail4j/pipelines

.. |ReadTheDocs| image:: https://readthedocs.org/projects/testrail4j/badge/?version=master
    :target: https://testrail4j.readthedocs.io

.. |AlertStatus| image:: https://touchbit.org/sonar/api/project_badges/measure?project=org.touchbit.testrail4j%3Atestrail4j&metric=alert_status
    :target: https://touchbit.org/sonar/dashboard?id=org.touchbit.testrail4j%3Atestrail4j

.. |ncloc| image:: https://touchbit.org/sonar/api/badges/measure?key=org.touchbit.testrail4j%3Atestrail4j&metric=ncloc&blinking=true
    :target: https://touchbit.org/sonar/component_measures?id=org.touchbit.testrail4j%3Atestrail4j&metric=ncloc

.. |Coverage| image:: https://touchbit.org/sonar/api/badges/measure?key=org.touchbit.testrail4j%3Atestrail4j&metric=coverage&blinking=true
    :target: https://touchbit.org/sonar/component_measures?id=org.touchbit.testrail4j%3Atestrail4j&metric=coverage

|UnitTests| |UnitTestSuccessDensity| |iTests| |iTestSuccess|

.. |UnitTests| image:: https://touchbit.org/sonar/api/badges/measure?key=org.touchbit.testrail4j%3Atestrail4j&metric=tests&blinking=true

.. |UnitTestSuccessDensity| image:: https://touchbit.org/sonar/api/badges/measure?key=org.touchbit.testrail4j%3Atestrail4j&blinking=true&metric=test_success_density

.. |iTests| image:: https://gitlab.com/TouchBIT/testrail4j/raw/master/.indirect/badges/TestNG-Integration-tests-total.svg

.. |iTestSuccess| image:: https://gitlab.com/TouchBIT/testrail4j/raw/master/.indirect/badges/TestNG-Integration-tests-success-percent.svg

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
TestRail4J |CI/CD| |MavenCentral| |ReadTheDocs| |AlertStatus| |Coverage|
========================================================================

.. |CI/CD| image:: https://github.com/touchbit/testrail4j/workflows/CI%2FCD/badge.svg?style=plastic
    :target: https://github.com/touchbit/testrail4j/actions?query=CI%2FCD

.. |MavenCentral| image:: https://maven-badges.herokuapp.com/maven-central/org.touchbit.testrail4j/parent/badge.svg
    :target: https://mvnrepository.com/artifact/org.touchbit.testrail4j

.. |ReadTheDocs| image:: https://readthedocs.org/projects/testrail4j/badge/?version=master
    :target: https://testrail4j.readthedocs.io

.. |AlertStatus| image:: https://sonarcloud.io/api/project_badges/measure?project=org.touchbit.testrail4j%3Atestrail4j&metric=alert_status
    :target: https://sonarcloud.io/dashboard?id=org.touchbit.testrail4j%3Atestrail4j

.. |Coverage| image:: https://sonarcloud.io/api/project_badges/measure?project=org.touchbit.testrail4j%3Atestrail4j&metric=coverage&blinking=true
    :target: https://sonarcloud.io/component_measures?id=org.touchbit.testrail4j%3Atestrail4j&metric=coverage

|Java| HTTP-client for the `TestRail API`_.

.. |Java| image:: https://img.shields.io/badge/Java-8%2B-blue

.. _TestRail API: https://www.gurock.com/testrail/docs/api

TestRail4J represented by two client modules with gson and Jackson2 models respectively. This is done for the convenience of the end user, so as not to use unnecessary dependencies in the project if one of the presented models is already used in the project.

Full documentation is available on the `testrail4j.readthedocs.io`_.

.. _testrail4j.readthedocs.io: https://testrail4j.readthedocs.io/en/master/

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

            Case caze = client.addCase(caze, section);
            System.out.println(caze.getId());
        }
    }

* You can build the Feign client yourself and customize it to fit your needs.

.. code:: java

    TestRailClient client = new Feign.Builder()
        .client(new Client.Proxied(sslContextFactory, hostnameVerifier, proxy))
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .logger(new CustomLogger())
        .logLevel(FULL)
        .requestInterceptors(Arrays.asList(interceptors))
        .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true))
        .errorDecoder(new CustomTestRailErrorDecoder())
        .target(TestRailClient.class, "https://testrail.custom");

Modules (org.touchbit.testrail4j)
---------------------------------

* **jackson2-feign-client** - Feign client with jackson2 models.
* **gson-feign-client** - Feign client with gson models.
* **gson-api-model** - Gson annotated models (DTO).
* **jackson2-api-model** - Jackson2 annotated models (DTO).
* **testrail4j-core** - Base implementation of common classes for the Feign client (without depend of models).
* **testrail4j-schema** - Json schemas for TestRail API.

Restrictions
------------

`TestRailClient#addCaseField(TRCaseField)`
""""""""""""""""""""""""""""""""""""""""""

The returned object for the method of creating a new test case custom field
is not available until the correction of the `defect`_

.. _defect: https://discuss.gurock.com/t/bug-api-different-types-of-returned-data-for-case-fields-configs/10598
Modules
=======

Common
------

* **testrail4j-core** - contains a common implementation for TestRail4J HTTP clients.
  Used in modules: `jackson2-feign-client`, `gson-feign-client`.

Clients
-------

* **jackson2-feign-client** - `jackson2` annotated interface for the `feign` http-client

.. code:: xml

    <dependency>
        <groupId>org.touchbit.testrail4j</groupId>
        <artifactId>jackson2-feign-client</artifactId>
        <version>${testrail4j.version}</version>
    </dependency>

* **gson-feign-client** - `gson` annotated interface for the `feign` http-client

.. code:: xml

    <dependency>
        <groupId>org.touchbit.testrail4j</groupId>
        <artifactId>gson-feign-client</artifactId>
        <version>${testrail4j.version}</version>
    </dependency>

Models
------

* **jackson2-api-model** - `Jackson2` annotated models. Used in modules: `jackson2-feign-client`.

.. code:: xml

    <dependency>
        <groupId>org.touchbit.testrail4j</groupId>
        <artifactId>jackson2-api-model</artifactId>
        <version>${testrail4j.version}</version>
    </dependency>

* **gson-api-model** - `Gson` annotated models. Used in modules: `gson-feign-client`.

.. code:: xml

    <dependency>
        <groupId>org.touchbit.testrail4j</groupId>
        <artifactId>gson-api-model</artifactId>
        <version>${testrail4j.version}</version>
    </dependency>

* **testrail4j-schema** - Json schemas for TestRail API (if you need to generate your own models).

.. code:: xml

    <dependency>
        <groupId>org.touchbit.testrail4j</groupId>
        <artifactId>testrail4j-schema</artifactId>
        <version>${testrail4j.version}</version>
    </dependency>

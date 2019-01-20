Customisation
=============

TestRailClient
--------------

If you have a need to expand calls to TestRail (new version has been released), then you can always create your own
interface inherited from TestRailClient with your own methods of working with API. For example:

.. code:: java

    public interface CustomTestRailClient extends TestRailClient {

        @RequestLine(value = "GET /index.php%3F/api/v2/get_results/{foo}/{bar}")
        List<TRResult> getResultsWithFooBar(@Param("foo") Long foo, @Param("bar") Long bar);
    }

Next you need to build your specific TestRail client.

.. code:: java

    public class Example {
        public static void main(String[] a) {
            CustomTestRailClient client = TestRailClientBuilder
                    .build("login", "pass", "http://tr.host", CustomTestRailClient.class);
            client.getResultsWithFooBar("foooooo", "baaaaaar");
        }
    }

TestRailClientBuilder
---------------------

TestRailClientBuilder - not necessary to use. You can always build your own `TestRailClient` using `Feign.Builder()`

.. code:: java

    public class Example {
        public static void main(String[] a) {
            CustomTestRailClient clientt = new Feign.Builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(new CustomAuthInterceptor())
                .errorDecoder(new CustomTestRailErrorDecoder())
                .target(CustomTestRailClient.class, "http://custom.tr");
            client.getResultsWithFooBar("foooooo", "baaaaaar");
        }
    }

Query Maps
----------

All QueryMaps are empty interfaces to allow the end user to create and use their own filter with presets. Secondly to
level surprises from TR when updating the version of the server.

All existing default filters are presented in the package `org.touchbit.testrail4j.core.query.filter`

.. code:: java

    public class Example {
        public static void main(String[] a) {
            TestRailClient client = TestRailClientBuilder
                                        .build("user", "pass", "http://localhost");
            List<TRProject> projects = client
                                .getProjects(new GetProjectsFilter().withIsCompleted(true);
        }
    }
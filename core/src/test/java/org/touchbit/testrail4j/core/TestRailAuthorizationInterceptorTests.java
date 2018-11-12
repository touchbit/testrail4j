package org.touchbit.testrail4j.core;

import feign.RequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TestRailAuthorizationInterceptor class tests")
class TestRailAuthorizationInterceptorTests extends BaseUnitTest {

    @Test
    @DisplayName("Replace 'index.php%3F' to 'index.php?'")
    void unitTest_20181112025309() {
        TestRailAuthorizationInterceptor interceptor = new TestRailAuthorizationInterceptor() {
            @Override
            public void intercept(RequestTemplate template) {
                // do nothing
            }
        };
        RequestTemplate template = new RequestTemplate();
        template.uri("/index.php%3F/");
        interceptor.apply(template);
        assertThat(template.url()).contains("/index.php?/");
    }

    @Test
    @DisplayName("Exception if get 'index.php?'")
    void unitTest_20181112025726() {
        TestRailAuthorizationInterceptor interceptor = new TestRailAuthorizationInterceptor() {
            @Override
            public void intercept(RequestTemplate template) {
                // do nothing
            }
        };
        RequestTemplate template = new RequestTemplate();
        template.uri("/index.php?/");
        Executable executable = () -> interceptor.apply(template);
        Throwable t = executeThrowable(executable, IllegalArgumentException.class);
        assertThat(t.getMessage())
                .isEqualTo("Invalid url value. Expected contains '/index.php%3F/'. Received url: /index.php?/");
    }
}

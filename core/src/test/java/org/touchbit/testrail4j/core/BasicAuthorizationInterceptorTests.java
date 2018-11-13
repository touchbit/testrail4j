package org.touchbit.testrail4j.core;

import feign.RequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BasicAuthorizationInterceptor class tests")
class BasicAuthorizationInterceptorTests extends BaseUnitTest {

    @Test
    @DisplayName("Add authorization with encoded user pass to basic64")
    void unitTest_20181111212222() {
        RequestTemplate template = new RequestTemplate();
        BasicAuthorizationInterceptor interceptor = new BasicAuthorizationInterceptor("user", "pass");
        interceptor.intercept(template);
        assertThat(template.headers()).containsKeys("Authorization");
        assertThat(template.headers().get("Authorization")).contains("Basic dXNlcjpwYXNz");
    }

    @Test
    @DisplayName("Add basic64 authorization")
    void unitTest_20181113154823() {
        RequestTemplate template = new RequestTemplate();
        BasicAuthorizationInterceptor interceptor = new BasicAuthorizationInterceptor("unitTest_20181113154823");
        interceptor.intercept(template);
        assertThat(template.headers()).containsKeys("Authorization");
        assertThat(template.headers().get("Authorization")).contains("Basic unitTest_20181113154823");
    }

}

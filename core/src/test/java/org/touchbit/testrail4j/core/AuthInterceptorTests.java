/*
 * Copyright © 2018 Shaburov Oleg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.touchbit.testrail4j.core;

import feign.RequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("AuthInterceptor class tests")
class AuthInterceptorTests extends BaseUnitTest {

    @Test
    @DisplayName("Replace 'index.php%3F' to 'index.php?'")
    void unitTest_20181112025309() {
        AuthInterceptor interceptor = new AuthInterceptor() {
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
        AuthInterceptor interceptor = new AuthInterceptor() {
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

    @Test
    @DisplayName("Replace ? to &")
    void unitTest_20181112163152() {
        AuthInterceptor interceptor = new AuthInterceptor() {
            @Override
            public void intercept(RequestTemplate template) {
                // do nothing
            }
        };
        RequestTemplate template = new RequestTemplate();
        template.uri("/index.php%3F/api/v1/get_at?a=a&b=b");
        interceptor.apply(template);
        assertThat(template.url()).contains("/index.php?/api/v1/get_at&a=a&b=b");
    }

    @Test
    @DisplayName("No exception if absolute url")
    void unitTest_20190107234211() {
        AuthInterceptor interceptor = new AuthInterceptor() {
            @Override
            public void intercept(RequestTemplate template) {
                // do nothing
            }
        };
        RequestTemplate template = mock(RequestTemplate.class);
        when(template.url()).thenReturn("http://example.com/api/v2/index.php?/api/v1/get_at?a=a&b=b");
        interceptor.apply(template);
        assertThat(template.url()).contains("http://example.com/api/v2/index.php?/api/v1/get_at?a=a&b=b");
    }

}

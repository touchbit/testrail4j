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

import feign.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Oleg Shaburov on 12.01.2019
 * shaburov.o.a@gmail.com
 */
@DisplayName("TestRailFeignException class tests")
class TestRailFeignExceptionTests {

    @Test
    @DisplayName("Get exception if response body not null")
    void unitTest_20190112180907() throws Exception {
        Response response = mock(Response.class);
        Response.Body body = mock(Response.Body.class);
        when(body.asInputStream()).thenReturn(new ByteArrayInputStream("body str".getBytes(UTF_8)));
        when(response.body()).thenReturn(body);
        when(response.status()).thenReturn(500);
        TestRailFeignException exception = TestRailFeignException.errorStatus("GET", response);
        assertThat(exception.getMessage()).isEqualTo("status 500 reading GET message body str");
    }

    @Test
    @DisplayName("Get exception if response body is null")
    void unitTest_20190112181118() {
        Response response = mock(Response.class);
        Response.Body body = mock(Response.Body.class);
        when(response.body()).thenReturn(body);
        when(response.status()).thenReturn(500);
        TestRailFeignException exception = TestRailFeignException.errorStatus("GET", response);
        assertThat(exception.getMessage()).isEqualTo("status 500 reading GET");
    }

    @Test
    @DisplayName("TestRailClient#")
    void unitTest_20190112181712() {
        Response response = mock(Response.class);
        when(response.body()).thenReturn(null);
        when(response.status()).thenReturn(500);
        TestRailFeignException exception = TestRailFeignException.errorStatus("GET", response);
        assertThat(exception.getMessage()).isEqualTo("status 500 reading GET");
    }

}

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

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BasicAuth class tests")
class BasicAuthTests extends BaseUnitTest {

    @Test
    @DisplayName("Add authorization with encoded user pass to basic64")
    void unitTest_20181111212222() {
        RequestTemplate template = new RequestTemplate();
        BasicAuth interceptor = new BasicAuth("user", "pass");
        interceptor.intercept(template);
        assertThat(template.headers()).containsKeys("Authorization");
        assertThat(template.headers().get("Authorization")).contains("Basic dXNlcjpwYXNz");
    }

    @Test
    @DisplayName("Add basic64 authorization")
    void unitTest_20181113154823() {
        RequestTemplate template = new RequestTemplate();
        BasicAuth interceptor = new BasicAuth("unitTest_20181113154823");
        interceptor.intercept(template);
        assertThat(template.headers()).containsKeys("Authorization");
        assertThat(template.headers().get("Authorization")).contains("Basic unitTest_20181113154823");
    }

}

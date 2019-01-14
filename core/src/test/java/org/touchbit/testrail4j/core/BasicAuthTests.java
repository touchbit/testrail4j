/*
 * MIT License
 *
 * Copyright © 2019 TouchBIT.
 * Copyright © 2019 Oleg Shaburov.
 * Copyright © 2018 Maria Vasilenko.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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

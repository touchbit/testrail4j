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

package org.touchbit.testrail4j.jackson2.feign.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.core.ExecutionLogger;
import org.touchbit.testrail4j.test.core.BaseUnitTest;

import static feign.Logger.Level.HEADERS;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TestRail client builder tests")
class TestRailClientBuilderTests extends BaseUnitTest {

    @Test
    @DisplayName("build(String base64, String target)")
    void unitTest_20190108140721() {
        TestRailClientBuilder.build("base64", TARGET).getCase(2100L);
        assertThat(TEST_LOGGER.takeLoggedMessages()).isEmpty();
    }

    @Test
    @DisplayName("build(String login, String passToken, String target)")
    void unitTest_20190107220511() {
        TestRailClientBuilder.build("login", "passToken", TARGET).getCase(2100L);
        assertThat(TEST_LOGGER.takeLoggedMessages()).isEmpty();
    }

    @Test
    @DisplayName("build(String login, String passToken, String target, Logger log)")
    void unitTest_20190108140832() {
        TestRailClientBuilder.build("login", "passToken", TARGET, new ExecutionLogger(TEST_LOGGER)).getCase(2100L);
        String msg = TEST_LOGGER.takeLoggedMessages().toString();
        assertThat(msg).contains(GET_API + "/get_case/2100");
        assertThat(msg).contains("Authorization: Basic bG9naW46cGFzc1Rva2Vu");
    }

    @Test
    @DisplayName("build(I auth, String target)")
    void unitTest_20181112131511() {
        TestRailClientBuilder.build(new Auth(), TARGET).getCase(2100L);
        assertThat(TEST_LOGGER.takeLoggedMessages()).isEmpty();
    }

    @Test
    @DisplayName("build(String, String, String, Class<C>")
    void unitTest_20190120213037() {
        TestRailClientBuilder.build("login", "passToken", TARGET, TestRailClient.class).getCase(2100L);
        assertThat(TEST_LOGGER.takeLoggedMessages()).isEmpty();
    }

    @Test
    @DisplayName("build(I auth, String target, Logger log)")
    void unitTest_20181112132100() {
        TestRailClientBuilder.build(new Auth(), TARGET, new ExecutionLogger(TEST_LOGGER)).getCase(2100L);
        String msg = TEST_LOGGER.takeLoggedMessages().toString();
        assertThat(msg).contains(GET_API + "/get_case/2100");
        assertThat(msg).contains("Authorization: Basic dXNlcjpwYXNz");
    }

    @Test
    @DisplayName("build(I auth, String target, Logger logger, Logger.Level logLevel)")
    void unitTest_20190107220640() {
        TestRailClientBuilder
                .build(new Auth(), TARGET, TestRailClient.class, new ExecutionLogger(TEST_LOGGER)).getCase(2100L);
        String msg = TEST_LOGGER.takeLoggedMessages().toString();
        assertThat(msg).contains(GET_API + "/get_case/2100");
        assertThat(msg).contains("Authorization: Basic dXNlcjpwYXNz");
    }

    @Test
    @DisplayName("build(I auth, String target, Logger logger, Logger.Level logLevel)")
    void unitTest_20181112132306() {
        TestRailClientBuilder.build(new Auth(), TARGET, new ExecutionLogger(TEST_LOGGER), HEADERS).getCase(2100L);
        String msg = TEST_LOGGER.takeLoggedMessages().toString();
        assertThat(msg).contains(GET_API + "/get_case/2100");
        assertThat(msg).contains("Authorization: Basic dXNlcjpwYXNz");
    }

    @Test
    @DisplayName("build(I auth, String target, Logger logger, Logger.Level logLevel, true)")
    void unitTest_20190119015337() {
        TestRailClientBuilder.build(new Auth(), TARGET, new ExecutionLogger(TEST_LOGGER), HEADERS, true).getCase(2100L);
        String msg = TEST_LOGGER.takeLoggedMessages().toString();
        assertThat(msg).contains(GET_API + "/get_case/2100");
        assertThat(msg).contains("Authorization: Basic dXNlcjpwYXNz");
    }

}

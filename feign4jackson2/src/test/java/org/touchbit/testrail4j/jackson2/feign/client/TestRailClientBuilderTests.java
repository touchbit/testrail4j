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

package org.touchbit.testrail4j.jackson2.feign.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.core.BaseUnitTest;
import org.touchbit.testrail4j.core.ExecutionLogger;
import org.touchbit.testrail4j.helpful.Auth;

import static feign.Logger.Level.BASIC;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TestRail client builder tests")
class TestRailClientBuilderTests extends BaseUnitTest {

    @Test
    @DisplayName("build(String login, String passToken, String target)")
    void unitTest_20190107220511() {
        TestRailClientBuilder.build("login", "passToken", TARGET).getCase(2100L);
        assertThat(TEST_LOGGER.takeLoggedMessages()).isEmpty();
    }

    @Test
    @DisplayName("build(I auth, String target)")
    void unitTest_20181112131511() {
        TestRailClientBuilder.build(new Auth(), TARGET).getCase(2100L);
        assertThat(TEST_LOGGER.takeLoggedMessages()).isEmpty();
    }

    @Test
    @DisplayName("build(I auth, String target, Logger log)")
    void unitTest_20181112132100() {
        TestRailClientBuilder.build(new Auth(), TARGET, new ExecutionLogger(TEST_LOGGER)).getCase(2100L);
        assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_case/2100");
    }

    @Test
    @DisplayName("build(I auth, String target, Logger logger, Logger.Level logLevel)")
    void unitTest_20190107220640() {
        TestRailClientBuilder
                .build(new Auth(), TARGET, TestRailClient.class, new ExecutionLogger(TEST_LOGGER)).getCase(2100L);
        assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_case/2100");
    }

    @Test
    @DisplayName("build(I auth, String target, Logger logger, Logger.Level logLevel)")
    void unitTest_20181112132306() {
        TestRailClientBuilder.build(new Auth(), TARGET, new ExecutionLogger(TEST_LOGGER), BASIC).getCase(2100L);
        assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_case/2100");
    }

}

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
    @DisplayName("build(I auth, String target)")
    void unitTest_20181112131511() {
        TestRailClientBuilder.build(new Auth(), TARGET);
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
    void unitTest_20181112132306() {
        TestRailClientBuilder.build(new Auth(), TARGET, new ExecutionLogger(TEST_LOGGER), BASIC).getCase(2100L);
        assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_case/2100");
    }

}

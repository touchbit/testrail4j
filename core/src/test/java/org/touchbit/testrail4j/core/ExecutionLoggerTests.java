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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ExecutionLogger class tests")
class ExecutionLoggerTests extends BaseUnitTest {

    @Test
    @DisplayName("Check ExecutionLogger().log")
    void unitTest_20181013222513() {
        ExecutionUnitTestLogger feignCallLogger = new ExecutionUnitTestLogger(TEST_LOGGER);
        feignCallLogger.log("Class.method()", "format %s", "object");
        assertThat(TEST_LOGGER.takeLoggedMessages()).contains("[Class.method] format object");
    }

    @Test
    @DisplayName("WHEN ExecutionLogger() THEN no exception")
    void unitTest_20181028160734() {
        ExecutionUnitTestLogger feignCallLogger = new ExecutionUnitTestLogger();
        feignCallLogger.log("Class.method()", "format %s", "object");
        assertThat(TEST_LOGGER.takeLoggedMessages()).isEmpty();
    }

    private static class ExecutionUnitTestLogger extends ExecutionLogger {

        private ExecutionUnitTestLogger() {
            super();
        }

        private ExecutionUnitTestLogger(final Logger logger) {
            super(logger::info);
        }

        @Override
        public void log(String configKey, String format, Object... args) {
            super.log(configKey, format, args);
        }
    }

}

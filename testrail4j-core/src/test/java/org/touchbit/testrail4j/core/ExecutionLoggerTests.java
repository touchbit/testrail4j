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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.touchbit.testrail4j.test.core.BaseUnitTest;

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

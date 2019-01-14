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

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.status.StatusLogger;
import org.junit.jupiter.api.function.Executable;
import org.touchbit.testrail4j.jackson2.feign.client.helpful.MockServer;
import org.touchbit.testrail4j.jackson2.feign.client.helpful.UnitTestLogger;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseUnitTest {

    static {
        StatusLogger.getLogger().setLevel(Level.OFF);
    }

    protected static final UnitTestLogger TEST_LOGGER = new UnitTestLogger();
    private static final int TARGET_PORT = 19876;
    protected static final String TARGET = "http://localhost:" + TARGET_PORT + "/";
    protected static final String GET_API = "GET " + TARGET + "index.php?/api/v2";
    protected static final String POST_API = "POST " + TARGET + "index.php?/api/v2";
    protected static final MockServer SERVER = new MockServer(TARGET_PORT);


    @SuppressWarnings({"unchecked", "SameParameterValue"})
    protected <T> T executeThrowable(Executable executable, Class<T> exceptionClass) {
        Throwable throwable = null;
        try {
            executable.execute();
        } catch (Throwable e) {
            throwable = e;
        }
        assertThat(throwable).isInstanceOf(exceptionClass);
        return (T) throwable;
    }

}

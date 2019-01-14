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

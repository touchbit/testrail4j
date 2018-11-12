package org.touchbit.testrail4j.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.status.StatusLogger;
import org.junit.jupiter.api.function.Executable;
import org.touchbit.testrail4j.helpful.MockServer;
import org.touchbit.testrail4j.helpful.UnitTestLogger;

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

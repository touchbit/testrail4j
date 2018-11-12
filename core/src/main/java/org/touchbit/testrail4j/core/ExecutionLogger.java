package org.touchbit.testrail4j.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * Created by Oleg Shaburov on 12.11.2018
 * shaburov.o.a@gmail.com
 */
public class ExecutionLogger extends feign.Logger {

    private final Consumer<String> logMethod;

    public ExecutionLogger() {
        this(LoggerFactory.getLogger(ExecutionLogger.class));
    }

    public ExecutionLogger(final Logger logger) {
        this(logger::info);
    }

    public ExecutionLogger(final Consumer<String> logMethod) {
        this.logMethod = logMethod;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        String msg = methodTag(configKey) + format;
        logMethod.accept(String.format(msg, args));
    }

}

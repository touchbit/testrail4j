/*
 * MIT License
 *
 * Copyright © 2020 TouchBIT.
 * Copyright © 2020 Oleg Shaburov.
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

import feign.Feign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * Http request and response logger
 * <p>
 * Created by Oleg Shaburov on 12.11.2018
 * shaburov.o.a@gmail.com
 */
public class ExecutionLogger extends feign.Logger {

    private final Consumer<String> logMethod;

    /**
     * Default class logger
     */
    public ExecutionLogger() {
        this(LoggerFactory.getLogger(ExecutionLogger.class));
    }

    /**
     * @param logger - your logger implementing the slf4j {@link Logger} interface.
     */
    public ExecutionLogger(final Logger logger) {
        this(logger::info);
    }

    /**
     * @param logMethod - your logger with log level call. For example: new ExecutionLogger(logger::trace);
     */
    public ExecutionLogger(final Consumer<String> logMethod) {
        this.logMethod = logMethod;
    }

    /**
     * Messages will be http request and response text.
     *
     * @param configKey value of {@link Feign#configKey(Class, java.lang.reflect.Method)}
     * @param format {@link java.util.Formatter format string}
     * @param args arguments applied to {@code format}
     */
    @Override
    protected void log(String configKey, String format, Object... args) {
        String msg = methodTag(configKey) + format;
        logMethod.accept(String.format(msg, args));
    }

}

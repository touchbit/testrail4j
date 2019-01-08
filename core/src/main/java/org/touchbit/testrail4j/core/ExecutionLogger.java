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

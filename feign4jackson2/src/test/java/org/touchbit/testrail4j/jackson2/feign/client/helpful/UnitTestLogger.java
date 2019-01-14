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

package org.touchbit.testrail4j.jackson2.feign.client.helpful;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.slf4j.Log4jLogger;
import org.slf4j.helpers.SubstituteLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.logging.log4j.Level.ALL;

/**
 * Created by Oleg Shaburov on 16.10.2018
 * shaburov.o.a@gmail.com
 */
public class UnitTestLogger extends SubstituteLogger {

    private boolean isDebug = true;
    private boolean isError = true;
    private boolean isTrace = true;
    private boolean isWarn = true;
    private boolean isInfo = true;

    private List<String> loggedMessages = Collections.synchronizedList(new ArrayList<>());
    private List<String> loggedMessagesWithLevel = Collections.synchronizedList(new ArrayList<>());

    public UnitTestLogger() {
        super("UnitTestLogger", null, false);
        setDelegate(new Log4jLogger(new UnitTestLog4jLogger(), "UnitTestLog4jLogger"));
    }

    public List<String> takeLoggedMessages() {
        List<String> tmp = new ArrayList<>(loggedMessages);
        loggedMessages.clear();
        return tmp;
    }

    public List<String> takeLoggedMessagesWithLevel() {
        List<String> tmp = new ArrayList<>(loggedMessagesWithLevel);
        loggedMessagesWithLevel.clear();
        return tmp;
    }

    public void reset() {
        this.loggedMessages.clear();
        this.loggedMessagesWithLevel.clear();
        this.isDebug = true;
        this.isError = true;
        this.isTrace = true;
        this.isWarn = true;
        this.isInfo = true;
    }

    private class UnitTestLog4jLogger extends AbstractLogger {

        @Override
        public void logMessage(String fqcn, Level level, Marker marker, Message message, Throwable t) {
            loggedMessages.add(message.getFormattedMessage());
            loggedMessagesWithLevel.add(level + " " + message.getFormattedMessage());
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, Message message, Throwable t) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, CharSequence message, Throwable t) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, Object message, Throwable t) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Throwable t) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object... params) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object p0) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
            return true;
        }

        @Override
        public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
            return true;
        }

        @Override
        public Level getLevel() {
            return ALL;
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return this.isDebug;
    }

    public UnitTestLogger whenDebugEnabled(boolean isDebug) {
        this.isDebug = isDebug;
        return this;
    }

    @Override
    public boolean isTraceEnabled() {
        return isTrace;
    }

    public UnitTestLogger whenTraceEnabled(boolean isTrace) {
        this.isTrace = isTrace;
        return this;
    }

    @Override
    public boolean isInfoEnabled() {
        return isInfo;
    }

    public UnitTestLogger whenInfoEnabled(boolean isInfo) {
        this.isInfo = isInfo;
        return this;
    }

    @Override
    public boolean isWarnEnabled() {
        return isWarn;
    }

    public UnitTestLogger whenWarnEnabled(boolean isWarn) {
        this.isWarn = isWarn;
        return this;
    }

    @Override
    public boolean isErrorEnabled() {
        return this.isError;
    }

    public UnitTestLogger whenErrorEnabled(boolean isError) {
        this.isError = isError;
        return this;
    }

}

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

package org.touchbit.testrail4j.test.core;

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

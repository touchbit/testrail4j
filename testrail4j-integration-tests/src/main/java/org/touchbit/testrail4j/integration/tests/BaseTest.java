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

package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.touchbit.buggy.core.Buggy;
import org.touchbit.buggy.core.test.BaseBuggyTest;
import org.touchbit.buggy.core.testng.listeners.IntellijIdeaTestNgPluginListener;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 17.09.2020
 * shaburov.o.a@gmail.com
 */

@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public class BaseTest extends BaseBuggyTest {

    static {
        if (!IntellijIdeaTestNgPluginListener.isIntellijIdeaTestRun() && !Buggy.isTestRun()) {
            Buggy.getExitHandler().exitRun(1, "Missing IntellijIdeaPluginListener in the Intellij IDEA" +
                    " TestNG plugin configuration.");
        }
    }

    protected FeignException executeThrowable(Executable executable) {
        return executeThrowable(executable, FeignException.class);
    }

    @SuppressWarnings("unchecked")
    protected <T> T executeThrowable(Executable executable, Class<T> exceptionClass) {
        Throwable throwable = null;
        try {
            executable.execute();
        } catch (Throwable e) {
            throwable = e;
        }
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(exceptionClass);
        return (T) throwable;
    }

    @FunctionalInterface
    public interface Executable {
        void execute() throws Throwable;
    }

    public static String getRandomString(int length) {
        return getRandomString(length, true);
    }

    public static String getRandomString(int length, boolean withLowerCase) {
        String str = generateString("QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm", length);
        return withLowerCase ? str.toLowerCase() : str;
    }

    private static String generateString(String characters, int length) {
        if (length < 1) {
            throw new IllegalArgumentException("The length of the generated string must be positive. Received: " + length);
        } else {
            char[] text = new char[length];

            for(int i = 0; i < length; ++i) {
                text[i] = characters.charAt((new Random()).nextInt(characters.length()));
            }

            return new String(text);
        }
    }

}

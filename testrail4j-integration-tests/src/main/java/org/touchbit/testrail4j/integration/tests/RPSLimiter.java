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

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.touchbit.buggy.core.utils.log.BuggyLog;

import java.util.Date;

/**
 * Feign request interceptor for basic request authorization.
 * <p>
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
public class RPSLimiter implements RequestInterceptor {

    private static final Logger LOG = BuggyLog.framework();

    private static long time = new Date().getTime();

    private static int requests = 0;

    private static final int MAX = 160;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        limit();
    }

    private synchronized static void limit() {
        requests++;
        if (requests >= MAX) {
            long diff = time + 60000 - new Date().getTime();
            if (diff > 0) {
                try {
                    long diffSec = diff / 1000;
                    LOG.info("The limit of {} requests per minute has been reached.\nWaiting: {} sec.", MAX, diffSec);
                    Thread.sleep(diff);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                time = new Date().getTime();
                requests = 0;
            }
        }
    }

}

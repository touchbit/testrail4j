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

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.touchbit.testrail4j.core.AuthInterceptor;
import org.touchbit.testrail4j.core.BasicAuth;
import org.touchbit.testrail4j.core.RestRailErrorDecoder;

import static feign.Logger.Level.FULL;

/**
 * Created by Oleg Shaburov on 12.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings("WeakerAccess")
public class TestRailClientBuilder {

    public static TestRailClient build(String base64, String target) {
        BasicAuth basicAuth = new BasicAuth(base64);
        return build(basicAuth, target, TestRailClient.class, new Logger.NoOpLogger(), FULL);
    }

    public static TestRailClient build(String login, String passToken, String target) {
        BasicAuth basicAuth = new BasicAuth(login, passToken);
        return build(basicAuth, target, TestRailClient.class, new Logger.NoOpLogger(), FULL);
    }

    public static TestRailClient build(String login, String passToken, String target, Logger log) {
        BasicAuth basicAuth = new BasicAuth(login, passToken);
        return build(basicAuth, target, TestRailClient.class, log, FULL);
    }

    public static <I extends AuthInterceptor> TestRailClient build(I auth, String target) {
        return build(auth, target, TestRailClient.class, new Logger.NoOpLogger(), FULL);
    }

    public static <I extends AuthInterceptor> TestRailClient build(I auth, String target, Logger log) {
        return build(auth, target, TestRailClient.class, log, FULL);
    }

    public static <I extends AuthInterceptor> TestRailClient build(I auth, String target,
                                                                   Logger log,
                                                                   Logger.Level logLevel) {
        return build(auth, target, TestRailClient.class, log, logLevel);
    }

    public static <I extends AuthInterceptor, C extends  TestRailClient> C build(I auth,
                                                                                 String target,
                                                                                 Class<C> c,
                                                                                 Logger log) {
        return build(auth, target, c, log, FULL);
    }

    public static <I extends AuthInterceptor, C extends  TestRailClient> C build(I auth,
                                                                                 String target,
                                                                                 Class<C> c,
                                                                                 Logger logger,
                                                                                 Logger.Level logLevel) {
        return new Feign.Builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(logger)
                .logLevel(logLevel)
                .requestInterceptor(auth)
                .errorDecoder(new RestRailErrorDecoder())
                .target(c, target);
    }

    /** Utility class. Prohibit instantiation. */
    private TestRailClientBuilder() { }

}

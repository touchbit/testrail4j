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

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.touchbit.testrail4j.core.AuthInterceptor;
import org.touchbit.testrail4j.core.BasicAuth;

import static feign.Logger.Level.FULL;

/**
 * Created by Oleg Shaburov on 12.11.2018
 * shaburov.o.a@gmail.com
 */
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
                .target(c, target);
    }

    /** Utility class. Prohibit instantiation. */
    private TestRailClientBuilder() { }

}

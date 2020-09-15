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

package org.touchbit.testrail4j.jackson2.feign.client;

import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.RequestInterceptor;
import org.touchbit.testrail4j.core.BasicAuth;
import org.touchbit.testrail4j.core.ExecutionLogger;
import org.touchbit.testrail4j.core.TestRailErrorDecoder;
import org.touchbit.testrail4j.core.TrustSocketHelper;

import java.util.Arrays;

import static feign.Logger.Level.FULL;

/**
 * Base builder to get the {@link TestRailClient} object.
 * You can always build your http-client using the {@link Feign.Builder()} class.
 *
 * Created by Oleg Shaburov on 12.11.2018
 * shaburov.o.a@gmail.com
 */
public class TestRailClientBuilder {

    /**
     * @param base64 - base64-encoded login:password or login:token.
     * @param target - TestRail host.
     *
     * @return {@link TestRailClient} object without logging.
     */
    public static TestRailClient build(String base64, String target) {
        return build(base64, target, false);
    }

    /**
     * @param base64 - base64-encoded login:password or login:token.
     * @param target - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     *
     * @return {@link TestRailClient} object without logging.
     */
    public static TestRailClient build(String base64, String target, boolean ignoreSslErrors) {
        BasicAuth basicAuth = new BasicAuth(base64);
        return build(target, TestRailClient.class, ignoreSslErrors, new Logger.NoOpLogger(), FULL, basicAuth);
    }

    /**
     * @param login - user login (email).
     * @param passToken - user password or API token.
     * @param target - TestRail host.
     * @param testRailClientClass - {@link TestRailClient} class or the heir of this class.
     *
     * @return {@link TestRailClient} object without logging.
     */
    public static <C extends TestRailClient> C build(String login,
                                                     String passToken,
                                                     String target,
                                                     Class<C> testRailClientClass) {
        return build(login, passToken, target, testRailClientClass, false);
    }

    /**
     * @param login - user login (email).
     * @param passToken - user password or API token.
     * @param target - TestRail host.
     * @param testRailClientClass - {@link TestRailClient} class or the heir of this class.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     *
     * @return {@link TestRailClient} object without logging.
     */
    public static <C extends TestRailClient> C build(String login,
                                                     String passToken,
                                                     String target,
                                                     Class<C> testRailClientClass,
                                                     boolean ignoreSslErrors) {
        BasicAuth basicAuth = new BasicAuth(login, passToken);
        return build(target, testRailClientClass, ignoreSslErrors, new Logger.NoOpLogger(), FULL, basicAuth);
    }

    /**
     * @param login - user login (email).
     * @param passToken - user password or API token.
     * @param target - TestRail host.
     *
     * @return {@link TestRailClient} object without logging.
     */
    public static TestRailClient build(String login, String passToken, String target) {
        return build(login, passToken, target, false);
    }

    /**
     * @param login - user login (email).
     * @param passToken - user password or API token.
     * @param target - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     *
     * @return {@link TestRailClient} object without logging.
     */
    public static TestRailClient build(String login, String passToken, String target, boolean ignoreSslErrors) {
        BasicAuth basicAuth = new BasicAuth(login, passToken);
        return build(target, TestRailClient.class, ignoreSslErrors, new Logger.NoOpLogger(), FULL, basicAuth);
    }

    /**
     * @param login - user login (email).
     * @param passToken - user password or API token.
     * @param target - TestRail host.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     *
     * @return {@link TestRailClient} object with fill request logging.
     */
    public static TestRailClient build(String login, String passToken, String target, Logger logger) {
        return build(login, passToken, target, logger, false);
    }

    /**
     * @param login - user login (email).
     * @param passToken - user password or API token.
     * @param target - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     *
     * @return {@link TestRailClient} object with fill request logging.
     */
    public static TestRailClient build(String login,
                                       String passToken,
                                       String target,
                                       Logger logger,
                                       boolean ignoreSslErrors) {
        BasicAuth basicAuth = new BasicAuth(login, passToken);
        return build(target, TestRailClient.class, ignoreSslErrors, logger, FULL, basicAuth);
    }

    /**
     * @param interceptors - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     *
     * @return {@link TestRailClient} object without logging.
     */
    public static TestRailClient build(String target, RequestInterceptor... interceptors) {
        return build(target, false, interceptors);
    }

    /**
     * @param interceptors - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     *
     * @return {@link TestRailClient} object without logging.
     */
    public static TestRailClient build(String target, boolean ignoreSslErrors, RequestInterceptor... interceptors) {
        return build(target, TestRailClient.class, ignoreSslErrors, new Logger.NoOpLogger(), FULL, interceptors);
    }

    /**
     * @param interceptors - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     *
     * @return {@link TestRailClient} object with fill request logging.
     */
    public static TestRailClient build(String target, Logger logger, RequestInterceptor... interceptors) {
        return build(target, logger, false, interceptors);
    }

    /**
     * @param interceptors - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     *
     * @return {@link TestRailClient} object with fill request logging.
     */
    public static TestRailClient build(String target,
                                       Logger logger,
                                       boolean ignoreSslErrors,
                                       RequestInterceptor... interceptors) {
        return build(target, TestRailClient.class, ignoreSslErrors, logger, FULL, interceptors);
    }

    /**
     * @param interceptors - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     * @param logLevel - Log level. Possible values NONE, BASIC, HEADERS, FULL.
     *
     * @return {@link TestRailClient} object with request logging.
     */
    public static TestRailClient build(String target,
                                       Logger logger,
                                       Logger.Level logLevel,
                                       RequestInterceptor... interceptors) {
        return build(target, logger, logLevel, false, interceptors);
    }

    /**
     * @param interceptors - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     * @param logLevel - Log level. Possible values NONE, BASIC, HEADERS, FULL.
     *
     * @return {@link TestRailClient} object with request logging.
     */
    public static TestRailClient build(String target,
                                       Logger logger,
                                       Logger.Level logLevel,
                                       boolean ignoreSslErrors,
                                       RequestInterceptor... interceptors) {
        return build(target, TestRailClient.class, ignoreSslErrors, logger, logLevel, interceptors);
    }

    /**
     * @param interceptors - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param testRailClientClass - {@link TestRailClient} class or the heir of this class.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     *
     * @return {@link TestRailClient} object with fill request logging.
     */
    public static <C extends  TestRailClient> C build(String target,
                                                      Class<C> testRailClientClass,
                                                      Logger logger,
                                                      RequestInterceptor... interceptors) {
        return build(target, testRailClientClass, logger, false, interceptors);
    }

    /**
     * @param interceptors - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     * @param testRailClientClass - {@link TestRailClient} class or the heir of this class.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     *
     * @return {@link TestRailClient} object with fill request logging.
     */
    public static <C extends  TestRailClient> C build(String target,
                                                      Class<C> testRailClientClass,
                                                      Logger logger,
                                                      boolean ignoreSslErrors,
                                                      RequestInterceptor... interceptors) {
        return build(target, testRailClientClass, ignoreSslErrors, logger, FULL, interceptors);
    }

    /**
     * @param interceptors - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param testRailClientClass - {@link TestRailClient} class or the heir of this class.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     * @param logLevel - Log level. Possible values NONE, BASIC, HEADERS, FULL.
     *
     * @return {@link TestRailClient} object with request logging.
     */
    public static <C extends  TestRailClient> C build(String target,
                                                      Class<C> testRailClientClass,
                                                      boolean ignoreSslErrors,
                                                      Logger logger,
                                                      Logger.Level logLevel,
                                                      RequestInterceptor... interceptors) {
        Client client;
        if (ignoreSslErrors) {
            client = new Client
                    .Default(TrustSocketHelper.getTrustAllCrtSocketFactory(), TrustSocketHelper.getTrustAllHostname());
        } else {
            client = new Client.Default(null, null);
        }
        return build(client, target, testRailClientClass, logger, logLevel, interceptors);
    }

    /**
     * @param interceptors - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param client - Feign http client.
     * @param target - TestRail host.
     * @param testRailClientClass - {@link TestRailClient} class or the heir of this class.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     * @param logLevel - Log level. Possible values NONE, BASIC, HEADERS, FULL.
     *
     * @return {@link TestRailClient} object with request logging.
     */
    public static <C extends  TestRailClient> C build(Client client,
                                                      String target,
                                                      Class<C> testRailClientClass,
                                                      Logger logger,
                                                      Logger.Level logLevel,
                                                      RequestInterceptor... interceptors) {
        return new Feign.Builder()
                .client(client)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(logger)
                .logLevel(logLevel)
                .requestInterceptors(Arrays.asList(interceptors))
                .errorDecoder(new TestRailErrorDecoder())
                .target(testRailClientClass, target);
    }

    /** Utility class. Prohibit instantiation. */
    private TestRailClientBuilder() { }

}

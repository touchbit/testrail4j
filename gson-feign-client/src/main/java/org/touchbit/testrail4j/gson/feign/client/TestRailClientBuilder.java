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

package org.touchbit.testrail4j.gson.feign.client;

import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.touchbit.testrail4j.core.BasicAuth;
import org.touchbit.testrail4j.core.ExecutionLogger;
import org.touchbit.testrail4j.core.RestRailErrorDecoder;
import org.touchbit.testrail4j.core.TrustSocketHelper;

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
        return build(basicAuth, target, TestRailClient.class, ignoreSslErrors, new Logger.NoOpLogger(), FULL);
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
        return build(basicAuth, target, TestRailClient.class, ignoreSslErrors, new Logger.NoOpLogger(), FULL);
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
        return build(basicAuth, target, TestRailClient.class, ignoreSslErrors, logger, FULL);
    }

    /**
     * @param auth - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     *
     * @return {@link TestRailClient} object without logging.
     */
    public static TestRailClient build(BasicAuth auth, String target) {
        return build(auth, target, false);
    }

    /**
     * @param auth - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     *
     * @return {@link TestRailClient} object without logging.
     */
    public static TestRailClient build(BasicAuth auth, String target, boolean ignoreSslErrors) {
        return build(auth, target, TestRailClient.class, ignoreSslErrors, new Logger.NoOpLogger(), FULL);
    }

    /**
     * @param auth - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     *
     * @return {@link TestRailClient} object with fill request logging.
     */
    public static TestRailClient build(BasicAuth auth, String target, Logger logger) {
        return build(auth, target, logger, false);
    }

    /**
     * @param auth - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     *
     * @return {@link TestRailClient} object with fill request logging.
     */
    public static TestRailClient build(BasicAuth auth, String target, Logger logger, boolean ignoreSslErrors) {
        return build(auth, target, TestRailClient.class, ignoreSslErrors, logger, FULL);
    }

    /**
     * @param auth - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     * @param logLevel - Log level. Possible values NONE, BASIC, HEADERS, FULL.
     *
     * @return {@link TestRailClient} object with request logging.
     */
    public static TestRailClient build(BasicAuth auth, String target, Logger logger, Logger.Level logLevel) {
        return build(auth, target, logger, logLevel, false);
    }

    /**
     * @param auth - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     * @param logLevel - Log level. Possible values NONE, BASIC, HEADERS, FULL.
     *
     * @return {@link TestRailClient} object with request logging.
     */
    public static TestRailClient build(BasicAuth auth,
                                       String target,
                                       Logger logger,
                                       Logger.Level logLevel,
                                       boolean ignoreSslErrors) {
        return build(auth, target, TestRailClient.class, ignoreSslErrors, logger, logLevel);
    }

    /**
     * @param auth - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param testRailClientClass - {@link TestRailClient} class or the heir of this class.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     *
     * @return {@link TestRailClient} object with fill request logging.
     */
    public static <C extends  TestRailClient> C build(BasicAuth auth,
                                                      String target,
                                                      Class<C> testRailClientClass,
                                                      Logger logger) {
        return build(auth, target, testRailClientClass, logger, false);
    }

    /**
     * @param auth - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param ignoreSslErrors - ignore ssl certificate errors.
     * @param testRailClientClass - {@link TestRailClient} class or the heir of this class.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     *
     * @return {@link TestRailClient} object with fill request logging.
     */
    public static <C extends  TestRailClient> C build(BasicAuth auth,
                                                      String target,
                                                      Class<C> testRailClientClass,
                                                      Logger logger,
                                                      boolean ignoreSslErrors) {
        return build(auth, target, testRailClientClass, ignoreSslErrors, logger, FULL);
    }

    /**
     * @param auth - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param target - TestRail host.
     * @param testRailClientClass - {@link TestRailClient} class or the heir of this class.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     * @param logLevel - Log level. Possible values NONE, BASIC, HEADERS, FULL.
     *
     * @return {@link TestRailClient} object with request logging.
     */
    public static <C extends  TestRailClient> C build(BasicAuth auth,
                                                      String target,
                                                      Class<C> testRailClientClass,
                                                      boolean ignoreSslErrors,
                                                      Logger logger,
                                                      Logger.Level logLevel) {
        Client client;
        if (ignoreSslErrors) {
            client = new Client
                    .Default(TrustSocketHelper.getTrustAllCrtSocketFactory(), TrustSocketHelper.getTrustAllHostname());
        } else {
            client = new Client.Default(null, null);
        }
        return build(auth, client, target, testRailClientClass, logger, logLevel);
    }

    /**
     * @param auth - Feign request interceptor represented by class {@link BasicAuth} for basic request authorization.
     * @param client - Feign http client.
     * @param target - TestRail host.
     * @param testRailClientClass - {@link TestRailClient} class or the heir of this class.
     * @param logger - Custom logger inherits from {@link Logger}. It is recommended to use {@link ExecutionLogger}
     * @param logLevel - Log level. Possible values NONE, BASIC, HEADERS, FULL.
     *
     * @return {@link TestRailClient} object with request logging.
     */
    public static <C extends  TestRailClient> C build(BasicAuth auth,
                                                      Client client,
                                                      String target,
                                                      Class<C> testRailClientClass,
                                                      Logger logger,
                                                      Logger.Level logLevel) {
        return new Feign.Builder()
                .client(client)
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(logger)
                .logLevel(logLevel)
                .requestInterceptor(auth)
                .errorDecoder(new RestRailErrorDecoder())
                .target(testRailClientClass, target);
    }

    /** Utility class. Prohibit instantiation. */
    private TestRailClientBuilder() { }

}

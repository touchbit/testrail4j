package org.touchbit.testrail4j.jackson2.feign.client;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.touchbit.testrail4j.core.TestRailAuthorizationInterceptor;

import static feign.Logger.Level.FULL;

/**
 * Created by Oleg Shaburov on 12.11.2018
 * shaburov.o.a@gmail.com
 */
public class TestRailClientBuilder {

    public static <I extends TestRailAuthorizationInterceptor> TestRailClient build(I auth, String target) {
        return build(auth, target, new Logger.NoOpLogger(), FULL);
    }

    public static <I extends TestRailAuthorizationInterceptor> TestRailClient build(I auth, String target, Logger log) {
        return build(auth, target, log, FULL);
    }

    public static <I extends TestRailAuthorizationInterceptor> TestRailClient build(I auth,
                                                                                    String target,
                                                                                    Logger logger,
                                                                                    Logger.Level logLevel) {
        return new Feign.Builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(logger)
                .logLevel(logLevel)
                .requestInterceptor(auth)
                .target(TestRailClient.class, target);
    }

    /** Utility class. Prohibit instantiation. */
    private TestRailClientBuilder() { }

}

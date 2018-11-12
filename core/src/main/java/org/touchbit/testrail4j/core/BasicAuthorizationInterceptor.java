package org.touchbit.testrail4j.core;

import feign.RequestTemplate;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
public class BasicAuthorizationInterceptor extends TestRailAuthorizationInterceptor {

    private final String user;
    private final String pass;

    public BasicAuthorizationInterceptor(final String user, final String pass) {
        this.user = user;
        this.pass = pass;
    }

    @Override
    public void intercept(RequestTemplate template) {
        template.header("Authorization", "Basic " + Base64.getEncoder()
                .encodeToString((user + ":" + pass).getBytes(UTF_8)));
    }

}

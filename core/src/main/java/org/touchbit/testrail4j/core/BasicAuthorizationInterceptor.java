package org.touchbit.testrail4j.core;

import feign.RequestTemplate;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
public class BasicAuthorizationInterceptor extends TestRailAuthorizationInterceptor {

    private final String base64;

    public BasicAuthorizationInterceptor(final String authBase64UserPass) {
        this.base64 = authBase64UserPass;
    }

    public BasicAuthorizationInterceptor(final String user, final String pass) {
        this.base64 = Base64.getEncoder().encodeToString((user + ":" + pass).getBytes(UTF_8));
    }

    @Override
    public void intercept(RequestTemplate template) {
        template.header("Authorization", "Basic " + base64);
    }

}

package org.touchbit.testrail4j.core;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Created by Oleg Shaburov on 12.11.2018
 * shaburov.o.a@gmail.com
 */
public abstract class TestRailAuthorizationInterceptor implements RequestInterceptor {

    /**
     * Workaround for 838 defect in OpenFeign
     * See https://github.com/OpenFeign/feign/issues/838
     */
    @Override
    public final void apply(RequestTemplate template) {
        String url = template.url();
        if (!url.contains("/index.php%3F/")) {
            throw new IllegalArgumentException("Invalid url value. " +
                    "Expected contains '/index.php%3F/'. Received url: " + url);
        }
        int parameter = url.indexOf('=');
        int question = url.indexOf('?');
        if (question < parameter) {
            url = url.replaceFirst("\\?", "&");
        }
        String fixIssue838 = url.replace("index.php%3F", "index.php?");
        template.uri(fixIssue838);
        intercept(template);
    }

    public abstract void intercept(RequestTemplate template);

}

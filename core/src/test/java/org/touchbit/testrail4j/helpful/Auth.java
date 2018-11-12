package org.touchbit.testrail4j.helpful;

import org.touchbit.testrail4j.core.BasicAuthorizationInterceptor;

public class Auth extends BasicAuthorizationInterceptor {

    public Auth() {
        super("user", "pass");
    }

}

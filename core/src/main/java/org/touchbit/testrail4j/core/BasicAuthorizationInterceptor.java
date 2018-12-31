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

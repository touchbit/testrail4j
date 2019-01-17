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

package org.touchbit.testrail4j.core;

import feign.RequestTemplate;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Feign request interceptor for basic request authorization.
 * <p>
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
public class BasicAuth extends AuthInterceptor {

    /**
     * base64-encoded login:password or login:token.
     */
    private final String base64;

    /**
     * @param authBase64UserPass - base64-encoded login:password or login:token.
     */
    public BasicAuth(final String authBase64UserPass) {
        this.base64 = authBase64UserPass;
    }

    /**
     * @param login - user login (email).
     * @param passToken - user password or API token.
     */
    public BasicAuth(final String login, final String passToken) {
        this.base64 = Base64.getEncoder().encodeToString((login + ":" + passToken).getBytes(UTF_8));
    }

    @Override
    public void intercept(RequestTemplate template) {
        template.header("Authorization", "Basic " + base64);
    }

}

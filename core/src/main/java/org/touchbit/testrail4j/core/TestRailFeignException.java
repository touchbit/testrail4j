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

import feign.FeignException;
import feign.Response;
import feign.Util;

import static java.lang.String.format;

/**
 * Exception for {@link RestRailErrorDecoder}
 * <p>
 * Created by Oleg Shaburov on 12.01.2019
 * shaburov.o.a@gmail.com
 */
public class TestRailFeignException extends FeignException {

    private TestRailFeignException(int status, String message, byte[] content) {
        super(status, message, content);
    }

    public static TestRailFeignException errorStatus(String methodKey, Response response) {

        byte[] body = {};
        try {
            if (response.body() != null) {
                body = Util.toByteArray(response.body().asInputStream());
            }
        } catch (Exception ignored) { // NOPMD
        }

        String message = format("status %s reading %s", response.status(), methodKey);
        if (body.length != 0) {
            message += " message " + new String(body);
        }
        return new TestRailFeignException(response.status(), message, body);
    }

}

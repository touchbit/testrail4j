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

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.template.UriUtils;

/**
 * Created by Oleg Shaburov on 12.11.2018
 * shaburov.o.a@gmail.com
 */
public abstract class AuthInterceptor implements RequestInterceptor {

    /**
     * Workaround for 838 defect in OpenFeign
     * See https://github.com/OpenFeign/feign/issues/838
     */
    @Override
    public final void apply(RequestTemplate template) {
        String url = template.url();
        if (!UriUtils.isAbsolute(url)) {
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
        }
        intercept(template);
    }

    public abstract void intercept(RequestTemplate template);

}

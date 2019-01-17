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

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.template.UriUtils;

/**
 * This class was created to bypass the defect in the Feign http client and will be removed in the future.
 * For more information see issue https://github.com/OpenFeign/feign/issues/838
 * <p>
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

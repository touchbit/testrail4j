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

package org.touchbit.testrail4j.integration.config;

import com.beust.jcommander.Parameter;
import org.touchbit.buggy.core.config.PrimaryConfig;

/**
 * Test configuration for local testing with docker-compose
 *
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
public class Config implements PrimaryConfig {

    @Parameter(names = {"--http-port"}, description = "TestRail HTTP port")
    private static int http_port = 80;
    @Parameter(names = {"--https-port"}, description = "TestRail HTTP port")
    private static int https_port = 443;
    @Parameter(names = {"--host"}, description = "TestRail host")
    private static String host = "localhost";
    private static String auth = "dGVzdHJhaWxAdGVzdHJhaWwudGVzdHJhaWw6dGVzdHJhaWw=";

    public Config() {
        setPrintLogFile(true);
        setPrintSuite(true);
    }

    public static String getHttpHost() {
        return "http://" + host + ":" + http_port;
    }

    public static String getHttpsHost() {
        return "https://" + host + ":" + https_port;
    }

    public static String getAuth() {
        return auth;
    }

}

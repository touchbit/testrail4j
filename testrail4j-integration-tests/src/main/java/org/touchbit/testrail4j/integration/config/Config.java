/*
 * MIT License
 *
 * Copyright © 2020 TouchBIT.
 * Copyright © 2020 Oleg Shaburov.
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
import org.touchbit.testrail4j.integration.goals.TestRail;

/**
 * Test configuration for local testing with docker-compose
 *
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
public class Config implements PrimaryConfig {

    @Parameter(names = {"--port"}, description = "TestRail HTTP port")
    private static int port = 443;

    @Parameter(names = {"--host"}, description = "TestRail host", required = true)
    private static String host = "";

    @Parameter(names = {"--login"}, description = "TestRail host", required = true)
    private static String login = "";

    @Parameter(names = {"--password"}, description = "TestRail host", required = true)
    private static String password = "";

    @Parameter(names = {"--token"}, description = "TestRail host", required = true)
    private static String token = "";

    public Config() {
        setPrintLogFile(true);
        setPrintSuite(true);
        setServices(new TestRail().getServices());
    }

    public static String geHost() {
        return "https://" + host + ":" + port;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public static String getToken() {
        return token;
    }

}

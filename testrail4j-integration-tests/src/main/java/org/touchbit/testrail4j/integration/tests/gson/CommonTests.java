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

package org.touchbit.testrail4j.integration.tests.gson;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.buggy.feign.FeignCallLogger;
import org.touchbit.testrail4j.core.BasicAuth;
import org.touchbit.testrail4j.integration.config.Config;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Gson;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseGsonTest;
import org.touchbit.testrail4j.gson.feign.client.TestRailClient;
import org.touchbit.testrail4j.gson.feign.client.TestRailClientBuilder;

import java.util.Base64;

/**
 * Created by Oleg Shaburov on 07.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Gson.class, interfaze = API.class, task = "common_operations")
public class CommonTests extends BaseGsonTest {

    @Test(description = "Expecting successful authentication with SSL errors")
    @Details()
    public void test_20190119015452() {
        TestRailTestClient client = TestRailClientBuilder
                .build(new BasicAuth("testrail@testrail.testrail", "testrail"),
                        Config.getHost().replace("http", "https"),
                        TestRailTestClient.class,
                        new FeignCallLogger(log),
                        true);
        client.getProject();
    }

    @Test(description = "Expecting successful authentication with base64 string")
    @Details()
    public void test_20200107185620() {
        String auth = Base64.getEncoder()
                .encodeToString("testrail@testrail.testrail:1IhRVxFoYL0SFm2A6Wyq-Yv703Uawzs/PjmM1auBj".getBytes());
        TestRailTestClient client = TestRailClientBuilder
                .build(new BasicAuth(auth),
                        Config.getHost(),
                        TestRailTestClient.class,
                        new FeignCallLogger(log));
        client.getProject();
    }

    @Test(description = "Expecting successful authentication with login/password")
    @Details()
    public void test_20200107202118() {
        TestRailTestClient client = TestRailClientBuilder
                .build(new BasicAuth("testrail@testrail.testrail", "testrail"),
                        Config.getHost(),
                        TestRailTestClient.class,
                        new FeignCallLogger(log));
        client.getProject();
    }

    @Test(description = "Expecting successful authentication with login/token")
    @Details()
    public void test_20200107202205() {
        TestRailTestClient client = TestRailClientBuilder
                .build(new BasicAuth("testrail@testrail.testrail",
                                                         "1IhRVxFoYL0SFm2A6Wyq-Yv703Uawzs/PjmM1auBj"),
                        Config.getHost(),
                        TestRailTestClient.class,
                        new FeignCallLogger(log));
        client.getProject();
    }

    @Test(description = "Expecting successful call with base builder")
    @Details()
    public void test_20200107203936() {
        String login = "testrail@testrail.testrail";
        String pass = "testrail";
        TestRailClient client = TestRailClientBuilder.build(login, pass, Config.getHost());
        step("Get existing statuses");
        client.getStatuses();
    }

}

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

package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.buggy.feign.FeignCallLogger;
import org.touchbit.testrail4j.core.BasicAuth;
import org.touchbit.testrail4j.integration.config.Config;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.feign.client.TestRailClient;
import org.touchbit.testrail4j.jackson2.feign.client.TestRailClientBuilder;

import java.util.Base64;

/**
 * Created by Oleg Shaburov on 07.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "common_operations")
public class CommonTests extends BaseCorvusTest {

    @Test(description = "Expecting successful authentication with base64 string")
    @Details()
    public void test_20190107185620() {
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
    public void test_20190107202118() {
        TestRailTestClient client = TestRailClientBuilder
                .build(new BasicAuth("testrail@testrail.testrail", "testrail"),
                        Config.getHost(),
                        TestRailTestClient.class,
                        new FeignCallLogger(log));
        client.getProject();
    }

    @Test(description = "Expecting successful authentication with login/token")
    @Details()
    public void test_20190107202205() {
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
    public void test_20190107203936() {
        String login = "testrail@testrail.testrail";
        String pass = "testrail";
        TestRailClient client = TestRailClientBuilder.build(login, pass, Config.getHost());
        step("Get existing statuses");
        client.getStatuses();
    }

}

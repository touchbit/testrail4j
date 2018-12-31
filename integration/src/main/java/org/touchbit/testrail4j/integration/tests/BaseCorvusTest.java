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

import org.touchbit.buggy.core.test.BaseBuggyTest;
import org.touchbit.buggy.feign.FeignCallLogger;
import org.touchbit.testrail4j.core.BasicAuthorizationInterceptor;
import org.touchbit.testrail4j.integration.config.Config;
import org.touchbit.testrail4j.jackson2.feign.client.TestRailClient;
import org.touchbit.testrail4j.jackson2.feign.client.TestRailClientBuilder;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */

public class BaseCorvusTest extends BaseBuggyTest {

    protected static final TestRailClient CLIENT = TestRailClientBuilder
                    .build(new BasicAuthorizationInterceptor(Config.getAuth()),
                            Config.getHost(),
                            new FeignCallLogger(log));

}

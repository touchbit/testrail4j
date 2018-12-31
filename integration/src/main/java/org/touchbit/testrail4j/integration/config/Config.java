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

package org.touchbit.testrail4j.integration.config;

import org.touchbit.buggy.core.config.PrimaryConfig;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
public class Config implements PrimaryConfig {

    private static String host = "http://localhost";
    private static String auth = "dGVzdHJhaWxAdGVzdHJhaWwudGVzdHJhaWw6dGVzdHJhaWw=";

    public Config() {
        setPrintLogFile(true);
    }

    public static String getHost() {
        return host;
    }

    public static String getAuth() {
        return auth;
    }

}

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

package org.touchbit.testrail4j.jackson2.feign.client;

/**
 * The suite mode of the project
 * 1 for single suite mode,
 * 2 for single suite + baselines,
 * 3 for multiple suites
 * <p>
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
public enum SuiteMode {

    SINGLE (1),
    BASELINES (2),
    MULTIPLE (3),
    ;

    private long id;

    SuiteMode(long id) {
        this.id = id;
    }

    public long id() {
        return id;
    }

}

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

package org.touchbit.testrail4j.jackson2.feign.client.expander;

import feign.Param;
import org.touchbit.testrail4j.jackson2.feign.client.SuiteMode;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
public class SuiteExp implements Param.Expander {

    @Override
    public String expand(Object value) {
        if (value instanceof SuiteMode) {
            return String.valueOf(SuiteMode.valueOf(String.valueOf(value)).id());
        }
        throw new IllegalArgumentException("Value is not a SuiteMode: " + value);
    }

}
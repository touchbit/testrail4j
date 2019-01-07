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

package org.touchbit.testrail4j.core.query.filter;

import org.touchbit.testrail4j.core.type.Type;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
public abstract class BaseFilter {

    protected String toCommaSeparatedString(Number... array) {
        if (array == null || array.length == 0) {
            return null;
        }
        StringJoiner sj = new StringJoiner(",");
        for (Number s : array) {
            sj.add(String.valueOf(s));
        }
        return sj.toString();
    }

    protected String toCommaSeparatedString(Type... array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return toCommaSeparatedString(Arrays.stream(array).map(Type::getId).toArray(Number[]::new));
    }

    protected Integer booleanToInteger(boolean value) {
        return value ? 1 : 0;
    }

}

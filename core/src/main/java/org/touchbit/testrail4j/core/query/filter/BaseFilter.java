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
        if (array.length == 0) {
            return null;
        }
        StringJoiner sj = new StringJoiner(",");
        for (Number s : array) {
            sj.add(String.valueOf(s));
        }
        return sj.toString();
    }

    protected String toCommaSeparatedString(Type... array) {
        if (array.length == 0) {
            return null;
        }
        return toCommaSeparatedString(Arrays.stream(array).map(Type::getId).toArray(Number[]::new));
    }

    protected Integer booleanToInteger(boolean value) {
        return value ? 1 : 0;
    }

}

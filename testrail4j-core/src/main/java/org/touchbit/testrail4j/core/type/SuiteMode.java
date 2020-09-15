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

package org.touchbit.testrail4j.core.type;

/**
 * The suite mode of the project
 * 1 for single suite mode,
 * 2 for single suite + baselines,
 * 3 for multiple suites
 * <p>
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
public enum SuiteMode implements Type {

    SINGLE (1),
    BASELINES (2),
    MULTIPLE (3),
    ;

    private long id;

    SuiteMode(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

}

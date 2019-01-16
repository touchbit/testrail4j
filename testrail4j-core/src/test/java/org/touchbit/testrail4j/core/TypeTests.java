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

package org.touchbit.testrail4j.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.test.core.BaseUnitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.AutomationType.*;
import static org.touchbit.testrail4j.core.type.CaseTypes.*;
import static org.touchbit.testrail4j.core.type.FieldTypes.*;
import static org.touchbit.testrail4j.core.type.Priorities.*;
import static org.touchbit.testrail4j.core.type.Statuses.*;
import static org.touchbit.testrail4j.core.type.Templates.*;
import static org.touchbit.testrail4j.core.type.SuiteMode.*;

/**
 * Created by Oleg Shaburov on 07.01.2019
 * shaburov.o.a@gmail.com
 */
@DisplayName("Types clases tests")
class TypeTests extends BaseUnitTest {

    @Test
    @DisplayName("Check AutomationType enum")
    void unitTest_20190107215036() {
        assertThat(RANOREX.getId()).isEqualTo(1);
        assertThat(NONE.getId()).isEqualTo(0);
    }

    @Test
    @DisplayName("Check CaseTypes enum")
    void unitTest_20190107215200() {
        assertThat(ACCEPTANCE.getId()).isEqualTo(1);
        assertThat(ACCESSIBILITY.getId()).isEqualTo(2);
        assertThat(AUTOMATED.getId()).isEqualTo(3);
        assertThat(COMPATIBILITY.getId()).isEqualTo(4);
        assertThat(DESTRUCTIVE.getId()).isEqualTo(5);
        assertThat(FUNCTIONAL.getId()).isEqualTo(6);
        assertThat(OTHER.getId()).isEqualTo(7);
        assertThat(PERFORMANCE.getId()).isEqualTo(8);
        assertThat(REGRESSION.getId()).isEqualTo(9);
        assertThat(SECURITY.getId()).isEqualTo(10);
        assertThat(SMOKE .getId()).isEqualTo(11);
        assertThat(USABILITY.getId()).isEqualTo(12);
    }

    @Test
    @DisplayName("Check FieldTypes enum")
    void unitTest_20190107215304() {
        assertThat(STRING.getId()).isEqualTo(1);
        assertThat(INTEGER.getId()).isEqualTo(2);
        assertThat(TEXT.getId()).isEqualTo(3);
        assertThat(URL.getId()).isEqualTo(4);
        assertThat(CHECKBOX.getId()).isEqualTo(5);
        assertThat(DROPDOWN.getId()).isEqualTo(6);
        assertThat(USER.getId()).isEqualTo(7);
        assertThat(DATE.getId()).isEqualTo(8);
        assertThat(MILESTONE.getId()).isEqualTo(9);
        assertThat(STEPS.getId()).isEqualTo(10);
        assertThat(MULTI_SELECT.getId()).isEqualTo(12);
    }

    @Test
    @DisplayName("Check Priorities enum")
    void unitTest_20190107215402() {
        assertThat(LOW.getId()).isEqualTo(1);
        assertThat(MEDIUM.getId()).isEqualTo(2);
        assertThat(HIGH.getId()).isEqualTo(3);
        assertThat(CRITICAL.getId()).isEqualTo(4);
    }

    @Test
    @DisplayName("Check Statuses enum")
    void unitTest_20190107215442() {
        assertThat(PASSED.getId()).isEqualTo(1);
        assertThat(BLOCKED.getId()).isEqualTo(2);
        assertThat(UNTESTED.getId()).isEqualTo(3);
        assertThat(RETEST.getId()).isEqualTo(4);
        assertThat(FAILED.getId()).isEqualTo(5);
        assertThat(CUSTOM_STATUS1.getId()).isEqualTo(6);
        assertThat(CUSTOM_STATUS2.getId()).isEqualTo(7);
        assertThat(CUSTOM_STATUS3.getId()).isEqualTo(8);
        assertThat(CUSTOM_STATUS4.getId()).isEqualTo(9);
        assertThat(CUSTOM_STATUS5.getId()).isEqualTo(10);
        assertThat(CUSTOM_STATUS6.getId()).isEqualTo(11);
        assertThat(CUSTOM_STATUS7.getId()).isEqualTo(12);
    }

    @Test
    @DisplayName("Check Templates enum")
    void unitTest_20190107215524() {
        assertThat(TEST_CASE_TEXT.getId()).isEqualTo(1);
        assertThat(TEST_CASE_STEPS.getId()).isEqualTo(2);
        assertThat(EXPLORATORY_SESSION.getId()).isEqualTo(3);
    }

    @Test
    @DisplayName("Check SuiteMode enum")
    void unitTest_20190107215904() {
        assertThat(SINGLE.getId()).isEqualTo(1);
        assertThat(BASELINES.getId()).isEqualTo(2);
        assertThat(MULTIPLE.getId()).isEqualTo(3);
    }

}

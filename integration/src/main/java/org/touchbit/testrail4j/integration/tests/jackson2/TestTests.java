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

package org.touchbit.testrail4j.integration.tests.jackson2;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.core.query.filter.GetTestsFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseCorvusTest;
import org.touchbit.testrail4j.jackson2.model.*;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.Statuses.*;
import static org.touchbit.testrail4j.core.type.Statuses.CUSTOM_STATUS6;
import static org.touchbit.testrail4j.core.type.Statuses.CUSTOM_STATUS7;
import static org.touchbit.testrail4j.core.type.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "test_operations")
public class TestTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful receive of existing tests")
    @Details()
    public void test_20190102023252() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRSection section = J2_CLIENT.addSection(project);
        J2_CLIENT.addCase(section);
        TRRun run = J2_CLIENT.addRun(project);
        List<TRTest> tests = J2_CLIENT.getTests(run);
        assertThat(tests).isNotEmpty();
        for (TRTest test : tests) {
            test = J2_CLIENT.getTest(test);
            assertThat(test).isNotNull();
            assertThat(test.getAdditionalProperties()).isEmpty();
            if (test.getCustomStepsSeparated() != null) {
                for (TRStep trStep : test.getCustomStepsSeparated()) {
                    assertThat(trStep.getAdditionalProperties()).isEmpty();
                }
            }
        }
    }

    @Test(description = "Expecting a successful receive of tests with filter")
    @Details()
    public void test_20190107184213() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRSection section = J2_CLIENT.addSection(project);
        J2_CLIENT.addCase(section);
        TRRun run = J2_CLIENT.addRun(project);
        List<TRTest> tests = J2_CLIENT.getTests(run, new GetTestsFilter()
                .withStatusId(PASSED, BLOCKED, UNTESTED, RETEST, FAILED, CUSTOM_STATUS1, CUSTOM_STATUS2,
                CUSTOM_STATUS3, CUSTOM_STATUS4, CUSTOM_STATUS5, CUSTOM_STATUS6, CUSTOM_STATUS7));
        assertThat(tests).isNotEmpty();
        for (TRTest test : tests) {
            test = J2_CLIENT.getTest(test);
            assertThat(test).isNotNull();
            assertThat(test.getAdditionalProperties()).isEmpty();
            if (test.getCustomStepsSeparated() != null) {
                for (TRStep trStep : test.getCustomStepsSeparated()) {
                    assertThat(trStep.getAdditionalProperties()).isEmpty();
                }
            }
        }
    }

}

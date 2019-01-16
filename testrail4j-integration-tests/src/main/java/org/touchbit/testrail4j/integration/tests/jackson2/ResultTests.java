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
import org.touchbit.testrail4j.core.query.filter.GetResultsFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseJackson2Test;
import org.touchbit.testrail4j.jackson2.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.Statuses.*;
import static org.touchbit.testrail4j.core.type.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "result_operations")
public class ResultTests extends BaseJackson2Test {

    @Test(description = "Expecting successful adding result for test")
    @Details()
    public void test_20190102011809() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(project);
        CLIENT.addCase(section);
        TRRun run = CLIENT.addRun(project);
        List<TRTest> trTests = CLIENT.getTests(run);
        for (TRTest trTest : trTests) {
            assertThat(trTest.getAdditionalProperties()).isEmpty();
            if (trTest.getCustomStepsSeparated() != null) {
                for (TRStep trStep : trTest.getCustomStepsSeparated()) {
                    assertThat(trStep.getAdditionalProperties()).isEmpty();
                }
            }
            TRResult result = new TRResult().withStatusId(FAILED.getId());
            TRResult actResult = CLIENT.addResult(result, trTest);
            assertThat(actResult.getStatusId()).isEqualTo(result.getStatusId());
            assertThat(actResult.getAdditionalProperties()).isEmpty();
            if (actResult.getCustomStepResults() != null) {
                for (TRStep trStep : actResult.getCustomStepResults()) {
                    assertThat(trStep.getAdditionalProperties()).isEmpty();
                }
            }
        }
    }

    @Test(description = "Expecting successful adding result for test case")
    @Details()
    public void test_20190105231426() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(project);
        TRCase caze = CLIENT.addCase(section);
        TRRun run = CLIENT.addRun(project);
        TRResult result = new TRResult().withStatusId(FAILED.getId());
        TRResult actResult = CLIENT.addResultForCase(result, run, caze);
        assertThat(actResult.getStatusId()).isEqualTo(result.getStatusId());
    }

    @Test(description = "Expecting successful adding results for test run")
    @Details()
    public void test_20190105234446() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(project);
        CLIENT.addCase(section);
        TRRun run = CLIENT.addRun(project);
        List<TRResult> resultList = new ArrayList<>();
        List<TRTest> trTests = CLIENT.getTests(run);
        for (TRTest trTest : trTests) {
            TRResult result = new TRResult().withStatusId(FAILED.getId()).withTestId(trTest.getId());
            resultList.add(result);
        }
        TRResults results = new TRResults().withResults(resultList);
        List<TRResult> actResult = CLIENT.addResults(results, run);
        assertThat(actResult).isNotEmpty();
    }

    @Test(description = "Expecting successful adding results for test cases")
    @Details()
    public void test_20190105235310() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(project);
        TRCase caze = CLIENT.addCase(section);
        TRRun run = CLIENT.addRun(project);
        List<TRResult> resultList = new ArrayList<>();
        TRResult result = new TRResult().withStatusId(FAILED.getId()).withCaseId(caze.getId());
        resultList.add(result);
        TRResults results = new TRResults().withResults(resultList);
        List<TRResult> actResult = CLIENT.addResultsForCases(results, run);
        assertThat(actResult).isNotEmpty();
    }

    @Test(description = "Expecting successful reserve test results for test run")
    @Details()
    public void test_20190106002859() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(project);
        TRCase caze = CLIENT.addCase(section);
        TRRun run = CLIENT.addRun(project);
        List<TRResult> resultList = new ArrayList<>();
        TRResult result = new TRResult().withStatusId(FAILED.getId()).withCaseId(caze.getId());
        resultList.add(result);
        TRResults results = new TRResults().withResults(resultList);
        List<TRResult> expResult = CLIENT.addResultsForCases(results, run);

        List<TRResult> actResult = CLIENT.getResultsForRun(run);
        assertThat(actResult).isEqualTo(expResult);

        actResult = CLIENT.getResultsForRun(run, new GetResultsFilter()
                .withStatusId(PASSED, BLOCKED, UNTESTED, RETEST, FAILED, CUSTOM_STATUS1, CUSTOM_STATUS2,
                        CUSTOM_STATUS3, CUSTOM_STATUS4, CUSTOM_STATUS5, CUSTOM_STATUS6, CUSTOM_STATUS7)
                .withCreatedAfter(500000000)
                .withCreatedBefore(500000000)
                .withCreatedBy(2, 1, 3, 4)
                .withLimit(123)
                .withOffset(123L)
        );
        assertThat(actResult).isEmpty();
    }

    @Test(description = "Expecting successful reserve test results for test case")
    @Details()
    public void test_20190106003350() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(project);
        TRCase caze = CLIENT.addCase(section);
        TRRun run = CLIENT.addRun(project);
        List<TRResult> resultList = new ArrayList<>();
        TRResult result = new TRResult().withStatusId(FAILED.getId()).withCaseId(caze.getId());
        resultList.add(result);
        TRResults results = new TRResults().withResults(resultList);
        List<TRResult> expResult = CLIENT.addResultsForCases(results, run);
        List<TRResult> actResult = CLIENT.getResultsForCase(run, caze);
        assertThat(actResult).isEqualTo(expResult);
        actResult = CLIENT.getResultsForCase(run, caze, new GetResultsFilter().withStatusId(PASSED.getId()));
        assertThat(actResult).isEmpty();
    }

    @Test(description = "Expecting successful reserve test results for test")
    @Details()
    public void test_20190106003453() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(project);
        TRCase caze = CLIENT.addCase(section);
        TRRun run = CLIENT.addRun(project);
        List<TRResult> resultList = new ArrayList<>();
        TRResult result = new TRResult().withStatusId(FAILED.getId()).withCaseId(caze.getId());
        resultList.add(result);
        TRResults results = new TRResults().withResults(resultList);
        List<TRResult> expResult = CLIENT.addResultsForCases(results, run);
        List<TRTest> trTests = CLIENT.getTests(run);
        for (TRTest trTest : trTests) {
            List<TRResult> actResult = CLIENT.getResults(trTest);
            assertThat(actResult).isEqualTo(expResult);
            actResult = CLIENT.getResults(trTest, new GetResultsFilter().withStatusId(PASSED.getId()));
            assertThat(actResult).isEmpty();
        }
    }

}

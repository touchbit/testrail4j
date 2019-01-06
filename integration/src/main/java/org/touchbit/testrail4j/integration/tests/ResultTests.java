package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.core.query.GetResultsQueryMap;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.Statuses.FAILED;
import static org.touchbit.testrail4j.core.type.Statuses.PASSED;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "result_operations")
public class ResultTests extends BaseCorvusTest {

    @Test(description = "Expecting successful adding result for test")
    @Details()
    public void test_20190102011809() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(project);
        CLIENT.addCase(section);
        TRRun run = CLIENT.addRun(project);
        List<TRTest> trTests = CLIENT.getTests(run);
        for (TRTest trTest : trTests) {
            TRResult result = new TRResult().withStatusId(FAILED.getId());
            TRResult actResult = CLIENT.addResult(result, trTest);
            assertThat(actResult.getStatusId()).isEqualTo(result.getStatusId());
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
        actResult = CLIENT.getResultsForRun(run, new GetResultsQueryMap().withStatusId(PASSED.getId()));
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
        actResult = CLIENT.getResultsForCase(run, caze, new GetResultsQueryMap().withStatusId(PASSED.getId()));
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
            actResult = CLIENT.getResults(trTest, new GetResultsQueryMap().withStatusId(PASSED.getId()));
            assertThat(actResult).isEmpty();
        }
    }

}

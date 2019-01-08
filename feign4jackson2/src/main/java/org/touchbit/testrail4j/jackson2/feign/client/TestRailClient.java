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

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.touchbit.testrail4j.core.query.*;
import org.touchbit.testrail4j.core.query.filter.*;
import org.touchbit.testrail4j.core.type.FieldTypes;
import org.touchbit.testrail4j.jackson2.model.*;

import java.util.Arrays;
import java.util.List;

/**
 * This class explains how to access and use TestRail's API
 * to integrate automated tests, submit test results
 * and automate various aspects of TestRail.
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-results">API: Results</a>
 *      {@link TestRailClient#getResults(Long, GetResultsQueryMap)}
 *      {@link TestRailClient#getResultsForCase(Long, Long, GetResultsQueryMap)}
 *      {@link TestRailClient#getResultsForRun(Long)}
 *      {@link TestRailClient#getResultsForRun(Long, GetResultsQueryMap)}
 *      {@link TestRailClient#addResult(TRResult, Long)}
 *      {@link TestRailClient#addResults(TRResults, Long)}
 *      {@link TestRailClient#addResultForCase(TRResult, Long, Long)}
 *      {@link TestRailClient#addResultsForCases(TRResults, Long)}
 *      Default
 *      {@link TestRailClient#getResults(Long)}
 *      {@link TestRailClient#getResults(TRTest)}
 *      {@link TestRailClient#getResults(TRTest, GetResultsQueryMap)}
 *      {@link TestRailClient#getResultsForCase(Long, Long)}
 *      {@link TestRailClient#getResultsForCase(TRRun, TRCase)}
 *      {@link TestRailClient#getResultsForCase(TRRun, TRCase, GetResultsQueryMap)}
 *      {@link TestRailClient#getResultsForRun(TRRun)}
 *      {@link TestRailClient#getResultsForRun(TRRun, GetResultsQueryMap)}
 *      {@link TestRailClient#addResult(TRResult, TRTest)}
 *      {@link TestRailClient#addResults(TRResults, TRRun)}
 *      {@link TestRailClient#addResultForCase(TRResult, TRRun, TRCase)}
 *      {@link TestRailClient#addResultsForCases(TRResults, TRRun)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases">API: Cases</a>
 *      {@link TestRailClient#getCase(Long)}
 *      {@link TestRailClient#getCases(Long, GetCasesQueryMap)}
 *      {@link TestRailClient#addCase(TRCase, Long)}
 *      {@link TestRailClient#updateCase(TRCase, Long)}
 *      {@link TestRailClient#deleteCase(Long)}
 *      Default
 *      {@link TestRailClient#getCase(TRCase)}
 *      {@link TestRailClient#getCases(TRProject)}
 *      {@link TestRailClient#getCases(TRProject, GetCasesQueryMap)}
 *      {@link TestRailClient#getCases(Long)}
 *      {@link TestRailClient#addCase(TRCase, TRSection)}
 *      {@link TestRailClient#updateCase(TRCase)}
 *      {@link TestRailClient#deleteCase(TRCase)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-projects">API: Projects</a>
 *      {@link TestRailClient#getProject(Long)}
 *      {@link TestRailClient#getProjects(GetProjectsQueryMap)}
 *      {@link TestRailClient#addProject(TRProject)}
 *      {@link TestRailClient#updateProject(TRProject, Long)}
 *      {@link TestRailClient#deleteProject(Long)}
 *      Default
 *      {@link TestRailClient#getProject(TRProject)}
 *      {@link TestRailClient#getProjects()}
 *      {@link TestRailClient#updateProject(TRProject)}
 *      {@link TestRailClient#deleteProject(TRProject)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-runs#get_run">API: Get run</a>
 *      {@link TestRailClient#getRun(Long)}
 *      {@link TestRailClient#getRuns(Long, GetRunsQueryMap)}
 *      {@link TestRailClient#addRun(TRRun, Long)}
 *      {@link TestRailClient#updateRun(TRRun, Long)}
 *      {@link TestRailClient#closeRun(Long)}
 *      {@link TestRailClient#deleteRun(Long)}
 *      Default
 *      {@link TestRailClient#getRun(TRRun)}
 *      {@link TestRailClient#getRuns(Long)}
 *      {@link TestRailClient#getRuns(TRProject)}
 *      {@link TestRailClient#getRuns(TRProject, GetRunsQueryMap)}
 *      {@link TestRailClient#addRun(TRRun, TRProject)}
 *      {@link TestRailClient#updateRun(TRRun)}
 *      {@link TestRailClient#closeRun(TRRun)}
 *      {@link TestRailClient#deleteRun(TRRun)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-suites">API: Suites</a>
 *      {@link TestRailClient#getSuite(Long)}
 *      {@link TestRailClient#getSuites(Long)}
 *      {@link TestRailClient#addSuite(TRSuite, Long)}
 *      {@link TestRailClient#updateSuite(TRSuite, Long)}
 *      {@link TestRailClient#deleteSuite(Long)}
 *      Default
 *      {@link TestRailClient#getSuite(TRSuite)}
 *      {@link TestRailClient#getSuites(TRProject)}
 *      {@link TestRailClient#addSuite(TRSuite, TRProject)}
 *      {@link TestRailClient#updateSuite(TRSuite)}
 *      {@link TestRailClient#deleteSuite(TRSuite)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-sections">API: Sections</a>
 *      {@link TestRailClient#addSection(TRSection, Long)}
 *      {@link TestRailClient#getSection(Long)}
 *      {@link TestRailClient#getSections(Long, GetSectionsQueryMap)}
 *      {@link TestRailClient#updateSection(TRSection, Long)}
 *      {@link TestRailClient#deleteSection(Long)}
 *      Default
 *      {@link TestRailClient#addSection(TRSection, TRProject)}
 *      {@link TestRailClient#getSection(TRSection)}
 *      {@link TestRailClient#getSections(Long)}
 *      {@link TestRailClient#getSections(TRProject)}
 *      {@link TestRailClient#getSections(TRProject, GetSectionsQueryMap)}
 *      {@link TestRailClient#updateSection(TRSection)}
 *      {@link TestRailClient#deleteSection(TRSection)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-tests">API: Tests</a>
 *      {@link TestRailClient#getTest(Long)}
 *      {@link TestRailClient#getTests(Long, GetTestsQueryMap)}
 *      Default
 *      {@link TestRailClient#getTest(TRTest)}
 *      {@link TestRailClient#getTests(Long)}
 *      {@link TestRailClient#getTests(TRRun)}
 *      {@link TestRailClient#getTests(TRRun, GetTestsQueryMap)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases-fields">API: Case fields</a>
 *      {@link TestRailClient#getCaseFields()}
 *      {@link TestRailClient#addCaseField(TRCaseField)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases-types">API: Case types</a>
 *      {@link TestRailClient#getCaseTypes()}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-configs">API: Configs</a>
 *      {@link TestRailClient#getConfigs(Long)}
 *      {@link TestRailClient#addConfigGroup(TRProjectConfigGroup, Long)}
 *      {@link TestRailClient#updateConfigGroup(TRProjectConfigGroup, Long)}
 *      {@link TestRailClient#deleteConfigGroup(Long)}
 *      {@link TestRailClient#addConfig(TRProjectConfig, Long)}
 *      {@link TestRailClient#updateConfig(TRProjectConfig, Long)}
 *      {@link TestRailClient#deleteConfig(Long)}
 *      Default
 *      {@link TestRailClient#getConfigs(TRProject)}
 *      {@link TestRailClient#addConfigGroup(TRProjectConfigGroup, TRProject)}
 *      {@link TestRailClient#updateConfigGroup(TRProjectConfigGroup)}
 *      {@link TestRailClient#deleteConfigGroup(TRProjectConfigGroup)}
 *      {@link TestRailClient#addConfig(TRProjectConfig, TRProjectConfigGroup)}
 *      {@link TestRailClient#updateConfig(TRProjectConfig)}
 *      {@link TestRailClient#deleteConfig(TRProjectConfig)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-milestones">API: Milestones</a>
 *      {@link TestRailClient#getMilestone(Long)}
 *      {@link TestRailClient#getMilestones(Long)}
 *      {@link TestRailClient#addMilestone(TRMilestone, Long)}
 *      {@link TestRailClient#updateMilestone(TRMilestone, Long)}
 *      {@link TestRailClient#deleteMilestone(Long)}
 *      Default
 *      {@link TestRailClient#getMilestone(TRMilestone)}
 *      {@link TestRailClient#getMilestones(TRProject)}
 *      {@link TestRailClient#getMilestones(Long, GetMilestonesQueryMap)}
 *      {@link TestRailClient#getMilestones(TRProject, GetMilestonesQueryMap)}
 *      {@link TestRailClient#addMilestone(TRMilestone, TRProject)}
 *      {@link TestRailClient#updateMilestone(TRMilestone)}
 *      {@link TestRailClient#deleteMilestone(TRMilestone)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-priorities">API: Priorities</a>
 *      {@link TestRailClient#getPriorities()}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-users">API: Users</a>
 *      {@link TestRailClient#getUsers()}
 *      {@link TestRailClient#getUser(Long)}
 *      {@link TestRailClient#getUserByEmail(String)}
 *      Default
 *      {@link TestRailClient#getUser(TRUser)}
 *      {@link TestRailClient#getUserByEmail(TRUser)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-templates">API: Templates</a>
 *      {@link TestRailClient#getTemplates(Long)}
 *      Default
 *      {@link TestRailClient#getTemplates(TRProject)}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-statuses">API: Statuses</a>
 *      {@link TestRailClient#getStatuses()}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-results-fields">API: Results fields</a>
 *      {@link TestRailClient#getResultFields()}
 *
 * @see <a href="http://docs.gurock.com/testrail-api2/reference-plans">API: Plans</a>
 *      {@link TestRailClient#getPlan(Long)}
 *      {@link TestRailClient#getPlans(Long)}
 *      {@link TestRailClient#addPlan(TRPlan, Long)}
 *      {@link TestRailClient#updatePlan(TRPlan, Long)}
 *      {@link TestRailClient#closePlan(Long)}
 *      {@link TestRailClient#deletePlan(Long)}
 *      {@link TestRailClient#addPlanEntry(TRPlanEntry, Long)}
 *      {@link TestRailClient#updatePlanEntry(TRPlanEntry, Long, String)}
 *      {@link TestRailClient#deletePlanEntry(Long, String)}
 *      Default
 *      {@link TestRailClient#getPlan(TRPlan)}
 *      {@link TestRailClient#getPlans(TRProject)}
 *      {@link TestRailClient#getPlans(Long, GetPlansQueryMap)}
 *      {@link TestRailClient#getPlans(TRProject, GetPlansQueryMap)}
 *      {@link TestRailClient#addPlan(TRPlan, TRProject)}
 *      {@link TestRailClient#updatePlan(TRPlan)}
 *      {@link TestRailClient#closePlan(TRPlan)}
 *      {@link TestRailClient#deletePlan(TRPlan)}
 *      {@link TestRailClient#addPlanEntry(TRPlanEntry, TRPlan)}
 *      {@link TestRailClient#addPlanEntry(TRPlanEntry, TRPlan, TRRun...)}
 *      {@link TestRailClient#addPlanEntry(TRPlanEntry, TRPlan, TRSuite, TRRun...)}
 *      {@link TestRailClient#updatePlanEntry(TRPlanEntry, TRPlan)}
 *      {@link TestRailClient#deletePlanEntry(TRPlan, TRPlanEntry)}
 *
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Headers("Content-Type: application/json; charset=utf-8")
public interface TestRailClient {

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results#get_results">API: Get results</a>
     *
     * Get the latest 10 results for test with ID 1 and statuses 4 or 5 (Retest, Failed)
     * GET /index.php?/api/v2/get_results/1&status_id=4,5&limit=10
     *
     * @param testID is the ID of the test
     * @param queryMap is the request filter. See {@link GetResultsQueryMap}
     *                 The latest 10 results for test with ID 1 and statuses 4 or 5 (Retest, Failed)
     *                 GET index.php?/api/v2/get_results/1&status_id=4,5&limit=10
     *
     *
     * @return a list of test {@link TRResult}s for a test.
     * Custom fields are also included in the response and use their system name prefixed with 'custom_'
     * as their field identifier. Please see add_result for a full list of available custom field types.
     * [
     *   {
     *     "assignedto_id": 1,
     *     "comment": "This test failed: ..",
     *     "created_by": 1,
     *     "created_on": 1393851801,
     *     "defects": "TR-1",
     *     "elapsed": "5m",
     *     "id": 1,
     *     "status_id": 5,
     *     "test_id": 1,
     *     "version": "1.0RC1",
     *     "custom_step_results": [
     *       {
     *         "content": "Step 1",
     *         "expected": "Expected Result 1",
     *         "actual": "Actual Result 1",
     *         "status_id": 1
     *       }
     *     ]
     *   }
     * ]
     * @apiNote Response codes
     * 200	Success, the test results are returned as part of the response
     * 400	Invalid or unknown test
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_results/{test_id}")
    List<TRResult> getResults(@Param("test_id") Long testID, @QueryMap GetResultsQueryMap queryMap);

    /**
     * See {@link TestRailClient#getResults(Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResults(TRTest trTest, GetResultsQueryMap queryMap) {
        return getResults(trTest.getId(), queryMap);
    }

    /**
     * See {@link TestRailClient#getResults(Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResults(Long testID) {
        return getResults(testID, new GetResultsFilter());
    }

    /**
     * See {@link TestRailClient#getResults(Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResults(TRTest trTest) {
        return getResults(trTest.getId(), new GetResultsFilter());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results#get_results_for_case">API: Get results for case</a>
     *
     * Returns a list of test results for a test run and case combination.
     *
     * The difference to get_results is that this method expects a test run + test case instead of a test.
     * In TestRail, tests are part of a test run and the test cases are part of the related test suite.
     * So, when you create a new test run, TestRail creates a test for each test case found in the test
     * suite of the run. You can therefore think of a test as an “instance” of a test case which can
     * have test results, comments and a test status. Please also see TestRail's getting started guide
     * for more details about the differences between test cases and tests.
     *
     * All results for test run with ID 1 and test case with ID 2
     * GET index.php?/api/v2/get_results_for_case/1/2
     *
     * @param runID is the ID of the test run
     * @param caseID is the ID of the test case
     * @param queryMap is the request filter. See {@link GetResultsQueryMap}
     *
     * @return This method uses the same response format as {@link TestRailClient#getResults(Long, GetResultsQueryMap)}.
     *
     * @apiNote Response codes
     * 200	Success, the test results are returned as part of the response
     * 400	Invalid or unknown test run or case
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_results_for_case/{run_id}/{case_id}")
    List<TRResult> getResultsForCase(@Param("run_id") Long runID,
                                                                    @Param("case_id") Long caseID,
                                                                    @QueryMap GetResultsQueryMap queryMap);

    /**
     * See {@link TestRailClient#getResultsForCase(Long, Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResultsForCase(Long runID, Long caseID) {
        return getResultsForCase(runID, caseID, new GetResultsFilter());
    }

    /**
     * See {@link TestRailClient#getResultsForCase(Long, Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResultsForCase(TRRun run, TRCase caze) {
        return getResultsForCase(run.getId(), caze.getId(), new GetResultsFilter());
    }

    /**
     * See {@link TestRailClient#getResultsForCase(Long, Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResultsForCase(TRRun run, TRCase caze, GetResultsQueryMap queryMap) {
        return getResultsForCase(run.getId(), caze.getId(), queryMap);
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results#get_results_for_run">API: Get results for run</a>
     *
     * Returns a list of test results for a test run.
     *
     * @param runID is the ID of the test run
     * @param queryMap is the request filter. See {@link GetResultsQueryMap}
     *
     * @return a list of test {@link TRResult}s for a test run.
     *
     * @apiNote Response codes
     * 200	Success, the test results are returned as part of the response
     * 400	Invalid or unknown test run
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_results_for_run/{run_id}")
    List<TRResult> getResultsForRun(@Param("run_id") Long runID, @QueryMap GetResultsQueryMap queryMap);

    /**
     * See {@link TestRailClient#getResultsForRun(Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResultsForRun(TRRun run, GetResultsQueryMap queryMap) {
        return getResultsForRun(run.getId(), queryMap);
    }

    /**
     * See {@link TestRailClient#getResultsForRun(Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResultsForRun(Long runID) {
        return getResultsForRun(runID, new GetResultsFilter());
    }

    /**
     * See {@link TestRailClient#getResultsForRun(Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResultsForRun(TRRun run) {
        return getResultsForRun(run.getId(), new GetResultsFilter());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results#add_result">API: Add result</a>
     *
     * Adds a new test result, comment or assigns a test.
     * It's recommended to use add_results instead if you plan to add results for multiple tests.
     *
     * Request example
     * @implNote Also see the following example which shows how to submit step results with
     * the <a href="http://docs.gurock.com/testrail-faq/config-steps">structured steps custom field</a>
     * {
     * 	"status_id": 5,
     * 	"comment": "This test failed",
     * 	"elapsed": "15s",
     * 	"defects": "TR-7",
     * 	"version": "1.0 RC1 build 3724",
     * 	"custom_step_results": [
     * 		{
     * 			"content": "Step 1",
     * 			"expected": "Expected Result 1",
     * 			"actual": "Actual Result 1",
     * 			"status_id": 1
     * 		}
     * 	]
     * }
     *
     * @param testID is the ID of the test the result should be added to
     *
     * @return the new test {@link TRResult} using the same response format as
     *         {@link TestRailClient#getResults(Long, GetResultsQueryMap)},
     *         but with a single result instead of a list of results.
     *
     * @apiNote Response codes
     * 200	Success, the test result was created and is returned as part of the response
     * 400	Invalid or unknown test
     * 403	No permissions to add test results or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_result/{test_id}")
    TRResult addResult(TRResult result, @Param("test_id") Long testID);

    /**
     * See {@link TestRailClient#addResult(TRResult, Long)}
     */
    default TRResult addResult(TRResult result, TRTest test) {
        return addResult(result, test.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results#add_result_for_case">API: Add result for case</a>
     *
     * Adds a new test result, comment or assigns a test (for a test run and case combination).
     * It's recommended to use {@link TestRailClient#addResultsForCases(TRResults, Long)}
     * instead if you plan to add results for multiple test cases.
     *
     * The difference to {@link TestRailClient#addResult(TRResult, Long)} is that this method expects a test run +
     * test case instead of a test. In TestRail, tests are part of a test run and the test cases are part of the
     * related test suite. So, when you create a new test run, TestRail creates a test for each test case found in
     * the test suite of the run. You can therefore think of a test as an “instance” of a test case which can have test
     * results, comments and a test status. Please also see TestRail's getting started guide for more details
     * about the differences between test cases and tests.
     *
     * @param runID is the ID of the test run
     * @param caseID is the ID of the test case
     *
     * @return the new test {@link TRResult} using the same response format as
     *         {@link TestRailClient#getResults(Long, GetResultsQueryMap)},
     *         but with a single result instead of a list of results.
     *
     * @apiNote Response codes
     * 200	Success, the test result was created and is returned as part of the response
     * 400	Invalid or unknown test run or case
     * 403	No permissions to add test results or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_result_for_case/{run_id}/{case_id}")
    TRResult addResultForCase(TRResult result, @Param("run_id") Long runID, @Param("case_id") Long caseID);

    /**
     * See {@link TestRailClient#addResultForCase(TRResult, Long, Long)}
     */
    default TRResult addResultForCase(TRResult result, TRRun run, TRCase caze) {
        return addResultForCase(result, run.getId(), caze.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results#add_results">API: Add results</a>
     *
     * Adds one or more new test results, comments or assigns one or more tests.
     * Ideal for test automation to bulk-add multiple test results in one step.
     * This method expects an array of test results (via the 'results' field, please see below).
     * Each test result must specify the test ID and can pass in the same fields
     * as {@link TestRailClient#addResult(TRResult, Long)}, namely all test related system and custom fields.
     *
     * Please note that all referenced tests must belong to the same test run.
     *
     * The following listing shows a typical example request.
     * In addition to the test, you need to specify at least one of the
     * status, comment or assignee fields for each result.
     *
     * {
     * 	"results": [
     * 		{
     * 			"test_id": 101,
     * 			"status_id": 5,
     * 			"comment": "This test failed",
     * 			"defects": "TR-7"
     *
     * 		}
     * 	]
     * }
     *
     * @param runID is ID of the test run the results should be added to
     *
     * @return the new test {@link TRResult}s using the same response format
     * as {@link TestRailClient#getResults(Long, GetResultsQueryMap)} and in the same order as the list of the request.
     *
     * @apiNote Response codes
     * 200	Success, the test results were created and are returned as part of the response
     * 400	Invalid or unknown test run/tests
     * 403	No permissions to add test results or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_results/{run_id}")
    List<TRResult> addResults(TRResults results, @Param("run_id") Long runID);

    /**
     * See {@link TestRailClient#addResults(TRResults, Long)}
     */
    default List<TRResult> addResults(TRResults results, TRRun run) {
        return addResults(results, run.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results#add_results_for_cases">API: Add results for cases</a>
     *
     * Adds one or more new test results, comments or assigns one or more tests (using the case IDs).
     * Ideal for test automation to bulk-add multiple test results in one step.
     *
     * This method expects an array of test results (via the 'results' field, please see below).
     * Each test result must specify the test case ID and can pass in the same fields
     * as {@link TestRailClient#addResult(TRResult, Long)}, namely all test related system and custom fields.
     *
     * The difference to add_results is that this method expects test case IDs instead of test IDs.
     * Please see {@link TestRailClient#addResultForCase(TRResult, Long, Long)} for details.
     *
     * Please note that all referenced tests must belong to the same test run.
     *
     * The following listing shows a typical example request.
     * In addition to the test case, you need to specify at least one of the status,
     * comment or assignee fields for each result.
     *
     * {
     * 	"results": [
     * 		{
     * 			"case_id": 1,
     * 			"status_id": 5,
     * 			"comment": "This test failed",
     * 			"defects": "TR-7"
     *
     * 		}
     * 	]
     * }
     *
     * @param runID the test run the results should be added to
     *
     * @return the new test {@link TRResult}s using the same response format
     * as {@link TestRailClient#getResults(Long, GetResultsQueryMap)} and in the same order as the list of the request.
     *
     * @apiNote Response codes
     * 200	Success, the test results were created and are returned as part of the response
     * 400	Invalid or unknown test run/cases
     * 403	No permissions to add test results or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_results_for_cases/{run_id}")
    List<TRResult> addResultsForCases(TRResults results, @Param("run_id") Long runID);

    /**
     * See {@link TestRailClient#addResultsForCases(TRResults, Long)}
     */
    default List<TRResult> addResultsForCases(TRResults results, TRRun run) {
        return addResultsForCases(results, run.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases#get_case">API: Get case</a>
     *
     * @param caseID is the ID of the test case
     *
     * @return an existing test case.
     * {
     *   "created_by": 5,
     *   "created_on": 1392300984,
     *   "estimate": "1m 5s",
     *   "estimate_forecast": null,
     *   "id": 1,
     *   "milestone_id": 7,
     *   "priority_id": 2,
     *   "refs": "RF-1, RF-2",
     *   "section_id": 1,
     *   "suite_id": 1,
     *   "title": "Change document attributes (author, title, organization)",
     *   "type_id": 4,
     *   "updated_by": 1,
     *   "updated_on": 1393586511
     * }
     * @apiNote Response codes
     *
     * */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_case/{case_id}")
    TRCase getCase(@Param("case_id") Long caseID);

    /**
     * See {@link TestRailClient#getCase(Long)}
     */
    default TRCase getCase(TRCase caze) {
        return getCase(caze.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases#get_cases">API: Get cases</a>
     *
     *
     * For example get all test cases for project with ID 1, suite with ID 2 and priority 3 or 4
     * GET /index.php?/api/v2/get_cases/1&suite_id=2&priority_id=3,4
     *
     * @param projectID is the ID of the project
     * @param queryMap is the following optional parameters and filters {@link GetCasesFilter}
     *
     * @return a list of {@link TRCase} for a test suite or specific section in a test suite.
     * [
     * 	{ "id": 1, "title": "..", .. },
     * 	{ "id": 2, "title": "..", .. }
     * ]
     * @apiNote Response codes
     *
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_cases/{project_id}")
    List<TRCase> getCases(@Param("project_id") Long projectID, @QueryMap GetCasesQueryMap queryMap);

    /**
     * See {@link TestRailClient#getCases(Long, GetCasesQueryMap)}
     */
    default List<TRCase> getCases(Long projectID) {
        return getCases(projectID, new GetCasesFilter());
    }

    /**
     * See {@link TestRailClient#getCases(Long, GetCasesQueryMap)}
     */
    default List<TRCase> getCases(TRProject project) {
        return getCases(project.getId(), new GetCasesFilter());
    }

    /**
     * See {@link TestRailClient#getCases(Long, GetCasesQueryMap)}
     */
    default List<TRCase> getCases(TRProject project, GetCasesQueryMap queryMap) {
        return getCases(project.getId(), queryMap);
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases#add_case">API: Add case</a>
     *
     * Creates a new test case.
     *
     * @param sectionID is the ID of the section the test case should be added to
     * @param aCase is the {@link TRCase}
     *
     * @return If successful, this method returns the new test case using
     * the same response format as {@link TestRailClient#getCase(Long)}.
     *
     * @apiNote Response codes
     * 200 Success, the test case was created and is returned as part of the response
     * 400 Invalid or unknown section
     * 403 No permissions to add test cases or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_case/{section_id}")
    TRCase addCase(TRCase aCase, @Param("section_id") Long sectionID);

    /**
     * See {@link TestRailClient#addCase(TRCase, Long)}
     */
    default TRCase addCase(TRCase aCase, TRSection section) {
        return addCase(aCase, section.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases#update_case">API: Update case</a>
     *
     * Updates an existing test case (partial updates are supported, i.e.
     * you can submit and update specific fields only).
     * This method supports the same POST fields as {@link TestRailClient#addCase(TRCase, Long)} (except section_id).
     *
     * @param caseID is the ID of the test case
     * @param caze is the {@link TRCase}
     *
     * @return If successful, this method returns the new test case using
     * the same response format as {@link TestRailClient#getCase(Long)}.
     *
     * @apiNote Response codes
     * 200 Success, the test case was updated and is returned as part of the response
     * 400 Invalid or unknown test case
     * 403 No permissions to modify test cases or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/update_case/{case_id}")
    TRCase updateCase(TRCase caze, @Param("case_id") Long caseID);

    /**
     * See {@link TestRailClient#updateCase(TRCase, Long)}
     */
    default TRCase updateCase(TRCase caze) {
        return updateCase(caze, caze.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases#delete_case">API: Delete case</a>
     *
     * Deletes an existing test case.
     * @implNote Deleting a test case cannot be undone and also permanently deletes
     * all test results in active test runs (i.e. test runs that haven't been closed (archived) yet).
     *
     * @param caseID is the ID of the test case
     *
     * @apiNote Response codes
     * 200 Success, the test case was deleted
     * 400 Invalid or unknown test case
     * 403 No permissions to delete test cases or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_case/{case_id}")
    void deleteCase(@Param("case_id") Long caseID);

    /**
     * See {@link TestRailClient#deleteCase(Long)}
     */
    default void deleteCase(TRCase caze) {
        deleteCase(caze.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-projects#get_project">API: Get projects</a>
     *
     * @param projectID is the ID of the project
     *
     * @return an existing {@link TRProject}.
     * {
     * 	"announcement": "announcement",
     * 	"completed_on": null,
     * 	"id": 1,
     * 	"suite_mode": 3,
     * 	"is_completed": false,
     * 	"name": "Datahub",
     * 	"show_announcement": true,
     * 	"url": "http://localhost/testrail/index.php?/projects/overview/1"
     * }
     * */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_project/{projectID}")
    TRProject getProject(@Param("projectID") Long projectID);

    /**
     * See {@link TestRailClient#getProject(Long)}
     */
    default TRProject getProject(TRProject project) {
        return getProject(project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-projects#get_projects">API: Get project</a>
     *
     * Returns the list of available projects.
     *
     * @param queryMap is the request filter
     *
     * @return The response includes an array of projects.
     * Each project in this list follows the same format as
     * {@link TestRailClient#getProject(Long)}
     * [
     * 	{ "id": 1, "name": "DataHub", .. },
     * 	{ "id": 2, "name": "Writer", .. }
     * ]
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_projects")
    List<TRProject> getProjects(@QueryMap GetProjectsQueryMap queryMap);

    /**
     * See {@link TestRailClient#getProjects(GetProjectsQueryMap)}
     */
    default List<TRProject> getProjects() {
        return getProjects(new GetProjectsFilter());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-projects#add_project">API: Add project</a>
     *
     * Creates a new project (admin status required).
     *
     * @param project is the request body. The following POST fields are supported:
     *                name, announcement, show_announcement, suite_mode.
     *
     * @return If successful, this method returns the new {@link TRProject}
     * using the same response format as {@link TestRailClient#getProject(Long)}.
     * { "id": 1, "name": "DataHub", .. }
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_project")
    TRProject addProject(TRProject project);

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-projects#update_project">API: Update project</a>
     *
     *
     * Updates an existing project (admin status required;
     * partial updates are supported, i.e. you can submit and update specific fields only).
     *
     * @param projectID is the ID of the project
     * @param project is the request body.
     *
     * @return If successful, this method returns the new {@link TRProject}
     * using the same response format as {@link TestRailClient#getProject(Long)}.
     * { "id": 1, "name": "DataHub", .. }
     *
     * @apiNote Response codes
     * 200 Success, the project was updated and is returned as part of the response
     * 400 Invalid or unknown project
     * 403 No permissions to modify projects (requires admin rights)
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/update_project/{projectID}")
    TRProject updateProject(TRProject project, @Param("projectID") Long projectID);

    /**
     * See {@link TestRailClient#updateProject(TRProject, Long)}
     */
    default TRProject updateProject(TRProject project) {
        return updateProject(project, project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-projects#delete_project#get_run">API: Delete project</a>
     *
     * Deletes an existing project (admin status required).
     * @implNote Deleting a project cannot be undone and also permanently deletes all test suites & cases,
     * test runs & results and everything else that is part of the project.
     *
     * @param projectID is the ID of the project
     *
     * @apiNote Response codes
     * 200 Success, the project was deleted
     * 400 Invalid or unknown project
     * 403 No permissions to delete projects (requires admin rights)
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_project/{projectID}")
    void deleteProject(@Param("projectID") Long projectID);

    /**
     * See {@link TestRailClient#deleteProject(Long)}
     */
    default void deleteProject(TRProject project) {
        deleteProject(project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-runs#get_run">API: Get run</a>
     *
     * @param runID is the ID of the test run
     *
     * @return an existing test {@link TRRun}
     * {
     * 	"assignedto_id": 6,
     * 	"blocked_count": 0,
     * 	"completed_on": null,
     * 	"config": "Firefox, Ubuntu 12",
     * 	"config_ids": [ 2, 6 ],
     * 	"created_by": 1,
     * 	"created_on": 1393845644,
     * 	"custom_status1_count": 0,
     * 	"custom_status2_count": 0,
     * 	"custom_status3_count": 0,
     * 	"custom_status4_count": 0,
     * 	"custom_status5_count": 0,
     * 	"custom_status6_count": 0,
     * 	"custom_status7_count": 0,
     * 	"description": null,
     * 	"failed_count": 2,
     * 	"id": 81,
     * 	"include_all": false,
     * 	"is_completed": false,
     * 	"milestone_id": 7,
     * 	"name": "File Formats",
     * 	"passed_count": 2,
     * 	"plan_id": 80,
     * 	"project_id": 1,
     * 	"retest_count": 1,
     * 	"suite_id": 4,
     * 	"untested_count": 3,
     * 	"url": "http://<server>/testrail/index.php?/runs/view/81"
     * }
     * 
     * @apiNote Response codes
     * 200 Success, the test run is returned as part of the response
     * 400 Invalid or unknown test run
     * 403 No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_run/{run_id}")
    TRRun getRun(@Param("run_id") Long runID);

    /**
     * See {@link TestRailClient#getRun(Long)}
     */
    default TRRun getRun(TRRun run) {
        return getRun(run.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-runs#get_runs">API: Get Runs</a>
     *
     * 
     * All active test runs for project with ID 1 created by user with ID 1 or 2
     * GET index.php?/api/v2/get_runs/1&is_completed=0&created_by=1,2
     *
     * @param projectID is the ID of the project
     * @param queryMap is a request filter {@link GetRunsQueryMap}
     *                 All active test runs for project with ID 1 created by user with ID 1 or 2
     *                 GET index.php?/api/v2/get_runs/1&is_completed=0&created_by=1,2
     *
     * @return Returns a list of test {@link TRRun}s for a project.
     * Only returns those test runs that are not part of a test plan (please see get_plans/get_plan for this).
     *
     * @apiNote Response codes
     * 200 Success, the test runs are returned as part of the response
     * 400 Invalid or unknown project
     * 403 No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_runs/{project_id}")
    List<TRRun> getRuns(@Param("project_id") Long projectID, @QueryMap GetRunsQueryMap queryMap);

    /**
     * See {@link TestRailClient#getRuns(Long, GetRunsQueryMap)}
     */
    default List<TRRun> getRuns(Long projectID) {
        return getRuns(projectID, new GetRunsFilter());
    }

    /**
     * See {@link TestRailClient#getRuns(Long, GetRunsQueryMap)}
     */
    default List<TRRun> getRuns(TRProject project) {
        return getRuns(project.getId(), new GetRunsFilter());
    }

    /**
     * See {@link TestRailClient#getRuns(Long, GetRunsQueryMap)}
     */
    default List<TRRun> getRuns(TRProject project, GetRunsQueryMap queryMap) {
        return getRuns(project.getId(), queryMap);
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-runs#add_run">API: Add run</a>
     *
     * Creates a new test run.
     * Also see the following example which shows how to create a new test run including a custom test case selection:
     * {
     * 	"suite_id": 1,
     * 	"name": "This is a new test run",
     * 	"assignedto_id": 5,
     * 	"include_all": false,
     * 	"case_ids": [1, 2, 3, 4, 7, 8]
     * }
     *
     * @param run is the request body ({@link TRRun})
     * @param projectID is the ID of the test run
     *
     * @return If successful, this method returns the new test {@link TRRun}
     *         using the same response format as {@link TestRailClient#getRun(Long)}.
     *
     * @apiNote Response codes
     * 200	Success, the test run was created and is returned as part of the response
     * 400	Invalid or unknown project
     * 403	No permissions to add test runs or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_run/{project_id}")
    TRRun addRun(TRRun run, @Param("project_id") Long projectID);

    /**
     * See {@link TestRailClient#addRun(TRRun, Long)}
     */
    default TRRun addRun(TRRun run, TRProject project) {
        return addRun(run, project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-runs#update_run">API: Update run</a>
     *
     * Updates an existing test run (partial updates are supported, i.e. you can submit and update specific fields only).
     * With the exception of the suite_id and assignedto_id fields,
     * this method supports the same POST fields as {@link TestRailClient#addRun(TRRun, Long)}.
     * Request example
     * Also see the following example which shows how to update the description and test case selection of a test run:
     * {
     * 	"description": "A description for the test run",
     * 	"include_all": true
     * }
     * The following example updates a test run to use a manual test case selection:
     * {
     * 	"include_all": false,
     * 	"case_ids": [1, 2, 3, 5, 8]
     * }
     *
     * @param run is the request body ({@link TRRun})
     * @param runID is the ID of the test run
     *
     * @return If successful, this method returns the updated test {@link TRRun}
     *         using the same response format as {@link TestRailClient#getRun(Long)}}.
     *
     * @apiNote Response codes
     * 200	Success, the test run was updated and is returned as part of the response
     * 400	Invalid or unknown test run
     * 403	No permissions to modify test runs or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/update_run/{run_id}")
    TRRun updateRun(TRRun run, @Param("run_id") Long runID);

    /**
     * See {@link TestRailClient#updateRun(TRRun, Long)}
     */
    default TRRun updateRun(TRRun run) {
        return updateRun(run, run.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-runs#close_run">API: Close run</a>
     *
     * Closes an existing test run and archives its tests & results.
     * @implNote Closing a test run cannot be undone.
     *
     * @param runID is the ID of the test run
     *
     * @return If successful, this method returns the updated test {@link TRRun}
     *         using the same response format as {@link TestRailClient#getRun(Long)}}.
     *
     * @apiNote Response codes
     * 200	Success, the test run was closed (archived) and is returned as part of the response.
     * 400	Invalid or unknown test run
     * 403	No permissions to close test runs or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/close_run/{run_id}")
    TRRun closeRun(@Param("run_id") Long runID);

    /**
     * See {@link TestRailClient#closeRun(Long)}
     */
    default TRRun closeRun(TRRun run) {
        return closeRun(run.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-runs#delete_run">API: Delete run</a>
     *
     * Deletes an existing test run.
     * @implNote Deleting a test run cannot be undone and also permanently deletes all tests & results of the test run.
     *
     * @param runID is the ID of the test run
     *
     * @apiNote Response codes
     * 200	Success, the test run and all its tests & results were deleted
     * 400	Invalid or unknown test run
     * 403	No permissions to delete test runs or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_run/{run_id}")
    void deleteRun(@Param("run_id") Long runID);

    /**
     * See {@link TestRailClient#deleteRun(Long)}
     */
    default void deleteRun(TRRun run) {
        deleteRun(run.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-suites#get_suite">API: Get suite</a>
     *
     * @param suiteID is the ID of the test suite
     *
     * @return an existing test {@link TRSuite}.
     * {
     * 	"description": "description",
     * 	"id": 1,
     * 	"name": "Setup & Installation",
     * 	"project_id": 1,
     * 	"url": "http://<server>/testrail/index.php?/suites/view/1"
     * }
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_suite/{suite_id}")
    TRSuite getSuite(@Param("suite_id") Long suiteID);

    /**
     * See {@link TestRailClient#getSuite(Long)}
     */
    default TRSuite getSuite(TRSuite suite) {
        return getSuite(suite.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-suites#get_suites">API: Get suites</a>
     *
     * @param projectID is the ID of the project
     *
     * @return The response includes an array of test suite. Each test suite in this
     * list follows the same format as {@link TestRailClient#getSuite(Long)}.
     * [
     * 	{ "id": 1, "name": "Setup & Installation", .. },
     * 	{ "id": 2, "name": "Document Editing", .. }
     * ]
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_suites/{project_id}")
    List<TRSuite> getSuites(@Param("project_id") Long projectID);

    /**
     * See {@link TestRailClient#getSuites(Long)}
     */
    default List<TRSuite> getSuites(TRProject project) {
        return getSuites(project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-suites#add_suite">API: Add suite</a>
     *
     * Creates a new test suite.
     *
     * @param projectID is the ID of the project
     *
     * @return If successful, this method returns the new test suite using
     * the same response format as {@link TestRailClient#getSuite(Long)}.
     *
     * @apiNote Response codes
     * 200 Success, the test suite was created and is returned as part of the response
     * 400 Invalid or unknown project
     * 404 No permissions to add test suites or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_suite/{project_id}")
    TRSuite addSuite(TRSuite suite, @Param("project_id") Long projectID);

    /**
     * See {@link TestRailClient#addSuite(TRSuite, Long)}
     */
    default TRSuite addSuite(TRSuite suite, TRProject project) {
        return addSuite(suite, project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-suites#update_suite">API: Update suite</a>
     *
     * Updates an existing test suite (partial updates are supported,
     * i.e. you can submit and update specific fields only).
     *
     * @param suiteID is the ID of the test suite
     *
     * @return If successful, this method returns the new test suite using
     * the same response format as {@link TestRailClient#getSuite(Long)}.
     *
     * @apiNote Response codes
     * 200 Success, the test suite was updated and is returned as part of the response
     * 400 Invalid or unknown test suite
     * 404 No permissions to modify test suites or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/update_suite/{suite_id}")
    TRSuite updateSuite(TRSuite suite, @Param("suite_id") Long suiteID);

    /**
     * See {@link TestRailClient#updateSuite(TRSuite, Long)}
     */
    default TRSuite updateSuite(TRSuite suite) {
        return updateSuite(suite, suite.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-suites#delete_suite">API: Delete suite</a>
     *
     * Deletes an existing test suite.
     * @implNote Deleting a test suite cannot be undone and also deletes all active
     * test runs & results, i.e. test runs & results that weren't closed (archived) yet.
     *
     * @param suiteID is the ID of the test suite
     *
     * @apiNote Response codes
     * 200 Success, the test suite and all active test runs & results were deleted
     * 400 Invalid or unknown test suite
     * 404 No permissions to delete test suites or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_suite/{suite_id}")
    void deleteSuite(@Param("suite_id") Long suiteID);

    /**
     * See {@link TestRailClient#deleteSuite(Long)}
     */
    default void deleteSuite(TRSuite suite) {
        deleteSuite(suite.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-sections#add_section">API: Add section</a>
     *
     * @param projectID is the ID of the project
     * @param section is the request body
     *
     * @return If successful, this method returns the new section using
     * the same response format as {@link TestRailClient#getSection(Long)}.
     *
     * @apiNote Response codes
     * 200 Success, the section was created and is returned as part of the response
     * 400 Invalid or unknown project or test suite
     * 403 No permissions to add sections or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_section/{project_id}")
    TRSection addSection(TRSection section, @Param("project_id") Long projectID);

    /**
     * See {@link TestRailClient#addSection(TRSection, Long)}
     */
    default TRSection addSection(TRSection section, TRProject project) {
        return addSection(section, project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-sections#get_section">API: Get section</a>
     *
     * Returns an existing section.
     *
     * @param sectionID is the ID of the section
     *
     * @return for the following fields are included in the response see {@link TRSection}
     * Please see below for a typical response
     * <code>
     * {
     * 	"depth": 0,
     * 	"description": null,
     * 	"display_order": 1,
     * 	"id": 1,
     * 	"name": "Prerequisites",
     * 	"parent_id": null,
     * 	"suite_id": 1
     * }
     * </code>
     * The depth, display_order and parent fields determine the hierarchy of the sections in a test suite.
     * The depth field is 0 for all sections on the root level and greater than 0 for all child sections.
     * The depth field therefore resembles the level in the section hierarchy.
     *
     * @apiNote Response codes
     * 200 Success, the section is returned as part of the response
     * 400 Invalid or unknown section
     * 403 No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_section/{section_id}")
    TRSection getSection(@Param("section_id") Long sectionID);

    /**
     * See {@link TestRailClient#getSection(Long)}
     */
    default TRSection getSection(TRSection section) {
        return getSection(section.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-sections#get_sections">API: Get sections</a>
     *
     * @param projectID is the ID of the project
     * @param queryMap is the request filter {@link GetSectionsQueryMap}
     *
     * @return a list of sections for a project and test suite.
     * <code>
     * [
     * 	    {
     * 	    	"depth": 0,
     * 	    	"display_order": 1,
     * 	    	"id": 1,
     * 	    	"name": "Prerequisites",
     * 	    	"parent_id": null,
     * 	    	"suite_id": 1
     * 	    },
     * 	    ..
     * ]
     * </code>
     *
     * @apiNote Response codes
     * 200 Success, the sections are returned as part of the response
     * 400 Invalid or unknown project or test suite
     * 403 No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_sections/{project_id}")
    List<TRSection> getSections(@Param("project_id") Long projectID, @QueryMap GetSectionsQueryMap queryMap);

    /**
     * See {@link TestRailClient#getSections(Long, GetSectionsQueryMap)}
     */
    default List<TRSection> getSections(Long projectID) {
        return getSections(projectID, new GetSectionsFilter());
    }

    /**
     * See {@link TestRailClient#getSections(Long, GetSectionsQueryMap)}
     */
    default List<TRSection> getSections(TRProject project) {
        return getSections(project.getId(), new GetSectionsFilter());
    }

    /**
     * See {@link TestRailClient#getSections(Long, GetSectionsQueryMap)}
     */
    default List<TRSection> getSections(TRProject project, GetSectionsQueryMap queryMap) {
        return getSections(project.getId(), queryMap);
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-sections#update_section">API: Update section</a>
     *
     * Updates an existing section (partial updates are supported, i.e. you can submit and update specific fields only).
     *
     * @param sectionID is the ID of the section
     * @param section is the following POST fields are supported: description, name; See {@link TRSection}
     *
     * @return If successful, this method returns the updated section using the
     * same response format as {@link TestRailClient#getSection(Long)}.
     *
     * @apiNote Response codes
     * 200 Success, the section was updated and is returned as part of the response
     * 400 Invalid or unknown section
     * 403 No permissions to modify sections or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/update_section/{section_id}")
    TRSection updateSection(TRSection section, @Param("section_id") Long sectionID);

    /**
     * See {@link TestRailClient#updateSection(TRSection, Long)}
     */
    default TRSection updateSection(TRSection section) {
        return updateSection(section, section.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-sections#delete_section">API: Delete section</a>
     *
     * Deletes an existing section.
     * @implNote Deleting a section cannot be undone and also deletes all related
     * test cases as well as active tests & results, i.e. tests & results that weren't closed (archived) yet.
     *
     * @param sectionID is the ID of the section
     *
     * @apiNote Response codes
     * 200 Success, the section was deleted
     * 400 Invalid or unknown section
     * 403 No permissions to delete sections or test cases or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_section/{section_id}")
    void deleteSection(@Param("section_id") Long sectionID);

    /**
     * See {@link TestRailClient#deleteSection(Long)}
     */
    default void deleteSection(TRSection section) {
        deleteSection(section.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-tests#get_test">API: Get test</a>
     *
     * If you interested in the test results rather than the tests, please see get_results instead.
     * Please see below for a typical example response:
     * {
     * 	"assignedto_id": 1,
     * 	"case_id": 1,
     * 	"custom_expected": "..",
     * 	"custom_preconds": "..",
     * 	"custom_steps_separated": [
     * 		{
     * 			"content": "Step 2",
     * 			"expected": "Expected Result 2"
     * 		}
     * 	],
     * 	"estimate": "1m 5s",
     * 	"estimate_forecast": null,
     * 	"id": 100,
     * 	"priority_id": 2,
     * 	"run_id": 1,
     * 	"status_id": 5,
     * 	"title": "Verify line spacing on multi-page document",
     * 	"type_id": 4
     * }
     * Custom fields of test cases are also included in the response and use their system name prefixed with
     * 'custom_' as their field identifier. Please see {@link TestRailClient#addCase(TRCase, Long)}
     * for a full list of available custom field types.
     *
     * @param testID is the ID of the test
     *
     * @return an existing test.
     *
     * @apiNote Response codes
     * 200	Success, the test is returned as part of the response
     * 400	Invalid or unknown test
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_test/{test_id}")
    TRTest getTest(@Param("test_id") Long testID);

    /**
     * See {@link TestRailClient#getTest(Long)}
     */
    default TRTest getTest(TRTest test) {
        return getTest(test.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-tests#get_tests">API: Get tests</a>
     *
     * @param runID is the ID of the test run
     * @param queryMap is the  following filters
     *                 All test cases for test run with ID 1 and status 4, 5 (Retest and Failed)
     *                 GET index.php?/api/v2/get_tests/1&status_id=4,5
     *
     * @return a list of tests for a test run.
     * The response includes an array of tests.
     * Each test in this list follows the same format as {@link TestRailClient#getTest(Long)}.
     * [
     * 	    {
     * 	    	"id": 1,
     * 	    	"title": "Test conditional formatting with basic value range",
     * 	    }
     * ]
     *
     * @apiNote Response codes
     * 200	Success, the tests are returned as part of the response
     * 400	Invalid or unknown test run
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_tests/{run_id}")
    List<TRTest> getTests(@Param("run_id") Long runID, @QueryMap GetTestsQueryMap queryMap);

    /**
     * See {@link TestRailClient#getTests(Long, GetTestsQueryMap)}
     */
    default List<TRTest> getTests(Long runID) {
        return getTests(runID, new GetTestsFilter());
    }

    /**
     * See {@link TestRailClient#getTests(Long, GetTestsQueryMap)}
     */
    default List<TRTest> getTests(TRRun run) {
        return getTests(run.getId(), new GetTestsFilter());
    }

    /**
     * See {@link TestRailClient#getTests(Long, GetTestsQueryMap)}
     */
    default List<TRTest> getTests(TRRun run, GetTestsQueryMap queryMap) {
        return getTests(run.getId(), queryMap);
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases-fields#get_case_fields">API: Get case fields</a>
     *
     * @return a list of available test case custom fields {@link TRCaseField}.
     * The response includes an array of custom field definitions. Please see below for a typical response:
     * [
     * 	{
     * 		"configs": [
     * 		{
     * 			"context": {
     * 				"is_global": true,
     * 				"project_ids": null
     * 			},
     * 			"id": "..",
     * 			"options": {
     * 				"default_value": "",
     * 				"format": "markdown",
     * 				"is_required": false,
     * 				"rows": "5"
     * 			}
     * 		}
     * 		],
     * 		"description": "The preconditions of this test case. ..",
     * 		"display_order": 1,
     * 		"id": 1,
     * 		"label": "Preconditions",
     * 		"name": "preconds",
     * 		"system_name": "custom_preconds",
     * 		"type_id": 3
     * 	}
     * ]
     * The following list shows the available custom field types (type_id field): {@link FieldTypes}
     *
     * @apiNote Response codes
     * 200	Success, the available custom fields are returned as part of the response
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_case_fields")
    List<TRCaseField> getCaseFields();

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases-fields#add_case_field">API: Add case field</a>
     *
     * Creates a new test case custom field.
     *
     * @param caseField is the request body
     *
     * @return the new custom field.
     * {
     *   "id":33,
     *   "name":"my_multiselect",
     *   "system_name":"custom_my_multiselect",
     *   "entity_id":1,
     *   "label":"My Multiselect",
     *   "description":"my custom Multiselect description",
     *   "type_id":12,
     *   "location_id":2,
     *   "display_order":7,
     *   "configs":[{"context":{"is_global":true,"project_ids":""},"options":{"is_required":false,"items":"1, One\\n2, Two"},"id":"9f105ba2-1ed0-45e0-b459-18d890bad86e"}],
     *   "is_multi":1,
     *   "is_active":1,
     *   "status_id":1,
     *   "is_system":0,
     *   "include_all":1,
     *   "template_ids":[]
     * }
     *
     * @apiNote Response codes
     * 200	Success, the new custom field is returned in the response
     * 400	Bad request, check the error message for diagnostics
     * 404	Not found, bad parameter passed
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_case_field")
    TRCaseField addCaseField(TRCaseField caseField);

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases-types#get_case_types">API: Get case types</a>
     *
     * @return a list of available case types.
     * The response includes an array of test case types. Each case type has a unique ID and a name.
     * The is_default field is true for the default case type and false otherwise.
     *
     * @apiNote Response codes
     * 200	Success, the case types are returned as part of the response
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_case_types")
    List<TRCaseType> getCaseTypes();

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-configs#get_configs">API: Get configs</a>
     *
     * @param projectID is the ID of the project
     *
     * @return a list of available configurations {@link TRProjectConfigGroup},
     * grouped by configuration groups (requires TestRail 3.1 or later).
     *
     * @apiNote Response codes
     * 200	Success, the configurations are returned as part of the response
     * 400	Invalid or unknown project
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_configs/{project_id}")
    List<TRProjectConfigGroup> getConfigs(@Param("project_id") Long projectID);

    /**
     * See {@link TestRailClient#getConfigs(Long)}
     */
    default List<TRProjectConfigGroup> getConfigs(TRProject project) {
        return getConfigs(project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-configs#add_config_group">API: Add config group</a>
     *
     * @param projectID is the ID of the project the configuration group should be added to
     * @param cGroup is the request body
     *
     * @return new {@link TRProjectConfigGroup}
     *
     * @apiNote Response codes
     * 200	Success, the configuration group was created and is returned as part of the response
     * 400	Invalid or unknown project
     * 403	No permissions to add configuration groups or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_config_group/{project_id}")
    TRProjectConfigGroup addConfigGroup(TRProjectConfigGroup cGroup, @Param("project_id") Long projectID);

    /**
     * See {@link TestRailClient#addConfigGroup(TRProjectConfigGroup, Long)}
     */
    default TRProjectConfigGroup addConfigGroup(TRProjectConfigGroup cGroup, TRProject project) {
        return addConfigGroup(cGroup, project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-configs#update_config_group">API: Update config group</a>
     *
     * Updates an existing configuration group (requires TestRail 5.2 or later).
     *
     * @param configGroupID the ID of the configuration group
     * @param cGroup is the request body
     *
     * @return updated {@link TRProjectConfigGroup}
     *
     * @apiNote Response codes
     * 200	Success, the configuration group was updated and is returned as part of the response
     * 400	Invalid or unknown configuration group
     * 403	No permissions to modify configuration groups or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/update_config_group/{config_group_id}")
    TRProjectConfigGroup updateConfigGroup(TRProjectConfigGroup cGroup, @Param("config_group_id") Long configGroupID);

    /**
     * See {@link TestRailClient#updateConfigGroup(TRProjectConfigGroup, Long)}
     */
    default TRProjectConfigGroup updateConfigGroup(TRProjectConfigGroup cGroup) {
        return updateConfigGroup(cGroup, cGroup.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-configs#delete_config_group">API: Delete config group</a>
     *
     * Deletes an existing configuration group and its configurations (requires TestRail 5.2 or later).
     * Please note: Deleting a configuration group cannot be undone and also permanently deletes all configurations in
     * this group. It does not, however, affect closed test plans/runs, or active test plans/runs unless they are updated.
     *
     * @param configGroupID the ID of the configuration group
     *
     * @apiNote Response codes
     * 200	Success, the configuration group and all its configurations were deleted
     * 400	Invalid or unknown configuration group
     * 403	No permissions to delete configuration groups or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_config_group/{config_group_id}")
    void deleteConfigGroup(@Param("config_group_id") Long configGroupID);

    /**
     * See {@link TestRailClient#deleteConfigGroup(Long)}
     */
    default void deleteConfigGroup(TRProjectConfigGroup cGroup) {
        deleteConfigGroup(cGroup.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-configs#add_config">API: Add config</a>
     *
     * Creates a new configuration (requires TestRail 5.2 or later).
     *
     * @param configGroupID The ID of the configuration group the configuration should be added to
     * @param config the {@link TRProjectConfig} request body
     *
     * @return a new configuration {@link TRProjectConfig}
     *
     * @apiNote Response codes
     * 200	Success, the configuration was created and is returned as part of the response
     * 400	Invalid or unknown project
     * 403	No permissions to add configurations or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_config/{config_group_id}")
    TRProjectConfig addConfig(TRProjectConfig config, @Param("config_group_id") Long configGroupID);

    /**
     * See {@link TestRailClient#addConfig(TRProjectConfig, Long)}
     */
    default TRProjectConfig addConfig(TRProjectConfig config, TRProjectConfigGroup cGroup) {
        return addConfig(config, cGroup.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-configs#update_config">API: Update config</a>
     *
     * Updates an existing configuration (requires TestRail 5.2 or later).
     *
     * @param configID the ID of the configuration
     * @param config the {@link TRProjectConfig} request body
     *
     * @return updated configuration {@link TRProjectConfig}
     *
     * @apiNote Response codes
     * 200	Success, the configuration was updated and is returned as part of the response
     * 400	Invalid or unknown configuration
     * 403	No permissions to modify configurations or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/update_config/{config_id}")
    TRProjectConfig updateConfig(TRProjectConfig config, @Param("config_id") Long configID);

    /**
     * See {@link TestRailClient#updateConfig(TRProjectConfig, Long)}
     */
    default TRProjectConfig updateConfig(TRProjectConfig config) {
        return updateConfig(config, config.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-configs#delete_config">API: Delete config</a>
     *
     * Deletes an existing configuration (requires TestRail 5.2 or later).
     * Please note: Deleting a configuration cannot be undone. It does not, however,
     * affect closed test plans/runs, or active test plans/runs unless they are updated.
     *
     * @param configID the ID of the configuration
     *
     * @apiNote Response codes
     * 200	Success, the configuration was deleted
     * 400	Invalid or unknown configuration
     * 403	No permissions to delete configurations or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_config/{config_id}")
    void deleteConfig(@Param("config_id") Long configID);

    /**
     * See {@link TestRailClient#deleteConfig(Long)}
     */
    default void deleteConfig(TRProjectConfig config) {
        deleteConfig(config.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-milestones#get_milestone">API: Get milestone</a>
     *
     * @param milestoneID is the ID of the milestone
     *
     * @return an existing milestone {@link TRMilestone}
     *
     * @apiNote Response codes
     * 200	Success, the milestone is returned as part of the response
     * 400	Invalid or unknown milestone
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_milestone/{milestone_id}")
    TRMilestone getMilestone(@Param("milestone_id") Long milestoneID);

    /**
     * See {@link TestRailClient#getMilestone(Long)}
     */
    default TRMilestone getMilestone(TRMilestone milestone) {
        return getMilestone(milestone.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-milestones#get_milestones">API: Get milestones</a>
     *
     * @param projectID is the ID of the project
     *
     * @return the list of {@link TRMilestone} for a project.
     *
     * @apiNote Response codes
     * 200	Success, the milestones are returned as part of the response
     * 400	Invalid or unknown project
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_milestones/{project_id}")
    List<TRMilestone> getMilestones(@Param("project_id") Long projectID, @QueryMap GetMilestonesQueryMap queryMap);

    /**
     * See {@link TestRailClient#getMilestones(Long, GetMilestonesQueryMap)}
     */
    default List<TRMilestone> getMilestones(Long projectID) {
        return getMilestones(projectID, new GetMilestonesFilter());
    }

    /**
     * See {@link TestRailClient#getMilestones(Long, GetMilestonesQueryMap)}
     */
    default List<TRMilestone> getMilestones(TRProject project) {
        return getMilestones(project.getId());
    }

    /**
     * See {@link TestRailClient#getMilestones(Long, GetMilestonesQueryMap)}
     */
    default List<TRMilestone> getMilestones(TRProject project, GetMilestonesQueryMap queryMap) {
        return getMilestones(project.getId(), queryMap);
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-milestones#add_milestone">API: Add milestone</a>
     *
     * Creates a new milestone.
     *
     * @param projectID is the ID of the project the milestone should be added to
     * @param milestone is the request body {@link TRMilestone}
     *
     * @return a new {@link TRMilestone}
     *
     * @apiNote Response codes
     * 200	Success, the milestone was created and is returned as part of the response
     * 400	Invalid or unknown project
     * 403	No permissions to add milestones or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_milestone/{project_id}")
    TRMilestone addMilestone(TRMilestone milestone, @Param("project_id") Long projectID);

    /**
     * See {@link TestRailClient#addMilestone(TRMilestone, Long)}
     */
    default TRMilestone addMilestone(TRMilestone milestone, TRProject project) {
        return addMilestone(milestone, project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-milestones#update_milestone">API: Update milestone</a>
     *
     * Updates an existing milestone (partial updates are supported, i.e. you can submit and update specific fields only).
     *
     * @param milestoneID is the ID of the milestone
     * @param milestone is the request body {@link TRMilestone}
     *
     * Request fields
     * is_completed	- True if a milestone is considered completed and false otherwise
     * is_started - True if a milestone is considered started and false otherwise
     * parent_id - The ID of the parent milestone, if any (for sub-milestones) (available since TestRail 5.3)
     * start_on	- The scheduled start date of the milestone (as UNIX timestamp) (available since TestRail 5.3)
     *
     * @return updated {@link TRMilestone}
     *
     * @apiNote Response codes
     * 200	Success, the milestone was updated and is returned as part of the response
     * 400	Invalid or unknown milestone
     * 403	No permissions to modify milestones or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/update_milestone/{milestone_id}")
    TRMilestone updateMilestone(TRMilestone milestone, @Param("milestone_id") Long milestoneID);

    /**
     * See {@link TestRailClient#updateMilestone(TRMilestone, Long)}
     */
    default TRMilestone updateMilestone(TRMilestone milestone) {
        return updateMilestone(milestone, milestone.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-milestones#delete_milestone">API: Delete milestone</a>
     *
     * Deletes an existing milestone.
     * @implNote Deleting a milestone cannot be undone.
     *
     * @apiNote Response codes
     * 200	Success, the milestone was deleted
     * 400	Invalid or unknown milestone
     * 403	No permissions to delete milestones or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_milestone/{milestone_id}")
    void deleteMilestone(@Param("milestone_id") Long milestoneID);

    /**
     * See {@link TestRailClient#deleteMilestone(Long)}
     */
    default void deleteMilestone(TRMilestone milestone) {
        deleteMilestone(milestone.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-priorities#get_priorities">API: Get priorities</a>
     *
     * @return a list of available {@link TRPriority}.
     * The response include an array of priorities.
     * Each priority has a unique ID, a name and a short version of the name.
     * The priority field determines the order of the priorities.
     * The is_default field is true for the default priority and false otherwise.
     * [
     * 	{
     * 		"id": 1,
     * 		"is_default": false,
     * 		"name": "1 - Don't Test",
     * 		"priority": 1,
     * 		"short_name": "1 - Don't"
     * 	}
     * ]
     * @apiNote Response codes
     * 200	Success, the available priorities are returned as part of the response
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_priorities")
    List<TRPriority> getPriorities();

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-users#get_user">API: Get user</a>
     *
     * @param userID is the ID of the user
     *
     * @return an existing {@link TRUser}.
     *
     * @apiNote Response codes
     * 200	Success, the user is returned as part of the response
     * 400	Invalid or unknown user
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_user/{user_id}")
    TRUser getUser(@Param("user_id") Long userID);

    /**
     * See {@link TestRailClient#getUser(Long)}
     */
    default TRUser getUser(TRUser user) {
        return getUser(user.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-users#get_user_by_email">API: Get user by email</a>
     *
     * @param email is the email address to get the user for
     *
     * @return an existing {@link TRUser} by his/her email address.
     *
     * @apiNote Response codes
     * 200	Success, the user is returned as part of the response
     * 400/404	Invalid or unknown email address
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_user_by_email/&email={email}")
    TRUser getUserByEmail(@Param("email") String email);

    /**
     * See {@link TestRailClient#getUserByEmail(String)}
     */
    default TRUser getUserByEmail(TRUser user) {
        return getUserByEmail(user.getEmail());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-users#get_users">API: Get users</a>
     *
     * @return a list of {@link TRUser}s.
     *
     * @apiNote Response codes
     * 200	Success, the users are returned as part of the response
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_users")
    List<TRUser> getUsers();

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-templates#get_templates">API: Get templates</a>
     *
     * Returns a list of available templates (requires TestRail 5.2 or later).
     * The response includes an array of templates (field layouts). Each template has a unique ID and a name.
     * The is_default field is true for the default template and false otherwise.
     *
     * @param projectID is the ID of the project
     *
     * @return a list of available {@link TRTemplate}s (requires TestRail 5.2 or later).
     *
     * @apiNote Response codes
     * 200	Success, the templates are returned as part of the response
     * 400	Invalid or unknown project
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_templates/{project_id}")
    List<TRTemplate> getTemplates(@Param("project_id") Long projectID);

    /**
     * See {@link TestRailClient#getTemplates(Long)}
     */
    default List<TRTemplate> getTemplates(TRProject project) {
        return getTemplates(project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-statuses#get_statuses">API: Get statuses</a>
     *
     * @return a list of available test {@link TRStatus}es.
     * The returned response includes all system and custom statuses
     *
     * @apiNote Response codes
     * 200	Success, the available statuses are returned as part of the response
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_statuses")
    List<TRStatus> getStatuses();

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results-fields#get_result_fields">API: Get result fields</a>
     *
     * @return a list of available test result custom fields {@link TRResultField}.
     * The response includes an array of custom field definitions.
     * A custom field can have different configurations and options per project which is indicated by the configs field.
     * To check if a custom field is applicable to a specific project (and to find out the field options for this
     * project), the context of the field configuration must either be global (is_global) or include the ID of
     * the project in project_ids.
     *
     * @apiNote Response codes
     * 200	Success, the available custom fields are returned as part of the response
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_result_fields")
    List<TRResultField> getResultFields();

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-plans#get_plan">API: Get plan</a>
     *
     * @param planID is the ID of the test plan
     *
     * @return an existing test plan {@link TRPlan}.
     * The entries field includes an array of test plan entries. A test plan entry is a group of test runs that belong
     * to the same test suite (just like in the user interface). Each group can have a variable amount of test runs
     * and also supports configurations.
     *
     * @apiNote Response codes
     * 200	Success, the test plan and its test runs are returned as part of the response
     * 400	Invalid or unknown test plan
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_plan/{plan_id}")
    TRPlan getPlan(@Param("plan_id") Long planID);

    /**
     * See {@link TestRailClient#getPlan(Long)}
     */
    default TRPlan getPlan(TRPlan plan) {
        return getPlan(plan.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-plans#get_plans">API: Get plans</a>
     *
     * @param projectID is the ID of the project
     * @param queryMap is the request filter
     *
     * @return a list of test {@link TRPlan} for a project.
     *
     * @apiNote Response codes
     * 200	Success, the test plans are returned as part of the response
     * 400	Invalid or unknown project
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_plans/{project_id}")
    List<TRPlan> getPlans(@Param("project_id") Long projectID, @QueryMap GetPlansQueryMap queryMap);

    /**
     * See {@link TestRailClient#getPlans(Long, GetPlansQueryMap)}
     */
    default List<TRPlan> getPlans(Long projectID) {
        return getPlans(projectID, new GetPlansFilter());
    }

    /**
     * See {@link TestRailClient#getPlans(Long, GetPlansQueryMap)}
     */
    default List<TRPlan> getPlans(TRProject project) {
        return getPlans(project.getId(), new GetPlansFilter());
    }

    /**
     * See {@link TestRailClient#getPlans(Long, GetPlansQueryMap)}
     */
    default List<TRPlan> getPlans(TRProject project, GetPlansQueryMap queryMap) {
        return getPlans(project.getId(), queryMap);
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-plans#add_plan">API: Add plan</a>
     *
     * Creates a new test plan.
     *
     * Request fields
     * The following POST fields are supported:
     * name - The name of the test plan (required)
     * description - The description of the test plan
     * milestone_id - The ID of the milestone to link to the test plan
     * entries - An array of objects describing the test runs of the plan, see the example below and add_plan_entry
     *
     * @param projectID is the ID of the project the test plan should be added to
     * @param plan is the request body
     *
     * @return a new test {@link TRPlan}.
     *
     * @apiNote Response codes
     * 200	Success, the test plan was created and is returned as part of the response
     * 400	Invalid or unknown project
     * 403	No permissions to add test plans or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_plan/{project_id}")
    TRPlan addPlan(TRPlan plan, @Param("project_id") Long projectID);

    /**
     * See {@link TestRailClient#addPlan(TRPlan, Long)}
     */
    default TRPlan addPlan(TRPlan plan, TRProject project) {
        return addPlan(plan, project.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-plans#update_plan">API: Update plan</a>
     *
     * Updates an existing test plan (partial updates are supported, i.e. you can submit and update specific fields only).
     *
     * @param planID is the ID of the plan
     *
     * @return the updated test {@link TRPlan}.
     *
     * @apiNote Response codes
     * 200	Success, the test plan was updated and is returned as part of the response
     * 400	Invalid or unknown test plan
     * 403	No permissions to modify test plans or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/update_plan/{plan_id}")
    TRPlan updatePlan(TRPlan plan, @Param("plan_id") Long planID);

    /**
     * See {@link TestRailClient#updatePlan(TRPlan, Long)}
     */
    default TRPlan updatePlan(TRPlan plan) {
        return updatePlan(plan, plan.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-plans#close_plan">API: Close plan</a>
     *
     * Closes an existing test plan and archives its test runs & results.
     * @implNote Closing a test plan cannot be undone.
     *
     * @param planID is the ID of the plan
     *
     * @return the closed test {@link TRPlan}.
     *
     * @apiNote Response codes
     * 200	Success, the test plan and all its test runs were closed (archived).
     *      The test plan and its test runs are returned as part of the response.
     * 400	Invalid or unknown test plan
     * 403	No permissions to close test plans or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/close_plan/{plan_id}")
    TRPlan closePlan(@Param("plan_id") Long planID);

    /**
     * See {@link TestRailClient#closePlan(Long)}
     */
    default TRPlan closePlan(TRPlan plan) {
        return closePlan(plan.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-plans#delete_plan">API: Delete plan</a>
     *
     * Deletes an existing test plan.
     * @apiNote Deleting a test plan cannot be undone and also
     *          permanently deletes all test runs & results of the test plan.
     *
     * @param planID is the ID of the plan
     *
     * @apiNote Response codes
     * 200	Success, the test plan and all its test run were deleted
     * 400	Invalid or unknown test plan
     * 403	No permissions to delete test plans or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_plan/{plan_id}")
    void deletePlan(@Param("plan_id") Long planID);

    /**
     * See {@link TestRailClient#deletePlan(Long)}
     */
    default void deletePlan(TRPlan plan) {
        deletePlan(plan.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-plans#add_plan_entry">API: Add plan entry</a>
     *
     * Adds one or more new test {@link TRRun}s to a test {@link TRPlan}.
     *
     * @param planID is the ID of the plan the test runs should be added to
     * @param entry is the request body. The following POST fields are supported:
     *              suite_id, name, description, assignedto_id, include_all, case_ids, config_ids, runs
     *
     * @return the new test plan entry including test runs
     *
     * @apiNote Response codes
     * 200	Success, the test run(s) were created and are returned as part of the response.
     *      Please note that test runs in a plan are organized into 'entries' and these have
     *      their own IDs (to be used with update_plan_entry and delete_plan_entry, respectively).
     * 400	Invalid or unknown test plan
     * 403	No permissions to modify test plans or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_plan_entry/{plan_id}")
    TRPlanEntry addPlanEntry(TRPlanEntry entry, @Param("plan_id") Long planID);

    /**
     * See {@link TestRailClient#addPlanEntry(TRPlanEntry, Long)}
     */
    default TRPlanEntry addPlanEntry(TRPlanEntry entry, TRPlan plan) {
        return addPlanEntry(entry, plan.getId());
    }

    /**
     * See {@link TestRailClient#addPlanEntry(TRPlanEntry, Long)}
     */
    default TRPlanEntry addPlanEntry(TRPlanEntry entry, TRPlan plan, TRRun... runs) {
        return addPlanEntry(entry.withRuns(Arrays.asList(runs)), plan.getId());
    }

    /**
     * See {@link TestRailClient#addPlanEntry(TRPlanEntry, Long)}
     */
    default TRPlanEntry addPlanEntry(TRPlanEntry entry, TRPlan plan, TRSuite suite, TRRun... runs) {
        return addPlanEntry(entry.withRuns(Arrays.asList(runs)).withSuiteId(suite.getId()), plan.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-plans#update_plan_entry">API: Update plan entry</a>
     *
     * @param planID is the ID of the test plan
     * @param entryID is the ID of the test plan entry (note: not the test run ID)
     * @param entry is the request body. The following POST fields are supported:
     *              name, description, assignedto_id, include_all, case_ids
     *
     * @return the updated test @{@link TRPlanEntry} including test runs
     *
     * @apiNote Response codes
     * 200	Success, the test run(s) were updated and are returned as part of the response
     * 400	Invalid or unknown test plan or entry
     * 403	No permissions to modify test plans or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_plan_entry/{plan_id}/{entry_id}")
    TRPlanEntry updatePlanEntry(TRPlanEntry entry, @Param("plan_id") Long planID, @Param("entry_id") String entryID);

    /**
     * See {@link TestRailClient#updatePlanEntry(TRPlanEntry, Long, String)}
     */
    default TRPlanEntry updatePlanEntry(TRPlanEntry entry, TRPlan plan) {
        return updatePlanEntry(entry, plan.getId(), entry.getId());
    }

    /**
     * {@link TestRailClient}
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-plans#delete_plan_entry">API: Delete plan entry</a>
     *
     * Deletes one or more existing test runs from a plan.
     * @implNote Deleting a test run from a plan cannot be undone and also permanently deletes all related test results.
     *
     * @param planID is the ID of the test plan
     * @param entryID is the ID of the test plan entry (note: not the test run ID)
     *
     * @apiNote Response codes
     * 200	Success, the test run(s) were removed from the test plan
     * 400	Invalid or unknown test plan or entry
     * 403	No permissions to modify test plans or no access to the project
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_plan_entry/{plan_id}/{entry_id}")
    void deletePlanEntry(@Param("plan_id") Long planID, @Param("entry_id") String entryID);

    /**
     * See {@link TestRailClient#deletePlanEntry(Long, String)}
     */
    default void deletePlanEntry(TRPlan plan, TRPlanEntry entry) {
        deletePlanEntry(plan.getId(), entry.getId());
    }

}

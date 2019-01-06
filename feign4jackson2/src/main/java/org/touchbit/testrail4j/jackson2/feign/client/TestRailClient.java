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
import org.touchbit.testrail4j.core.type.FieldTypes;
import org.touchbit.testrail4j.jackson2.feign.client.expander.BoolExp;
import org.touchbit.testrail4j.jackson2.feign.client.expander.SuiteExp;
import org.touchbit.testrail4j.jackson2.model.*;

import java.util.List;

/**
 * This class explains how to access and use TestRail's API
 * to integrate automated tests, submit test results
 * and automate various aspects of TestRail.
 *
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Headers("Content-Type: application/json; charset=utf-8")
public interface TestRailClient {

    /** Utility table of contents method for quickly navigate through categories */
    default void tableOfContents() {
        apiResults();
        apiCases();
        apiProjects();
        apiRuns();
        apiSuites();
        apiSections();
        apiTests();
        apiCasesFields();
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results">API: Results</a>
     *
     * Utility empty method for quickly navigate through categories.
     * TOC - {@link TestRailClient#tableOfContents()}
     */
    default void apiResults() { /* do nothing */ }

    /**
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results#get_results">API: Get results</a>
     *
     * Get the latest 10 results for test with ID 1 and statuses 4 or 5 (Retest, Failed)
     * GET /index.php?/api/v2/get_results/1&status_id=4,5&limit=10
     *
     * @param testID is the ID of the test
     * @param getResultsQueryMap is the request filter. See {@link GetResultsQueryMap}
     *                           The latest 10 results for test with ID 1 and statuses 4 or 5 (Retest, Failed)
     *                           GET index.php?/api/v2/get_results/1&status_id=4,5&limit=10
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
    List<TRResult> getResults(@Param("test_id") Long testID, @QueryMap GetResultsQueryMap getResultsQueryMap);

    /**
     * See {@link TestRailClient#getResults(Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResults(Long testID) {
        return getResults(testID, new GetResultsQueryMap());
    }

    /**
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
     * @param getResultsQueryMap is the request filter. See {@link GetResultsQueryMap}
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
                                   @QueryMap GetResultsQueryMap getResultsQueryMap);

    /**
     * See {@link TestRailClient#getResultsForCase(Long, Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResultsForCase(@Param("run_id") Long runID, @Param("case_id") Long caseID) {
        return getResultsForCase(runID, caseID, new GetResultsQueryMap());
    }

    /**
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-results#get_results_for_run">API: Get results for run</a>
     *
     * Returns a list of test results for a test run.
     *
     * @param runID is the ID of the test run
     * @param getResultsQueryMap is the request filter. See {@link GetResultsQueryMap}
     *
     * @return a list of test {@link TRResult}s for a test run.
     *
     * @apiNote Response codes
     * 200	Success, the test results are returned as part of the response
     * 400	Invalid or unknown test run
     * 403	No access to the project
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_results_for_run/{run_id}")
    List<TRResult> getResultsForRun(@Param("run_id") Long runID, @QueryMap GetResultsQueryMap getResultsQueryMap);

    /**
     * See {@link TestRailClient#getResultsForRun(Long, GetResultsQueryMap)}
     */
    default List<TRResult> getResultsForRun(@Param("run_id") Long runID) {
        return getResultsForRun(runID, new GetResultsQueryMap());
    }

    /**
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
     * 	...
     * 	"custom_step_results": [
     * 		{
     * 			"content": "Step 1",
     * 			"expected": "Expected Result 1",
     * 			"actual": "Actual Result 1",
     * 			"status_id": 1
     * 		},
     * 		{
     * 			"content": "Step 2",
     * 			"expected": "Expected Result 2",
     * 			"actual": "Actual Result 2",
     * 			"status_id": 2
     * 		},
     * 		..
     * 	]
     * 	..
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

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases">API: Cases</a>
     *
     * Utility empty method for quickly navigate through categories.
     * TOC - {@link TestRailClient#tableOfContents()}
     */
    default void apiCases() { /* do nothing */ }

    /**
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
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases#get_cases">API: Get cases</a>
     *
     *
     * For example get all test cases for project with ID 1, suite with ID 2 and priority 3 or 4
     * GET /index.php?/api/v2/get_cases/1&suite_id=2&priority_id=3,4
     *
     * @param projectID is the ID of the project
     * @param queryMap is the following optional parameters and filters {@link GetCasesQueryMap}
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
        return getCases(projectID, new GetCasesQueryMap());
    }

    /**
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
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases#update_case">API: Update case</a>
     *
     * Updates an existing test case (partial updates are supported, i.e.
     * you can submit and update specific fields only).
     * This method supports the same POST fields as {@link TestRailClient#addCase(TRCase, Long)} (except section_id).
     *
     * @param caseID is the ID of the test case
     * @param aCase is the {@link TRCase}
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
    TRCase updateCase(TRCase aCase, @Param("case_id") Long caseID);

    /**
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

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-projects">API: Projects</a>
     *
     * Utility empty method for quickly navigate through categories.
     * TOC - {@link TestRailClient#tableOfContents()}
     */
    default void apiProjects() { /* do nothing */ }

    /**
     * http://docs.gurock.com/testrail-api2/reference-projects#get_project
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
     * http://docs.gurock.com/testrail-api2/reference-projects#get_projects
     *
     * Returns the list of available projects.
     *
     * @param isCompleted - true to return completed projects only. false to return active projects only.
     *
     * @return The response includes an array of projects.
     * Each project in this list follows the same format as
     * {@link TestRailClient#getProject(Long)}
     * [
     * 	{ "id": 1, "name": "DataHub", .. },
     * 	{ "id": 2, "name": "Writer", .. }
     * ]
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_projects&is_completed={is_completed}")
    List<TRProject> getProjects(@Param(value = "is_completed", expander = BoolExp.class) Boolean isCompleted);

    /**
     * See {@link TestRailClient#getProjects(Boolean)}
     */
    default List<TRProject> getProjects() {
        return getProjects(false);
    }

    /**
     * http://docs.gurock.com/testrail-api2/reference-projects#add_project
     *
     * Creates a new project (admin status required).
     *
     * @param name is the name of the project (required)
     * @param announcement is the description of the project
     * @param showAnnouncement - True if the announcement should be displayed
     *                         on the project's overview page and false otherwise
     * @param suiteMode is the suite mode of the project
     *                  (1 for single suite mode, 2 for single suite + baselines, 3 for multiple suites)
     *                  (added with TestRail 4.0)
     *
     * @return If successful, this method returns the new {@link TRProject}
     * using the same response format as {@link TestRailClient#getProject(Long)}.
     * { "id": 1, "name": "DataHub", .. }
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_project")
    TRProject addProject(@Param(value = "name") String name,
                       @Param(value = "announcement") String announcement,
                       @Param(value = "show_announcement", expander = BoolExp.class) Boolean showAnnouncement,
                       @Param(value = "suite_mode", expander = SuiteExp.class) SuiteMode suiteMode);

    /**
     * See {@link TestRailClient#addProject(String, String, Boolean, SuiteMode)}
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_project")
    TRProject addProject(TRProject project);

    /**
     * http://docs.gurock.com/testrail-api2/reference-projects#update_project
     *
     * Updates an existing project (admin status required;
     * partial updates are supported, i.e. you can submit and update specific fields only).
     *
     * @param projectID is the ID of the project
     * @param isCompleted is the specifies whether a project is considered completed or not
     * @param name is the name of the project (required)
     * @param announcement is the description of the project
     * @param showAnnouncement - True if the announcement should be displayed
     *                         on the project's overview page and false otherwise
     * @param suiteMode is the suite mode of the project
     *                  (1 for single suite mode, 2 for single suite + baselines, 3 for multiple suites)
     *                  (added with TestRail 4.0)
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
    TRProject updateProject(@Param("projectID") Long projectID,
                       @Param(value = "name") String name,
                       @Param(value = "announcement") String announcement,
                       @Param(value = "is_completed", expander = BoolExp.class) Boolean isCompleted,
                       @Param(value = "show_announcement", expander = BoolExp.class) Boolean showAnnouncement,
                       @Param(value = "suite_mode", expander = SuiteExp.class) SuiteMode suiteMode);

    /**
     * http://docs.gurock.com/testrail-api2/reference-projects#delete_project
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

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-runs">API: Runs</a>
     *
     * Utility empty method for quickly navigate through categories.
     * TOC - {@link TestRailClient#tableOfContents()}
     */
    default void apiRuns() { /* do nothing */ }

    /**
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
    default List<TRRun> getRuns(@Param("project_id") Long projectID) {
        return getRuns(projectID, new GetRunsQueryMap());
    }

    /**
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

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-suites">API: Suites</a>
     *
     * Utility empty method for quickly navigate through categories.
     * TOC - {@link TestRailClient#tableOfContents()}
     */
    default void apiSuites() { /* do nothing */ }

    /**
     * http://docs.gurock.com/testrail-api2/reference-suites#get_suite
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
     * http://docs.gurock.com/testrail-api2/reference-suites#get_suites
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
     * http://docs.gurock.com/testrail-api2/reference-suites#add_suite
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
     * http://docs.gurock.com/testrail-api2/reference-suites#update_suite
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
     * http://docs.gurock.com/testrail-api2/reference-suites#delete_suite
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

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-sections">API: Sections</a>
     *
     * Utility empty method for quickly navigate through categories.
     * TOC - {@link TestRailClient#tableOfContents()}
     */
    default void apiSections() { /* do nothing */ }

    /**
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
    default List<TRSection> getSections(@Param("project_id") Long projectID) {
        return getSections(projectID, new GetSectionsQueryMap());
    }

    /**
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

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-tests">API: Tests</a>
     *
     * Utility empty method for quickly navigate through categories.
     * TOC - {@link TestRailClient#tableOfContents()}
     */
    default void apiTests() { /* do nothing */ }

    /**
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
    default List<TRTest> getTests(@Param("run_id") Long runID) {
        return getTests(runID, new GetTestsQueryMap());
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * @see <a href="http://docs.gurock.com/testrail-api2/reference-cases-fields">API: Cases fields</a>
     *
     * Utility empty method for quickly navigate through categories.
     * TOC - {@link TestRailClient#tableOfContents()}
     */
    default void apiCasesFields() { /* do nothing */ }

    /**
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
    @RequestLine(value = "GET /index.php%3F/api/v2/add_case_field")
    TRCaseField addCaseField(TRCaseField caseField);

}

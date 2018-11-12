package org.touchbit.testrail4j.jackson2.feign.client;

import feign.*;
import org.touchbit.testrail4j.core.query.GetCasesQueryMap;
import org.touchbit.testrail4j.core.query.GetResultsQueryMap;
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
@SuppressWarnings("unused")
@Headers("Content-Type: application/json; charset=utf-8")
public interface TestRailClient {

    /*
     * API: Results
     * http://docs.gurock.com/testrail-api2/reference-results
     */

    /**
     * http://docs.gurock.com/testrail-api2/reference-results#get_results
     *
     * Get the latest 10 results for test with ID 1 and statuses 4 or 5 (Retest, Failed)
     * GET /index.php?/api/v2/get_results/1&status_id=4,5&limit=10
     *
     * @param testID - The ID of the test
     * @param getResultsQueryMap - see {@link GetResultsQueryMap}
     *
     * @return a list of test {@link Result}s for a test.
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
     * */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_results/{test_id}")
    List<Result> getResults(@Param("test_id") Long testID, @QueryMap GetResultsQueryMap getResultsQueryMap);

    /**
     * See {@link TestRailClient#getResults(Long, GetResultsQueryMap)}
     */
    default List<Result> getResults(Long testID) {
        return getResults(testID, new GetResultsQueryMap());
    }

    /**
     * http://docs.gurock.com/testrail-api2/reference-results#add_results_for_cases
     *
     * @param runID - The ID of the test run the results should be added to
     * @param results - {@link Results} object
     * The following listing shows a typical example request.
     * In addition to the test case, you need to specify at
     * least one of the status, comment or assignee fields for each result.
     * {
     *   "results": [
     *     {
     *       "case_id": 1,
     *       "status_id": 5,
     *       "comment": "This test failed",
     *       "defects": "TR-7"
     *     }
     *   ]
     * }
     * @return if successful, this method returns the new list of test {@link Result}s
     *  using the same response format as {@link TestRailClient#getResults(Long, GetResultsQueryMap)}
     *  and in the same order as the list of the request.
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_results_for_cases/{run_id}")
    List<Result> addResultsForCases(Results results, @Param("run_id") Long runID);

    /*
     * API: Cases
     * http://docs.gurock.com/testrail-api2/reference-cases
     */

    /**
     * http://docs.gurock.com/testrail-api2/reference-cases#get_case
     *
     * @param caseID - The ID of the test case
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
     * */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_case/{case_id}")
    Case getCase(@Param("case_id") Long caseID);

    /**
     * http://docs.gurock.com/testrail-api2/reference-cases#get_cases
     *
     * For example get all test cases for project with ID 1, suite with ID 2 and priority 3 or 4
     * GET /index.php?/api/v2/get_cases/1&suite_id=2&priority_id=3,4
     *
     * @param projectID - The ID of the project
     * @param queryMap - following optional parameters and filters {@link GetCasesQueryMap}
     *
     * @return a list of {@link Case} for a test suite or specific section in a test suite.
     * [
     * 	{ "id": 1, "title": "..", .. },
     * 	{ "id": 2, "title": "..", .. }
     * ]
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_cases/{project_id}")
    List<Case> getCases(@Param("project_id") Long projectID, @QueryMap GetCasesQueryMap queryMap);

    /**
     * See {@link TestRailClient#getCases(Long, GetCasesQueryMap)}
     */
    default List<Case> getCases(Long projectID) {
        return getCases(projectID, new GetCasesQueryMap());
    }

    /*
     * API: Projects
     * http://docs.gurock.com/testrail-api2/reference-projects
     */

    /**
     * http://docs.gurock.com/testrail-api2/reference-projects#get_project
     *
     * @param projectID - The ID of the project
     *
     * @return an existing {@link Project}.
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
    Project getProject(@Param("projectID") Long projectID);

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
    List<Project> getProjects(@Param("is_completed") Boolean isCompleted);

    /**
     * See {@link TestRailClient#getProjects(Boolean)}
     */
    default List<Project> getProjects() {
        return getProjects(false);
    }

    /*
     * API: Runs
     * http://docs.gurock.com/testrail-api2/reference-runs
     */

    /**
     * http://docs.gurock.com/testrail-api2/reference-runs#get_run
     *
     * @param runID - The ID of the test run
     *
     * @return an existing test run
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
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_run/{run_id}")
    Run getRun(@Param("run_id") Long runID);

    /*
     * API: Suites
     * http://docs.gurock.com/testrail-api2/reference-suites
     */

    /**
     * http://docs.gurock.com/testrail-api2/reference-suites#get_suite
     *
     * @param suiteID - The ID of the test suite
     *
     * @return an existing test {@link Suite}.
     * {
     * 	"description": "description",
     * 	"id": 1,
     * 	"name": "Setup & Installation",
     * 	"project_id": 1,
     * 	"url": "http://<server>/testrail/index.php?/suites/view/1"
     * }
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_suite/{suite_id}")
    Suite getSuite(@Param("suite_id") Long suiteID);

    /**
     * http://docs.gurock.com/testrail-api2/reference-suites#get_suites
     *
     * @param projectID - The ID of the project
     *
     * @return The response includes an array of test suite. Each test suite in this
     * list follows the same format as {@link TestRailClient#getSuite(Long)}.
     * [
     * 	{ "id": 1, "name": "Setup & Installation", .. },
     * 	{ "id": 2, "name": "Document Editing", .. }
     * ]
     */
    @RequestLine(value = "GET /index.php%3F/api/v2/get_suites/{project_id}")
    List<Suite> getSuites(@Param("project_id") Long projectID);

}

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
import org.touchbit.testrail4j.core.query.GetCasesQueryMap;
import org.touchbit.testrail4j.core.query.GetResultsQueryMap;
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
    List<Project> getProjects(@Param(value = "is_completed", expander = BoolExp.class) Boolean isCompleted);

    /**
     * See {@link TestRailClient#getProjects(Boolean)}
     */
    default List<Project> getProjects() {
        return getProjects(false);
    }

    /**
     * http://docs.gurock.com/testrail-api2/reference-projects#add_project
     *
     * Creates a new project (admin status required).
     *
     * @param name - The name of the project (required)
     * @param announcement - The description of the project
     * @param showAnnouncement - True if the announcement should be displayed
     *                         on the project's overview page and false otherwise
     * @param suiteMode - The suite mode of the project
     *                  (1 for single suite mode, 2 for single suite + baselines, 3 for multiple suites)
     *                  (added with TestRail 4.0)
     *
     * @return If successful, this method returns the new {@link Project}
     * using the same response format as {@link TestRailClient#getProject(Long)}.
     * { "id": 1, "name": "DataHub", .. }
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_project")
    Project addProject(@Param(value = "name") String name,
                       @Param(value = "announcement") String announcement,
                       @Param(value = "show_announcement", expander = BoolExp.class) Boolean showAnnouncement,
                       @Param(value = "suite_mode", expander = SuiteExp.class) SuiteMode suiteMode);

    /**
     * See {@link TestRailClient#addProject(String, String, Boolean, SuiteMode)}
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/add_project")
    Project addProject(Project project);

    /**
     * http://docs.gurock.com/testrail-api2/reference-projects#update_project
     *
     * Updates an existing project (admin status required;
     * partial updates are supported, i.e. you can submit and update specific fields only).
     *
     * @param projectID - The ID of the project
     * @param isCompleted - Specifies whether a project is considered completed or not
     * @param name - The name of the project (required)
     * @param announcement - The description of the project
     * @param showAnnouncement - True if the announcement should be displayed
     *                         on the project's overview page and false otherwise
     * @param suiteMode - The suite mode of the project
     *                  (1 for single suite mode, 2 for single suite + baselines, 3 for multiple suites)
     *                  (added with TestRail 4.0)
     *
     * @return If successful, this method returns the new {@link Project}
     * using the same response format as {@link TestRailClient#getProject(Long)}.
     * { "id": 1, "name": "DataHub", .. }
     *
     * @apiNote Response codes
     * 200 - Success, the project was updated and is returned as part of the response
     * 400 - Invalid or unknown project
     * 403 - No permissions to modify projects (requires admin rights)
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/update_project/{projectID}")
    Project updateProject(@Param("projectID") Long projectID,
                       @Param(value = "name") String name,
                       @Param(value = "announcement") String announcement,
                       @Param(value = "is_completed", expander = BoolExp.class) Boolean isCompleted,
                       @Param(value = "show_announcement", expander = BoolExp.class) Boolean showAnnouncement,
                       @Param(value = "suite_mode", expander = SuiteExp.class) SuiteMode suiteMode);

    /**
     * http://docs.gurock.com/testrail-api2/reference-projects#delete_project
     *
     * Deletes an existing project (admin status required).
     * Please note: Deleting a project cannot be undone and also permanently deletes all test suites & cases,
     * test runs & results and everything else that is part of the project.
     *
     * @param projectID - The ID of the project
     *
     * @apiNote Response codes
     * 200 - Success, the project was deleted
     * 400 - Invalid or unknown project
     * 403 - No permissions to delete projects (requires admin rights)
     */
    @RequestLine(value = "POST /index.php%3F/api/v2/delete_project/{projectID}")
    Project deleteProject(@Param("projectID") Long projectID);

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

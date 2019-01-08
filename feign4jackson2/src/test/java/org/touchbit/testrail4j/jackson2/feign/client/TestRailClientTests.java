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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.core.BaseUnitTest;
import org.touchbit.testrail4j.core.ExecutionLogger;
import org.touchbit.testrail4j.core.query.filter.*;
import org.touchbit.testrail4j.helpful.Auth;
import org.touchbit.testrail4j.jackson2.model.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.Statuses.FAILED;
import static org.touchbit.testrail4j.core.type.Statuses.PASSED;

/**
 * Created by Oleg Shaburov on 12.11.2018
 * shaburov.o.a@gmail.com
 */
@DisplayName("TestRail feign client interface tests")
class TestRailClientTests extends BaseUnitTest {

    private static final TestRailClient CLIENT = TestRailClientBuilder
            .build(new Auth(), TARGET, new ExecutionLogger(TEST_LOGGER));

    @Nested
    @DisplayName("API: Results")
    class APIResultsTests {

        @Test
        @DisplayName("TestRailClient#getResults(Integer, GetResultsQueryMap)")
        void unitTest_20181112032818() {
            GetResultsFilter resultsQueryMap = new GetResultsFilter()
                    .withLimit(1L)
                    .withOffset(2)
                    .withStatusId(3, 4, 5L)
                    .withCreatedBefore(22)
                    .withCreatedAfter(111)
                    .withCreatedBy(1,2);
            CLIENT.getResults(2818L, resultsQueryMap);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_results/2818");
            assertThat(msg).contains("&status_id=3%2C4%2C5");
            assertThat(msg).contains("&offset=2");
            assertThat(msg).contains("&created_after=111");
            assertThat(msg).contains("&created_before=22");
            assertThat(msg).contains("&limit=1");
            assertThat(msg).contains("&created_by=1%2C2");
        }

        @Test
        @DisplayName("TestRailClient#getResults(TRTest, GetResultsQueryMap)")
        void unitTest_20190107221313() {
            GetResultsFilter resultsQueryMap = new GetResultsFilter()
                    .withLimit(1L)
                    .withOffset(2)
                    .withStatusId(3, 4, 5L)
                    .withCreatedBefore(22)
                    .withCreatedAfter(111)
                    .withCreatedBy(1,2);
            CLIENT.getResults(new TRTest().withId(2818L), resultsQueryMap);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_results/2818");
            assertThat(msg).contains("&status_id=3%2C4%2C5");
            assertThat(msg).contains("&offset=2");
            assertThat(msg).contains("&created_after=111");
            assertThat(msg).contains("&created_before=22");
            assertThat(msg).contains("&limit=1");
            assertThat(msg).contains("&created_by=1%2C2");
        }

        @Test
        @DisplayName("TestRailClient#getResults(Long)")
        void unitTest_20181112040352() {
            CLIENT.getResults(352L);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_results/352");
        }

        @Test
        @DisplayName("TestRailClient#getResults(Long, GetResultsQueryMap)")
        void unitTest_20190107221459() {
            CLIENT.getResults(new TRTest().withId(352L));
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_results/352");
        }

        @Test
        @DisplayName("TestRailClient#getResultsForCase(Long, Long, GetResultsQueryMap)")
        void unitTest_20190107222421() {
            GetResultsFilter filter = new GetResultsFilter()
                    .withLimit(1L)
                    .withOffset(2)
                    .withStatusId(3, 4, 5L)
                    .withCreatedBefore(22)
                    .withCreatedAfter(111)
                    .withCreatedBy(1,2);
            CLIENT.getResultsForCase(1L,2L, filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_results_for_case/1/2");
            assertThat(msg).contains("&status_id=3%2C4%2C5");
            assertThat(msg).contains("&offset=2");
            assertThat(msg).contains("&created_after=111");
            assertThat(msg).contains("&created_before=22");
            assertThat(msg).contains("&limit=1");
            assertThat(msg).contains("&created_by=1%2C2");
        }

        @Test
        @DisplayName("TestRailClient#getResultsForCase(Long, Long)")
        void unitTest_20190107223029() {
            CLIENT.getResultsForCase(1L,2L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_results_for_case/1/2");
        }

        @Test
        @DisplayName("TestRailClient#getResultsForCase(TRRun, TRCase)")
        void unitTest_20190107223131() {
            CLIENT.getResultsForCase(new TRRun().withId(1L), new TRCase().withId(2L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_results_for_case/1/2");
        }

        @Test
        @DisplayName("TestRailClient#getResultsForCase(TRRun, TRCase, GetResultsQueryMap)")
        void unitTest_20190107223240() {
            GetResultsFilter filter = new GetResultsFilter()
                    .withLimit(1L)
                    .withOffset(2)
                    .withStatusId(3, 4, 5L)
                    .withCreatedBefore(22)
                    .withCreatedAfter(111)
                    .withCreatedBy(1,2);
            CLIENT.getResultsForCase(new TRRun().withId(1L), new TRCase().withId(2L), filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_results_for_case/1/2");
            assertThat(msg).contains("&status_id=3%2C4%2C5");
            assertThat(msg).contains("&offset=2");
            assertThat(msg).contains("&created_after=111");
            assertThat(msg).contains("&created_before=22");
            assertThat(msg).contains("&limit=1");
            assertThat(msg).contains("&created_by=1%2C2");
        }

        @Test
        @DisplayName("TestRailClient#getResultsForRun(Long, GetResultsQueryMap)")
        void unitTest_20190107223331() {
            GetResultsFilter filter = new GetResultsFilter()
                    .withLimit(1L)
                    .withOffset(2)
                    .withStatusId(3, 4, 5L)
                    .withCreatedBefore(22)
                    .withCreatedAfter(111)
                    .withCreatedBy(1,2);
            CLIENT.getResultsForRun(1L, filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_results_for_run/1");
            assertThat(msg).contains("&status_id=3%2C4%2C5");
            assertThat(msg).contains("&offset=2");
            assertThat(msg).contains("&created_after=111");
            assertThat(msg).contains("&created_before=22");
            assertThat(msg).contains("&limit=1");
            assertThat(msg).contains("&created_by=1%2C2");
        }

        @Test
        @DisplayName("TestRailClient#getResultsForRun(TRRun, GetResultsQueryMap)")
        void unitTest_20190107223519() {
            GetResultsFilter filter = new GetResultsFilter()
                    .withLimit(1L)
                    .withOffset(2)
                    .withStatusId(3, 4, 5L)
                    .withCreatedBefore(22)
                    .withCreatedAfter(111)
                    .withCreatedBy(1,2);
            CLIENT.getResultsForRun(new TRRun().withId(1L), filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_results_for_run/1");
            assertThat(msg).contains("&status_id=3%2C4%2C5");
            assertThat(msg).contains("&offset=2");
            assertThat(msg).contains("&created_after=111");
            assertThat(msg).contains("&created_before=22");
            assertThat(msg).contains("&limit=1");
            assertThat(msg).contains("&created_by=1%2C2");
        }

        @Test
        @DisplayName("TestRailClient#getResultsForRun(Long)")
        void unitTest_20190107223703() {
            CLIENT.getResultsForRun(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_results_for_run/1");
        }

        @Test
        @DisplayName("TestRailClient#getResultsForRun(TRRun)")
        void unitTest_20190107223749() {
            CLIENT.getResultsForRun(new TRRun().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_results_for_run/1");
        }

        @Test
        @DisplayName("TestRailClient#addResult(TRResult, Long)")
        void unitTest_20190107223950() {
            CLIENT.addResult(new TRResult(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_result/1");
        }

        @Test
        @DisplayName("TestRailClient#addResult(TRResult, TRTest)")
        void unitTest_20190107224249() {
            CLIENT.addResult(new TRResult(), new TRTest().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_result/1");
        }

        @Test
        @DisplayName("TestRailClient#addResultForCase(TRResult, Long, Long)")
        void unitTest_20190107224333() {
            CLIENT.addResultForCase(new TRResult(), 1L, 2L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_result_for_case/1/2");
        }

        @Test
        @DisplayName("TestRailClient#addResultForCase(TRResult, TRRun, TRCase)")
        void unitTest_20190107224548() {
            CLIENT.addResultForCase(new TRResult(), new TRRun().withId(1L), new TRCase().withId(2L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_result_for_case/1/2");
        }

        @Test
        @DisplayName("TestRailClient#addResults(TRResults, Long)")
        void unitTest_20190107224640() {
            CLIENT.addResults(new TRResults(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_results/1");
        }

        @Test
        @DisplayName("TestRailClient#addResults(TRResults, TRRun)")
        void unitTest_20190107224732() {
            CLIENT.addResults(new TRResults(), new TRRun().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_results/1");
        }

        @Test
        @DisplayName("TestRailClient#addResultsForCases(Results, Integer)")
        void unitTest_20181112132609() {
            CLIENT.addResultsForCases(new TRResults(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_results_for_cases/1");
        }

        @Test
        @DisplayName("TestRailClient#addResultsForCases(TRResults, TRRun)")
        void unitTest_20190107224834() {
            CLIENT.addResultsForCases(new TRResults(), new TRRun().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_results_for_cases/1");
        }

    }

    @Nested
    @DisplayName("API: Cases")
    class APICasesTests {

        @Test
        @DisplayName("TestRailClient#getCase(Integer)")
        void unitTest_20181112134433() {
            CLIENT.getCase(4433L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_case/4433");
        }

        @Test
        @DisplayName("TestRailClient#getCase(TRCase)")
        void unitTest_20190107225235() {
            CLIENT.getCase(new TRCase().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_case/1");
        }

        @Test
        @DisplayName("TestRailClient#getCases(Integer)")
        void unitTest_20181112134530() {
            GetCasesFilter filter = new GetCasesFilter()
                    .withCreatedAfter(1)
                    .withCreatedBefore(2)
                    .withCreatedBy(3, 1)
                    .withMilestoneId(4, 1)
                    .withPriorityId(5, 1)
                    .withTemplateId(6, 1)
                    .withTypeId(7, 1)
                    .withUpdatedAfter(8)
                    .withUpdatedBefore(9)
                    .withUpdatedBy(10, 1)
                    .withSuiteId(11)
                    .withSectionId(12L);
            CLIENT.getCases(4620L, filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_cases/4620");
            assertThat(msg).contains("&updated_before=9");
            assertThat(msg).contains("&priority_id=5%2C1");
            assertThat(msg).contains("&section_id=12");
            assertThat(msg).contains("&created_after=1");
            assertThat(msg).contains("&created_before=2");
            assertThat(msg).contains("&type_id=7%2C1");
            assertThat(msg).contains("&updated_after=8");
            assertThat(msg).contains("&milestone_id=4%2C1");
            assertThat(msg).contains("&updated_by=10%2C1");
            assertThat(msg).contains("&template_id=6%2C1");
            assertThat(msg).contains("&suite_id=11");
            assertThat(msg).contains("&created_by=3%2C1");
        }

        @Test
        @DisplayName("TestRailClient#getCases(Long)")
        void unitTest_20190107225322() {
            CLIENT.getCases(4620L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_cases/4620");
        }

        @Test
        @DisplayName("TestRailClient#getCases(TRProject)")
        void unitTest_20190107225732() {
            CLIENT.getCases(new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_cases/1");
        }

        @Test
        @DisplayName("TestRailClient#getCases(TRProject, GetCasesQueryMap)")
        void unitTest_20190107225805() {
            GetCasesFilter filter = new GetCasesFilter()
                    .withCreatedAfter(1)
                    .withCreatedBefore(2)
                    .withCreatedBy(3, 1)
                    .withMilestoneId(4, 1)
                    .withPriorityId(5, 1)
                    .withTemplateId(6, 1)
                    .withTypeId(7, 1)
                    .withUpdatedAfter(8)
                    .withUpdatedBefore(9)
                    .withUpdatedBy(10, 1)
                    .withSuiteId(11)
                    .withSectionId(12L);
            CLIENT.getCases(new TRProject().withId(1L), filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_cases/1");
            assertThat(msg).contains("&updated_before=9");
            assertThat(msg).contains("&priority_id=5%2C1");
            assertThat(msg).contains("&section_id=12");
            assertThat(msg).contains("&created_after=1");
            assertThat(msg).contains("&created_before=2");
            assertThat(msg).contains("&type_id=7%2C1");
            assertThat(msg).contains("&updated_after=8");
            assertThat(msg).contains("&milestone_id=4%2C1");
            assertThat(msg).contains("&updated_by=10%2C1");
            assertThat(msg).contains("&template_id=6%2C1");
            assertThat(msg).contains("&suite_id=11");
            assertThat(msg).contains("&created_by=3%2C1");
        }

        @Test
        @DisplayName("TestRailClient#addCase(TRCase, Long)")
        void unitTest_20190107225907() {
            CLIENT.addCase(new TRCase(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_case/1");
        }

        @Test
        @DisplayName("TestRailClient#addCase(TRCase, TRSection)")
        void unitTest_20190107230010() {
            CLIENT.addCase(new TRCase(), new TRSection().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_case/1");
        }

        @Test
        @DisplayName("TestRailClient#updateCase(TRCase, Long)")
        void unitTest_20190107230104() {
            CLIENT.updateCase(new TRCase(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_case/1");
        }

        @Test
        @DisplayName("TestRailClient#updateCase(TRCase)")
        void unitTest_20190107230142() {
            CLIENT.updateCase(new TRCase().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_case/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteCase(Long)")
        void unitTest_20190107230300() {
            CLIENT.deleteCase(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_case/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteCase(TRCase)")
        void unitTest_20190107230340() {
            CLIENT.deleteCase(new TRCase().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_case/1");
        }

    }

    @Nested
    @DisplayName("API: Projects")
    class APIProjectsTests {

        @Test
        @DisplayName("TestRailClient#getProject(Long)")
        void unitTest_20181112134626() {
            CLIENT.getProject(4626L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_project/4626");
        }

        @Test
        @DisplayName("TestRailClient#getProject(TRProject)")
        void unitTest_20190107230622() {
            CLIENT.getProject(new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_project/1");
        }

        @Test
        @DisplayName("TestRailClient#getProjects(GetProjectsQueryMap)")
        void unitTest_20190107230700() {
            GetProjectsFilter filter = new GetProjectsFilter();
            filter.withIsCompleted(true);
            CLIENT.getProjects(filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_projects");
            assertThat(msg).contains("&is_completed=1");
        }

        @Test
        @DisplayName("TestRailClient#getProjects()")
        void unitTest_20190107230825() {
            CLIENT.getProjects();
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_projects");
        }

        @Test
        @DisplayName("TestRailClient#addProject(TRProject)")
        void unitTest_20190107230920() {
            CLIENT.addProject(new TRProject());
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_project");
        }

        @Test
        @DisplayName("TestRailClient#updateProject(TRProject, Long)")
        void unitTest_20190107230946() {
            CLIENT.updateProject(new TRProject(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_project/1");
        }

        @Test
        @DisplayName("TestRailClient#updateProject(TRProject)")
        void unitTest_20190107231048() {
            CLIENT.updateProject(new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_project/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteProject(Long)")
        void unitTest_20190107231111() {
            CLIENT.deleteProject(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_project/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteProject(TRProject)")
        void unitTest_20190107231213() {
            CLIENT.deleteProject(new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_project/1");
        }
    }

    @Nested
    @DisplayName("API: Runs")
    class APIRunsTests {

        @Test
        @DisplayName("TestRailClient#getRun(Long)")
        void unitTest_20181112151321() {
            CLIENT.getRun(1321L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_run/1321");
        }

        @Test
        @DisplayName("TestRailClient#getRun(TRRun)")
        void unitTest_20190107231513() {
            CLIENT.getRun(new TRRun().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_run/1");
        }

        @Test
        @DisplayName("TestRailClient#getRuns(Long, GetRunsQueryMap)")
        void unitTest_20190107233028() {
            GetRunsFilter filter = new GetRunsFilter()
                    .withCreatedAfter(11111)
                    .withCreatedBefore(22222)
                    .withCreatedBy(1,2,3)
                    .withIsCompleted(false)
                    .withLimit(1)
                    .withMilestoneId(33,44)
                    .withOffset(13)
                    .withSuiteId(4,5,6);
            CLIENT.getRuns(1L, filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_runs/1");
            assertThat(msg).contains("&created_after=11111");
            assertThat(msg).contains("&offset=13");
            assertThat(msg).contains("&milestone_id=33%2C44");
            assertThat(msg).contains("&limit=1");
            assertThat(msg).contains("&createdBefore=22222");
            assertThat(msg).contains("&created_by=1%2C2%2C3");
            assertThat(msg).contains("&is_completed=0");
            assertThat(msg).contains("&suite_id=4%2C5%2C6");
        }

        @Test
        @DisplayName("TestRailClient#getRuns(Long)")
        void unitTest_20190107233250() {
            CLIENT.getRuns(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_runs/1");
        }

        @Test
        @DisplayName("TestRailClient#getRuns(TRProject)")
        void unitTest_20190107233334() {
            CLIENT.getRuns(new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_runs/1");
        }

        @Test
        @DisplayName("TestRailClient#getRuns(TRProject, GetRunsQueryMap)")
        void unitTest_20190107233406() {
            GetRunsFilter filter = new GetRunsFilter()
                    .withCreatedAfter(11111)
                    .withCreatedBefore(22222)
                    .withCreatedBy(1,2,3)
                    .withIsCompleted(false)
                    .withLimit(1)
                    .withMilestoneId(33,44)
                    .withOffset(13)
                    .withSuiteId(4,5,6);
            CLIENT.getRuns(new TRProject().withId(1L), filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_runs/1");
            assertThat(msg).contains("&created_after=11111");
            assertThat(msg).contains("&offset=13");
            assertThat(msg).contains("&milestone_id=33%2C44");
            assertThat(msg).contains("&limit=1");
            assertThat(msg).contains("&createdBefore=22222");
            assertThat(msg).contains("&created_by=1%2C2%2C3");
            assertThat(msg).contains("&is_completed=0");
            assertThat(msg).contains("&suite_id=4%2C5%2C6");
        }

        @Test
        @DisplayName("TestRailClient#addRun(TRRun, Long)")
        void unitTest_20190107233439() {
            CLIENT.addRun(new TRRun(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_run/1");
        }
        
        @Test
        @DisplayName("TestRailClient#addRun(TRRun, TRProject)")
        void unitTest_20190107233540() {
            CLIENT.addRun(new TRRun(), new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_run/1");
        }

        @Test
        @DisplayName("TestRailClient#updateRun(TRRun, Long)")
        void unitTest_20190107233630() {
            CLIENT.updateRun(new TRRun(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_run/1");
        }

        @Test
        @DisplayName("TestRailClient#updateRun(TRRun)")
        void unitTest_20190107233719() {
            CLIENT.updateRun(new TRRun().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_run/1");
        }

        @Test
        @DisplayName("TestRailClient#closeRun(Long)")
        void unitTest_20190107233816() {
            CLIENT.closeRun(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/close_run/1");
        }

        @Test
        @DisplayName("TestRailClient#closeRun(TRRun)")
        void unitTest_20190107233852() {
            CLIENT.closeRun(new TRRun().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/close_run/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteRun(Long)")
        void unitTest_20190107233920() {
            CLIENT.deleteRun(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_run/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteRun(TRRun)")
        void unitTest_20190107233948() {
            CLIENT.deleteRun(new TRRun().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_run/1");
        }
    }

    @Nested
    @DisplayName("API: Suites")
    class APISuitesTests {

        @Test
        @DisplayName("TestRailClient#getSuite(Long")
        void unitTest_20190108000031() {
            CLIENT.getSuite(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_suite/1");
        }

        @Test
        @DisplayName("TestRailClient#getSuite(TRSuite)")
        void unitTest_20190108000115() {
            CLIENT.getSuite(new TRSuite().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_suite/1");
        }

        @Test
        @DisplayName("TestRailClient#getSuites(Long)")
        void unitTest_20190108000436() {
            CLIENT.getSuites(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_suites/1");
        }

        @Test
        @DisplayName("TestRailClient#getSuites(TRProject)")
        void unitTest_20190108000506() {
            CLIENT.getSuites(new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_suites/1");
        }

        @Test
        @DisplayName("TestRailClient#addSuite(TRSuite, Long)")
        void unitTest_20190108000533() {
            CLIENT.addSuite(new TRSuite(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_suite/1");
        }

        @Test
        @DisplayName("TestRailClient#addSuite(TRSuite, TRProject)")
        void unitTest_20190108000619() {
            CLIENT.addSuite(new TRSuite(), new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_suite/1");
        }

        @Test
        @DisplayName("TestRailClient#updateSuite(TRSuite, Long)")
        void unitTest_20190108000713() {
            CLIENT.updateSuite(new TRSuite(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_suite/1");
        }
        
        @Test
        @DisplayName("TestRailClient#updateSuite(TRSuite)")
        void unitTest_20190108000746() {
            CLIENT.updateSuite(new TRSuite().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_suite/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteSuite(Long)")
        void unitTest_20190108000822() {
            CLIENT.deleteSuite(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_suite/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteSuite(TRSuite)")
        void unitTest_20190108000851() {
            CLIENT.deleteSuite(new TRSuite().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_suite/1");
        }
    }

    @Nested
    @DisplayName("API: Sections")
    class APISectionsTests {

        @Test
        @DisplayName("TestRailClient#addSection(TRSection, Long")
        void unitTest_20190108135707() {
            CLIENT.addSection(new TRSection(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_section/1");
        }

        @Test
        @DisplayName("TestRailClient#addSection(TRSection, TRProject)")
        void unitTest_20190108135754() {
            CLIENT.addSection(new TRSection(), new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_section/1");
        }

        @Test
        @DisplayName("TestRailClient#getSection(Long)")
        void unitTest_20190108135826() {
            CLIENT.getSection(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_section/1");
        }
        
        @Test
        @DisplayName("TestRailClient#getSection(TRSection)")
        void unitTest_20190108135907() {
            CLIENT.getSection(new TRSection().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_section/1");
        }

        @Test
        @DisplayName("TestRailClient#getSections(Long, GetSectionsQueryMap)")
        void unitTest_20190108135953() {
            CLIENT.getSections(1L, new GetSectionsFilter().withSuiteId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_sections/1");
            assertThat(msg).contains("&suite_id=1");
        }

        @Test
        @DisplayName("TestRailClient#getSections(Long)")
        void unitTest_20190108140150() {
            CLIENT.getSections(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_sections/1");
        }

        @Test
        @DisplayName("TestRailClient#getSections(TRProject)")
        void unitTest_20190108140232() {
            CLIENT.getSections(new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_sections/1");
        }

        @Test
        @DisplayName("TestRailClient#getSections(TRProject, GetSectionsQueryMap)")
        void unitTest_20190108140255() {
            CLIENT.getSections(new TRProject().withId(1L), new GetSectionsFilter().withSuiteId(22L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_sections/1");
            assertThat(msg).contains("&suite_id=22");
        }

        @Test
        @DisplayName("TestRailClient#updateSection(TRSection, Long)")
        void unitTest_20190108140337() {
            CLIENT.updateSection(new TRSection(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_section/1");
        }

        @Test
        @DisplayName("TestRailClient#updateSection(TRSection)")
        void unitTest_20190108140428() {
            CLIENT.updateSection(new TRSection().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_section/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteSection(Long)")
        void unitTest_20190108140508() {
            CLIENT.deleteSection(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_section/1");
        }
        
        @Test
        @DisplayName("TestRailClient#deleteSection(TRSection)")
        void unitTest_20190108140541() {
            CLIENT.deleteSection(new TRSection().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_section/1");
        }

    }

    @Nested
    @DisplayName("API: Tests")
    class APITestsTests {

        @Test
        @DisplayName("TestRailClient#getTest(Long)")
        void unitTest_20190108141447() {
            CLIENT.getTest(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_test/1");
        }
        
        @Test
        @DisplayName("TestRailClient#getTest(TRTest)")
        void unitTest_20190108141526() {
            CLIENT.getTest(new TRTest().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_test/1");
        }

        @Test
        @DisplayName("TestRailClient#getTests(Long, GetTestsQueryMap)")
        void unitTest_20190108141605() {
            GetTestsFilter filter = new GetTestsFilter();
            filter.withStatusId(PASSED, FAILED);
            CLIENT.getTests(1L, filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_tests/1");
            assertThat(msg).contains("&status_id=1%2C5");
        }
        
        @Test
        @DisplayName("TestRailClient#getTests(Long)")
        void unitTest_20190108141740() {
            CLIENT.getTests(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_tests/1");
            assertThat(msg).doesNotContain("&status_id");
        }

        @Test
        @DisplayName("TestRailClient#getTests(TRRun)")
        void unitTest_20190108141845() {
            CLIENT.getTests(new TRRun().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_tests/1");
            assertThat(msg).doesNotContain("&status_id");
        }

        @Test
        @DisplayName("TestRailClient#getTests(TRRun, GetTestsQueryMap)")
        void unitTest_20190108141923() {
            GetTestsFilter filter = new GetTestsFilter();
            filter.withStatusId(PASSED, FAILED);
            CLIENT.getTests(new TRRun().withId(1L), filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_tests/1");
            assertThat(msg).contains("&status_id=1%2C5");
        }

    }

    @Nested
    @DisplayName("API: Cases Fields")
    class APICasesFieldsTests {

        @Test
        @DisplayName("TestRailClient#getCaseFields()")
        void unitTest_20190108142112() {
            CLIENT.getCaseFields();
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_case_fields");
        }

        @Test
        @DisplayName("TestRailClient#addCaseField(TRCaseField)")
        void unitTest_20190108142154() {
            CLIENT.addCaseField(new TRCaseField());
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_case_field");
        }
    }

    @Nested
    @DisplayName("API: Cases Types")
    class APICasesTypesTests {

        @Test
        @DisplayName("TestRailClient#getCaseTypes()")
        void unitTest_20190108142359() {
            CLIENT.getCaseTypes();
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_case_types");
        }

    }

    @Nested
    @DisplayName("API: Configs")
    class APIConfigsTests {

        @Test
        @DisplayName("TestRailClient#getConfigs(Long)")
        void unitTest_20190108142459() {
            CLIENT.getConfigs(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_configs/1");
        }

        @Test
        @DisplayName("TestRailClient#getConfigs(TRProject)")
        void unitTest_20190108142536() {
            CLIENT.getConfigs(new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_configs/1");
        }

        @Test
        @DisplayName("TestRailClient#addConfigGroup(TRProjectConfigGroup, Long)")
        void unitTest_20190108142612() {
            CLIENT.addConfigGroup(new TRProjectConfigGroup(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_config_group/1");
        }

        @Test
        @DisplayName("TestRailClient#addConfigGroup(TRProjectConfigGroup, TRProject)")
        void unitTest_20190108142652() {
            CLIENT.addConfigGroup(new TRProjectConfigGroup(), new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_config_group/1");
        }

        @Test
        @DisplayName("TestRailClient#updateConfigGroup(TRProjectConfigGroup, Long)")
        void unitTest_20190108142731() {
            CLIENT.updateConfigGroup(new TRProjectConfigGroup(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_config_group/1");
        }

        @Test
        @DisplayName("TestRailClient#updateConfigGroup(TRProjectConfigGroup)")
        void unitTest_20190108142813() {
            CLIENT.updateConfigGroup(new TRProjectConfigGroup().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_config_group/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteConfigGroup(Long)")
        void unitTest_20190108142850() {
            CLIENT.deleteConfigGroup(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_config_group/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteConfigGroup(TRProjectConfigGroup)")
        void unitTest_20190108142919() {
            CLIENT.deleteConfigGroup(new TRProjectConfigGroup().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_config_group/1");
        }

        @Test
        @DisplayName("TestRailClient#addConfig(TRProjectConfig, Long)")
        void unitTest_20190108143005() {
            CLIENT.addConfig(new TRProjectConfig(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_config/1");
        }

        @Test
        @DisplayName("TestRailClient#addConfig(TRProjectConfig, TRProjectConfigGroup)")
        void unitTest_20190108172222() {
            CLIENT.addConfig(new TRProjectConfig(), new TRProjectConfigGroup().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_config/1");
        }

        @Test
        @DisplayName("TestRailClient#updateConfig(TRProjectConfig, Long)")
        void unitTest_20190108172303() {
            CLIENT.updateConfig(new TRProjectConfig(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_config/1");
        }

        @Test
        @DisplayName("TestRailClient#updateConfig(TRProjectConfig)")
        void unitTest_20190108172340() {
            CLIENT.updateConfig(new TRProjectConfig().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_config/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteConfig(Long)")
        void unitTest_20190108172418() {
            CLIENT.deleteConfig(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_config/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteConfig(TRProjectConfig)")
        void unitTest_20190108172432() {
            CLIENT.deleteConfig(new TRProjectConfig().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_config/1");
        }

    }

    @Nested
    @DisplayName("API: Milestones")
    class APIMilestonesTests {

        @Test
        @DisplayName("TestRailClient#getMilestone(Long")
        void unitTest_20190108181401() {
            CLIENT.getMilestone(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_milestone/1");
        }

        @Test
        @DisplayName("TestRailClient#getMilestone(TRMilestone)")
        void unitTest_20190108181508() {
            CLIENT.getMilestone(new TRMilestone().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_milestone/1");
        }

        @Test
        @DisplayName("TestRailClient#getMilestones(Long, GetMilestonesQueryMap)")
        void unitTest_20190108181544() {
            GetMilestonesFilter filter = new GetMilestonesFilter()
                    .withIsCompleted(true)
                    .withIsStarted(true);
            CLIENT.getMilestones(1L, filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_milestones/1");
            assertThat(msg).contains("&is_started=1");
            assertThat(msg).contains("&is_completed=1");
        }

        @Test
        @DisplayName("TestRailClient#getMilestones(Long)")
        void unitTest_20190108181722() {
            CLIENT.getMilestones(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_milestones/1");
            assertThat(msg).doesNotContain("is_started");
            assertThat(msg).doesNotContain("is_completed");
        }

        @Test
        @DisplayName("TestRailClient#getMilestones(TRProject)")
        void unitTest_20190108183237() {
            CLIENT.getMilestones(new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_milestones/1");
            assertThat(msg).doesNotContain("is_started");
            assertThat(msg).doesNotContain("is_completed");
        }

        @Test
        @DisplayName("TestRailClient#getMilestones(TRProject, GetMilestonesQueryMap)")
        void unitTest_20190108183305() {
            GetMilestonesFilter filter = new GetMilestonesFilter()
                    .withIsCompleted(true)
                    .withIsStarted(true);
            CLIENT.getMilestones(new TRProject().withId(1L), filter);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_milestones/1");
            assertThat(msg).contains("&is_started=1");
            assertThat(msg).contains("&is_completed=1");
        }

        @Test
        @DisplayName("TestRailClient#addMilestone(TRMilestone, Long)")
        void unitTest_20190108183520() {
            CLIENT.addMilestone(new TRMilestone(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_milestone/1");
        }

        @Test
        @DisplayName("TestRailClient#addMilestone(TRMilestone, TRProject)")
        void unitTest_20190108183630() {
            CLIENT.addMilestone(new TRMilestone(), new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/add_milestone/1");
        }

        @Test
        @DisplayName("TestRailClient#updateMilestone(TRMilestone, Long)")
        void unitTest_20190108183715() {
            CLIENT.updateMilestone(new TRMilestone(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_milestone/1");
        }
        
        @Test
        @DisplayName("TestRailClient#updateMilestone(TRMilestone)")
        void unitTest_20190108183751() {
            CLIENT.updateMilestone(new TRMilestone().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/update_milestone/1");
        }
        
        @Test
        @DisplayName("TestRailClient#deleteMilestone(Long)")
        void unitTest_20190108183812() {
            CLIENT.deleteMilestone(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_milestone/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteMilestone(TRMilestone)")
        void unitTest_20190108183850() {
            CLIENT.deleteMilestone(new TRMilestone().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(POST_API + "/delete_milestone/1");
        }

    }

    @Nested
    @DisplayName("API: Priorities")
    class APIPrioritiesTests {

        @Test
        @DisplayName("TestRailClient#getPriorities()")
        void unitTest_20190108184010() {
            CLIENT.getPriorities();
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_priorities");
        }

    }

    @Nested
    @DisplayName("API: Users")
    class APIUsersTests {

    }

    @Nested
    @DisplayName("API: Templates")
    class APITemplatesTests {

    }

    @Nested
    @DisplayName("API: Statuses")
    class APIStatusesTests {

    }

    @Nested
    @DisplayName("API: Results fields")
    class APIResultsFieldsTests {

    }

    @Nested
    @DisplayName("API: Plans")
    class APIPlansTests {

    }
}

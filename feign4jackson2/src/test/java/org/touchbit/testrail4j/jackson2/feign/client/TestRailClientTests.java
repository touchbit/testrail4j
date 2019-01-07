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
import org.touchbit.testrail4j.core.query.filter.GetCasesFilter;
import org.touchbit.testrail4j.core.query.filter.GetProjectsFilter;
import org.touchbit.testrail4j.core.query.filter.GetResultsFilter;
import org.touchbit.testrail4j.helpful.Auth;
import org.touchbit.testrail4j.jackson2.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
            assertThat(msg).contains("/get_cases/4620");
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
            assertThat(msg).contains("/get_cases/4620");
        }

        @Test
        @DisplayName("TestRailClient#getCases(TRProject)")
        void unitTest_20190107225732() {
            CLIENT.getCases(new TRProject().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains("/get_cases/1");
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
            assertThat(msg).contains("/get_cases/1");
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
            assertThat(msg).contains("/add_case/1");
        }

        @Test
        @DisplayName("TestRailClient#addCase(TRCase, TRSection)")
        void unitTest_20190107230010() {
            CLIENT.addCase(new TRCase(), new TRSection().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains("/add_case/1");
        }

        @Test
        @DisplayName("TestRailClient#updateCase(TRCase, Long)")
        void unitTest_20190107230104() {
            CLIENT.updateCase(new TRCase(), 1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains("/update_case/1");
        }

        @Test
        @DisplayName("TestRailClient#updateCase(TRCase)")
        void unitTest_20190107230142() {
            CLIENT.updateCase(new TRCase().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains("/update_case/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteCase(Long)")
        void unitTest_20190107230300() {
            CLIENT.deleteCase(1L);
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains("/delete_case/1");
        }

        @Test
        @DisplayName("TestRailClient#deleteCase(TRCase)")
        void unitTest_20190107230340() {
            CLIENT.deleteCase(new TRCase().withId(1L));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains("/delete_case/1");
        }

    }

    @Nested
    @DisplayName("API: Projects")
    class APIProjectsTests {

        @Test
        @DisplayName("TestRailClient#getProject(Long)")
        void unitTest_20181112134626() {
            CLIENT.getProject(4626L);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_project/4626");
        }

        @Test
        @DisplayName("TestRailClient#getProjects(Boolean)")
        void unitTest_20181112151002() {
            CLIENT.getProjects(new GetProjectsFilter().withIsCompleted(true));
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_projects");
            assertThat(msg).contains("is_completed=1");
        }

        @Test
        @DisplayName("TestRailClient#getProjects()")
        void unitTest_20181112151227() {
            CLIENT.getProjects();
            String msg = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(msg).contains(GET_API + "/get_projects");
        }
    }

    @Nested
    @DisplayName("API: Runs")
    class APIRunsTests {

        @Test
        @DisplayName("TestRailClient#getRun(Long)")
        void unitTest_20181112151321() {
            CLIENT.getRun(1321L);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_run/1321");
        }

    }

    @Nested
    @DisplayName("API: Suites")
    class APISuitesTests {

        @Test
        @DisplayName("TestRailClient#getSuite(Long)")
        void unitTest_20181112151636() {
            CLIENT.getSuite(1636L);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_suite/1636");
        }

        @Test
        @DisplayName("TestRailClient#getSuites(Long)")
        void unitTest_20181112151722() {
            CLIENT.getSuites(1722L);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_suites/1722");
        }

    }

}

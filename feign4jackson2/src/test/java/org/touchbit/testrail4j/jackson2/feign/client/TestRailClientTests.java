package org.touchbit.testrail4j.jackson2.feign.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.core.BaseUnitTest;
import org.touchbit.testrail4j.core.ExecutionLogger;
import org.touchbit.testrail4j.core.query.GetCasesQueryMap;
import org.touchbit.testrail4j.core.query.GetResultsQueryMap;
import org.touchbit.testrail4j.helpful.Auth;
import org.touchbit.testrail4j.jackson2.model.TRResult;
import org.touchbit.testrail4j.jackson2.model.TRResults;

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
            GetResultsQueryMap resultsQueryMap = new GetResultsQueryMap();
            resultsQueryMap.setLimit(10L);
            CLIENT.getResults(2818L, resultsQueryMap);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_results/2818&limit=10");
        }

        @Test
        @DisplayName("TestRailClient#getResults(Integer)")
        void unitTest_20181112040352() {
            CLIENT.getResults(352L);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_results/352");
        }

        @Test
        @DisplayName("TestRailClient#addResultsForCases(Results, Integer)")
        void unitTest_20181112132609() {
            TRResults results = new TRResults();
            List<TRResult> resultList = new ArrayList<>();
            resultList.add(new TRResult() {{ setId(1L); }});
            results.setResults(resultList);
            CLIENT.addResultsForCases(results, 2609L);
            String loggedMessages = TEST_LOGGER.takeLoggedMessages().toString();
            assertThat(loggedMessages).contains(POST_API + "/add_results_for_cases/2609");
            assertThat(loggedMessages).contains("{\n  \"results\" : [ {\n    \"id\" : 1,\n    \"custom_step_results\" : [ ]\n  } ]");
        }

    }

    @Nested
    @DisplayName("API: Cases")
    class APICasesTests {

        @Test
        @DisplayName("TestRailClient#getCase(Integer)")
        void unitTest_20181112134433() {
            CLIENT.getCase(4433L);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_case/4433");
        }

        @Test
        @DisplayName("TestRailClient#getCases(Integer)")
        void unitTest_20181112134530() {
            CLIENT.getCases(4620L);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_cases/4620");
        }

        @Test
        @DisplayName("TestRailClient#getCases(Integer, GetCasesQueryMap)")
        void unitTest_20181112134620() {
            GetCasesQueryMap map = new GetCasesQueryMap();
            map.setTypeId(3L);
            CLIENT.getCases(34530L, map);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString()).contains(GET_API + "/get_cases/34530&type_id=3");
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
            CLIENT.getProjects(true);
            assertThat(TEST_LOGGER.takeLoggedMessages().toString())
                    .contains(GET_API + "/get_projects&is_completed=1");
        }

        @Test
        @DisplayName("TestRailClient#getProjects()")
        void unitTest_20181112151227() {
            CLIENT.getProjects();
            assertThat(TEST_LOGGER.takeLoggedMessages().toString())
                    .contains(GET_API + "/get_projects&is_completed=0");
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

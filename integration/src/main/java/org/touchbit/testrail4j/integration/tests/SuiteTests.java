package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRProject;
import org.touchbit.testrail4j.jackson2.model.TRSuite;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings("WeakerAccess")
@Suite(service = TestRail.class, interfaze = API.class, task = "suite_operations")
public class SuiteTests extends BaseCorvusTest {

    @Test(description = "Expected successful suite creation with required fields")
    @Details
    public void test_20181231221005() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = new TRSuite().withName("name");
        TRSuite actualSuite = CLIENT.addNewSuite(suite, project);
        assertThat(actualSuite.getName()).isEqualTo(suite.getName());
    }

    @Test(description = "Expected successful suite creation with all fields")
    @Details()
    public void test_20181231221908() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = new TRSuite()
                .withName("test_20181231221908_name")
                .withDescription("test_20181231221908_description")
                .withId(100500L)
                .withCompletedOn(100500L)
                .withIsBaseline(true)
                .withIsCompleted(true)
                .withUrl("url")
                .withProjectId(123L)
                .withIsMaster(true);
        TRSuite actualSuite = CLIENT.addNewSuite(suite, project);
        assertThat(actualSuite.getName()).isEqualTo(suite.getName());
    }

    @Test(description = "Expected successful received existing suite")
    @Details()
    public void test_20181231222652() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addNewSuite(project);
        TRSuite actualSuite = CLIENT.getSuite(suite);
        assertThat(actualSuite).isEqualTo(suite);
    }

    @Test(description = "Expected successful received list existing suites")
    @Details()
    public void test_20181231223026() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addNewSuite(project);
        List<TRSuite> suiteList = CLIENT.getSuites(project);
        assertThat(suiteList).contains(suite);
    }

    @Test(description = "Expected successful update existing suite")
    @Details()
    public void test_20181231223730() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addNewSuite(project);
        suite.setName("test_20181231223730");
        TRSuite actualSuite = CLIENT.updateSuite(suite);
        assertThat(actualSuite).isEqualTo(suite);
    }

    @Test(description = "Expected successful delete existing suite")
    @Details()
    public void test_20181231224319() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addNewSuite(project);
        CLIENT.deleteSuite(suite);
        FeignException exception = executeThrowable(() -> CLIENT.getSuite(suite));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :suite_id is not a valid test suite.\"}");
    }

}

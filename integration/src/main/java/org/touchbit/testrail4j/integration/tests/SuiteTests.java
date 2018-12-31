package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.Project;
import org.touchbit.testrail4j.jackson2.model.Suite;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.integration.tests.ProjectTests.getNewProject;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings("WeakerAccess")
@org.touchbit.buggy.core.model.Suite(service = TestRail.class, interfaze = API.class, task = "suite_operations")
public class SuiteTests extends BaseCorvusTest {

    @Test(description = "Expected successful suite creation with required fields")
    @Details
    public void test_20181231221005() {
        Project project = getNewProject();
        Suite suite = new Suite().withName("name");
        addSuite(suite, project);
    }

    @Test(description = "Expected successful suite creation with all fields")
    @Details()
    public void test_20181231221908() {
        Project project = getNewProject();
        Suite suite = new Suite()
                .withName("name")
                .withDescription("description")
                .withId(100500L)
                .withCompletedOn(100500L)
                .withIsBaseline(true)
                .withIsCompleted(true)
                .withUrl("url")
                .withProjectId(123L)
                .withIsMaster(true);
        addSuite(suite, project);
    }

    @Test(description = "Expected successful received existing suite")
    @Details()
    public void test_20181231222652() {
        Project project = getNewProject();
        Suite suite = addSuite(project);
        Suite actualSuite = getSuite(suite);
        assertThat(actualSuite).isEqualTo(suite);
    }

    @Test(description = "Expected successful received list existing suites")
    @Details()
    public void test_20181231223026() {
        Project project = getNewProject();
        Suite suite = addSuite(project);
        List<Suite> suiteList = getSuites(project);
        assertThat(suiteList).contains(suite);
    }

    @Test(description = "Expected successful update existing suite")
    @Details()
    public void test_20181231223730() {
        Project project = getNewProject();
        Suite suite = addSuite(project);
        suite.setName("test_20181231223730");
        Suite actualSuite = updateSuite(suite);
        assertThat(actualSuite).isEqualTo(suite);
    }

    @Test(description = "Expected successful delete existing suite")
    @Details()
    public void test_20181231224319() {
        Project project = getNewProject();
        Suite suite = addSuite(project);
        deleteSuite(suite);
        FeignException exception = execute(() -> getSuite(suite));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :suite_id is not a valid test suite.\"}");
    }

    public static void deleteSuite(Suite suite) {
        step("Delete suite with ID: {}", suite.getId());
        CLIENT.deleteSuite(suite.getId());
    }

    public static Suite updateSuite(Suite suite) {
        step("Update suite with ID: {}", suite.getId());
        return CLIENT.updateSuite(suite);
    }

    public static Suite addSuite(Suite suite, Project project) {
        step("Create new suite with name: {}", suite.getName());
        return CLIENT.addSuite(suite, project.getId());
    }

    public static Suite addSuite(Project project) {
        Suite suite = new Suite().withName("name");
        return addSuite(suite, project);
    }

    public static Suite getSuite(Long suiteID) {
        step("Get suite with ID: {}", suiteID);
        return CLIENT.getSuite(suiteID);
    }

    public static Suite getSuite(Suite suite) {
        return getSuite(suite.getId());
    }

    public static List<Suite> getSuites(Project project) {
        step("Get list suites for project ID: {}", project.getId());
        return CLIENT.getSuites(project.getId());
    }

}

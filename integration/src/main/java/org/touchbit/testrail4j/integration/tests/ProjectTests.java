package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.Project;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.MULTIPLE;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings("WeakerAccess")
@Suite(service = TestRail.class, interfaze = API.class, task = "project_operations")
public class ProjectTests extends BaseCorvusTest {

    @Test(description = "Expected successful project creation with required fields")
    @Details()
    public void test_20181231050759() {
        String name = UUID.randomUUID().toString();
        step("Add new project with name: {}", name);
        Project project = CLIENT.addProject(name, null, null, null);
        assertThat(project.getAnnouncement()).isNull();
        assertThat(project.getCompletedOn()).isNull();
        assertThat(project.getIsCompleted()).isFalse();
        assertThat(project.getName()).isEqualTo(name);
        assertThat(project.getShowAnnouncement()).isFalse();
        assertThat(project.getId()).isGreaterThan(0);
        assertThat(project.getUrl()).isNotEmpty();
        assertThat(project.getSuiteMode()).isEqualTo(MULTIPLE.id());
    }

    @Test(description = "Expected successful project creation with all fields")
    @Details()
    public void test_20181231062525() {
        String name = UUID.randomUUID().toString();
        String announcement = UUID.randomUUID().toString();
        step("Add new project with name: {}", name);
        Project project = CLIENT.addProject(name, announcement, true, SINGLE);
        assertThat(project.getName()).isEqualTo(name);
        assertThat(project.getAnnouncement()).isEqualTo(announcement);
        assertThat(project.getShowAnnouncement()).isTrue();
        assertThat(project.getSuiteMode()).isEqualTo(SINGLE.id());
        assertThat(project.getCompletedOn()).isNull();
        assertThat(project.getIsCompleted()).isFalse();
        assertThat(project.getId()).isGreaterThan(0);
        assertThat(project.getUrl()).isNotEmpty();
    }

    @Test(description = "Expected successful project creation with Project json object")
    @Details()
    public void test_20181231181048() {
        Project project = new Project()
                .withAnnouncement(UUID.randomUUID().toString())
                .withName(UUID.randomUUID().toString())
                .withSuiteMode(2L)
                .withIsCompleted(false)
                .withShowAnnouncement(true);
        step("Add new project with name: {}", project.getName());
        Project actualProject = CLIENT.addProject(project);
        assertThat(actualProject.getName()).isEqualTo(project.getName());
        assertThat(actualProject.getAnnouncement()).isEqualTo(project.getAnnouncement());
        assertThat(actualProject.getShowAnnouncement()).isTrue();
        assertThat(actualProject.getSuiteMode()).isEqualTo(project.getSuiteMode());
        assertThat(actualProject.getCompletedOn()).isNull();
        assertThat(actualProject.getIsCompleted()).isFalse();
        assertThat(actualProject.getId()).isGreaterThan(0);
        assertThat(actualProject.getUrl()).isNotEmpty();
    }

    @Test(description = "Expected successful delete existing project")
    @Details()
    public void test_20181231184200() {
        Project project = getNewProject();
        deleteProject(project);
        FeignException exception = execute(() -> getProject(project));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :project_id is not a valid or accessible project.\"}");
    }

    @Test(description = "Expected successful receive existing project")
    @Details()
    public void test_20181231190218() {
        Project project = getNewProject();
        Project actualProject = getProject(project);
        assertThat(actualProject.getName()).isEqualTo(project.getName());
        assertThat(actualProject.getAnnouncement()).isEqualTo(project.getAnnouncement());
        assertThat(actualProject.getShowAnnouncement()).isTrue();
        assertThat(actualProject.getSuiteMode()).isEqualTo(project.getSuiteMode());
        assertThat(actualProject.getCompletedOn()).isNull();
        assertThat(actualProject.getIsCompleted()).isFalse();
        assertThat(actualProject.getId()).isGreaterThan(0);
        assertThat(actualProject.getUrl()).isNotEmpty();
    }

    @Test(description = "Expected successful receive existing projects list")
    @Details()
    public void test_20181231190411() {
        Project project1 = getNewProject();
        Project project2 = getNewProject();
        List<Long> actualProject = getProjects().stream().map(Project::getId).collect(Collectors.toList());
        assertThat(actualProject).contains(project1.getId());
        assertThat(actualProject).contains(project2.getId());
    }

    /**
     * Util method
     * Get an existing actual project
     */
    public static List<Project> getProjects() {
        step("Get existing projects list");
        return CLIENT.getProjects();
    }

    /**
     * Util method
     * Get an existing project
     */
    public static Project getProject(Long projectID) {
        step("Get project with ID: {}", projectID);
        return CLIENT.getProject(projectID);
    }

    /**
     * see {@link ProjectTests#getProject(Long)}
     */
    public static Project getProject(Project project) {
        return getProject(project.getId());
    }

    /**
     * Util method
     * Deletes an existing project
     */
    public static void deleteProject(Long projectID) {
        step("Delete project with ID: {}", projectID);
        CLIENT.deleteProject(projectID);
    }

    /**
     * see {@link ProjectTests#deleteProject(Long)}
     */
    public static void deleteProject(Project project) {
        deleteProject(project.getId());
    }

    /**
     * Util method
     * @return generated {@link Project}
     */
    public static Project getNewProject() {
        String name = UUID.randomUUID().toString();
        String announcement = UUID.randomUUID().toString();
        step("Add new project with name: {}", name);
        return CLIENT.addProject(name, announcement, true, MULTIPLE);
    }

}

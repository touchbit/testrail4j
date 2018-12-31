package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.MULTIPLE;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
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

    /**
     * Util method
     * @return generated {@link Project}
     */
    public static Project getNewProject() {
        String name = UUID.randomUUID().toString();
        String announcement = UUID.randomUUID().toString();
        step("Add new project with name: {}", name);
        return CLIENT.addProject(name, announcement, true, SINGLE);
    }

}

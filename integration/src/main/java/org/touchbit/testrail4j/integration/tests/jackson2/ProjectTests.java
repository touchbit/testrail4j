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

package org.touchbit.testrail4j.integration.tests.jackson2;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.core.query.filter.GetProjectsFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseCorvusTest;
import org.touchbit.testrail4j.jackson2.model.TRProject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.SuiteMode.MULTIPLE;
import static org.touchbit.testrail4j.core.type.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings("WeakerAccess")
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "project_operations")
public class ProjectTests extends BaseCorvusTest {

    @Test(description = "Expecting successful the project creation with required fields")
    @Details()
    public void test_20181231050759() {
        String name = UUID.randomUUID().toString();
        step("Add new project with name: {}", name);
        TRProject project = new TRProject().withName(name);
        TRProject actProject = J2_CLIENT.addProject(project);
        assertThat(actProject.getAnnouncement()).isNull();
        assertThat(actProject.getCompletedOn()).isNull();
        assertThat(actProject.getIsCompleted()).isFalse();
        assertThat(actProject.getName()).isEqualTo(name);
        assertThat(actProject.getShowAnnouncement()).isFalse();
        assertThat(actProject.getId()).isGreaterThan(0);
        assertThat(actProject.getUrl()).isNotEmpty();
        assertThat(actProject.getSuiteMode()).isEqualTo(MULTIPLE.getId());
        assertThat(actProject.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting successful the project creation with all fields")
    @Details()
    public void test_20181231062525() {
        String name = UUID.randomUUID().toString();
        String announcement = UUID.randomUUID().toString();
        step("Add new project with name: {}", name);
        TRProject project = new TRProject()
                .withName(name)
                .withAnnouncement(announcement)
                .withShowAnnouncement(true)
                .withSuiteMode(SINGLE.getId());
        TRProject actProject = J2_CLIENT.addProject(project);
        assertThat(actProject.getName()).isEqualTo(name);
        assertThat(actProject.getAnnouncement()).isEqualTo(announcement);
        assertThat(actProject.getShowAnnouncement()).isTrue();
        assertThat(actProject.getSuiteMode()).isEqualTo(SINGLE.getId());
        assertThat(actProject.getCompletedOn()).isNull();
        assertThat(actProject.getIsCompleted()).isFalse();
        assertThat(actProject.getId()).isGreaterThan(0);
        assertThat(actProject.getUrl()).isNotEmpty();
    }

    @Test(description = "Expecting successful the project creation with Project json object")
    @Details()
    public void test_20181231181048() {
        TRProject project = new TRProject()
                .withAnnouncement(UUID.randomUUID().toString())
                .withName(UUID.randomUUID().toString())
                .withSuiteMode(2L)
                .withIsCompleted(false)
                .withShowAnnouncement(true);
        step("Add new project with name: {}", project.getName());
        TRProject actualProject = J2_CLIENT.addProject(project);
        assertThat(actualProject.getName()).isEqualTo(project.getName());
        assertThat(actualProject.getAnnouncement()).isEqualTo(project.getAnnouncement());
        assertThat(actualProject.getShowAnnouncement()).isTrue();
        assertThat(actualProject.getSuiteMode()).isEqualTo(project.getSuiteMode());
        assertThat(actualProject.getCompletedOn()).isNull();
        assertThat(actualProject.getIsCompleted()).isFalse();
        assertThat(actualProject.getId()).isGreaterThan(0);
        assertThat(actualProject.getUrl()).isNotEmpty();
    }

    @Test(description = "Expecting successful delete the existing project")
    @Details()
    public void test_20181231184200() {
        TRProject project = J2_CLIENT.getProject();
        J2_CLIENT.deleteProject(project);
        FeignException exception = executeThrowable(() -> J2_CLIENT.getProject(project));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :project_id is not a valid or accessible project.\"}");
    }

    @Test(description = "Expecting successful receive the existing project")
    @Details()
    public void test_20181231190218() {
        TRProject project = J2_CLIENT.getProject();
        TRProject actualProject = J2_CLIENT.getProject(project);
        assertThat(actualProject.getName()).isEqualTo(project.getName());
        assertThat(actualProject.getAnnouncement()).isEqualTo(project.getAnnouncement());
        assertThat(actualProject.getShowAnnouncement()).isTrue();
        assertThat(actualProject.getSuiteMode()).isEqualTo(project.getSuiteMode());
        assertThat(actualProject.getCompletedOn()).isNull();
        assertThat(actualProject.getIsCompleted()).isFalse();
        assertThat(actualProject.getId()).isGreaterThan(0);
        assertThat(actualProject.getUrl()).isNotEmpty();
    }

    @Test(description = "Expecting successful receive the existing projects list")
    @Details()
    public void test_20181231190411() {
        TRProject project1 = J2_CLIENT.getProject();
        TRProject project2 = J2_CLIENT.getProject();
        List<Long> actualProject = J2_CLIENT.getTRProjects().stream().map(TRProject::getId).collect(Collectors.toList());
        assertThat(actualProject).contains(project1.getId());
        assertThat(actualProject).contains(project2.getId());
    }

    @Test(description = "Expecting successful receive the projects list with filter")
    @Details()
    public void test_20190107160932() {
        J2_CLIENT.getProject();
        J2_CLIENT.getProject();
        List<TRProject> actualProjects = J2_CLIENT.getTRProjects(new GetProjectsFilter().withIsCompleted(false));
        for (TRProject actualProject : actualProjects) {
            assertThat(actualProject.getAdditionalProperties()).isEmpty();
        }
    }


}

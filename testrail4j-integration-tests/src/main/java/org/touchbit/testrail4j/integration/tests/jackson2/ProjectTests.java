/*
 * MIT License
 *
 * Copyright © 2020 TouchBIT.
 * Copyright © 2020 Oleg Shaburov.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
import org.touchbit.testrail4j.integration.tests.BaseJackson2Test;
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
public class ProjectTests extends BaseJackson2Test {

    @Test(description = "Expecting successful the project creation with required fields")
    @Details()
    public void test_20181231050759() {
        String name = UUID.randomUUID().toString();
        step("Add new project with name: {}", name);
        TRProject project = new TRProject().withName(name);
        TRProject actProject = CLIENT.addProject(project);
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
        TRProject actProject = CLIENT.addProject(project);
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
        TRProject actualProject = CLIENT.addProject(project);
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
        TRProject project = CLIENT.getProject();
        CLIENT.deleteProject(project);
        FeignException exception = executeThrowable(() -> CLIENT.getProject(project));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :project_id is not a valid or accessible project.\"}");
    }

    @Test(description = "Expecting successful receive the existing project")
    @Details()
    public void test_20181231190218() {
        TRProject project = CLIENT.getProject();
        TRProject actualProject = CLIENT.getProject(project);
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
        TRProject project1 = CLIENT.getProject();
        TRProject project2 = CLIENT.getProject();
        List<Long> actualProject = CLIENT.getTRProjects().stream().map(TRProject::getId).collect(Collectors.toList());
        assertThat(actualProject).contains(project1.getId());
        assertThat(actualProject).contains(project2.getId());
    }

    @Test(description = "Expecting successful receive the projects list with filter")
    @Details()
    public void test_20190107160932() {
        CLIENT.getProject();
        CLIENT.getProject();
        List<TRProject> actualProjects = CLIENT.getTRProjects(new GetProjectsFilter().withIsCompleted(false));
        for (TRProject actualProject : actualProjects) {
            assertThat(actualProject.getAdditionalProperties()).isEmpty();
        }
    }


}

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
import org.touchbit.testrail4j.core.query.filter.GetSectionsFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseCorvusTest;
import org.touchbit.testrail4j.jackson2.model.TRProject;
import org.touchbit.testrail4j.jackson2.model.TRSection;
import org.touchbit.testrail4j.jackson2.model.TRSuite;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.SuiteMode.MULTIPLE;
import static org.touchbit.testrail4j.core.type.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings("WeakerAccess")
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "section_operations")
public class SectionTests extends BaseCorvusTest {

    @Test(description = "Expected successful section creation with required fields")
    @Details()
    public void test_20190101160155() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRSection section = new TRSection().withName(UUID.randomUUID().toString());
        TRSection actualSection = J2_CLIENT.addSection(section, project);
        assertThat(actualSection.getName()).isEqualTo(section.getName());
        assertThat(actualSection.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expected successful section creation with all fields")
    @Details()
    public void test_20190101161936() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRSection section = genSection();
        TRSection actualSection = J2_CLIENT.addSection(section, project);
        assertThat(actualSection.getName()).isEqualTo(section.getName());
        assertThat(actualSection.getDescription()).isEqualTo(section.getDescription());
    }

    @Test(description = "Expected successful subsection creation")
    @Details()
    public void test_20190101195318() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRSection section = genSection();
        TRSection actualSection = J2_CLIENT.addSection(section, project);
        TRSection subSection = genSection().withParentId(actualSection.getId());
        TRSection actualSubSection = J2_CLIENT.addSection(subSection, project);
        assertThat(actualSubSection.getName()).isEqualTo(subSection.getName());
        assertThat(actualSubSection.getDescription()).isEqualTo(subSection.getDescription());
        assertThat(actualSubSection.getParentId()).isEqualTo(subSection.getParentId());
    }

    @Test(description = "Expected successful section creation for project with multiple suite mode")
    @Details()
    public void test_20190101162032() {
        TRProject project = J2_CLIENT.getProject(MULTIPLE);
        TRSuite suite = J2_CLIENT.addSuite(project);
        TRSection section = genSection().withSuiteId(suite.getId());
        TRSection actualSection = J2_CLIENT.addSection(section, project);
        assertThat(actualSection.getName()).isEqualTo(section.getName());
        assertThat(actualSection.getDescription()).isEqualTo(section.getDescription());
        assertThat(actualSection.getSuiteId()).isEqualTo(suite.getId());
    }

    @Test(description = "Expected successful receive existing section")
    @Details()
    public void test_20190101162834() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRSection section = J2_CLIENT.addSection(genSection(), project);
        TRSection actualSection = J2_CLIENT.getSection(section);
        assertThat(actualSection).isEqualTo(section);
    }

    @Test(description = "Expected successful receive sections list")
    @Details()
    public void test_20190107182532() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRSection section = J2_CLIENT.addSection(genSection(), project);
        List<TRSection> actualSection = J2_CLIENT.getSections(project);
        assertThat(actualSection).isNotEmpty();
        assertThat(actualSection).hasSize(1);
        for (TRSection trSection : actualSection) {
            assertThat(trSection).isEqualTo(section);
            assertThat(trSection.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expected successful receive sections list with filter")
    @Details()
    public void test_20190107183047() {
        TRProject project = J2_CLIENT.getProject(MULTIPLE);
        TRSuite suite = J2_CLIENT.addSuite(project);
        TRSection section = J2_CLIENT.addSection(genSection().withSuiteId(suite.getId()), project);
        List<TRSection> actualSection = J2_CLIENT.getSections(project, new GetSectionsFilter().withSuiteId(suite.getId()));
        assertThat(actualSection).isNotEmpty();
        assertThat(actualSection).hasSize(1);
        for (TRSection trSection : actualSection) {
            assertThat(trSection).isEqualTo(section);
            assertThat(trSection.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expected successful update existing section")
    @Details()
    public void test_20190101194252() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRSection section = J2_CLIENT.addSection(genSection(), project);
        section.setName("test_20190101194252");
        TRSection actualSection = J2_CLIENT.updateSection(section);
        assertThat(actualSection).isEqualTo(section);
    }

    @Test(description = "Expected successful delete existing section")
    @Details()
    public void test_20190101194905() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRSection section = J2_CLIENT.addSection(genSection(), project);
        J2_CLIENT.deleteSection(section);
        FeignException exception = executeThrowable(() -> J2_CLIENT.getSection(section));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :section_id is not a valid section.\"}");
    }

    public static TRSection genSection() {
        return new TRSection()
                .withName(UUID.randomUUID().toString())
                .withDescription(UUID.randomUUID().toString());
    }

}
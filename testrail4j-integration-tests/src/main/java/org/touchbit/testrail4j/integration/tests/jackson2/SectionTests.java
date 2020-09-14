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
import org.touchbit.testrail4j.core.query.filter.GetSectionsFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseJackson2Test;
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
public class SectionTests extends BaseJackson2Test {

    @Test(description = "Expected successful section creation with required fields")
    @Details()
    public void test_20190101160155() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = new TRSection().withName(UUID.randomUUID().toString());
        TRSection actualSection = CLIENT.addSection(section, project);
        assertThat(actualSection.getName()).isEqualTo(section.getName());
        assertThat(actualSection.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expected successful section creation with all fields")
    @Details()
    public void test_20190101161936() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = genSection();
        TRSection actualSection = CLIENT.addSection(section, project);
        assertThat(actualSection.getName()).isEqualTo(section.getName());
        assertThat(actualSection.getDescription()).isEqualTo(section.getDescription());
    }

    @Test(description = "Expected successful subsection creation")
    @Details()
    public void test_20190101195318() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = genSection();
        TRSection actualSection = CLIENT.addSection(section, project);
        TRSection subSection = genSection().withParentId(actualSection.getId());
        TRSection actualSubSection = CLIENT.addSection(subSection, project);
        assertThat(actualSubSection.getName()).isEqualTo(subSection.getName());
        assertThat(actualSubSection.getDescription()).isEqualTo(subSection.getDescription());
        assertThat(actualSubSection.getParentId()).isEqualTo(subSection.getParentId());
    }

    @Test(description = "Expected successful section creation for project with multiple suite mode")
    @Details()
    public void test_20190101162032() {
        TRProject project = CLIENT.getProject(MULTIPLE);
        TRSuite suite = CLIENT.addSuite(project);
        TRSection section = genSection().withSuiteId(suite.getId());
        TRSection actualSection = CLIENT.addSection(section, project);
        assertThat(actualSection.getName()).isEqualTo(section.getName());
        assertThat(actualSection.getDescription()).isEqualTo(section.getDescription());
        assertThat(actualSection.getSuiteId()).isEqualTo(suite.getId());
    }

    @Test(description = "Expected successful receive existing section")
    @Details()
    public void test_20190101162834() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(genSection(), project);
        TRSection actualSection = CLIENT.getSection(section);
        assertThat(actualSection).isEqualTo(section);
    }

    @Test(description = "Expected successful receive sections list")
    @Details()
    public void test_20190107182532() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(genSection(), project);
        List<TRSection> actualSection = CLIENT.getSections(project);
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
        TRProject project = CLIENT.getProject(MULTIPLE);
        TRSuite suite = CLIENT.addSuite(project);
        TRSection section = CLIENT.addSection(genSection().withSuiteId(suite.getId()), project);
        List<TRSection> actualSection = CLIENT.getSections(project, new GetSectionsFilter().withSuiteId(suite.getId()));
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
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(genSection(), project);
        section.setName("test_20190101194252");
        TRSection actualSection = CLIENT.updateSection(section);
        assertThat(actualSection).isEqualTo(section);
    }

    @Test(description = "Expected successful delete existing section")
    @Details()
    public void test_20190101194905() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(genSection(), project);
        CLIENT.deleteSection(section);
        FeignException exception = executeThrowable(() -> CLIENT.getSection(section));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :section_id is not a valid section.\"}");
    }

    public static TRSection genSection() {
        return new TRSection()
                .withName(UUID.randomUUID().toString())
                .withDescription(UUID.randomUUID().toString());
    }

}

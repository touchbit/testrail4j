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
import org.touchbit.testrail4j.core.query.filter.GetCasesFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseCorvusTest;
import org.touchbit.testrail4j.jackson2.model.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.CaseTypes.ACCEPTANCE;
import static org.touchbit.testrail4j.core.type.Priorities.CRITICAL;
import static org.touchbit.testrail4j.core.type.Templates.TEST_CASE_TEXT;
import static org.touchbit.testrail4j.core.type.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "case_operations")
public class CaseTests extends BaseCorvusTest {

    @Test(description = "Expecting successful creation of a case with required fields")
    @Details()
    public void test_20190101195810() {
        TRSection section = J2_CLIENT.addSection();
        TRCase caze = new TRCase().withTitle("test_20190101195810").withSectionId(section.getId());
        TRCase actualCaze = J2_CLIENT.addCase(caze);
        assertThat(actualCaze.getTitle()).isEqualTo(caze.getTitle());
        assertThat(actualCaze.getSectionId()).isEqualTo(caze.getSectionId());
    }

    @Test(description = "Expecting successful creation of a case with all fields")
    @Details()
    public void test_20190101201312() {
        TRSection section = J2_CLIENT.addSection();
        long time = new Date().getTime() / 1000;
        TRCase caze = new TRCase()
                .withTitle("test_20190101201312")
                .withSectionId(section.getId())
                .withCreatedBy(1L)
                .withUpdatedBy(1L)
                .withCreatedOn(time)
                .withPriorityId(CRITICAL.getId())
                .withSuiteId(section.getSuiteId())
                .withRefs("JIRA-123")
                .withTypeId(ACCEPTANCE.getId())
                .withTemplateId(TEST_CASE_TEXT.getId())
                .withEstimate("1m 45s")
                .withCustomPreconds("withCustomPreconds")
                .withCustomSteps("withCustomSteps")
                .withCustomExpected("withCustomExpected")
                .withCustomStepsSeparated(null)
                ;
        TRCase actualCaze = J2_CLIENT.addCase(caze);
        actualCaze.getAdditionalProperties().clear();
        caze.setId(actualCaze.getId());
        caze.setUpdatedOn(actualCaze.getUpdatedOn());
        caze.setCreatedOn(actualCaze.getCreatedOn());
        assertThat(actualCaze).isEqualTo(caze);
    }

    @Test(description = "Expecting successful update of existing test case")
    @Details()
    public void test_20190101204226() {
        TRCase caze = J2_CLIENT.addCase();
        caze.setTitle("test_20190101204226");
        TRCase actualCaze = J2_CLIENT.updateCase(caze);
        caze.setUpdatedOn(actualCaze.getUpdatedOn());
        caze.setCreatedOn(actualCaze.getCreatedOn());
        assertThat(actualCaze).isEqualTo(caze);
    }

    @Test(description = "Expecting successful receive of existing test case")
    @Details()
    public void test_20190101212406() {
        TRCase caze = J2_CLIENT.addCase();
        TRCase actualCaze = J2_CLIENT.getCase(caze);
        caze.setUpdatedOn(actualCaze.getUpdatedOn());
        caze.setCreatedOn(actualCaze.getCreatedOn());
        assertThat(actualCaze).isEqualTo(caze);
    }

    @Test(description = "Expecting successful receive of existing test case list")
    @Details()
    public void test_20190102011912() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRSection section = new TRSection()
                .withName(UUID.randomUUID().toString())
                .withDescription(UUID.randomUUID().toString());
        section = J2_CLIENT.addSection(section, project);
        TRCase caze = new TRCase().withTitle("test_20190101195810").withSectionId(section.getId());
        caze = J2_CLIENT.addCase(caze);
        List<TRCase> actualCaze = J2_CLIENT.getCases(project);
        List<Long> ids = actualCaze.stream().map(TRCase::getId).collect(Collectors.toList());
        assertThat(ids).contains(caze.getId());
    }

    @Test(description = "Expecting successful receive of existing test case list with filter")
    @Details()
    public void test_20190102012601() {
        TRProject project = J2_CLIENT.getProject();
        TRSuite suite = J2_CLIENT.addSuite(project);
        TRSection section = new TRSection()
                .withName(UUID.randomUUID().toString())
                .withDescription(UUID.randomUUID().toString())
                .withSuiteId(suite.getId());
        section = J2_CLIENT.addSection(section, project);
        TRCase caze = new TRCase().withTitle("test_20190101195810").withSectionId(section.getId());
        caze = J2_CLIENT.addCase(caze);

        List<TRCase> actualCaze = J2_CLIENT.getCases(project, new GetCasesFilter()
                .withSectionId(section.getId())
                .withCreatedBy(2L, 1L)
                .withSuiteId(suite.getId()));
        List<Long> ids = actualCaze.stream().map(TRCase::getId).collect(Collectors.toList());
        assertThat(ids).contains(caze.getId());
        for (TRCase trCase : actualCaze) {
            assertThat(trCase.getAdditionalProperties()).isEmpty();
            if (trCase.getCustomStepsSeparated() != null) {
                for (TRStep trStep : trCase.getCustomStepsSeparated()) {
                    assertThat(trStep.getAdditionalProperties()).isEmpty();
                }
            }
        }

        actualCaze = J2_CLIENT.getCases(project, new GetCasesFilter()
                .withSectionId(section.getId())
                .withCreatedAfter(500000000L)
                .withCreatedBefore(500000000L)
                .withUpdatedBefore(500000000L)
                .withUpdatedAfter(500000000L)
                .withCreatedBy(1L,2L)
                .withUpdatedBy(1L,2L)
                .withMilestoneId(1L)
                .withPriorityId(1L, 2L)
                .withTemplateId(1L,2L)
                .withTypeId(1L,2L)
                .withSuiteId(suite.getId())
        );
        for (TRCase trCase : actualCaze) {
            assertThat(trCase.getAdditionalProperties()).isEmpty();
            for (TRStep trStep : trCase.getCustomStepsSeparated()) {
                assertThat(trStep.getAdditionalProperties()).isEmpty();
            }
        }
    }

    @Test(description = "Expecting successful delete of existing test case")
    @Details()
    public void test_20190101214043() {
        TRCase caze = J2_CLIENT.addCase();
        J2_CLIENT.deleteCase(caze);
        FeignException exception = executeThrowable(() -> J2_CLIENT.getCase(caze));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :case_id is not a valid test case.\"}");
    }

}
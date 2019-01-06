package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.core.query.GetCasesQueryMap;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRCase;
import org.touchbit.testrail4j.jackson2.model.TRProject;
import org.touchbit.testrail4j.jackson2.model.TRSection;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.CaseTypes.ACCEPTANCE;
import static org.touchbit.testrail4j.core.type.Priorities.CRITICAL;
import static org.touchbit.testrail4j.core.type.Templates.TEST_CASE_TEXT;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "case_operations")
public class CaseTests extends BaseCorvusTest {

    @Test(description = "Expecting successful creation of a case with required fields")
    @Details()
    public void test_20190101195810() {
        TRSection section = CLIENT.addSection();
        TRCase caze = new TRCase().withTitle("test_20190101195810").withSectionId(section.getId());
        TRCase actualCaze = CLIENT.addCase(caze);
        assertThat(actualCaze.getTitle()).isEqualTo(caze.getTitle());
        assertThat(actualCaze.getSectionId()).isEqualTo(caze.getSectionId());
    }

    @Test(description = "Expecting successful creation of a case with all fields")
    @Details()
    public void test_20190101201312() {
        TRSection section = CLIENT.addSection();
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
        TRCase actualCaze = CLIENT.addCase(caze);
        actualCaze.getAdditionalProperties().clear();
        caze.setId(actualCaze.getId());
        caze.setUpdatedOn(actualCaze.getUpdatedOn());
        caze.setCreatedOn(actualCaze.getCreatedOn());
        assertThat(actualCaze).isEqualTo(caze);
    }

    @Test(description = "Expecting successful update of existing test case")
    @Details()
    public void test_20190101204226() {
        TRCase caze = CLIENT.addCase();
        caze.setTitle("test_20190101204226");
        TRCase actualCaze = CLIENT.updateCase(caze);
        caze.setUpdatedOn(actualCaze.getUpdatedOn());
        caze.setCreatedOn(actualCaze.getCreatedOn());
        assertThat(actualCaze).isEqualTo(caze);
    }

    @Test(description = "Expecting successful receive of existing test case")
    @Details()
    public void test_20190101212406() {
        TRCase caze = CLIENT.addCase();
        TRCase actualCaze = CLIENT.getCase(caze);
        caze.setUpdatedOn(actualCaze.getUpdatedOn());
        caze.setCreatedOn(actualCaze.getCreatedOn());
        assertThat(actualCaze).isEqualTo(caze);
    }

    @Test(description = "Expecting successful receive of existing test case list")
    @Details()
    public void test_20190102011912() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = new TRSection()
                .withName(UUID.randomUUID().toString())
                .withDescription(UUID.randomUUID().toString());
        section = CLIENT.addSection(section, project);
        TRCase caze = new TRCase().withTitle("test_20190101195810").withSectionId(section.getId());
        caze = CLIENT.addCase(caze);
        List<TRCase> actualCaze = CLIENT.getCases(project);
        List<Long> ids = actualCaze.stream().map(TRCase::getId).collect(Collectors.toList());
        assertThat(ids).contains(caze.getId());
    }

    @Test(description = "Expecting successful receive of existing test case list with filter")
    @Details()
    public void test_20190102012601() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = new TRSection()
                .withName(UUID.randomUUID().toString())
                .withDescription(UUID.randomUUID().toString());
        section = CLIENT.addSection(section, project);
        TRCase caze = new TRCase().withTitle("test_20190101195810").withSectionId(section.getId());
        caze = CLIENT.addCase(caze);
        List<TRCase> actualCaze = CLIENT.getCases(project, new GetCasesQueryMap().withSectionId(section.getId()));
        List<Long> ids = actualCaze.stream().map(TRCase::getId).collect(Collectors.toList());
        assertThat(ids).contains(caze.getId());
    }

    @Test(description = "Expecting successful delete of existing test case")
    @Details()
    public void test_20190101214043() {
        TRCase caze = CLIENT.addCase();
        CLIENT.deleteCase(caze);
        FeignException exception = executeThrowable(() -> CLIENT.getCase(caze));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :case_id is not a valid test case.\"}");
    }

}

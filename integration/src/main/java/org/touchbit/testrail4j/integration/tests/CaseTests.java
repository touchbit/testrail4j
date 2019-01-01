package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.Case;
import org.touchbit.testrail4j.jackson2.model.Section;

import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.CaseTypes.ACCEPTANCE;
import static org.touchbit.testrail4j.core.type.Priorities.CRITICAL;
import static org.touchbit.testrail4j.core.type.Templates.TEST_CASE_TEXT;

/**
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "case_operations")
public class CaseTests extends BaseCorvusTest {

    @Test(description = "Expecting successful creation of a case with required fields")
    @Details()
    public void test_20190101195810() {
        Section section = CLIENT.addSection();
        Case caze = new Case().withTitle("test_20190101195810").withSectionId(section.getId());
        Case actualCaze = CLIENT.addCase(caze);
        assertThat(actualCaze.getTitle()).isEqualTo(caze.getTitle());
        assertThat(actualCaze.getSectionId()).isEqualTo(caze.getSectionId());
    }

    @Test(description = "Expecting successful creation of a case with all fields")
    @Details()
    public void test_20190101201312() {
        Section section = CLIENT.addSection();
        long time = new Date().getTime() / 1000;
        Case caze = new Case()
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
        Case actualCaze = CLIENT.addCase(caze);
        caze.setId(actualCaze.getId());
        caze.setUpdatedOn(actualCaze.getUpdatedOn());
        caze.setCreatedOn(actualCaze.getCreatedOn());
        assertThat(actualCaze).isEqualTo(caze);
    }

    @Test(description = "Expecting successful update of existing test case")
    @Details()
    public void test_20190101204226() {
        Case caze = CLIENT.addCase();
        caze.setTitle("test_20190101204226");
        Case actualCaze = CLIENT.updateCase(caze);
        caze.setUpdatedOn(actualCaze.getUpdatedOn());
        caze.setCreatedOn(actualCaze.getCreatedOn());
        assertThat(actualCaze).isEqualTo(caze);
    }

    @Test(description = "Expecting successful receive of existing test case")
    @Details()
    public void test_20190101212406() {
        Case caze = CLIENT.addCase();
        Case actualCaze = CLIENT.getCase(caze);
        caze.setUpdatedOn(actualCaze.getUpdatedOn());
        caze.setCreatedOn(actualCaze.getCreatedOn());
        assertThat(actualCaze).isEqualTo(caze);
    }

    @Test(description = "Expecting successful delete of existing test case")
    @Details()
    public void test_20190101214043() {
        Case caze = CLIENT.addCase();
        CLIENT.deleteCase(caze);
        FeignException exception = executeThrowable(() -> CLIENT.getCase(caze));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :case_id is not a valid test case.\"}");
    }

}

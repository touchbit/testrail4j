package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRProject;
import org.touchbit.testrail4j.jackson2.model.TRSection;
import org.touchbit.testrail4j.jackson2.model.TRSuite;

import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.MULTIPLE;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings("WeakerAccess")
@org.touchbit.buggy.core.model.Suite(service = TestRail.class, interfaze = API.class, task = "section_operations")
public class SectionTests extends BaseCorvusTest {

    @Test(description = "Expected successful section creation with required fields")
    @Details()
    public void test_20190101160155() {
        TRProject project = CLIENT.getNewProject(SINGLE);
        TRSection section = new TRSection().withName(UUID.randomUUID().toString());
        TRSection actualSection = CLIENT.addSection(section, project);
        assertThat(actualSection.getName()).isEqualTo(section.getName());
    }

    @Test(description = "Expected successful section creation with all fields")
    @Details()
    public void test_20190101161936() {
        TRProject project = CLIENT.getNewProject(SINGLE);
        TRSection section = genSection();
        TRSection actualSection = CLIENT.addSection(section, project);
        assertThat(actualSection.getName()).isEqualTo(section.getName());
        assertThat(actualSection.getDescription()).isEqualTo(section.getDescription());
    }

    @Test(description = "Expected successful subsection creation")
    @Details()
    public void test_20190101195318() {
        TRProject project = CLIENT.getNewProject(SINGLE);
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
        TRProject project = CLIENT.getNewProject(MULTIPLE);
        TRSuite suite = CLIENT.addNewSuite(project);
        TRSection section = genSection().withSuiteId(suite.getId());
        TRSection actualSection = CLIENT.addSection(section, project);
        assertThat(actualSection.getName()).isEqualTo(section.getName());
        assertThat(actualSection.getDescription()).isEqualTo(section.getDescription());
        assertThat(actualSection.getSuiteId()).isEqualTo(suite.getId());
    }

    @Test(description = "Expected successful receive existing section")
    @Details()
    public void test_20190101162834() {
        TRProject project = CLIENT.getNewProject(SINGLE);
        TRSection section = CLIENT.addSection(genSection(), project);
        TRSection actualSection = CLIENT.getSection(section);
        assertThat(actualSection).isEqualTo(section);
    }

    @Test(description = "Expected successful update existing section")
    @Details()
    public void test_20190101194252() {
        TRProject project = CLIENT.getNewProject(SINGLE);
        TRSection section = CLIENT.addSection(genSection(), project);
        section.setName("test_20190101194252");
        TRSection actualSection = CLIENT.updateSection(section);
        assertThat(actualSection).isEqualTo(section);
    }

    @Test(description = "Expected successful delete existing section")
    @Details()
    public void test_20190101194905() {
        TRProject project = CLIENT.getNewProject(SINGLE);
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

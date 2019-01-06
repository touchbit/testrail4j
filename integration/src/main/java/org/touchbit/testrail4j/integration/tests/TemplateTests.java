package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRProject;
import org.touchbit.testrail4j.jackson2.model.TRTemplate;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "template_operations")
public class TemplateTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful receive of existing templates")
    @Details()
    public void test_20190106073141() {
        TRProject project = CLIENT.getProject();
        List<TRTemplate> templates = CLIENT.getTemplates(project);
        assertThat(templates).isNotEmpty();
        for (TRTemplate template : templates) {
            assertThat(template.getAdditionalProperties()).isEmpty();
        }
    }

}

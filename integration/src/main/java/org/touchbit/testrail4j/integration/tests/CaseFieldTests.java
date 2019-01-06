package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.Context;
import org.touchbit.testrail4j.jackson2.model.Options;
import org.touchbit.testrail4j.jackson2.model.TRCaseField;
import org.touchbit.testrail4j.jackson2.model.TRCaseFieldConfig;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "case_fields_operations")
public class CaseFieldTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful receive of the existing case fields")
    @Details()
    public void test_20190106014139() {
        List<TRCaseField> fields = CLIENT.getTRCaseFields();
        assertThat(fields).isNotEmpty();
        for (TRCaseField field : fields) {
            assertThat(field.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting a successful add new custom case field")
    @Details()
    public void test_20190106015332() {
        TRCaseFieldConfig config = new TRCaseFieldConfig()
                .withContext(new Context().withIsGlobal(true))
                .withOptions(new Options().withIsRequired(false).withItems("0, A\n1, B").withDefaultValue("1"));
        List<TRCaseFieldConfig> configs = new ArrayList<>();
        configs.add(config);
        TRCaseField field = new TRCaseField()
                .withConfigs(configs)
                .withName("with_name_" + getRandomString(5))
                .withDescription("withDescription")
                .withLabel("test_20190106015332")
                .withType("6")
                .withIncludeAll(true);
        TRCaseField actField = CLIENT.addTRCaseField(field);
        assertThat(actField.getAdditionalProperties()).isEmpty();
        assertThat(actField.getConfigs()).isInstanceOf(String.class);
        List<TRCaseField> fields = CLIENT.getTRCaseFields();
        for (TRCaseField trCaseField : fields) {
            if (trCaseField.getId().equals(actField.getId())) {
                for (LinkedHashMap actFieldConfig : (List<LinkedHashMap>) trCaseField.getConfigs()) {
                    assertThat(actFieldConfig.get("context")).isNotNull();
                    assertThat(actFieldConfig.get("options")).isNotNull();
                }
            }
        }
    }

}

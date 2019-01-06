package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRResultField;
import org.touchbit.testrail4j.jackson2.model.TRResultFieldConfig;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "result_fields_operations")
public class ResultFieldsTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful receive of existing result fields")
    @Details()
    public void test_20190106080246() {
        step("Get existing result fields");
        List<TRResultField> resultFields = CLIENT.getResultFields();
        assertThat(resultFields).isNotEmpty();
        for (TRResultField resultField : resultFields) {
            assertThat(resultField.getAdditionalProperties()).isEmpty();
            for (TRResultFieldConfig config : resultField.getConfigs()) {
                assertThat(config.getAdditionalProperties()).isEmpty();
                assertThat(config.getOptions().getAdditionalProperties()).isEmpty();
                assertThat(config.getContext().getAdditionalProperties()).isEmpty();

            }
        }
    }

}

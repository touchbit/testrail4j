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

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseCorvusTest;
import org.touchbit.testrail4j.jackson2.model.TRResultField;
import org.touchbit.testrail4j.jackson2.model.TRResultFieldConfig;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "result_fields_operations")
public class ResultFieldsTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful receive of existing result fields")
    @Details()
    public void test_20190106080246() {
        step("Get existing result fields");
        List<TRResultField> resultFields = J2_CLIENT.getResultFields();
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

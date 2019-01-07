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

package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.*;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "test_operations")
public class TestTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful receive of existing tests")
    @Details()
    public void test_20190102023252() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRSection section = CLIENT.addSection(project);
        CLIENT.addCase(section);
        TRRun run = CLIENT.addRun(project);
        List<TRTest> tests = CLIENT.getTests(run);
        assertThat(tests).isNotEmpty();
        for (TRTest test : tests) {
            test = CLIENT.getTest(test);
            assertThat(test).isNotNull();
        }
    }

}

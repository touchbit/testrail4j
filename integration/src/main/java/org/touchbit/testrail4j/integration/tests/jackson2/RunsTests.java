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
import org.touchbit.testrail4j.core.query.filter.GetRunsFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseCorvusTest;
import org.touchbit.testrail4j.jackson2.model.TRProject;
import org.touchbit.testrail4j.jackson2.model.TRRun;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "run_operations")
public class RunsTests extends BaseCorvusTest {

    @Test(description = "Expecting successful of the run creation")
    @Details()
    public void test_20190102004538() {
        TRProject project = J2_CLIENT.getProject(SINGLE);
        TRRun run = new TRRun().withProjectId(project.getId()).withName("test_20190102004538");
        TRRun actualRun = J2_CLIENT.addRun(run);
        assertThat(actualRun.getName()).isEqualTo(run.getName());
        assertThat(actualRun.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting successful update of the existing run")
    @Details()
    public void test_20190102010136() {
        TRRun run = J2_CLIENT.addRun().withName("test_20190102010136");
        TRRun actualRun = J2_CLIENT.updateRun(run);
        assertThat(actualRun.getName()).isEqualTo(run.getName());
    }

    @Test(description = "Expecting successful receive of the existing run")
    @Details()
    public void test_20190102010512() {
        TRRun run = J2_CLIENT.addRun();
        TRRun actRun = J2_CLIENT.getRun(run);
        assertThat(actRun.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting successful receive of the existing runs list")
    @Details()
    public void test_20190102013324() {
        TRRun run = J2_CLIENT.addRun();
        List<TRRun> actRuns = J2_CLIENT.getRuns(run);
        for (TRRun actRun : actRuns) {
            assertThat(actRun.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting successful receive of the runs list with filter")
    @Details()
    public void test_20190107181124() {
        TRRun run = J2_CLIENT.addRun();
        List<TRRun> actRuns = J2_CLIENT.getRuns(run, new GetRunsFilter()
                .withCreatedAfter(500000000)
                .withCreatedBefore(500000000)
                .withCreatedBy(7, 8)
                .withIsCompleted(false)
                .withMilestoneId(5, 6)
                .withSuiteId(3, 4)
                .withOffset(2)
                .withLimit(1)
        );
        assertThat(actRuns).isEmpty();
    }

    @Test(description = "Expecting successful delete of the existing run")
    @Details()
    public void test_20190102010623() {
        TRRun run = J2_CLIENT.addRun();
        J2_CLIENT.deleteRun(run);
        FeignException exception = executeThrowable(() -> J2_CLIENT.getRun(run));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :run is not a valid test run.\"}");
    }

}

/*
 * MIT License
 *
 * Copyright © 2019 TouchBIT.
 * Copyright © 2019 Oleg Shaburov.
 * Copyright © 2018 Maria Vasilenko.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
import org.touchbit.testrail4j.integration.tests.BaseJackson2Test;
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
public class RunsTests extends BaseJackson2Test {

    @Test(description = "Expecting successful of the run creation")
    @Details()
    public void test_20190102004538() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRRun run = new TRRun().withProjectId(project.getId()).withName("test_20190102004538");
        TRRun actualRun = CLIENT.addRun(run);
        assertThat(actualRun.getName()).isEqualTo(run.getName());
        assertThat(actualRun.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting successful update of the existing run")
    @Details()
    public void test_20190102010136() {
        TRRun run = CLIENT.addRun().withName("test_20190102010136");
        TRRun actualRun = CLIENT.updateRun(run);
        assertThat(actualRun.getName()).isEqualTo(run.getName());
    }

    @Test(description = "Expecting successful receive of the existing run")
    @Details()
    public void test_20190102010512() {
        TRRun run = CLIENT.addRun();
        TRRun actRun = CLIENT.getRun(run);
        assertThat(actRun.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting successful receive of the existing runs list")
    @Details()
    public void test_20190102013324() {
        TRRun run = CLIENT.addRun();
        List<TRRun> actRuns = CLIENT.getRuns(run);
        for (TRRun actRun : actRuns) {
            assertThat(actRun.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting successful receive of the runs list with filter")
    @Details()
    public void test_20190107181124() {
        TRRun run = CLIENT.addRun();
        List<TRRun> actRuns = CLIENT.getRuns(run, new GetRunsFilter()
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
        TRRun run = CLIENT.addRun();
        CLIENT.deleteRun(run);
        FeignException exception = executeThrowable(() -> CLIENT.getRun(run));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :run is not a valid test run.\"}");
    }

}

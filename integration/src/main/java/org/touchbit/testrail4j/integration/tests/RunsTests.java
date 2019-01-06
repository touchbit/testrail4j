package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRProject;
import org.touchbit.testrail4j.jackson2.model.TRRun;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "run_operations")
public class RunsTests extends BaseCorvusTest {

    @Test(description = "Expecting successful of the run creation")
    @Details()
    public void test_20190102004538() {
        TRProject project = CLIENT.getProject(SINGLE);
        TRRun run = new TRRun().withProjectId(project.getId()).withName("test_20190102004538");
        TRRun actualRun = CLIENT.addRun(run);
        assertThat(actualRun.getName()).isEqualTo(run.getName());
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
        TRRun actRun =CLIENT.getRun(run);
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

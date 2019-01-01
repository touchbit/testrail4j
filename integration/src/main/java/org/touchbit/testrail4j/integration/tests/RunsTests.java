package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.Project;
import org.touchbit.testrail4j.jackson2.model.Run;

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
        Project project = CLIENT.getNewProject(SINGLE);
        Run run = new Run().withProjectId(project.getId()).withName("test_20190102004538");
        Run actualRun = CLIENT.addRun(run);
        assertThat(actualRun.getName()).isEqualTo(run.getName());
    }

    @Test(description = "Expecting successful update of the existing run")
    @Details()
    public void test_20190102010136() {
        Run run = CLIENT.addRun().withName("test_20190102010136");
        Run actualRun = CLIENT.updateRun(run);
        assertThat(actualRun.getName()).isEqualTo(run.getName());
    }

    @Test(description = "Expecting successful receive of the existing run")
    @Details()
    public void test_20190102010512() {
        Run run = CLIENT.addRun();
        CLIENT.getRun(run);
    }

    @Test(description = "Expecting successful delete of the existing run")
    @Details()
    public void test_20190102010623() {
        Run run = CLIENT.addRun();
        CLIENT.deleteRun(run);
        FeignException exception = executeThrowable(() -> CLIENT.getRun(run));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :run is not a valid test run.\"}");
    }

}

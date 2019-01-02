package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.*;

import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "result_operations")
public class ResultTests extends BaseCorvusTest {

    @Test(description = "Expecting successful adding result for test")
    @Details()
    public void test_20190102011809() {
        TRProject project = CLIENT.getNewProject(SINGLE);
        TRSection section = CLIENT.addSection(project);
        TRCase caze = CLIENT.addCase(section);
        TRRun run = CLIENT.addRun(project);

//        List<TRResult> results = CLIENT.addResult(1);

    }
}

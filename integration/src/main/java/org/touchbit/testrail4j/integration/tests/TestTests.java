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

    @Test(description = "Expecting successful receive of existing tests")
    @Details()
    public void test_20190102023252() {
        TRProject project = CLIENT.getNewProject(SINGLE);
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

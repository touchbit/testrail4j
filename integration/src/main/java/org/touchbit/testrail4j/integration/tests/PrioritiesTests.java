package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.RTPriority;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "priorities_operations")
public class PrioritiesTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful received of the available priorities")
    @Details()
    public void test_20190106065007() {
        step("Get available priorities");
        List<RTPriority> priorities = CLIENT.getPriorities();
        assertThat(priorities).isNotNull();
    }

}

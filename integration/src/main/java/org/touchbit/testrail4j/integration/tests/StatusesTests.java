package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRStatus;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "statuses_operations")
public class StatusesTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful receive of existing statuses")
    @Details()
    public void test_20190106074343() {
        step("Get existing statuses");
        List<TRStatus> statuses = CLIENT.getStatuses();
        assertThat(statuses).isNotEmpty();
        for (TRStatus status : statuses) {
            assertThat(status).isNotNull();
            assertThat(status.getAdditionalProperties()).isEmpty();
        }
    }

}

package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRCaseType;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "case_types_operations")
public class CaseTypeTests extends BaseCorvusTest {

    @Test(description = "Expecting successful receive of existing case types")
    @Details()
    public void test_20190106033851() {
        List<TRCaseType> types = CLIENT.getTRCaseTypes();
        assertThat(types).isNotEmpty();
    }

}

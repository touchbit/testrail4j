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
import org.touchbit.testrail4j.core.query.filter.GetMilestonesFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseCorvusTest;
import org.touchbit.testrail4j.jackson2.model.TRMilestone;
import org.touchbit.testrail4j.jackson2.model.TRProject;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "milestones_operations")
public class MilestonesTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful create of the milestone")
    @Details()
    public void test_20190106061352() {
        TRProject project = J2_CLIENT.getProject();
        TRMilestone milestone1 = new TRMilestone().withName("test_20190106061352_1");
        TRMilestone actMilestone1 = J2_CLIENT.addMilestone(milestone1, project);
        TRMilestone milestone2 = new TRMilestone().withName("test_20190106061352_2").withParentId(actMilestone1.getId());
        TRMilestone actMilestone2 = J2_CLIENT.addMilestone(milestone2, project);
        assertThat(actMilestone1.getName()).isEqualTo("test_20190106061352_1");
        assertThat(actMilestone1.getParentId()).isNull();
        assertThat(actMilestone2.getName()).isEqualTo("test_20190106061352_2");
        assertThat(actMilestone2.getParentId()).isEqualTo(actMilestone1.getId());
        assertThat(actMilestone1.getAdditionalProperties()).isEmpty();
        assertThat(actMilestone2.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting a successful update of the existing milestone")
    @Details()
    public void test_20190106062046() {
        TRProject project = J2_CLIENT.getProject();
        TRMilestone milestone = new TRMilestone().withName("test");
        TRMilestone addedMilestone = J2_CLIENT.addMilestone(milestone, project);
        addedMilestone.withName("test_20190106062046");
        TRMilestone actMilestone = J2_CLIENT.updateMilestone(addedMilestone);
        assertThat(actMilestone.getId()).isEqualTo(addedMilestone.getId());
        assertThat(actMilestone.getName()).isEqualTo("test_20190106062046");
        assertThat(actMilestone.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting a successful received of the existing milestone")
    @Details()
    public void test_20190106062311() {
        TRProject project = J2_CLIENT.getProject();
        TRMilestone milestone = J2_CLIENT.addMilestone(project);
        TRMilestone actMilestone = J2_CLIENT.getMilestone(milestone);
        assertThat(actMilestone).isNotNull();
        assertThat(actMilestone.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting a successful received of the existing milestones")
    @Details()
    public void test_20190106062708() {
        TRProject project = J2_CLIENT.getProject();
        J2_CLIENT.addMilestone(project);
        J2_CLIENT.addMilestone(project);
        List<TRMilestone> actMilestones = J2_CLIENT.getMilestones(project);
        assertThat(actMilestones).isNotEmpty();
        for (TRMilestone actMilestone : actMilestones) {
            assertThat(actMilestone.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting a successful received of the existing milestones with filter")
    @Details()
    public void test_20190106062831() {
        TRProject project = J2_CLIENT.getProject();
        TRMilestone milestone = J2_CLIENT.addMilestone(project);
        J2_CLIENT.addMilestone(project, milestone);
        GetMilestonesFilter queryMap = new GetMilestonesFilter().withIsCompleted(false).withIsStarted(true);
        List<TRMilestone> actMilestones = J2_CLIENT.getMilestones(project, queryMap);
        assertThat(actMilestones).isNotEmpty();
        for (TRMilestone actMilestone : actMilestones) {
            assertThat(actMilestone.getAdditionalProperties()).isEmpty();
            for (TRMilestone actMilestoneMilestone : actMilestone.getMilestones()) {
                assertThat(actMilestoneMilestone.getAdditionalProperties()).isEmpty();
            }
        }
    }

    @Test(description = "Expecting a successful delete of the existing milestone")
    @Details()
    public void test_20190106063046() {
        TRProject project = J2_CLIENT.getProject();
        TRMilestone milestone = J2_CLIENT.addMilestone(project);
        J2_CLIENT.deleteMilestone(milestone);
        FeignException exception = executeThrowable(() -> J2_CLIENT.getMilestone(milestone));
        assertThat(exception.contentUTF8()).isEqualTo("{\"error\":\"Field :milestone_id is not a valid milestone.\"}");
    }

}

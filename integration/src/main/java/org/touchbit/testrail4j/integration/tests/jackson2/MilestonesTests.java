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

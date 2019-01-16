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

package org.touchbit.testrail4j.integration.tests.gson;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.core.query.filter.GetMilestonesFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Gson;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseGsonTest;
import org.touchbit.testrail4j.gson.model.TRMilestone;
import org.touchbit.testrail4j.gson.model.TRProject;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Gson.class, interfaze = API.class, task = "milestones_operations")
public class MilestonesTests extends BaseGsonTest {

    @Test(description = "Expecting a successful create of the milestone")
    @Details()
    public void test_20200106061352() {
        TRProject project = CLIENT.getProject();
        TRMilestone milestone1 = new TRMilestone().withName("test_20200106061352_1");
        TRMilestone actMilestone1 = CLIENT.addMilestone(milestone1, project);
        TRMilestone milestone2 = new TRMilestone().withName("test_20200106061352_2").withParentId(actMilestone1.getId());
        TRMilestone actMilestone2 = CLIENT.addMilestone(milestone2, project);
        assertThat(actMilestone1.getName()).isEqualTo("test_20200106061352_1");
        assertThat(actMilestone1.getParentId()).isNull();
        assertThat(actMilestone2.getName()).isEqualTo("test_20200106061352_2");
        assertThat(actMilestone2.getParentId()).isEqualTo(actMilestone1.getId());
    }

    @Test(description = "Expecting a successful update of the existing milestone")
    @Details()
    public void test_20200106062046() {
        TRProject project = CLIENT.getProject();
        TRMilestone milestone = new TRMilestone().withName("test");
        TRMilestone addedMilestone = CLIENT.addMilestone(milestone, project);
        addedMilestone.withName("test_20200106062046");
        TRMilestone actMilestone = CLIENT.updateMilestone(addedMilestone);
        assertThat(actMilestone.getId()).isEqualTo(addedMilestone.getId());
        assertThat(actMilestone.getName()).isEqualTo("test_20200106062046");
    }

    @Test(description = "Expecting a successful received of the existing milestone")
    @Details()
    public void test_20200106062311() {
        TRProject project = CLIENT.getProject();
        TRMilestone milestone = CLIENT.addMilestone(project);
        TRMilestone actMilestone = CLIENT.getMilestone(milestone);
        assertThat(actMilestone).isNotNull();
    }

    @Test(description = "Expecting a successful received of the existing milestones")
    @Details()
    public void test_20200106062708() {
        TRProject project = CLIENT.getProject();
        CLIENT.addMilestone(project);
        CLIENT.addMilestone(project);
        List<TRMilestone> actMilestones = CLIENT.getMilestones(project);
        assertThat(actMilestones).isNotEmpty();
    }

    @Test(description = "Expecting a successful received of the existing milestones with filter")
    @Details()
    public void test_20200106062831() {
        TRProject project = CLIENT.getProject();
        TRMilestone milestone = CLIENT.addMilestone(project);
        CLIENT.addMilestone(project, milestone);
        GetMilestonesFilter queryMap = new GetMilestonesFilter().withIsCompleted(false).withIsStarted(true);
        List<TRMilestone> actMilestones = CLIENT.getMilestones(project, queryMap);
        assertThat(actMilestones).isNotEmpty();
    }

    @Test(description = "Expecting a successful delete of the existing milestone")
    @Details()
    public void test_20200106063046() {
        TRProject project = CLIENT.getProject();
        TRMilestone milestone = CLIENT.addMilestone(project);
        CLIENT.deleteMilestone(milestone);
        FeignException exception = executeThrowable(() -> CLIENT.getMilestone(milestone));
        assertThat(exception.contentUTF8()).isEqualTo("{\"error\":\"Field :milestone_id is not a valid milestone.\"}");
    }

}

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
import org.touchbit.testrail4j.core.query.GetPlansQueryMap;
import org.touchbit.testrail4j.core.query.filter.GetPlansFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Gson;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseGsonTest;
import org.touchbit.testrail4j.gson.model.*;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Gson.class, interfaze = API.class, task = "test_plan_operations")
public class PlanTests extends BaseGsonTest {

    @Test(description = "Expecting a successful create of the test plan entry")
    @Details()
    public void test_20200106181613() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addSuite(project);
        TRRun run = CLIENT.addRun(project, suite);
        TRPlan plan = CLIENT.addPlan(project);
        TRPlanEntry entry = new TRPlanEntry()
                .withName("test_20200106181613")
                .withAssignedtoId(1L)
                .withDescription("test_20200106181613")
                .withIncludeAll(true)
                .withSuiteId(suite.getId());
        CLIENT.addPlanEntry(entry, plan, run);
    }

    @Test(description = "Expecting a successful update of the test plan entry")
    @Details()
    public void test_20200106184206() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addSuite(project);
        TRRun run = CLIENT.addRun(project, suite);
        TRPlan plan = CLIENT.addPlan(project);
        TRPlanEntry entry = CLIENT.addPlanEntry(plan, suite, run);
        entry.setName("test_20200106184206");
        CLIENT.updatePlanEntry(entry, plan);
    }

    @Test(description = "Expecting a successful delete of the test plan entry")
    @Details()
    public void test_20200106185055() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addSuite(project);
        TRRun run = CLIENT.addRun(project, suite);
        TRPlan plan = CLIENT.addPlan(project);
        TRPlanEntry actEntry = CLIENT.addPlanEntry(plan, suite, run);
        CLIENT.deletePlanEntry(plan, actEntry);
        TRPlan actPlan = CLIENT.getPlan(plan);
        assertThat(actPlan.getEntries()).isEmpty();
    }

    @Test(description = "Expecting a successful create of the new test plan")
    @Details()
    public void test_20200106185850() {
        TRProject project = CLIENT.getProject();
        TRMilestone milestone = CLIENT.addMilestone(project);
        TRPlan plan = new TRPlan()
                .withName("test_20200106180117")
                .withDescription("test_20200106180117")
                .withMilestoneId(milestone.getId());
        TRPlan actPlan = CLIENT.addPlan(plan, project);
        assertThat(actPlan).isNotNull();
        assertThat(actPlan.getName()).isEqualTo("test_20200106180117");
        assertThat(actPlan.getDescription()).isEqualTo("test_20200106180117");
        assertThat(actPlan.getMilestoneId()).isEqualTo(milestone.getId());
    }

    @Test(description = "Expecting a successful update of the existing test plan")
    @Details()
    public void test_20200106185910() {
        TRProject project = CLIENT.getProject();
        TRPlan plan = CLIENT.addPlan(project);
        plan.setName("test_20200106185910");
        TRPlan actPlan = CLIENT.updatePlan(plan);
        assertThat(actPlan).isNotNull();
        assertThat(actPlan.getName()).isEqualTo("test_20200106185910");
    }

    @Test(description = "Expecting a successful delete of the existing test plan")
    @Details()
    public void test_20200106190414() {
        TRProject project = CLIENT.getProject();
        TRPlan plan = CLIENT.addPlan(project);
        CLIENT.deletePlan(plan);
        FeignException exception = executeThrowable(() -> CLIENT.getPlan(plan));
        assertThat(exception.contentUTF8()).isEqualTo("{\"error\":\"Field :plan_id is not a valid test plan.\"}");
    }

    @Test(description = "Expecting a successful receive of the existing test plan")
    @Details()
    public void test_20200106191159() {
        TRProject project = CLIENT.getProject();
        TRPlan plan = CLIENT.addPlan(project);
        TRPlan actPlan = CLIENT.getPlan(plan);
        assertThat(actPlan).isNotNull();
    }

    @Test(description = "Expecting a successful receive of the existing test plans list")
    @Details()
    public void test_20200106191307() {
        TRProject project = CLIENT.getProject();
        CLIENT.addPlan(project);
        CLIENT.addPlan(project);
        List<TRPlan> actPlan = CLIENT.getPlans(project);
        assertThat(actPlan).isNotEmpty();
        assertThat(actPlan).hasSize(2);
    }

    @Test(description = "Expecting a successful receive of the existing test plans list with filter")
    @Details()
    public void test_20200106191511() {
        TRProject project = CLIENT.getProject();
        CLIENT.addPlan(project);
        CLIENT.addPlan(project);
        GetPlansQueryMap queryMap = new GetPlansFilter()
                .withCreatedAfter(500000000)
                .withCreatedBefore(2000000000)
                .withCreatedBy(1)
                .withIsCompleted(false)
                .withLimit(10)
                .withMilestoneId(1)
                .withOffset(1);
        List<TRPlan> actPlan = CLIENT.getPlans(project, queryMap);
        assertThat(actPlan).isEmpty();
    }

}

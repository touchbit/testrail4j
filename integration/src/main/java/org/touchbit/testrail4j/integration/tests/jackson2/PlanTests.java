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
import org.touchbit.testrail4j.core.query.GetPlansQueryMap;
import org.touchbit.testrail4j.core.query.filter.GetPlansFilter;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseCorvusTest;
import org.touchbit.testrail4j.jackson2.model.*;


import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "test_plan_operations")
public class PlanTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful create of the test plan entry")
    @Details()
    public void test_20190106181613() {
        TRProject project = J2_CLIENT.getProject();
        TRSuite suite = J2_CLIENT.addSuite(project);
        TRRun run = J2_CLIENT.addRun(project, suite);
        TRPlan plan = J2_CLIENT.addPlan(project);
        TRPlanEntry entry = new TRPlanEntry()
                .withName("test_20190106181613")
                .withAssignedtoId(1L)
                .withDescription("test_20190106181613")
                .withIncludeAll(true)
                .withSuiteId(suite.getId());
        TRPlanEntry actEntry = J2_CLIENT.addPlanEntry(entry, plan, run);
        assertThat(actEntry.getAdditionalProperties()).isEmpty();
        for (TRRun trRun : actEntry.getRuns()) {
            assertThat(trRun.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting a successful update of the test plan entry")
    @Details()
    public void test_20190106184206() {
        TRProject project = J2_CLIENT.getProject();
        TRSuite suite = J2_CLIENT.addSuite(project);
        TRRun run = J2_CLIENT.addRun(project, suite);
        TRPlan plan = J2_CLIENT.addPlan(project);
        TRPlanEntry entry = J2_CLIENT.addPlanEntry(plan, suite, run);
        entry.setName("test_20190106184206");
        TRPlanEntry actEntry = J2_CLIENT.updatePlanEntry(entry, plan);
        assertThat(actEntry.getAdditionalProperties()).isEmpty();
        for (TRRun trRun : actEntry.getRuns()) {
            assertThat(trRun.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting a successful delete of the test plan entry")
    @Details()
    public void test_20190106185055() {
        TRProject project = J2_CLIENT.getProject();
        TRSuite suite = J2_CLIENT.addSuite(project);
        TRRun run = J2_CLIENT.addRun(project, suite);
        TRPlan plan = J2_CLIENT.addPlan(project);
        TRPlanEntry actEntry = J2_CLIENT.addPlanEntry(plan, suite, run);
        J2_CLIENT.deletePlanEntry(plan, actEntry);
        TRPlan actPlan = J2_CLIENT.getPlan(plan);
        assertThat(actPlan.getEntries()).isEmpty();
    }

    @Test(description = "Expecting a successful create of the new test plan")
    @Details()
    public void test_20190106185850() {
        TRProject project = J2_CLIENT.getProject();
        TRMilestone milestone = J2_CLIENT.addMilestone(project);
        TRPlan plan = new TRPlan()
                .withName("test_20190106180117")
                .withDescription("test_20190106180117")
                .withMilestoneId(milestone.getId());
        TRPlan actPlan = J2_CLIENT.addPlan(plan, project);
        assertThat(actPlan).isNotNull();
        assertThat(actPlan.getName()).isEqualTo("test_20190106180117");
        assertThat(actPlan.getDescription()).isEqualTo("test_20190106180117");
        assertThat(actPlan.getMilestoneId()).isEqualTo(milestone.getId());
        assertThat(actPlan.getAdditionalProperties()).isEmpty();
        for (TRPlanEntry entry : actPlan.getEntries()) {
            assertThat(entry.getAdditionalProperties()).isEmpty();
            for (TRRun run : entry.getRuns()) {
                assertThat(run.getAdditionalProperties()).isEmpty();
            }
        }
    }

    @Test(description = "Expecting a successful update of the existing test plan")
    @Details()
    public void test_20190106185910() {
        TRProject project = J2_CLIENT.getProject();
        TRPlan plan = J2_CLIENT.addPlan(project);
        plan.setName("test_20190106185910");
        TRPlan actPlan = J2_CLIENT.updatePlan(plan);
        assertThat(actPlan).isNotNull();
        assertThat(actPlan.getName()).isEqualTo("test_20190106185910");
        assertThat(actPlan.getAdditionalProperties()).isEmpty();
        for (TRPlanEntry entry : actPlan.getEntries()) {
            assertThat(entry.getAdditionalProperties()).isEmpty();
            for (TRRun run : entry.getRuns()) {
                assertThat(run.getAdditionalProperties()).isEmpty();
            }
        }
    }

    @Test(description = "Expecting a successful delete of the existing test plan")
    @Details()
    public void test_20190106190414() {
        TRProject project = J2_CLIENT.getProject();
        TRPlan plan = J2_CLIENT.addPlan(project);
        J2_CLIENT.deletePlan(plan);
        FeignException exception = executeThrowable(() -> J2_CLIENT.getPlan(plan));
        assertThat(exception.contentUTF8()).isEqualTo("{\"error\":\"Field :plan_id is not a valid test plan.\"}");
    }

    @Test(description = "Expecting a successful receive of the existing test plan")
    @Details()
    public void test_20190106191159() {
        TRProject project = J2_CLIENT.getProject();
        TRPlan plan = J2_CLIENT.addPlan(project);
        TRPlan actPlan = J2_CLIENT.getPlan(plan);
        assertThat(actPlan).isNotNull();
        assertThat(actPlan.getAdditionalProperties()).isEmpty();
        for (TRPlanEntry entry : actPlan.getEntries()) {
            assertThat(entry.getAdditionalProperties()).isEmpty();
            for (TRRun run : entry.getRuns()) {
                assertThat(run.getAdditionalProperties()).isEmpty();
            }
        }
    }

    @Test(description = "Expecting a successful receive of the existing test plans list")
    @Details()
    public void test_20190106191307() {
        TRProject project = J2_CLIENT.getProject();
        J2_CLIENT.addPlan(project);
        J2_CLIENT.addPlan(project);
        List<TRPlan> actPlan = J2_CLIENT.getPlans(project);
        assertThat(actPlan).isNotEmpty();
        assertThat(actPlan).hasSize(2);
        for (TRPlan trPlan : actPlan) {
            assertThat(trPlan.getAdditionalProperties()).isEmpty();
            for (TRPlanEntry entry : trPlan.getEntries()) {
                assertThat(entry.getAdditionalProperties()).isEmpty();
                for (TRRun run : entry.getRuns()) {
                    assertThat(run.getAdditionalProperties()).isEmpty();
                }
            }
        }
    }

    @Test(description = "Expecting a successful receive of the existing test plans list with filter")
    @Details()
    public void test_20190106191511() {
        TRProject project = J2_CLIENT.getProject();
        J2_CLIENT.addPlan(project);
        J2_CLIENT.addPlan(project);
        GetPlansQueryMap queryMap = new GetPlansFilter()
                .withCreatedAfter(500000000)
                .withCreatedBefore(2000000000)
                .withCreatedBy(1)
                .withIsCompleted(false)
                .withLimit(10)
                .withMilestoneId(1)
                .withOffset(1);
        List<TRPlan> actPlan = J2_CLIENT.getPlans(project, queryMap);
        assertThat(actPlan).isEmpty();
    }

}

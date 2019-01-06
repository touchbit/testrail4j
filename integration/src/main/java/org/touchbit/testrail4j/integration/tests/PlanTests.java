package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.core.query.GetPlansQueryMap;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.*;


import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "test_plan_operations")
public class PlanTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful create of the test plan entry")
    @Details()
    public void test_20190106181613() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addNewSuite(project);
        TRRun run = CLIENT.addRun(project, suite);
        TRPlan plan = CLIENT.addPlan(project);
        TRPlanEntry entry = new TRPlanEntry()
                .withName("test_20190106181613")
                .withAssignedtoId(1L)
                .withDescription("test_20190106181613")
                .withIncludeAll(true)
                .withSuiteId(suite.getId());
        TRPlanEntry actEntry = CLIENT.addPlanEntry(entry, plan, run);
        assertThat(actEntry.getAdditionalProperties()).isEmpty();
        for (TRRun trRun : actEntry.getRuns()) {
            assertThat(trRun.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting a successful update of the test plan entry")
    @Details()
    public void test_20190106184206() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addNewSuite(project);
        TRRun run = CLIENT.addRun(project, suite);
        TRPlan plan = CLIENT.addPlan(project);
        TRPlanEntry entry = CLIENT.addPlanEntry(plan, suite, run);
        entry.setName("test_20190106184206");
        TRPlanEntry actEntry = CLIENT.updatePlanEntry(entry, plan);
        assertThat(actEntry.getAdditionalProperties()).isEmpty();
        for (TRRun trRun : actEntry.getRuns()) {
            assertThat(trRun.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting a successful delete of the test plan entry")
    @Details()
    public void test_20190106185055() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addNewSuite(project);
        TRRun run = CLIENT.addRun(project, suite);
        TRPlan plan = CLIENT.addPlan(project);
        TRPlanEntry actEntry = CLIENT.addPlanEntry(plan, suite, run);
        CLIENT.deletePlanEntry(plan, actEntry);
        TRPlan actPlan = CLIENT.getPlan(plan);
        assertThat(actPlan.getEntries()).isEmpty();
    }

    @Test(description = "Expecting a successful create of the new test plan")
    @Details()
    public void test_20190106185850() {
        TRProject project = CLIENT.getProject();
        TRMilestone milestone = CLIENT.addMilestone(project);
        TRPlan plan = new TRPlan()
                .withName("test_20190106180117")
                .withDescription("test_20190106180117")
                .withMilestoneId(milestone.getId());
        TRPlan actPlan = CLIENT.addPlan(plan, project);
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
        TRProject project = CLIENT.getProject();
        TRPlan plan = CLIENT.addPlan(project);
        plan.setName("test_20190106185910");
        TRPlan actPlan = CLIENT.updatePlan(plan);
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
        TRProject project = CLIENT.getProject();
        TRPlan plan = CLIENT.addPlan(project);
        CLIENT.deletePlan(plan);
        FeignException exception = executeThrowable(() -> CLIENT.getPlan(plan));
        assertThat(exception.contentUTF8()).isEqualTo("{\"error\":\"Field :plan_id is not a valid test plan.\"}");
    }

    @Test(description = "Expecting a successful receive of the existing test plan")
    @Details()
    public void test_20190106191159() {
        TRProject project = CLIENT.getProject();
        TRPlan plan = CLIENT.addPlan(project);
        TRPlan actPlan = CLIENT.getPlan(plan);
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
        TRProject project = CLIENT.getProject();
        CLIENT.addPlan(project);
        CLIENT.addPlan(project);
        List<TRPlan> actPlan = CLIENT.getPlans(project);
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
        TRProject project = CLIENT.getProject();
        CLIENT.addPlan(project);
        CLIENT.addPlan(project);
        GetPlansQueryMap queryMap = new GetPlansQueryMap()
                .withCreatedAfter(500000000L)
                .withCreatedBefore(500000000L)
                .withCreatedBy(1L)
                .withIsCompleted(false)
                .withLimit(10)
                .withMilestoneId(1L)
                .withOffset(1);
        List<TRPlan> actPlan = CLIENT.getPlans(project, queryMap);
        assertThat(actPlan).isEmpty();
    }

}

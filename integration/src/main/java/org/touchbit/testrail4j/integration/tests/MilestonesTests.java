package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.core.query.MilestonesQueryMap;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRMilestone;
import org.touchbit.testrail4j.jackson2.model.TRProject;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "milestones_operations")
public class MilestonesTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful create of the milestone")
    @Details()
    public void test_20190106061352() {
        TRProject project = CLIENT.getProject();
        TRMilestone milestone1 = new TRMilestone().withName("test_20190106061352_1");
        TRMilestone actMilestone1 = CLIENT.addMilestone(milestone1, project);
        TRMilestone milestone2 = new TRMilestone().withName("test_20190106061352_2").withParentId(actMilestone1.getId());
        TRMilestone actMilestone2 = CLIENT.addMilestone(milestone2, project);
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
        TRProject project = CLIENT.getProject();
        TRMilestone milestone = new TRMilestone().withName("test");
        TRMilestone addedMilestone = CLIENT.addMilestone(milestone, project);
        addedMilestone.withName("test_20190106062046");
        TRMilestone actMilestone = CLIENT.updateMilestone(addedMilestone);
        assertThat(actMilestone.getId()).isEqualTo(addedMilestone.getId());
        assertThat(actMilestone.getName()).isEqualTo("test_20190106062046");
        assertThat(actMilestone.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting a successful received of the existing milestone")
    @Details()
    public void test_20190106062311() {
        TRProject project = CLIENT.getProject();
        TRMilestone milestone = CLIENT.addMilestone(project);
        TRMilestone actMilestone = CLIENT.getMilestone(milestone);
        assertThat(actMilestone).isNotNull();
        assertThat(actMilestone.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting a successful received of the existing milestones")
    @Details()
    public void test_20190106062708() {
        TRProject project = CLIENT.getProject();
        CLIENT.addMilestone(project);
        CLIENT.addMilestone(project);
        List<TRMilestone> actMilestones = CLIENT.getMilestones(project);
        assertThat(actMilestones).isNotEmpty();
        for (TRMilestone actMilestone : actMilestones) {
            assertThat(actMilestone.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting a successful received of the existing milestones with filter")
    @Details()
    public void test_20190106062831() {
        TRProject project = CLIENT.getProject();
        CLIENT.addMilestone(project);
        CLIENT.addMilestone(project);
        MilestonesQueryMap queryMap = new MilestonesQueryMap().withIsCompleted(0).withIsStarted(1);
        List<TRMilestone> actMilestones = CLIENT.getMilestones(project, queryMap);
        assertThat(actMilestones).isNotEmpty();
        for (TRMilestone actMilestone : actMilestones) {
            assertThat(actMilestone.getAdditionalProperties()).isEmpty();
        }
    }

    @Test(description = "Expecting a successful delete of the existing milestone")
    @Details()
    public void test_20190106063046() {
        TRProject project = CLIENT.getProject();
        TRMilestone milestone = CLIENT.addMilestone(project);
        CLIENT.deleteMilestone(milestone);
        FeignException exception = executeThrowable(() -> CLIENT.getMilestone(milestone));
        assertThat(exception.contentUTF8()).isEqualTo("{\"error\":\"Field :milestone_id is not a valid milestone.\"}");
    }

}

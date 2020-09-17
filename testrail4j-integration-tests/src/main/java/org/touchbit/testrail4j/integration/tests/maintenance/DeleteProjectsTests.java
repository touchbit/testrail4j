package org.touchbit.testrail4j.integration.tests.maintenance;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.buggy.core.utils.log.BuggyLog;
import org.touchbit.testrail4j.core.BasicAuth;
import org.touchbit.testrail4j.gson.feign.client.TestRailClient;
import org.touchbit.testrail4j.gson.feign.client.TestRailClientBuilder;
import org.touchbit.testrail4j.integration.config.Config;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.goals.Maintenance;
import org.touchbit.testrail4j.integration.tests.BaseTest;
import org.touchbit.testrail4j.integration.tests.RPSLimiter;

@Suite(component = TestRail.class, service = Maintenance.class, interfaze = API.class, task = "Delete projects")
public class DeleteProjectsTests extends BaseTest {

    protected static final TestRailClient CLIENT = TestRailClientBuilder.build(Config.geHost(),
            new BasicAuth(Config.getLogin(), Config.getPassword()),
            new RPSLimiter());

    @Test(description = "Delete existing projects")
    @Details()
    public void test_20200917145204() {
        step("Delete existing projects");
        CLIENT.getProjects().stream()
                .parallel()
                .forEach(p -> {
                    CLIENT.deleteProject(p.getId());
                    BuggyLog.console().info("Project deleted. ID: {}", p.getId());
                });
    }

}

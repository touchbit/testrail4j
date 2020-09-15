/*
 * MIT License
 *
 * Copyright © 2020 TouchBIT.
 * Copyright © 2020 Oleg Shaburov.
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

package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.testng.annotations.AfterSuite;
import org.touchbit.buggy.core.Buggy;
import org.touchbit.buggy.core.test.BaseBuggyTest;
import org.touchbit.buggy.core.testng.listeners.IntellijIdeaTestNgPluginListener;
import org.touchbit.buggy.feign.FeignCallLogger;
import org.touchbit.testrail4j.core.BasicAuth;
import org.touchbit.testrail4j.core.query.*;
import org.touchbit.testrail4j.core.query.filter.GetPlansFilter;
import org.touchbit.testrail4j.core.query.filter.GetSectionsFilter;
import org.touchbit.testrail4j.core.type.SuiteMode;
import org.touchbit.testrail4j.integration.config.Config;
import org.touchbit.testrail4j.gson.feign.client.TestRailClient;
import org.touchbit.testrail4j.gson.feign.client.TestRailClientBuilder;
import org.touchbit.testrail4j.gson.model.*;
import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.SuiteMode.MULTIPLE;
import static org.touchbit.testrail4j.core.type.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */

@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public class BaseGsonTest extends BaseBuggyTest {

    protected static final TestRailTestClient CLIENT;

    static {
        if (!IntellijIdeaTestNgPluginListener.isIntellijIdeaTestRun() && !Buggy.isTestRun()) {
            Buggy.getExitHandler().exitRun(1, "Missing IntellijIdeaPluginListener in the Intellij IDEA" +
                    " TestNG plugin configuration.");
        }
        CLIENT = TestRailClientBuilder.build(Config.geHost(),
                TestRailTestClient.class,
                new FeignCallLogger(log),
                        new BasicAuth(Config.getLogin(), Config.getPassword()),
                        new RPSLimiter());
    }

    protected FeignException executeThrowable(Executable executable) {
        return executeThrowable(executable, FeignException.class);
    }

    @SuppressWarnings("unchecked")
    protected <T> T executeThrowable(Executable executable, Class<T> exceptionClass) {
        Throwable throwable = null;
        try {
            executable.execute();
        } catch (Throwable e) {
            throwable = e;
        }
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(exceptionClass);
        return (T) throwable;
    }

    @FunctionalInterface
    public interface Executable {
        void execute() throws Throwable;
    }

    public static String getRandomString(int length) {
        return getRandomString(length, true);
    }

    public static String getRandomString(int length, boolean withLowerCase) {
        String str = generateString("QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm", length);
        return withLowerCase ? str.toLowerCase() : str;
    }

    private static String generateString(String characters, int length) {
        if (length < 1) {
            throw new IllegalArgumentException("The length of the generated string must be positive. Received: " + length);
        } else {
            char[] text = new char[length];

            for(int i = 0; i < length; ++i) {
                text[i] = characters.charAt((new Random()).nextInt(characters.length()));
            }

            return new String(text);
        }
    }

    /**
     * Tests interface with Utility methods
     */
    public interface TestRailTestClient extends TestRailClient {

        /* ---------------------------------------------------------------------------------------------------------- */

        /**
         * @return generated {@link TRProject}
         */
        default TRProject getProject(SuiteMode suiteMode) {
            TRProject project = new TRProject()
                    .withName(UUID.randomUUID().toString())
                    .withAnnouncement(UUID.randomUUID().toString())
                    .withShowAnnouncement(true)
                    .withSuiteMode(suiteMode.getId());
            step("Add new project with name: {}", project.getName());
            return addProject(project);
        }

        /**
         * @return generated multiple suite mode {@link TRProject}
         */
        default TRProject getProject() {
            return getProject(MULTIPLE);
        }

        default void deleteProject(TRProject project) {
            step("Delete project with ID: {}", project.getId());
            CLIENT.deleteProject(project.getId());
        }

        default TRProject getProject(TRProject project) {
            step("Get project with ID: {}", project.getId());
            return getProject(project.getId());
        }

        default List<TRProject> getTRProjects() {
            step("Get existing projects list");
            return getProjects();
        }

        default List<TRProject> getTRProjects(GetProjectsQueryMap filter) {
            step("Get existing projects list with filter");
            return getProjects(filter);
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        /**
         * @return generated {@link TRProject} and {@link TRSection}
         */
        default TRSection addSection() {
            TRProject project = getProject(SINGLE);
            TRSection section = new TRSection()
                    .withName(UUID.randomUUID().toString())
                    .withDescription(UUID.randomUUID().toString());
            return addSection(section, project);
        }

        default TRSection addSection(TRProject project) {
            TRSection section = new TRSection()
                    .withName(UUID.randomUUID().toString())
                    .withDescription(UUID.randomUUID().toString());
            return addSection(section, project);
        }

        default TRSection addSection(TRProject project, TRSuite suite) {
            TRSection section = new TRSection()
                    .withSuiteId(suite.getId())
                    .withName(UUID.randomUUID().toString())
                    .withDescription(UUID.randomUUID().toString());
            return addSection(section, project);
        }

        default TRSection addSection(TRSection section, TRProject project) {
            step("Adding a new section with name {} to the project with id {}", section.getName(), project.getId());
            return addSection(section, project.getId());
        }

        default TRSection getSection(TRSection section) {
            step("Get section with ID {}", section.getId());
            return CLIENT.getSection(section.getId());
        }

        default List<TRSection> getSections(TRProject project) {
            step("Get sections for project ID {}", project.getId());
            return getSections(project.getId(), new GetSectionsFilter());
        }

        default List<TRSection> getSections(TRProject project, GetSectionsQueryMap queryMap) {
            step("Get sections for project ID {} with filter", project.getId());
            return getSections(project.getId(), queryMap);
        }

        default TRSection updateSection(TRSection section) {
            step("Update section with ID {}", section.getId());
            return CLIENT.updateSection(section, section.getId());
        }

        default void deleteSection(TRSection section) {
            step("Delete section with ID {}", section.getId());
            CLIENT.deleteSection(section.getId());
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        default void deleteSuite(TRSuite suite) {
            step("Delete suite with ID: {}", suite.getId());
            CLIENT.deleteSuite(suite.getId());
        }

        default TRSuite updateSuite(TRSuite suite) {
            step("Update suite with ID: {}", suite.getId());
            return CLIENT.updateSuite(suite, suite.getId());
        }

        default TRSuite addSuite(TRSuite suite, TRProject project) {
            step("Create new suite with name: {}", suite.getName());
            return CLIENT.addSuite(suite, project.getId());
        }

        default TRSuite addSuite(TRProject project) {
            TRSuite suite = new TRSuite().withName("name");
            return addSuite(suite, project);
        }

        default TRSuite getSuite(TRSuite suite) {
            step("Get suite with ID: {}", suite.getId());
            return getSuite(suite.getId());
        }

        default List<TRSuite> getSuites(TRProject project) {
            step("Get list suites for project ID: {}", project.getId());
            return CLIENT.getSuites(project.getId());
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        default TRCase getCase(TRCase caze) {
            step("Get case with ID: {}", caze.getId());
            return CLIENT.getCase(caze.getId());
        }

        default List<TRCase> getCases(TRProject project) {
            step("Get cases list for project ID: {}", project.getId());
            return CLIENT.getCases(project.getId());
        }

        default List<TRCase> getCases(TRProject project, GetCasesQueryMap queryMap) {
            step("Get cases list with filter for project ID: {}", project.getId());
            return CLIENT.getCases(project.getId(), queryMap);
        }

        default TRCase addCase() {
            TRSection section = CLIENT.addSection();
            return addCase(section);
        }

        default TRCase addCase(TRSection section) {
            TRCase caze = new TRCase().withTitle("test_20190101195810").withSectionId(section.getId());
            step("Add new case with title: {}", caze.getTitle());
            return CLIENT.addCase(caze, caze.getSectionId());
        }

        default TRCase addCase(TRCase caze) {
            step("Add new case with title: {}", caze.getTitle());
            return CLIENT.addCase(caze, caze.getSectionId());
        }

        default TRCase updateCase(TRCase caze) {
            step("Update case with ID: {}", caze.getId());
            return CLIENT.updateCase(caze, caze.getId());
        }

        default void deleteCase(TRCase caze) {
            step("Delete case with ID: {}", caze.getId());
            CLIENT.deleteCase(caze.getId());
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        default TRRun addRun() {
            TRProject project = CLIENT.getProject(SINGLE);
            return addRun(project);
        }

        default TRRun addRun(TRProject project, TRSuite suite) {
            TRRun run = new TRRun()
                    .withProjectId(project.getId())
                    .withName(UUID.randomUUID().toString())
                    .withSuiteId(suite.getId());
            return addRun(run);
        }

        default TRRun addRun(TRProject project) {
            TRRun run = new TRRun().withProjectId(project.getId()).withName(UUID.randomUUID().toString());
            return addRun(run);
        }

        default TRRun addRun(TRRun run) {
            step("Add run with name {} for project id {}", run.getName(), run.getProjectId());
            return CLIENT.addRun(run, run.getProjectId());
        }

        default TRRun updateRun(TRRun run) {
            step("Update run with id {}", run.getId());
            return CLIENT.updateRun(run, run.getId());
        }

        default TRRun getRun(TRRun run) {
            step("Get run with id {}", run.getId());
            return CLIENT.getRun(run.getId());
        }

        default List<TRRun> getRuns(TRRun run) {
            step("Get run list for project id {}", run.getProjectId());
            return CLIENT.getRuns(run.getProjectId());
        }

        default List<TRRun> getRuns(TRRun run, GetRunsQueryMap queryMap) {
            step("Get run list for project id {} with filter", run.getProjectId());
            return CLIENT.getRuns(run.getProjectId(), queryMap);
        }

        default void deleteRun(TRRun run) {
            step("Delete run with id {}", run.getId());
            CLIENT.deleteRun(run.getId());
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        default TRTest getTest(TRTest test) {
            step("Get test with id {}", test.getId());
            return CLIENT.getTest(test.getId());
        }

        default List<TRTest> getTests(TRRun run) {
            step("Get tests for run id {}", run.getId());
            return CLIENT.getTests(run.getId());
        }

        default List<TRTest> getTests(TRRun run, GetTestsQueryMap queryMap) {
            step("Get tests for run id {} with filter", run.getId());
            return getTests(run.getId(), queryMap);
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        default TRResult addResult(TRResult trResult, TRTest trTest) {
            step("Add test result for test id {}", trTest.getId());
            return CLIENT.addResult(trResult, trTest.getId());
        }

        default TRResult addResultForCase(TRResult trResult, TRRun trRun, TRCase trCase) {
            step("Add test result for run id {} and case id {}", trCase.getId(), trCase.getId());
            return CLIENT.addResultForCase(trResult, trRun.getId(), trCase.getId());
        }

        default List<TRResult> addResults(TRResults trResults, TRRun trRun) {
            step("Add test results for run id {}", trRun.getId());
            return CLIENT.addResults(trResults, trRun.getId());
        }

        default List<TRResult> addResultsForCases(TRResults trResults, TRRun trRun) {
            step("Add test results for cases with run id {}", trRun.getId());
            return CLIENT.addResultsForCases(trResults, trRun.getId());
        }

        default List<TRResult> getResultsForRun(TRRun trRun) {
            step("Get test results for run id {}", trRun.getId());
            return CLIENT.getResultsForRun(trRun.getId());
        }

        default List<TRResult> getResultsForRun(TRRun trRun, GetResultsQueryMap resultsQueryMap) {
            step("Get test results with filter for run id {}", trRun.getId());
            return CLIENT.getResultsForRun(trRun.getId(), resultsQueryMap);
        }

        default List<TRResult> getResultsForCase(TRRun trRun, TRCase trCase) {
            step("Get test results for case id {} with run id {}", trCase.getId(), trRun.getId());
            return CLIENT.getResultsForCase(trRun.getId(), trCase.getId());
        }

        default List<TRResult> getResultsForCase(TRRun trRun, TRCase trCase, GetResultsQueryMap queryMap) {
            step("Get test results with filter for case id {} with run id {}", trCase.getId(), trRun.getId());
            return CLIENT.getResultsForCase(trRun.getId(), trCase.getId(), queryMap);
        }

        default List<TRResult> getResults(TRTest trTest) {
            step("Get test results for test id {}", trTest.getId());
            return CLIENT.getResults(trTest.getId());
        }

        default List<TRResult> getResults(TRTest trTest, GetResultsQueryMap queryMap) {
            step("Get test results with filter for test id {}", trTest.getId());
            return CLIENT.getResults(trTest.getId(), queryMap);
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        default List<TRCaseField> getTRCaseFields() {
            step("Get existing case fields");
            return CLIENT.getCaseFields();
        }

        default void addTRCaseField(TRCaseField caseField, TRCaseFieldConfig... caseFieldConfigs) {
            if (caseFieldConfigs != null && caseFieldConfigs.length > 0) {
                caseField.setConfigs(Arrays.asList(caseFieldConfigs));
            }
            step("Add new case field with name {}", caseField.getName());
            CLIENT.addCaseField(caseField);
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        default List<TRCaseType> getTRCaseTypes() {
            step("Get existing case types");
            return CLIENT.getCaseTypes();
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        default List<TRProjectConfigGroup> getConfigs(TRProject project) {
            step("Get configurations for project ID {}", project.getId());
            return CLIENT.getConfigs(project.getId());
        }

        default TRProjectConfigGroup addConfigGroup(TRProject project) {
            TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName(UUID.randomUUID().toString());
            return addConfigGroup(configGroup, project);
        }

        default TRProjectConfigGroup addConfigGroup(TRProjectConfigGroup cGroup, TRProject project) {
            step("Add configuration group for project ID {}", project.getId());
            return CLIENT.addConfigGroup(cGroup, project.getId());
        }

        default TRProjectConfigGroup updateConfigGroup(TRProjectConfigGroup cGroup) {
            step("Update configuration group with ID {}", cGroup.getId());
            return CLIENT.updateConfigGroup(cGroup, cGroup.getId());
        }

        default void deleteConfigGroup(TRProjectConfigGroup cGroup) {
            step("Delete configuration group with ID {}", cGroup.getId());
            CLIENT.deleteConfigGroup(cGroup.getId());
        }

        default TRProjectConfig addConfig(TRProjectConfig config, TRProjectConfigGroup cGroup) {
            step("Add configuration to the group with ID {}", cGroup.getId());
            return CLIENT.addConfig(config, cGroup.getId());
        }

        default TRProjectConfig updateConfig(TRProjectConfig config) {
            step("Update configuration with ID {}", config.getId());
            return CLIENT.updateConfig(config, config.getId());
        }

        default void deleteConfig(TRProjectConfig config) {
            step("Delete configuration with ID {}", config.getId());
            CLIENT.deleteConfig(config.getId());
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        default TRMilestone getMilestone(TRMilestone milestone) {
            step("Get milestone with ID {}", milestone.getId());
            return CLIENT.getMilestone(milestone.getId());
        }

        default List<TRMilestone> getMilestones(TRProject project) {
            step("Get milestones for project with ID {}", project.getId());
            return CLIENT.getMilestones(project.getId());
        }

        default List<TRMilestone> getMilestones(TRProject project, GetMilestonesQueryMap queryMap) {
            step("Get milestones with filter for project with ID {}", project.getId(), queryMap);
            return CLIENT.getMilestones(project.getId(), queryMap);
        }

        default TRMilestone addMilestone(TRMilestone milestone, TRProject project) {
            step("Add milestone {} to the project with ID {}", milestone.getName(), project.getId());
            return CLIENT.addMilestone(milestone, project.getId());
        }

        default TRMilestone addMilestone(TRProject project, TRMilestone... milestones) {
            TRMilestone milestone = new TRMilestone()
                    .withName(UUID.randomUUID().toString())
                    .withMilestones(Arrays.asList(milestones));
            return addMilestone(milestone, project.getId());
        }

        default TRMilestone updateMilestone(TRMilestone milestone) {
            step("Update milestone with ID {}", milestone.getId());
            return CLIENT.updateMilestone(milestone, milestone.getId());
        }

        default void deleteMilestone(TRMilestone milestone) {
            step("Delete milestone with ID {}", milestone.getId());
            CLIENT.deleteMilestone(milestone.getId());
        }

        default List<TRTemplate> getTemplates(TRProject project) {
            step("Get existing templates for project ID {}", project.getId());
            return CLIENT.getTemplates(project.getId());
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        default TRPlan getPlan(TRPlan plan) {
            step("Get plan with ID {}", plan.getId());
            return getPlan(plan.getId());
        }

        default List<TRPlan> getPlans(TRProject project) {
            step("Get plan for project ID {}", project.getId());
            return getPlans(project.getId(), new GetPlansFilter());
        }

        default List<TRPlan> getPlans(TRProject project, GetPlansQueryMap queryMap) {
            step("Get plan for project ID {} with filter", project.getId());
            return getPlans(project.getId(), queryMap);
        }

        default TRPlan addPlan(TRProject project) {
            TRPlan plan = new TRPlan()
                    .withName("test_20190106180117")
                    .withDescription("test_20190106180117");
            return addPlan(plan, project.getId());
        }

        default TRPlan addPlan(TRPlan plan, TRProject project) {
            step("Add plan with name {} for project ID {}", plan.getName(), project.getId());
            return addPlan(plan, project.getId());
        }

        default TRPlan updatePlan(TRPlan plan) {
            step("Update plan with ID {}", plan.getId());
            return updatePlan(plan, plan.getId());
        }

        default TRPlan closePlan(TRPlan plan) {
            step("Close plan with ID {}", plan.getId());
            return closePlan(plan.getId());
        }

        default void deletePlan(TRPlan plan) {
            step("Delete plan with ID {}", plan.getId());
            deletePlan(plan.getId());
        }

        default TRPlanEntry addPlanEntry(TRPlanEntry entry, TRPlan plan) {
            step("Add plan entry with name {} for plan ID {}", plan.getName(), plan.getId());
            return addPlanEntry(entry, plan.getId());
        }

        default TRPlanEntry addPlanEntry(TRPlanEntry entry, TRPlan plan, TRRun... runs) {
            return addPlanEntry(entry.withRuns(Arrays.asList(runs)), plan);
        }

        default TRPlanEntry addPlanEntry(TRPlan plan, TRSuite suite, TRRun... runs) {
            List<TRRun> runList = new ArrayList<>(Arrays.asList(runs));
            TRPlanEntry entry = new TRPlanEntry()
                    .withName("test_20190106181613")
                    .withAssignedtoId(1L)
                    .withDescription("test_20190106181613")
                    .withIncludeAll(true)
                    .withSuiteId(suite.getId())
                    .withRuns(runList);
            return addPlanEntry(entry, plan);
        }

        default TRPlanEntry updatePlanEntry(TRPlanEntry entry, TRPlan plan) {
            step("Update plan entry with ID {} for plan ID {}", entry.getId(), plan.getId());
            return updatePlanEntry(entry, plan.getId(), entry.getId());
        }

        default void deletePlanEntry(TRPlan plan, TRPlanEntry entry) {
            step("Delete plan entry with ID {} for plan ID {}", entry.getId(), plan.getId());
            deletePlanEntry(plan.getId(), entry.getId());
        }

    }
}

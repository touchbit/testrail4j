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

package org.touchbit.testrail4j.integration.tests;

import feign.FeignException;
import org.touchbit.buggy.core.Buggy;
import org.touchbit.buggy.core.test.BaseBuggyTest;
import org.touchbit.buggy.core.testng.listeners.IntellijIdeaTestNgPluginListener;
import org.touchbit.buggy.feign.FeignCallLogger;
import org.touchbit.testrail4j.core.BasicAuthorizationInterceptor;
import org.touchbit.testrail4j.core.query.GetCasesQueryMap;
import org.touchbit.testrail4j.core.query.GetProjectsQueryMap;
import org.touchbit.testrail4j.core.query.GetResultsQueryMap;
import org.touchbit.testrail4j.core.query.GetMilestonesQueryMap;
import org.touchbit.testrail4j.integration.config.Config;
import org.touchbit.testrail4j.jackson2.feign.client.SuiteMode;
import org.touchbit.testrail4j.jackson2.feign.client.TestRailClient;
import org.touchbit.testrail4j.jackson2.feign.client.TestRailClientBuilder;
import org.touchbit.testrail4j.jackson2.model.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.MULTIPLE;
import static org.touchbit.testrail4j.jackson2.feign.client.SuiteMode.SINGLE;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */

@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public class BaseCorvusTest extends BaseBuggyTest {

    protected static final TestRailTestClient CLIENT;

    static {
        if (!IntellijIdeaTestNgPluginListener.isIntellijIdeaTestRun() && !Buggy.isTestRun()) {
            Buggy.getExitHandler().exitRun(1, "Missing IntellijIdeaPluginListener in the Intellij IDEA" +
                    " TestNG plugin configuration.");
        }
        CLIENT = TestRailClientBuilder
                .build(new BasicAuthorizationInterceptor(Config.getAuth()),
                        Config.getHost(),
                        TestRailTestClient.class,
                        new FeignCallLogger(log));
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
                    .withSuiteMode(suiteMode.id());
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

        default List<TRProject> getProjects() {
            step("Get existing projects list");
            return getProjects(new GetProjectsQueryMap().withIsCompleted(false));
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

        default TRSection addSection(TRSection section, TRProject project) {
            step("Adding a new section with name {} to the project with id {}", section.getName(), project.getId());
            return addSection(section, project.getId());
        }

        default TRSection getSection(TRSection section) {
            step("Get section with ID: {}", section.getId());
            return CLIENT.getSection(section.getId());
        }

        default TRSection updateSection(TRSection section) {
            step("Update section with ID: {}", section.getId());
            return CLIENT.updateSection(section, section.getId());
        }

        default void deleteSection(TRSection section) {
            step("Delete section with ID: {}", section.getId());
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

        default TRSuite addNewSuite(TRSuite suite, TRProject project) {
            step("Create new suite with name: {}", suite.getName());
            return CLIENT.addSuite(suite, project.getId());
        }

        default TRSuite addNewSuite(TRProject project) {
            TRSuite suite = new TRSuite().withName("name");
            return addNewSuite(suite, project);
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
            step("Get run list with project id {}", run.getProjectId());
            return CLIENT.getRuns(run.getProjectId());
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
            step("Get test with id {}", run.getId());
            return CLIENT.getTests(run.getId());
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

        default TRCaseField addTRCaseField(TRCaseField caseField) {
            step("Add new case field with name {}", caseField.getName());
            return CLIENT.addCaseField(caseField);
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

        default TRMilestone addMilestone(TRProject project) {
            TRMilestone milestone = new TRMilestone().withName(UUID.randomUUID().toString());
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

    }
}

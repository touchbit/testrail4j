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
import org.slf4j.Logger;
import org.touchbit.buggy.core.Buggy;
import org.touchbit.buggy.core.test.BaseBuggyTest;
import org.touchbit.buggy.core.testng.listeners.IntellijIdeaTestNgPluginListener;
import org.touchbit.buggy.core.utils.log.BuggyLog;
import org.touchbit.buggy.feign.FeignCallLogger;
import org.touchbit.testrail4j.core.BasicAuthorizationInterceptor;
import org.touchbit.testrail4j.core.query.GetCasesQueryMap;
import org.touchbit.testrail4j.integration.config.Config;
import org.touchbit.testrail4j.jackson2.feign.client.SuiteMode;
import org.touchbit.testrail4j.jackson2.feign.client.TestRailClient;
import org.touchbit.testrail4j.jackson2.feign.client.TestRailClientBuilder;
import org.touchbit.testrail4j.jackson2.model.*;
import sun.misc.Unsafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        disableWarning();
        waitMigrations();
        CLIENT = TestRailClientBuilder
                .build(new BasicAuthorizationInterceptor(Config.getAuth()),
                        Config.getHost(),
                        TestRailTestClient.class,
                        new FeignCallLogger(log));
    }

    /**
     * Tests interface with Utility methods
     */
    public interface TestRailTestClient extends TestRailClient {

        /**
         * @return generated {@link TRProject}
         */
        default TRProject getNewProject(SuiteMode suiteMode) {
            String name = UUID.randomUUID().toString();
            String announcement = UUID.randomUUID().toString();
            step("Add new project with name: {}", name);
            return addProject(name, announcement, true, suiteMode);
        }

        /**
         * @return generated multiple suite mode {@link TRProject}
         */
        default TRProject getNewProject() {
            return getNewProject(MULTIPLE);
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
            return getProjects(false);
        }

        /* ---------------------------------------------------------------------------------------------------------- */

        /**
         * @return generated {@link TRProject} and {@link TRSection}
         */
        default TRSection addSection() {
            TRProject project = getNewProject(SINGLE);
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
            TRProject project = CLIENT.getNewProject(SINGLE);
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

    private static void waitMigrations() {
        Logger log = BuggyLog.framework();
        boolean migrationContains = true;
        // + 5 min
        long waitTime = new Date().getTime() + 300000;
        try {
            while (migrationContains) {
                List<String> lines = new ArrayList<>();
                Process p = Runtime.getRuntime().exec("docker ps");
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    lines.add(line);
                }
                long count = lines.stream().filter(l -> l.toLowerCase().contains("migration")).count();
                if (count > 0) {
                    log.info("Waiting for migration to complete...");
                    Thread.sleep(500);
                } else {
                    log.info("Migration container not found. Running tests.");
                    migrationContains = false;
                }
                if (new Date().getTime() > waitTime) {
                    Buggy.getExitHandler().exitRun(1, "Timeout for loading migrations exceeded");
                }
            }
        } catch (IOException e) {
            Buggy.getExitHandler().exitRun(1, "'docker ps' call fail", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception ignore) {
            // ignore
        }
    }

}

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
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseCorvusTest;
import org.touchbit.testrail4j.jackson2.model.TRProject;
import org.touchbit.testrail4j.jackson2.model.TRSuite;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 31.12.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings("WeakerAccess")
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "suite_operations")
public class SuiteTests extends BaseCorvusTest {

    @Test(description = "Expected successful suite creation with required fields")
    @Details
    public void test_20181231221005() {
        TRProject project = J2_CLIENT.getProject();
        TRSuite suite = new TRSuite().withName("name");
        TRSuite actualSuite = J2_CLIENT.addSuite(suite, project);
        assertThat(actualSuite.getName()).isEqualTo(suite.getName());
        assertThat(actualSuite.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expected successful suite creation with all fields")
    @Details()
    public void test_20181231221908() {
        TRProject project = J2_CLIENT.getProject();
        TRSuite suite = new TRSuite()
                .withName("test_20181231221908_name")
                .withDescription("test_20181231221908_description")
                .withId(100500L)
                .withCompletedOn(100500L)
                .withIsBaseline(true)
                .withIsCompleted(true)
                .withUrl("url")
                .withProjectId(123L)
                .withIsMaster(true);
        TRSuite actualSuite = J2_CLIENT.addSuite(suite, project);
        assertThat(actualSuite.getName()).isEqualTo(suite.getName());
    }

    @Test(description = "Expected successful received existing suite")
    @Details()
    public void test_20181231222652() {
        TRProject project = J2_CLIENT.getProject();
        TRSuite suite = J2_CLIENT.addSuite(project);
        TRSuite actualSuite = J2_CLIENT.getSuite(suite);
        assertThat(actualSuite).isEqualTo(suite);
    }

    @Test(description = "Expected successful received list existing suites")
    @Details()
    public void test_20181231223026() {
        TRProject project = J2_CLIENT.getProject();
        TRSuite suite = J2_CLIENT.addSuite(project);
        List<TRSuite> suiteList = J2_CLIENT.getSuites(project);
        assertThat(suiteList).contains(suite);
    }

    @Test(description = "Expected successful update existing suite")
    @Details()
    public void test_20181231223730() {
        TRProject project = J2_CLIENT.getProject();
        TRSuite suite = J2_CLIENT.addSuite(project);
        suite.setName("test_20181231223730");
        TRSuite actualSuite = J2_CLIENT.updateSuite(suite);
        assertThat(actualSuite).isEqualTo(suite);
    }

    @Test(description = "Expected successful delete existing suite")
    @Details()
    public void test_20181231224319() {
        TRProject project = J2_CLIENT.getProject();
        TRSuite suite = J2_CLIENT.addSuite(project);
        J2_CLIENT.deleteSuite(suite);
        FeignException exception = executeThrowable(() -> J2_CLIENT.getSuite(suite));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :suite_id is not a valid test suite.\"}");
    }

}
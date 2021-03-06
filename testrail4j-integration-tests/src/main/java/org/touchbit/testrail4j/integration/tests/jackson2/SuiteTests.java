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

package org.touchbit.testrail4j.integration.tests.jackson2;

import feign.FeignException;
import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseJackson2Test;
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
public class SuiteTests extends BaseJackson2Test {

    @Test(description = "Expected successful suite creation with required fields")
    @Details
    public void test_20181231221005() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = new TRSuite().withName("name");
        TRSuite actualSuite = CLIENT.addSuite(suite, project);
        assertThat(actualSuite.getName()).isEqualTo(suite.getName());
        assertThat(actualSuite.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expected successful suite creation with all fields")
    @Details()
    public void test_20181231221908() {
        TRProject project = CLIENT.getProject();
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
        TRSuite actualSuite = CLIENT.addSuite(suite, project);
        assertThat(actualSuite.getName()).isEqualTo(suite.getName());
    }

    @Test(description = "Expected successful received existing suite")
    @Details()
    public void test_20181231222652() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addSuite(project);
        TRSuite actualSuite = CLIENT.getSuite(suite);
        assertThat(actualSuite).isEqualTo(suite);
    }

    @Test(description = "Expected successful received list existing suites")
    @Details()
    public void test_20181231223026() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addSuite(project);
        List<TRSuite> suiteList = CLIENT.getSuites(project);
        assertThat(suiteList).contains(suite);
    }

    @Test(description = "Expected successful update existing suite")
    @Details()
    public void test_20181231223730() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addSuite(project);
        suite.setName("test_20181231223730");
        TRSuite actualSuite = CLIENT.updateSuite(suite);
        assertThat(actualSuite).isEqualTo(suite);
    }

    @Test(description = "Expected successful delete existing suite")
    @Details()
    public void test_20181231224319() {
        TRProject project = CLIENT.getProject();
        TRSuite suite = CLIENT.addSuite(project);
        CLIENT.deleteSuite(suite);
        FeignException exception = executeThrowable(() -> CLIENT.getSuite(suite));
        assertThat(exception.contentUTF8())
                .isEqualTo("{\"error\":\"Field :suite_id is not a valid test suite.\"}");
    }

}

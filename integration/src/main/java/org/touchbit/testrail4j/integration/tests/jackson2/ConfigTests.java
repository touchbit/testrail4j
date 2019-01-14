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

package org.touchbit.testrail4j.integration.tests.jackson2;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseCorvusTest;
import org.touchbit.testrail4j.jackson2.model.TRProject;
import org.touchbit.testrail4j.jackson2.model.TRProjectConfig;
import org.touchbit.testrail4j.jackson2.model.TRProjectConfigGroup;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "config_operations")
public class ConfigTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful creation of the configuration group")
    @Details()
    public void test_20190106045215() {
        TRProject project = J2_CLIENT.getProject();
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test_20190106045215");
        TRProjectConfigGroup actConfigGroup = J2_CLIENT.addConfigGroup(configGroup, project);
        assertThat(actConfigGroup.getName()).isEqualTo(configGroup.getName());
        assertThat(actConfigGroup.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting a successful update of the existing configuration group")
    @Details()
    public void test_20190106050310() {
        TRProject project = J2_CLIENT.getProject();
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test");
        TRProjectConfigGroup actConfigGroup = J2_CLIENT.addConfigGroup(configGroup, project);
        assertThat(actConfigGroup.getName()).isEqualTo(configGroup.getName());
        assertThat(actConfigGroup.getAdditionalProperties()).isEmpty();

        configGroup.withName("test_20190106050310").withId(actConfigGroup.getId());
        actConfigGroup = J2_CLIENT.updateConfigGroup(configGroup);
        assertThat(actConfigGroup.getAdditionalProperties()).isEmpty();
        assertThat(actConfigGroup.getName()).isEqualTo(configGroup.getName());
    }

    @Test(description = "Expecting a successful delete of the existing configuration group")
    @Details()
    public void test_20190106050730() {
        TRProject project = J2_CLIENT.getProject();
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test");
        TRProjectConfigGroup actConfigGroup = J2_CLIENT.addConfigGroup(configGroup, project);
        J2_CLIENT.deleteConfigGroup(actConfigGroup);
    }

    @Test(description = "Expecting a successful creation of the project configuration")
    @Details()
    public void test_20190106050831() {
        TRProject project = J2_CLIENT.getProject();
        TRProjectConfigGroup configGroup = J2_CLIENT.addConfigGroup(project);
        TRProjectConfig config = new TRProjectConfig().withName("test_20190106050831");
        TRProjectConfig actConf = J2_CLIENT.addConfig(config, configGroup);
        assertThat(actConf.getAdditionalProperties()).isEmpty();
        assertThat(actConf.getName()).isEqualTo(config.getName());
    }

    @Test(description = "Expecting a successful update of the project configuration")
    @Details()
    public void test_20190106051135() {
        TRProject project = J2_CLIENT.getProject();
        TRProjectConfigGroup configGroup = J2_CLIENT.addConfigGroup(project);
        TRProjectConfig config = new TRProjectConfig().withName("test");
        TRProjectConfig actConf = J2_CLIENT.addConfig(config, configGroup);
        assertThat(actConf.getAdditionalProperties()).isEmpty();
        assertThat(actConf.getName()).isEqualTo(config.getName());

        config.withName("test_20190106051135").withId(actConf.getId());
        actConf = J2_CLIENT.updateConfig(config);
        assertThat(actConf.getAdditionalProperties()).isEmpty();
        assertThat(actConf.getName()).isEqualTo(config.getName());
    }

    @Test(description = "Expecting a successful delete of the existing project configuration")
    @Details()
    public void test_20190106051249() {
        TRProject project = J2_CLIENT.getProject();
        TRProjectConfigGroup configGroup = J2_CLIENT.addConfigGroup(project);
        TRProjectConfig config = new TRProjectConfig().withName("test");
        TRProjectConfig actConf = J2_CLIENT.addConfig(config, configGroup);
        J2_CLIENT.deleteConfig(actConf);
    }

    @Test(description = "Expecting a successful receive of the existing configuration")
    @Details()
    public void test_20190106051421() {
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test_20190106051421");
        TRProjectConfig config = new TRProjectConfig().withName("test_20190106051421");
        TRProject project = J2_CLIENT.getProject();
        TRProjectConfigGroup actConfigGroup = J2_CLIENT.addConfigGroup(configGroup, project);
        J2_CLIENT.addConfig(config, actConfigGroup);
        List<TRProjectConfigGroup> configGroupList = J2_CLIENT.getConfigs(project);
        assertThat(configGroupList).isNotEmpty();
        for (TRProjectConfigGroup trProjectConfigGroup : configGroupList) {
            assertThat(trProjectConfigGroup.getAdditionalProperties()).isEmpty();
            for (TRProjectConfig trProjectConfigGroupConfig : trProjectConfigGroup.getConfigs()) {
                assertThat(trProjectConfigGroupConfig.getAdditionalProperties()).isEmpty();
            }
        }
    }

}

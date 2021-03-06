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

package org.touchbit.testrail4j.integration.tests.gson;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Gson;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseGsonTest;
import org.touchbit.testrail4j.gson.model.TRProject;
import org.touchbit.testrail4j.gson.model.TRProjectConfig;
import org.touchbit.testrail4j.gson.model.TRProjectConfigGroup;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Gson.class, interfaze = API.class, task = "config_operations")
public class ConfigTests extends BaseGsonTest {

    @Test(description = "Expecting a successful creation of the configuration group")
    @Details()
    public void test_20200106045215() {
        TRProject project = CLIENT.getProject();
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test_20200106045215");
        TRProjectConfigGroup actConfigGroup = CLIENT.addConfigGroup(configGroup, project);
        assertThat(actConfigGroup.getName()).isEqualTo(configGroup.getName());
    }

    @Test(description = "Expecting a successful update of the existing configuration group")
    @Details()
    public void test_20200106050310() {
        TRProject project = CLIENT.getProject();
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test");
        TRProjectConfigGroup actConfigGroup = CLIENT.addConfigGroup(configGroup, project);
        assertThat(actConfigGroup.getName()).isEqualTo(configGroup.getName());

        configGroup.withName("test_20200106050310").withId(actConfigGroup.getId());
        actConfigGroup = CLIENT.updateConfigGroup(configGroup);
        assertThat(actConfigGroup.getName()).isEqualTo(configGroup.getName());
    }

    @Test(description = "Expecting a successful delete of the existing configuration group")
    @Details()
    public void test_20200106050730() {
        TRProject project = CLIENT.getProject();
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test");
        TRProjectConfigGroup actConfigGroup = CLIENT.addConfigGroup(configGroup, project);
        CLIENT.deleteConfigGroup(actConfigGroup);
    }

    @Test(description = "Expecting a successful creation of the project configuration")
    @Details()
    public void test_20200106050831() {
        TRProject project = CLIENT.getProject();
        TRProjectConfigGroup configGroup = CLIENT.addConfigGroup(project);
        TRProjectConfig config = new TRProjectConfig().withName("test_20200106050831");
        TRProjectConfig actConf = CLIENT.addConfig(config, configGroup);
        assertThat(actConf.getName()).isEqualTo(config.getName());
    }

    @Test(description = "Expecting a successful update of the project configuration")
    @Details()
    public void test_20200106051135() {
        TRProject project = CLIENT.getProject();
        TRProjectConfigGroup configGroup = CLIENT.addConfigGroup(project);
        TRProjectConfig config = new TRProjectConfig().withName("test");
        TRProjectConfig actConf = CLIENT.addConfig(config, configGroup);
        assertThat(actConf.getName()).isEqualTo(config.getName());

        config.withName("test_20200106051135").withId(actConf.getId());
        actConf = CLIENT.updateConfig(config);
        assertThat(actConf.getName()).isEqualTo(config.getName());
    }

    @Test(description = "Expecting a successful delete of the existing project configuration")
    @Details()
    public void test_20200106051249() {
        TRProject project = CLIENT.getProject();
        TRProjectConfigGroup configGroup = CLIENT.addConfigGroup(project);
        TRProjectConfig config = new TRProjectConfig().withName("test");
        TRProjectConfig actConf = CLIENT.addConfig(config, configGroup);
        CLIENT.deleteConfig(actConf);
    }

    @Test(description = "Expecting a successful receive of the existing configuration")
    @Details()
    public void test_20200106051421() {
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test_20200106051421");
        TRProjectConfig config = new TRProjectConfig().withName("test_20200106051421");
        TRProject project = CLIENT.getProject();
        TRProjectConfigGroup actConfigGroup = CLIENT.addConfigGroup(configGroup, project);
        CLIENT.addConfig(config, actConfigGroup);
        List<TRProjectConfigGroup> configGroupList = CLIENT.getConfigs(project);
        assertThat(configGroupList).isNotEmpty();
    }

}

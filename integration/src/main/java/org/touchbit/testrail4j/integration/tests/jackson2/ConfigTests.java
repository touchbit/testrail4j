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

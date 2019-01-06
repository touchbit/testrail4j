package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRProject;
import org.touchbit.testrail4j.jackson2.model.TRProjectConfig;
import org.touchbit.testrail4j.jackson2.model.TRProjectConfigGroup;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "config_operations")
public class ConfigTests extends BaseCorvusTest {

    @Test(description = "Expecting a successful creation of the configuration group")
    @Details()
    public void test_20190106045215() {
        TRProject project = CLIENT.getNewProject();
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test_20190106045215");
        TRProjectConfigGroup actConfigGroup = CLIENT.addConfigGroup(configGroup, project);
        assertThat(actConfigGroup.getName()).isEqualTo(configGroup.getName());
        assertThat(actConfigGroup.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting a successful update of the existing configuration group")
    @Details()
    public void test_20190106050310() {
        TRProject project = CLIENT.getNewProject();
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test");
        TRProjectConfigGroup actConfigGroup = CLIENT.addConfigGroup(configGroup, project);
        assertThat(actConfigGroup.getName()).isEqualTo(configGroup.getName());
        assertThat(actConfigGroup.getAdditionalProperties()).isEmpty();

        configGroup.withName("test_20190106050310").withId(actConfigGroup.getId());
        actConfigGroup = CLIENT.updateConfigGroup(configGroup);
        assertThat(actConfigGroup.getAdditionalProperties()).isEmpty();
        assertThat(actConfigGroup.getName()).isEqualTo(configGroup.getName());
    }

    @Test(description = "Expecting a successful delete of the existing configuration group")
    @Details()
    public void test_20190106050730() {
        TRProject project = CLIENT.getNewProject();
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test");
        TRProjectConfigGroup actConfigGroup = CLIENT.addConfigGroup(configGroup, project);
        CLIENT.deleteConfigGroup(actConfigGroup);
    }

    @Test(description = "Expecting a successful creation of the project configuration")
    @Details()
    public void test_20190106050831() {
        TRProject project = CLIENT.getNewProject();
        TRProjectConfigGroup configGroup = CLIENT.addConfigGroup(project);
        TRProjectConfig config = new TRProjectConfig().withName("test_20190106050831");
        TRProjectConfig actConf = CLIENT.addConfig(config, configGroup);
        assertThat(actConf.getAdditionalProperties()).isEmpty();
        assertThat(actConf.getName()).isEqualTo(config.getName());
    }

    @Test(description = "Expecting a successful update of the project configuration")
    @Details()
    public void test_20190106051135() {
        TRProject project = CLIENT.getNewProject();
        TRProjectConfigGroup configGroup = CLIENT.addConfigGroup(project);
        TRProjectConfig config = new TRProjectConfig().withName("test");
        TRProjectConfig actConf = CLIENT.addConfig(config, configGroup);
        assertThat(actConf.getAdditionalProperties()).isEmpty();
        assertThat(actConf.getName()).isEqualTo(config.getName());

        config.withName("test_20190106051135").withId(actConf.getId());
        actConf = CLIENT.updateConfig(config);
        assertThat(actConf.getAdditionalProperties()).isEmpty();
        assertThat(actConf.getName()).isEqualTo(config.getName());
    }

    @Test(description = "Expecting a successful delete of the existing project configuration")
    @Details()
    public void test_20190106051249() {
        TRProject project = CLIENT.getNewProject();
        TRProjectConfigGroup configGroup = CLIENT.addConfigGroup(project);
        TRProjectConfig config = new TRProjectConfig().withName("test");
        TRProjectConfig actConf = CLIENT.addConfig(config, configGroup);
        CLIENT.deleteConfig(actConf);
    }

    @Test(description = "Expecting a successful receive of the existing configuration")
    @Details()
    public void test_20190106051421() {
        TRProjectConfigGroup configGroup = new TRProjectConfigGroup().withName("test_20190106051421");
        TRProjectConfig config = new TRProjectConfig().withName("test_20190106051421");
        TRProject project = CLIENT.getNewProject();
        TRProjectConfigGroup actConfigGroup = CLIENT.addConfigGroup(configGroup, project);
        CLIENT.addConfig(config, actConfigGroup);
        List<TRProjectConfigGroup> configGroupList = CLIENT.getConfigs(project);
        assertThat(configGroupList).isNotEmpty();
        for (TRProjectConfigGroup trProjectConfigGroup : configGroupList) {
            assertThat(trProjectConfigGroup.getAdditionalProperties()).isEmpty();
        }
    }

}

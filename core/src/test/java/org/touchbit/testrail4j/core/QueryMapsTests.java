package org.touchbit.testrail4j.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.core.query.GetCasesQueryMap;
import org.touchbit.testrail4j.core.query.GetResultsQueryMap;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Feign QueryMaps classes tests")
class QueryMapsTests {

    @Test
    @DisplayName("Check GetCasesQueryMap")
    void unitTest_20181111214825() {
        GetCasesQueryMap map = new GetCasesQueryMap();
        map.setCreatedAfter(1);
        map.setCreatedBefore(2);
        map.setCreatedBy(3);
        map.setMilestoneId(4);
        map.setPriorityId(5);
        map.setTemplateId(6);
        map.setTypeId(7);
        map.setUpdatedAfter(8);
        map.setUpdatedBefore(9);
        map.setUpdatedBy(10);
        map.setSuiteId(11);
        map.setSectionId(12);
        assertThat(map.getCreatedAfter()).isEqualTo(1);
        assertThat(map.getCreatedBefore()).isEqualTo(2);
        assertThat(map.getCreatedBy()).isEqualTo(3);
        assertThat(map.getMilestoneId()).isEqualTo(4);
        assertThat(map.getPriorityId()).isEqualTo(5);
        assertThat(map.getTemplateId()).isEqualTo(6);
        assertThat(map.getTypeId()).isEqualTo(7);
        assertThat(map.getUpdatedAfter()).isEqualTo(8);
        assertThat(map.getUpdatedBefore()).isEqualTo(9);
        assertThat(map.getUpdatedBy()).isEqualTo(10);
        assertThat(map.getSuiteId()).isEqualTo(11);
        assertThat(map.getSectionId()).isEqualTo(12);
    }

    @Test
    @DisplayName("Check GetResultsQueryMap")
    void unitTest_20181111220009() {
        GetResultsQueryMap map = new GetResultsQueryMap();
        map.setLimit(1);
        map.setOffset(2);
        map.setStatusId(3);
        assertThat(map.getLimit()).isEqualTo(1);
        assertThat(map.getOffset()).isEqualTo(2);
        assertThat(map.getStatusId()).isEqualTo(3);
    }

}

package org.touchbit.testrail4j.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.core.query.GetCasesQueryMap;
import org.touchbit.testrail4j.core.query.GetResultsQueryMap;

import java.lang.reflect.Field;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Feign QueryMaps classes tests")
class QueryMapsTests {

    @Test
    @DisplayName("Check GetCasesQueryMap")
    void unitTest_20181111214825() {
        GetCasesQueryMap map = new GetCasesQueryMap();
        map.setCreatedAfter(1L);
        map.setCreatedBefore(2L);
        map.setCreatedBy(3L);
        map.setMilestoneId(4L);
        map.setPriorityId(5L);
        map.setTemplateId(6L);
        map.setTypeId(7L);
        map.setUpdatedAfter(8L);
        map.setUpdatedBefore(9L);
        map.setUpdatedBy(10L);
        map.setSuiteId(11L);
        map.setSectionId(12L);
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
        map.setLimit(1L);
        map.setOffset(2L);
        map.setStatusId(3L);
        assertThat(map.getLimit()).isEqualTo(1);
        assertThat(map.getOffset()).isEqualTo(2);
        assertThat(map.getStatusId()).isEqualTo(3);
    }

    @Test
    @DisplayName("GetCasesQueryMap number field type is Long")
    void unitTest_20181112162433() {
        Field[] fields = GetCasesQueryMap.class.getDeclaredFields();
        for (Field field : fields) {
            assertThat(field.getType()).isNotSameAs(Integer.class);
            assertThat(field.getType()).isNotSameAs(BigInteger.class);
        }
    }

    @Test
    @DisplayName("GetResultsQueryMap number field type is Long")
    void unitTest_20181112163018() {
        Field[] fields = GetResultsQueryMap.class.getDeclaredFields();
        for (Field field : fields) {
            assertThat(field.getType()).isNotSameAs(Integer.class);
            assertThat(field.getType()).isNotSameAs(BigInteger.class);
        }
    }

}

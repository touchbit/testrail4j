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

package org.touchbit.testrail4j.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.core.query.filter.GetCasesFilter;
import org.touchbit.testrail4j.core.query.GetResultsQueryMap;
import org.touchbit.testrail4j.core.query.filter.GetResultsFilter;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Feign QueryMaps classes tests")
class QueryMapsTests {

    @Test
    @DisplayName("Check GetCasesQueryMap")
    void unitTest_20181111214825() {
        GetCasesFilter map = new GetCasesFilter();
        map.setCreatedAfter(1L);
        map.setCreatedBefore(2L);
        map.setCreatedBy(3L, 1L);
        map.setMilestoneId(4L, 1L);
        map.setPriorityId(5L, 1L);
        map.setTemplateId(6L, 1L);
        map.setTypeId(7L, 1L);
        map.setUpdatedAfter(8L);
        map.setUpdatedBefore(9L);
        map.setUpdatedBy(10L, 1L);
        map.setSuiteId(11L);
        map.setSectionId(12L);
        assertThat(map.getCreatedAfter()).isEqualTo(1);
        assertThat(map.getCreatedBefore()).isEqualTo(2);
        assertThat(map.getCreatedBy()).isEqualTo("3,1");
        assertThat(map.getMilestoneId()).isEqualTo("4,1");
        assertThat(map.getPriorityId()).isEqualTo("5,1");
        assertThat(map.getTemplateId()).isEqualTo("6,1");
        assertThat(map.getTypeId()).isEqualTo("7,1");
        assertThat(map.getUpdatedAfter()).isEqualTo(8);
        assertThat(map.getUpdatedBefore()).isEqualTo(9);
        assertThat(map.getUpdatedBy()).isEqualTo("10,1");
        assertThat(map.getSuiteId()).isEqualTo(11);
        assertThat(map.getSectionId()).isEqualTo(12);
    }

    @Test
    @DisplayName("Check GetResultsQueryMap")
    void unitTest_20181111220009() {
        GetResultsFilter map = new GetResultsFilter();
        map.setLimit(1L);
        map.setOffset(2L);
        map.setStatusId(3L);
        assertThat(map.getLimit()).isEqualTo(1);
        assertThat(map.getOffset()).isEqualTo(2);
        assertThat(map.getStatusId()).isEqualTo("3");
    }

    @Test
    @DisplayName("GetCasesQueryMap number field type is Long")
    void unitTest_20181112162433() {
        Field[] fields = GetCasesFilter.class.getDeclaredFields();
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
            assertThat(field.getType()).isNotSameAs(Double.class);
            assertThat(field.getType()).isNotSameAs(BigInteger.class);
            assertThat(field.getType()).isNotSameAs(BigDecimal.class);
        }
    }

}

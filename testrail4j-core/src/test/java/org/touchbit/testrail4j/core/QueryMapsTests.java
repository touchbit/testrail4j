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

package org.touchbit.testrail4j.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.core.query.filter.*;
import org.touchbit.testrail4j.core.type.Type;
import org.touchbit.testrail4j.test.core.BaseUnitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.touchbit.testrail4j.core.type.Statuses.BLOCKED;
import static org.touchbit.testrail4j.core.type.Statuses.FAILED;
import static org.touchbit.testrail4j.core.type.Statuses.PASSED;

@DisplayName("Feign QueryMaps classes tests")
class QueryMapsTests extends BaseUnitTest {

    @Test
    @DisplayName("GIVEN BaseFilter WHEN toCommaSeparatedString([]) THEN return null")
    void unitTest_20190107205713() {
        Filter filter = new Filter();
        assertThat(filter.toCommaSeparatedString(new Number[0])).isNull();
        assertThat(filter.toCommaSeparatedString(new Type[0])).isNull();
    }

    @Test
    @DisplayName("GIVEN BaseFilter WHEN toCommaSeparatedString(1, 2) THEN return '1,2'")
    void unitTest_20190107210423() {
        Filter filter = new Filter();
        assertThat(filter.toCommaSeparatedString(1, 2)).isEqualTo("1,2");
    }

    @Test
    @DisplayName("GIVEN  WHEN toCommaSeparatedString(PASSED,BLOCKED) THEN return '1,2'")
    void unitTest_20190107210523() {
        Filter filter = new Filter();
        assertThat(filter.toCommaSeparatedString(PASSED, BLOCKED)).isEqualTo("1,2");
    }

    @Test
    @DisplayName("GIVEN BaseFilter WHEN booleanToInteger(false) THEN return 0")
    void unitTest_20190107210645() {
        Filter filter = new Filter();
        assertThat(filter.booleanToInteger(false)).isEqualTo(0);
    }

    @Test
    @DisplayName("GIVEN BaseFilter WHEN booleanToInteger(true) THEN return 1")
    void unitTest_20190107210919() {
        Filter filter = new Filter();
        assertThat(filter.booleanToInteger(true)).isEqualTo(1);
    }

    @Test
    @DisplayName("Check GetCasesFilter set Methods")
    void unitTest_20181111214825() {
        GetCasesFilter filter = new GetCasesFilter();
        filter.setCreatedAfter(1);
        filter.setCreatedBefore(2);
        filter.setCreatedBy(3, 1);
        filter.setMilestoneId(4, 1);
        filter.setPriorityId(5, 1);
        filter.setTemplateId(6, 1);
        filter.setTypeId(7, 1);
        filter.setUpdatedAfter(8);
        filter.setUpdatedBefore(9);
        filter.setUpdatedBy(10, 1);
        filter.setSuiteId(11);
        filter.setSectionId(12L);
        assertThat(filter.getCreatedAfter()).isEqualTo(1);
        assertThat(filter.getCreatedBefore()).isEqualTo(2);
        assertThat(filter.getCreatedBy()).isEqualTo("3,1");
        assertThat(filter.getMilestoneId()).isEqualTo("4,1");
        assertThat(filter.getPriorityId()).isEqualTo("5,1");
        assertThat(filter.getTemplateId()).isEqualTo("6,1");
        assertThat(filter.getTypeId()).isEqualTo("7,1");
        assertThat(filter.getUpdatedAfter()).isEqualTo(8);
        assertThat(filter.getUpdatedBefore()).isEqualTo(9);
        assertThat(filter.getUpdatedBy()).isEqualTo("10,1");
        assertThat(filter.getSuiteId()).isEqualTo(11);
        assertThat(filter.getSectionId()).isEqualTo(12L);
    }

    @Test
    @DisplayName("Check GetCasesFilter with Methods")
    void unitTest_20190107211404() {
        GetCasesFilter filter = new GetCasesFilter()
                .withCreatedAfter(1)
                .withCreatedBefore(2)
                .withCreatedBy(3, 1)
                .withMilestoneId(4, 1)
                .withPriorityId(5, 1)
                .withTemplateId(6, 1)
                .withTypeId(7, 1)
                .withUpdatedAfter(8)
                .withUpdatedBefore(9)
                .withUpdatedBy(10, 1)
                .withSuiteId(11)
                .withSectionId(12L);
        assertThat(filter.getCreatedAfter()).isEqualTo(1);
        assertThat(filter.getCreatedBefore()).isEqualTo(2);
        assertThat(filter.getCreatedBy()).isEqualTo("3,1");
        assertThat(filter.getMilestoneId()).isEqualTo("4,1");
        assertThat(filter.getPriorityId()).isEqualTo("5,1");
        assertThat(filter.getTemplateId()).isEqualTo("6,1");
        assertThat(filter.getTypeId()).isEqualTo("7,1");
        assertThat(filter.getUpdatedAfter()).isEqualTo(8);
        assertThat(filter.getUpdatedBefore()).isEqualTo(9);
        assertThat(filter.getUpdatedBy()).isEqualTo("10,1");
        assertThat(filter.getSuiteId()).isEqualTo(11);
        assertThat(filter.getSectionId()).isEqualTo(12L);
    }

    @Test
    @DisplayName("Check GetMilestonesFilter set Methods")
    void unitTest_20190107211530() {
        GetMilestonesFilter filter = new GetMilestonesFilter();
        filter.setIsCompleted(true);
        filter.setIsStarted(true);
        assertThat(filter.getIsCompleted()).isEqualTo(1);
        assertThat(filter.getIsStarted()).isEqualTo(1);
    }

    @Test
    @DisplayName("Check GetMilestonesFilter with Methods")
    void unitTest_20190107211800() {
        GetMilestonesFilter filter = new GetMilestonesFilter()
                .withIsCompleted(true)
                .withIsStarted(true);
        assertThat(filter.getIsCompleted()).isEqualTo(1);
        assertThat(filter.getIsStarted()).isEqualTo(1);
    }

    @Test
    @DisplayName("Check GetPlansFilter set Methods")
    void unitTest_20190107212054() {
        GetPlansFilter filter = new GetPlansFilter();
        filter.setCreatedAfter(1);
        filter.setCreatedBefore(2);
        filter.setCreatedBy(3,4);
        filter.setIsCompleted(true);
        filter.setLimit(5);
        filter.setMilestoneId(6,7);
        filter.setOffset(8);
        assertThat(filter.getCreatedAfter()).isEqualTo(1);
        assertThat(filter.getCreatedBefore()).isEqualTo(2);
        assertThat(filter.getCreatedBy()).isEqualTo("3,4");
        assertThat(filter.getIsCompleted()).isEqualTo(1);
        assertThat(filter.getLimit()).isEqualTo(5);
        assertThat(filter.getMilestoneId()).isEqualTo("6,7");
        assertThat(filter.getOffset()).isEqualTo(8);
    }

    @Test
    @DisplayName("Check GetPlansFilter with Methods")
    void unitTest_20190107212108() {
        GetPlansFilter filter = new GetPlansFilter()
                .withCreatedAfter(1)
                .withCreatedBefore(2)
                .withCreatedBy(3,4)
                .withIsCompleted(true)
                .withLimit(5)
                .withMilestoneId(6,7)
                .withOffset(8);
        assertThat(filter.getCreatedAfter()).isEqualTo(1);
        assertThat(filter.getCreatedBefore()).isEqualTo(2);
        assertThat(filter.getCreatedBy()).isEqualTo("3,4");
        assertThat(filter.getIsCompleted()).isEqualTo(1);
        assertThat(filter.getLimit()).isEqualTo(5);
        assertThat(filter.getMilestoneId()).isEqualTo("6,7");
        assertThat(filter.getOffset()).isEqualTo(8);
    }

    @Test
    @DisplayName("Check GetProjectsFilter set Methods")
    void unitTest_20190107212735() {
        GetProjectsFilter filter = new GetProjectsFilter();
        filter.setIsCompleted(true);
        assertThat(filter.getIsCompleted()).isEqualTo(1);
    }

    @Test
    @DisplayName("Check GetProjectsFilter set Methods")
    void unitTest_20190107212739() {
        GetProjectsFilter filter = new GetProjectsFilter();
        filter.withIsCompleted(true);
        assertThat(filter.getIsCompleted()).isEqualTo(1);
    }

    @Test
    @DisplayName("Check GetResultsFilter set Methods")
    void unitTest_20181111220009() {
        GetResultsFilter filter = new GetResultsFilter();
        filter.setStatusId(PASSED,FAILED);
        assertThat(filter.getStatusId()).isEqualTo("1,5");

        filter = new GetResultsFilter();
        filter.setLimit(1L);
        filter.setOffset(2);
        filter.setStatusId(3, 4, 5L);
        filter.setCreatedBefore(22);
        filter.setCreatedAfter(111);
        filter.setCreatedBy(1,2);
        assertThat(filter.getLimit()).isEqualTo(1L);
        assertThat(filter.getOffset()).isEqualTo(2);
        assertThat(filter.getStatusId()).isEqualTo("3,4,5");
        assertThat(filter.getCreatedBefore()).isEqualTo(22);
        assertThat(filter.getCreatedAfter()).isEqualTo(111);
        assertThat(filter.getCreatedBy()).isEqualTo("1,2");
    }

    @Test
    @DisplayName("Check GetResultsFilter with Methods")
    void unitTest_20190107213049() {
        GetResultsFilter filter = new GetResultsFilter()
                .withStatusId(PASSED,FAILED);
        assertThat(filter.getStatusId()).isEqualTo("1,5");

        filter = new GetResultsFilter()
                .withLimit(1L)
                .withOffset(2)
                .withStatusId(3, 4, 5L)
                .withCreatedBefore(22)
                .withCreatedAfter(111)
                .withCreatedBy(1,2);
        assertThat(filter.getLimit()).isEqualTo(1L);
        assertThat(filter.getOffset()).isEqualTo(2);
        assertThat(filter.getStatusId()).isEqualTo("3,4,5");
        assertThat(filter.getCreatedBefore()).isEqualTo(22);
        assertThat(filter.getCreatedAfter()).isEqualTo(111);
        assertThat(filter.getCreatedBy()).isEqualTo("1,2");
    }

    @Test
    @DisplayName("Check GetRunsFilter set Methods")
    void unitTest_20190107213438() {
        GetRunsFilter filter = new GetRunsFilter();
        filter.setCreatedAfter(11111);
        filter.setCreatedBefore(22222);
        filter.setCreatedBy(1,2,3);
        filter.setIsCompleted(false);
        filter.setLimit(1);
        filter.setMilestoneId(33,44);
        filter.setOffset(13);
        filter.setSuiteId(4,5,6);
        assertThat(filter.getCreatedAfter()).isEqualTo(11111);
        assertThat(filter.getCreatedBefore()).isEqualTo(22222);
        assertThat(filter.getCreatedBy()).isEqualTo("1,2,3");
        assertThat(filter.getIsCompleted()).isEqualTo(0);
        assertThat(filter.getLimit()).isEqualTo(1);
        assertThat(filter.getMilestoneId()).isEqualTo("33,44");
        assertThat(filter.getOffset()).isEqualTo(13);
        assertThat(filter.getSuiteId()).isEqualTo("4,5,6");
    }

    @Test
    @DisplayName("Check GetRunsFilter with Methods")
    void unitTest_20190107213454() {
        GetRunsFilter filter = new GetRunsFilter()
                .withCreatedAfter(11111)
                .withCreatedBefore(22222)
                .withCreatedBy(1,2,3)
                .withIsCompleted(false)
                .withLimit(1)
                .withMilestoneId(33,44)
                .withOffset(13)
                .withSuiteId(4,5,6);
        assertThat(filter.getCreatedAfter()).isEqualTo(11111);
        assertThat(filter.getCreatedBefore()).isEqualTo(22222);
        assertThat(filter.getCreatedBy()).isEqualTo("1,2,3");
        assertThat(filter.getIsCompleted()).isEqualTo(0);
        assertThat(filter.getLimit()).isEqualTo(1);
        assertThat(filter.getMilestoneId()).isEqualTo("33,44");
        assertThat(filter.getOffset()).isEqualTo(13);
        assertThat(filter.getSuiteId()).isEqualTo("4,5,6");
    }

    @Test
    @DisplayName("Check GetSectionsFilter set Methods")
    void unitTest_20190107213855() {
        GetSectionsFilter filter = new GetSectionsFilter();
        filter.setSuiteId(1);
        assertThat(filter.getSuiteId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Check GetSectionsFilter with Methods")
    void unitTest_20190107213858() {
        GetSectionsFilter filter = new GetSectionsFilter().withSuiteId(1);
        assertThat(filter.getSuiteId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Check GetTestsFilter set Methods")
    void unitTest_20190107214026() {
        GetTestsFilter filter = new GetTestsFilter();
        filter.setStatusId(PASSED, FAILED);
        assertThat(filter.getStatusIds()).isEqualTo("1,5");
        filter.setStatusId(1,2);
        assertThat(filter.getStatusIds()).isEqualTo("1,2");
    }

    @Test
    @DisplayName("Check GetTestsFilter with Methods")
    void unitTest_20190107214041() {
        GetTestsFilter filter = new GetTestsFilter();
        filter.withStatusId(PASSED, FAILED);
        assertThat(filter.getStatusIds()).isEqualTo("1,5");
        filter.withStatusId(1,2);
        assertThat(filter.getStatusIds()).isEqualTo("1,2");
    }

    private class Filter extends BaseFilter {
        public String toCommaSeparatedString(Number... array) {
            return super.toCommaSeparatedString(array);
        }

        public String toCommaSeparatedString(Type... array) {
            return super.toCommaSeparatedString(array);
        }

        public Integer booleanToInteger(boolean value) {
            return super.booleanToInteger(value);
        }
    }

}

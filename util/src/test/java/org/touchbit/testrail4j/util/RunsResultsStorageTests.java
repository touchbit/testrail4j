package org.touchbit.testrail4j.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RunsResultsStorage class tests")
class RunsResultsStorageTests {

    @Test
    @DisplayName("#add(Long, Result) and #getResultsForRun(Long)")
    void unitTest_20181113175023() {
        Result result1 = new Result();
        Result result2 = new Result();
        RunsResultsStorage<Result> storage = new RunsResultsStorage<>();

        storage.add(1L, result1);
        assertThat(storage.getResultsForRun(1L)).size().isEqualTo(1);
        assertThat(storage.getResultsForRun(1L)).contains(result1);

        storage.add(1L, result1);
        assertThat(storage.getResultsForRun(1L)).size().isEqualTo(1);
        assertThat(storage.getResultsForRun(1L)).contains(result1);

        storage.add(1L, result2);
        assertThat(storage.getResultsForRun(1L)).size().isEqualTo(2);
        assertThat(storage.getResultsForRun(1L)).contains(result1, result2);
    }

    @Test
    @DisplayName("#getRunIDs() and #getCaseIDsForRun(Long)")
    void unitTest_20181113180406() {
        Result result1 = new Result();
        result1.id = 11;
        Result result2 = new Result();
        result2.id = 22;
        RunsResultsStorage<Result> storage = new RunsResultsStorage<>();
        storage.add(1L, result1);
        storage.add(2L, result2);

        List<Long> runIDs = storage.getRunIDs();
        assertThat(runIDs).size().isEqualTo(2);
        assertThat(runIDs).contains(1L, 2L);

        for (Long runID : runIDs) {
            List<Long> caseIDsForRun = storage.getCaseIDsForRun(runID);
            assertThat(caseIDsForRun).size().isEqualTo(1);
            assertThat(caseIDsForRun).contains(runID * 11);
        }
    }

    private static class Result implements TestResult {
        long id = 0;
        @Override public Long getCaseId() {
            return id;
        }
    }

}

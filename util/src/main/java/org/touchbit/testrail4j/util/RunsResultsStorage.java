package org.touchbit.testrail4j.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Test Run Result Store with Run ID split
 * <p>
 * Created by Oleg Shaburov on 13.11.2018
 * shaburov.o.a@gmail.com
 */
public class RunsResultsStorage<R extends TestResult> {

    /**
     *  key   - TestRail run id
     *  value - list of test result objects {@link R}
     */
    private final Map<Long, List<R>> runsResults;

    public RunsResultsStorage() {
        this(new ConcurrentHashMap<>());
    }

    public RunsResultsStorage(Map<Long, List<R>> map) {
        runsResults = map;
    }

    public void add(final Long runID, final R result) {
        List<R> list = runsResults.get(runID);
        if (list == null) {
            list = new ArrayList<>();
            list.add(result);
            runsResults.put(runID, list);
        } else {
            if (!list.contains(result)) {
                runsResults.get(runID).add(result);
            }
        }
    }

    public List<R> getResultsForRun(final Long runID) {
        return runsResults.get(runID);
    }

    public List<Long> getRunIDs() {
        return new ArrayList<>(runsResults.keySet());
    }

    public List<Long> getCaseIDsForRun(final Long runID) {
        return getResultsForRun(runID).stream()
                .map(R::getCaseId)
                .collect(Collectors.toList());
    }

}

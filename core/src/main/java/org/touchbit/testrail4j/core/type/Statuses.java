package org.touchbit.testrail4j.core.type;

/**
 * The ID of the test status.
 * Untested - not allowed when adding a result
 *
 * <p>
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
public enum Statuses {
    
    PASSED (1),
    BLOCKED (2),
    UNTESTED (3),
    RETEST (4),
    FAILED (5),
    CUSTOM_STATUS1 (6),
    CUSTOM_STATUS2 (7),
    CUSTOM_STATUS3 (8),
    CUSTOM_STATUS4 (9),
    CUSTOM_STATUS5 (10),
    CUSTOM_STATUS6 (11),
    CUSTOM_STATUS7 (12),
    ;

    long id;

    Statuses(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}

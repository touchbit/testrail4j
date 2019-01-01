package org.touchbit.testrail4j.core.type;

/**
 * Default test case types
 * <p>
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
public enum CaseTypes {

    ACCEPTANCE (1),
    ACCESSIBILITY (2),
    AUTOMATED (3),
    COMPATIBILITY (4),
    DESTRUCTIVE (5),
    FUNCTIONAL (6),
    OTHER (7),
    PERFORMANCE (8),
    REGRESSION (9),
    SECURITY (10),
    SMOKE  (11),
    USABILITY (12),
    ;

    long id;

    CaseTypes(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}

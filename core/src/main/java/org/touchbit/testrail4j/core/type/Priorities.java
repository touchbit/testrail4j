package org.touchbit.testrail4j.core.type;

/**
 * Default test case priorities
 * <p>
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
public enum Priorities {
    
    LOW (1),
    MEDIUM (2),
    HIGH (3),
    CRITICAL (4),
    ;

    long id;

    Priorities(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}

package org.touchbit.testrail4j.core.type;

/**
 * Default test case automation types
 * <p>
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
public enum  AutomationType {

    NONE (0),
    RANOREX (1);

    long id;

    AutomationType(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}

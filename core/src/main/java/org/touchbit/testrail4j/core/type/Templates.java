package org.touchbit.testrail4j.core.type;

/**
 * Default test case templates
 * <p>
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
public enum Templates {
    
    TEST_CASE_TEXT (1),
    TEST_CASE_STEPS (2),
    EXPLORATORY_SESSION (3),
    ;

    long id;

    Templates(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}

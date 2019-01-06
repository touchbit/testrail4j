package org.touchbit.testrail4j.core.type;

/**
 * The following list shows the available custom field types (type_id field)
 * http://docs.gurock.com/testrail-api2/reference-cases-fields
 * <p>
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
public enum FieldTypes {

    STRING(1),
    INTEGER(2),
    TEXT(3),
    URL(4),
    CHECKBOX(5),
    DROPDOWN(6),
    USER(7),
    DATE(8),
    MILESTONE(9),
    STEPS(10),
    MULTI_SELECT(12);

    int id;

    FieldTypes(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}

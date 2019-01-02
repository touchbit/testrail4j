package org.touchbit.testrail4j.core.query;

import java.util.StringJoiner;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
public abstract class BaseQueryMap {

    protected String toCommaSeparatedString(Object[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        StringJoiner sj = new StringJoiner(",");
        for (Object s : array) {
            sj.add(String.valueOf(s));
        }
        return sj.toString();
    }

}

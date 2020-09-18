package org.touchbit.testrail4j.core.field;

import java.util.StringJoiner;

/**
 * The estimate, e.g. “30s” or “1m 45s”
 *
 * <p>
 * Created by Oleg Shaburov on 18.09.2020
 * shaburov.o.a@gmail.com
 */
public class Estimate {

    private int day = 0;
    private int hour = 0;
    private int min = 0;
    private int sec = 0;

    public int getDay() {
        return day;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Estimate withDay(int day) {
        this.day = day;
        return this;
    }

    public Estimate withHour(int hour) {
        this.hour = hour;
        return this;
    }

    public Estimate withMin(int min) {
        this.min = min;
        return this;
    }

    public Estimate withSec(int sec) {
        this.sec = sec;
        return this;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (day > 0) {
            stringJoiner.add(day + "d");
        }
        if (hour > 0) {
            stringJoiner.add(hour + "h");
        }
        if (min > 0) {
            stringJoiner.add(min + "m");
        }
        if (sec > 0) {
            stringJoiner.add(sec + "s");
        }
        return stringJoiner.toString();
    }

}

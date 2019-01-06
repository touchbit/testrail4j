package org.touchbit.testrail4j.core.query;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class MilestonesQueryMap {

    /**
     * 1 to return completed milestones only. 0 to return open (active/upcoming) milestones only (available since TestRail 4.0).
     */
    private Integer is_completed;

    /**
     * 1 to return started milestones only. 0 to return upcoming milestones only (available since TestRail 5.3).
     */
    private Integer is_started;

    public Integer getIsCompleted() {
        return is_completed;
    }

    public Integer getIsStarted() {
        return is_started;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.is_completed = isCompleted;
    }

    public void setIsStarted(Integer isStarted) {
        this.is_started = isStarted;
    }

    public MilestonesQueryMap withIsCompleted(Integer isCompleted) {
        this.is_completed = isCompleted;
        return this;
    }

    public MilestonesQueryMap withIsStarted(Integer isStarted) {
        this.is_started = isStarted;
        return this;
    }


}

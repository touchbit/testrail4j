package org.touchbit.testrail4j.core.query;

/**
 * The following filters can be applied
 * <p>
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetProjectsQueryMap extends BaseQueryMap {

    /**
     * true to return completed projects only. false to return active projects only.
     */
    private Integer is_completed;

    public Integer getIsCompleted() {
        return is_completed;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.is_completed = booleanToInteger(isCompleted);
    }

    public GetProjectsQueryMap withIsCompleted(Boolean isCompleted) {
        this.is_completed = booleanToInteger(isCompleted);
        return this;
    }

}

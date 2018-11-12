package org.touchbit.testrail4j.core.query;

/**
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetResultsQueryMap {

    /** A comma-separated list of status IDs to filter by */
    private Integer status_id;
    /** Limit the test result */
    private Integer limit;

    /** Use to skip records */
    private Integer offset;

    public Integer getStatusId() {
        return status_id;
    }

    public void setStatusId(Integer statusId) {
        this.status_id = statusId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}

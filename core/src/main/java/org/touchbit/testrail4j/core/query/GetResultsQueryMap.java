package org.touchbit.testrail4j.core.query;

/**
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetResultsQueryMap {

    /** A comma-separated list of status IDs to filter by */
    private Long status_id;
    /** Limit the test result */
    private Long limit;

    /** Use to skip records */
    private Long offset;

    public Long getStatusId() {
        return status_id;
    }

    public void setStatusId(Long statusId) {
        this.status_id = statusId;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

}

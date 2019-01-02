package org.touchbit.testrail4j.core.query;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
public class GetTestsQueryMap extends BaseQueryMap {

    /**
     * A comma-separated list of status IDs to filter by.
     */
    private String status_id;

    /**
     * A comma-separated list of status IDs to filter by.
     */
    public String getStatusIds() {
        return status_id;
    }

    public void setStatusId(Long... statusIds) {
        this.status_id = toCommaSeparatedString(statusIds);
    }

    public GetTestsQueryMap withStatusId(Long... statusIds) {
        this.status_id = toCommaSeparatedString(statusIds);
        return this;
    }

}

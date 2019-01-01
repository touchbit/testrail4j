package org.touchbit.testrail4j.core.query;

/**
 * The filter can be applied for the get runs call (available since TestRail 4.0)
 * All active test runs for project with ID 1 created by user with ID 1 or 2
 * GET index.php?/api/v2/get_runs/1&is_completed=0&created_by=1,2
 *
 * <p>
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetRunsQueryMap {

    /**
     * Only return test runs created after this date (as UNIX timestamp).
     */
    private Long created_after;

    /**
     * Only return test runs created before this date (as UNIX timestamp).
     */
    private Long created_before;

    /**
     * A comma-separated list of creators (user IDs) to filter by.
     */
    private String created_by;

    /**
     * 1 to return completed test runs only. 0 to return active test runs only.
     */
    private int is_completed;

    /**
     * Limit the result to :limit test runs. Use :offset to skip records.
     */
    private int limit;

    /**
     * Limit the result to :limit test runs. Use :offset to skip records.
     */
    private int offset;

    /**
     * A comma-separated list of milestone IDs to filter by.
     */
    private String milestone_id;

    /**
     * A comma-separated list of test suite IDs to filter by.
     */
    private String suite_id;

    public Long getCreatedAfter() {
        return created_after;
    }

    public void setCreatedAfter(Long created_after) {
        this.created_after = created_after;
    }

    public Long getCreatedBefore() {
        return created_before;
    }

    public void setCreatedBefore(Long created_before) {
        this.created_before = created_before;
    }

    public String getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(String created_by) {
        this.created_by = created_by;
    }

    public int getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(int is_completed) {
        this.is_completed = is_completed;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getMilestoneId() {
        return milestone_id;
    }

    public void setMilestoneId(String milestone_id) {
        this.milestone_id = milestone_id;
    }

    public String getSuiteId() {
        return suite_id;
    }

    public void setSuiteId(String suite_id) {
        this.suite_id = suite_id;
    }

}

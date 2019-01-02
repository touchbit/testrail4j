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
public class GetRunsQueryMap extends BaseQueryMap {

    /**
     * Only return test runs created after this date (as UNIX timestamp).
     */
    private Long created_after;

    /**
     * Only return test runs created before this date (as UNIX timestamp).
     */
    private Long createdBefore;

    /**
     * A comma-separated list of creators (user IDs) to filter by.
     */
    private String created_by;

    /**
     * 1 to return completed test runs only. 0 to return active test runs only.
     */
    private Integer is_completed;

    /**
     * Limit the result to :limit test runs. Use :offset to skip records.
     */
    private Integer limit;

    /**
     * Limit the result to :limit test runs. Use :offset to skip records.
     */
    private Integer offset;

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

    public Long getCreatedBefore() {
        return createdBefore;
    }

    public String getCreatedBy() {
        return created_by;
    }

    public Integer getIsCompleted() {
        return is_completed;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public String getMilestoneId() {
        return milestone_id;
    }

    public String getSuiteId() {
        return suite_id;
    }

    public void setCreatedAfter(Long createdAfter) {
        this.created_after = createdAfter;
    }

    public void setCreatedBefore(Long createdBefore) {
        this.createdBefore = createdBefore;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.is_completed = isCompleted;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setMilestoneId(Long... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
    }

    public void setSuiteId(Long... suiteId) {
        this.suite_id = toCommaSeparatedString(suiteId);
    }

    public void setCreatedBy(Long... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
    }

    public GetRunsQueryMap withCreatedAfter(Long createdAfter) {
        this.created_after = createdAfter;
        return this;
    }

    public GetRunsQueryMap withCreatedBefore(Long createdBefore) {
        this.createdBefore = createdBefore;
        return this;
    }

    public GetRunsQueryMap withIsCompleted(Integer isCompleted) {
        this.is_completed = isCompleted;
        return this;
    }

    public GetRunsQueryMap withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public GetRunsQueryMap withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public GetRunsQueryMap withMilestoneId(Long... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
        return this;
    }

    public GetRunsQueryMap withSuiteId(Long... suiteId) {
        this.suite_id = toCommaSeparatedString(suiteId);
        return this;
    }

    public GetRunsQueryMap withCreatedBy(Long... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
        return this;
    }

}

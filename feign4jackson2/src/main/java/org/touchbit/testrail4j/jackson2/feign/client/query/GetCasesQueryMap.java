package org.touchbit.testrail4j.jackson2.feign.client.query;

/**
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetCasesQueryMap {

    /** Only return test cases created after this date (as UNIX timestamp). */
    private Integer created_after;
    /** Only return test cases created before this date (as UNIX timestamp). */
    private Integer created_before;
    /** Only return test cases updated after this date (as UNIX timestamp). */
    private Integer updated_after;
    /** Only return test cases updated before this date (as UNIX timestamp). */
    private Integer updated_before;
    /** A comma-separated list of creators (user IDs) to filter by. */
    private Integer created_by;
    /** A comma-separated list of milestone IDs to filter by (not available if the milestone field is disabled for the project). */
    private Integer milestone_id;
    /** A comma-separated list of priority IDs to filter by. */
    private Integer priority_id;
    /** A comma-separated list of template IDs to filter by (requires TestRail 5.2 or later) */
    private Integer template_id;
    /** A comma-separated list of case type IDs to filter by. */
    private Integer type_id;
    /** A comma-separated list of users who updated test cases to filter by. */
    private Integer updated_by;

    public Integer getCreatedAfter() {
        return created_after;
    }

    public void setCreatedAfter(Integer createdAfter) {
        this.created_after = createdAfter;
    }

    public Integer getCreatedBefore() {
        return created_before;
    }

    public void setCreatedBefore(Integer createdBefore) {
        this.created_before = createdBefore;
    }

    public Integer getUpdatedAfter() {
        return updated_after;
    }

    public void setUpdatedAfter(Integer updatedAfter) {
        this.updated_after = updatedAfter;
    }

    public Integer getUpdatedBefore() {
        return updated_before;
    }

    public void setUpdatedBefore(Integer updatedBefore) {
        this.updated_before = updatedBefore;
    }

    public Integer getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(Integer createdBy) {
        this.created_by = createdBy;
    }

    public Integer getMilestoneId() {
        return milestone_id;
    }

    public void setMilestoneId(Integer milestoneId) {
        this.milestone_id = milestoneId;
    }

    public Integer getPriorityId() {
        return priority_id;
    }

    public void setPriorityId(Integer priorityId) {
        this.priority_id = priorityId;
    }

    public Integer getTemplateId() {
        return template_id;
    }

    public void setTemplateId(Integer templateId) {
        this.template_id = templateId;
    }

    public Integer getTypeId() {
        return type_id;
    }

    public void setTypeId(Integer typeId) {
        this.type_id = typeId;
    }

    public Integer getUpdatedBy() {
        return updated_by;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updated_by = updatedBy;
    }

}

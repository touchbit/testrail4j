/*
 * Copyright © 2018 Shaburov Oleg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.touchbit.testrail4j.core.query;

/**
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetCasesQueryMap {

    /** The ID of the test suite (optional if the project is operating in single suite mode) */
    private Long suite_id;
    /** The ID of the section (optional) */
    private Long section_id;
    /** Only return test cases created after this date (as UNIX timestamp). */
    private Long created_after;
    /** Only return test cases created before this date (as UNIX timestamp). */
    private Long created_before;
    /** Only return test cases updated after this date (as UNIX timestamp). */
    private Long updated_after;
    /** Only return test cases updated before this date (as UNIX timestamp). */
    private Long updated_before;
    /**
     * A comma-separated list of milestone IDs to filter by
     * (not available if the milestone field is disabled for the project).
     */
    private Long milestone_id;
    /** A comma-separated list of creators (user IDs) to filter by. */
    private Long created_by;
    /** A comma-separated list of priority IDs to filter by. */
    private Long priority_id;
    /** A comma-separated list of template IDs to filter by (requires TestRail 5.2 or later) */
    private Long template_id;
    /** A comma-separated list of case type IDs to filter by. */
    private Long type_id;
    /** A comma-separated list of users who updated test cases to filter by. */
    private Long updated_by;

    public Long getCreatedAfter() {
        return created_after;
    }

    public void setCreatedAfter(Long createdAfter) {
        this.created_after = createdAfter;
    }

    public Long getCreatedBefore() {
        return created_before;
    }

    public void setCreatedBefore(Long createdBefore) {
        this.created_before = createdBefore;
    }

    public Long getUpdatedAfter() {
        return updated_after;
    }

    public void setUpdatedAfter(Long updatedAfter) {
        this.updated_after = updatedAfter;
    }

    public Long getUpdatedBefore() {
        return updated_before;
    }

    public void setUpdatedBefore(Long updatedBefore) {
        this.updated_before = updatedBefore;
    }

    public Long getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(Long createdBy) {
        this.created_by = createdBy;
    }

    public Long getMilestoneId() {
        return milestone_id;
    }

    public void setMilestoneId(Long milestoneId) {
        this.milestone_id = milestoneId;
    }

    public Long getPriorityId() {
        return priority_id;
    }

    public void setPriorityId(Long priorityId) {
        this.priority_id = priorityId;
    }

    public Long getTemplateId() {
        return template_id;
    }

    public void setTemplateId(Long templateId) {
        this.template_id = templateId;
    }

    public Long getTypeId() {
        return type_id;
    }

    public void setTypeId(Long typeId) {
        this.type_id = typeId;
    }

    public Long getUpdatedBy() {
        return updated_by;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updated_by = updatedBy;
    }

    public Long getSuiteId() {
        return suite_id;
    }

    public void setSuiteId(Long suiteId) {
        this.suite_id = suiteId;
    }

    public Long getSectionId() {
        return section_id;
    }

    public void setSectionId(Long sectionId) {
        this.section_id = sectionId;
    }

}

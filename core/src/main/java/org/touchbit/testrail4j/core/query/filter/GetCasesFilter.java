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

package org.touchbit.testrail4j.core.query.filter;

import org.touchbit.testrail4j.core.query.GetCasesQueryMap;

/**
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetCasesFilter extends BaseQueryMap implements GetCasesQueryMap {

    /**
     * The ID of the test suite (optional if the project is operating in single suite mode)
     */
    private Long suite_id;
    /**
     * The ID of the section (optional)
     */
    private Long section_id;
    /**
     * Only return test cases created after this date (as UNIX timestamp).
     */
    private Long created_after;
    /**
     * Only return test cases created before this date (as UNIX timestamp).
     */
    private Long created_before;
    /**
     * Only return test cases updated after this date (as UNIX timestamp).
     */
    private Long updated_after;
    /**
     * Only return test cases updated before this date (as UNIX timestamp).
     */
    private Long updated_before;
    /**
     * A comma-separated list of milestone IDs to filter by
     * (not available if the milestone field is disabled for the project).
     */
    private String milestone_id;
    /**
     * A comma-separated list of creators (user IDs) to filter by.
     */
    private String created_by;
    /**
     * A comma-separated list of priority IDs to filter by.
     */
    private String priority_id;
    /**
     * A comma-separated list of template IDs to filter by (requires TestRail 5.2 or later)
     */
    private String template_id;
    /**
     * A comma-separated list of case type IDs to filter by.
     */
    private String type_id;
    /**
     * A comma-separated list of users who updated test cases to filter by.
     */
    private String updated_by;

    public Long getCreatedAfter() {
        return created_after;
    }

    public Long getCreatedBefore() {
        return created_before;
    }

    public Long getUpdatedAfter() {
        return updated_after;
    }

    public Long getUpdatedBefore() {
        return updated_before;
    }

    public String getCreatedBy() {
        return created_by;
    }

    public String getMilestoneId() {
        return milestone_id;
    }

    public String getPriorityId() {
        return priority_id;
    }

    public String getTemplateId() {
        return template_id;
    }

    public String getTypeId() {
        return type_id;
    }

    public String getUpdatedBy() {
        return updated_by;
    }

    public Long getSuiteId() {
        return suite_id;
    }

    public Long getSectionId() {
        return section_id;
    }

    public void setCreatedAfter(Long createdAfter) {
        this.created_after = createdAfter;
    }

    public void setCreatedBefore(Long createdBefore) {
        this.created_before = createdBefore;
    }

    public void setUpdatedAfter(Long updatedAfter) {
        this.updated_after = updatedAfter;
    }

    public void setUpdatedBefore(Long updatedBefore) {
        this.updated_before = updatedBefore;
    }

    public void setCreatedBy(Long... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
    }

    public void setMilestoneId(Long... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
    }

    public void setPriorityId(Long... priorityId) {
        this.priority_id = toCommaSeparatedString(priorityId);
    }

    public void setUpdatedBy(Long... updatedBy) {
        this.updated_by = toCommaSeparatedString(updatedBy);
    }

    public void setTemplateId(Long... templateId) {
        this.template_id = toCommaSeparatedString(templateId);
    }

    public void setTypeId(Long... typeId) {
        this.type_id = toCommaSeparatedString(typeId);
    }

    public void setSuiteId(Long suiteId) {
        this.suite_id = suiteId;
    }

    public void setSectionId(Long sectionId) {
        this.section_id = sectionId;
    }

    public GetCasesFilter withCreatedAfter(Long createdAfter) {
        this.created_after = createdAfter;
        return this;
    }

    public GetCasesFilter withCreatedBefore(Long createdBefore) {
        this.created_before = createdBefore;
        return this;
    }

    public GetCasesFilter withUpdatedAfter(Long updatedAfter) {
        this.updated_after = updatedAfter;
        return this;
    }

    public GetCasesFilter withUpdatedBefore(Long updatedBefore) {
        this.updated_before = updatedBefore;
        return this;
    }

    public GetCasesFilter withCreatedBy(Long... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
        return this;
    }

    public GetCasesFilter withMilestoneId(Long... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
        return this;
    }

    public GetCasesFilter withPriorityId(Long... priorityId) {
        this.priority_id = toCommaSeparatedString(priorityId);
        return this;
    }

    public GetCasesFilter withUpdatedBy(Long... updatedBy) {
        this.updated_by = toCommaSeparatedString(updatedBy);
        return this;
    }

    public GetCasesFilter withTemplateId(Long... templateId) {
        this.template_id = toCommaSeparatedString(templateId);
        return this;
    }

    public GetCasesFilter withTypeId(Long... typeId) {
        this.type_id = toCommaSeparatedString(typeId);
        return this;
    }

    public GetCasesFilter withSuiteId(Long suiteId) {
        this.suite_id = suiteId;
        return this;
    }

    public GetCasesFilter withSectionId(Long sectionId) {
        this.section_id = sectionId;
        return this;
    }

}

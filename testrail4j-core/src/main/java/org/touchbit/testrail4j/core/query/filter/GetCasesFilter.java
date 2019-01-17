/*
 * MIT License
 *
 * Copyright © 2019 TouchBIT.
 * Copyright © 2019 Oleg Shaburov.
 * Copyright © 2018 Maria Vasilenko.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.touchbit.testrail4j.core.query.filter;

import org.touchbit.testrail4j.core.query.GetCasesQueryMap;

/**
 * Get Cases QueryMap Filter
 * <p>
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetCasesFilter extends BaseFilter implements GetCasesQueryMap {

    /**
     * The ID of the test suite (optional if the project is operating in single suite mode)
     */
    private Number suite_id;
    /**
     * The ID of the section (optional)
     */
    private Number section_id;
    /**
     * Only return test cases created after this date (as UNIX timestamp).
     */
    private Number created_after;
    /**
     * Only return test cases created before this date (as UNIX timestamp).
     */
    private Number created_before;
    /**
     * Only return test cases updated after this date (as UNIX timestamp).
     */
    private Number updated_after;
    /**
     * Only return test cases updated before this date (as UNIX timestamp).
     */
    private Number updated_before;
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

    public Number getCreatedAfter() {
        return created_after;
    }

    public Number getCreatedBefore() {
        return created_before;
    }

    public Number getUpdatedAfter() {
        return updated_after;
    }

    public Number getUpdatedBefore() {
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

    public Number getSuiteId() {
        return suite_id;
    }

    public Number getSectionId() {
        return section_id;
    }

    public void setCreatedAfter(Number createdAfter) {
        this.created_after = createdAfter;
    }

    public void setCreatedBefore(Number createdBefore) {
        this.created_before = createdBefore;
    }

    public void setUpdatedAfter(Number updatedAfter) {
        this.updated_after = updatedAfter;
    }

    public void setUpdatedBefore(Number updatedBefore) {
        this.updated_before = updatedBefore;
    }

    public void setCreatedBy(Number... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
    }

    public void setMilestoneId(Number... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
    }

    public void setPriorityId(Number... priorityId) {
        this.priority_id = toCommaSeparatedString(priorityId);
    }

    public void setUpdatedBy(Number... updatedBy) {
        this.updated_by = toCommaSeparatedString(updatedBy);
    }

    public void setTemplateId(Number... templateId) {
        this.template_id = toCommaSeparatedString(templateId);
    }

    public void setTypeId(Number... typeId) {
        this.type_id = toCommaSeparatedString(typeId);
    }

    public void setSuiteId(Number suiteId) {
        this.suite_id = suiteId;
    }

    public void setSectionId(Number sectionId) {
        this.section_id = sectionId;
    }

    public GetCasesFilter withCreatedAfter(Number createdAfter) {
        this.created_after = createdAfter;
        return this;
    }

    public GetCasesFilter withCreatedBefore(Number createdBefore) {
        this.created_before = createdBefore;
        return this;
    }

    public GetCasesFilter withUpdatedAfter(Number updatedAfter) {
        this.updated_after = updatedAfter;
        return this;
    }

    public GetCasesFilter withUpdatedBefore(Number updatedBefore) {
        this.updated_before = updatedBefore;
        return this;
    }

    public GetCasesFilter withCreatedBy(Number... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
        return this;
    }

    public GetCasesFilter withMilestoneId(Number... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
        return this;
    }

    public GetCasesFilter withPriorityId(Number... priorityId) {
        this.priority_id = toCommaSeparatedString(priorityId);
        return this;
    }

    public GetCasesFilter withUpdatedBy(Number... updatedBy) {
        this.updated_by = toCommaSeparatedString(updatedBy);
        return this;
    }

    public GetCasesFilter withTemplateId(Number... templateId) {
        this.template_id = toCommaSeparatedString(templateId);
        return this;
    }

    public GetCasesFilter withTypeId(Number... typeId) {
        this.type_id = toCommaSeparatedString(typeId);
        return this;
    }

    public GetCasesFilter withSuiteId(Number suiteId) {
        this.suite_id = suiteId;
        return this;
    }

    public GetCasesFilter withSectionId(Number sectionId) {
        this.section_id = sectionId;
        return this;
    }

}

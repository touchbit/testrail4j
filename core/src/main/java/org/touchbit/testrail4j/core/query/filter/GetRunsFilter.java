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

import org.touchbit.testrail4j.core.query.GetRunsQueryMap;

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
public class GetRunsFilter extends BaseFilter implements GetRunsQueryMap {

    /**
     * Only return test runs created after this date (as UNIX timestamp).
     */
    private Number created_after;

    /**
     * Only return test runs created before this date (as UNIX timestamp).
     */
    private Number createdBefore;

    /**
     * A comma-separated list of creators (user IDs) to filter by.
     */
    private String created_by;

    /**
     * 1 to return completed test runs only. 0 to return active test runs only.
     */
    private Number is_completed;

    /**
     * Limit the result to :limit test runs. Use :offset to skip records.
     */
    private Number limit;

    /**
     * Limit the result to :limit test runs. Use :offset to skip records.
     */
    private Number offset;

    /**
     * A comma-separated list of milestone IDs to filter by.
     */
    private String milestone_id;

    /**
     * A comma-separated list of test suite IDs to filter by.
     */
    private String suite_id;

    public Number getCreatedAfter() {
        return created_after;
    }

    public Number getCreatedBefore() {
        return createdBefore;
    }

    public String getCreatedBy() {
        return created_by;
    }

    public Number getIsCompleted() {
        return is_completed;
    }

    public Number getLimit() {
        return limit;
    }

    public Number getOffset() {
        return offset;
    }

    public String getMilestoneId() {
        return milestone_id;
    }

    public String getSuiteId() {
        return suite_id;
    }

    public void setCreatedAfter(Number createdAfter) {
        this.created_after = createdAfter;
    }

    public void setCreatedBefore(Number createdBefore) {
        this.createdBefore = createdBefore;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.is_completed = booleanToInteger(isCompleted);
    }

    public void setLimit(Number limit) {
        this.limit = limit;
    }

    public void setOffset(Number offset) {
        this.offset = offset;
    }

    public void setMilestoneId(Number... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
    }

    public void setSuiteId(Number... suiteId) {
        this.suite_id = toCommaSeparatedString(suiteId);
    }

    public void setCreatedBy(Number... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
    }

    public GetRunsFilter withCreatedAfter(Number createdAfter) {
        this.created_after = createdAfter;
        return this;
    }

    public GetRunsFilter withCreatedBefore(Number createdBefore) {
        this.createdBefore = createdBefore;
        return this;
    }

    public GetRunsFilter withIsCompleted(Boolean isCompleted) {
        this.is_completed = booleanToInteger(isCompleted);
        return this;
    }

    public GetRunsFilter withLimit(Number limit) {
        this.limit = limit;
        return this;
    }

    public GetRunsFilter withOffset(Number offset) {
        this.offset = offset;
        return this;
    }

    public GetRunsFilter withMilestoneId(Number... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
        return this;
    }

    public GetRunsFilter withSuiteId(Number... suiteId) {
        this.suite_id = toCommaSeparatedString(suiteId);
        return this;
    }

    public GetRunsFilter withCreatedBy(Number... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
        return this;
    }

}

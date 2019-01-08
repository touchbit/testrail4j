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

import org.touchbit.testrail4j.core.query.GetPlansQueryMap;

/**
 * The following filters can be applied (available since TestRail 4.0)
 * <p>
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetPlansFilter extends BaseFilter implements GetPlansQueryMap {

    /**
     * Only return test plans created after this date (as UNIX timestamp).
     */
    private Number created_after;

    /**
     * Only return test plans created before this date (as UNIX timestamp).
     */
    private Number created_before;

    /**
     * 1 to return completed test plans only. 0 to return active test plans only.
     */
    private Number is_completed;

    /**
     * Limit the result to :limit test plans. Use :offset to skip records.
     */
    private Number limit;

    /**
     * Limit the result to :limit test plans. Use :offset to skip records.
     */
    private Number offset;
    /**
     * A comma-separated list of creators (user IDs) to filter by.
     */
    private String created_by;

    /**
     * A comma-separated list of milestone IDs to filter by.
     */
    private String milestone_id;

    public Number getCreatedAfter() {
        return created_after;
    }

    public Number getCreatedBefore() {
        return created_before;
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

    public String getCreatedBy() {
        return created_by;
    }

    public String getMilestoneId() {
        return milestone_id;
    }

    public void setCreatedAfter(Number createdAfter) {
        this.created_after = createdAfter;
    }

    public void setCreatedBefore(Number createdBefore) {
        this.created_before = createdBefore;
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

    public void setCreatedBy(Number... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
    }

    public void setMilestoneId(Number... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
    }

    public GetPlansFilter withCreatedAfter(Number createdAfter) {
        this.created_after = createdAfter;
        return this;
    }

    public GetPlansFilter withCreatedBefore(Number createdBefore) {
        this.created_before = createdBefore;
        return this;
    }

    public GetPlansFilter withIsCompleted(Boolean isCompleted) {
        this.is_completed = booleanToInteger(isCompleted);
        return this;
    }

    public GetPlansFilter withLimit(Number limit) {
        this.limit = limit;
        return this;
    }

    public GetPlansFilter withOffset(Number offset) {
        this.offset = offset;
        return this;
    }

    public GetPlansFilter withCreatedBy(Number... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
        return this;
    }

    public GetPlansFilter withMilestoneId(Number... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
        return this;
    }

}

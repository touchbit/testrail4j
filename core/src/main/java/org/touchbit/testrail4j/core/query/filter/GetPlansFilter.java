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
public class GetPlansFilter extends BaseQueryMap implements GetPlansQueryMap {

    /**
     * Only return test plans created after this date (as UNIX timestamp).
     */
    private Long created_after;

    /**
     * Only return test plans created before this date (as UNIX timestamp).
     */
    private Long created_before;

    /**
     * 1 to return completed test plans only. 0 to return active test plans only.
     */
    private Integer is_completed;

    /**
     * Limit the result to :limit test plans. Use :offset to skip records.
     */
    private Integer limit;

    /**
     * Limit the result to :limit test plans. Use :offset to skip records.
     */
    private Integer offset;
    /**
     * A comma-separated list of creators (user IDs) to filter by.
     */
    private String created_by;

    /**
     * A comma-separated list of milestone IDs to filter by.
     */
    private String milestone_id;

    public Long getCreatedAfter() {
        return created_after;
    }

    public Long getCreatedBefore() {
        return created_before;
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

    public String getCreatedBy() {
        return created_by;
    }

    public String getMilestoneId() {
        return milestone_id;
    }

    public void setCreatedAfter(Long createdAfter) {
        this.created_after = createdAfter;
    }

    public void setCreatedBefore(Long createdBefore) {
        this.created_before = createdBefore;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.is_completed = booleanToInteger(isCompleted);
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setCreatedBy(Long... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
    }

    public void setMilestoneId(Long... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
    }

    public GetPlansFilter withCreatedAfter(Long createdAfter) {
        this.created_after = createdAfter;
        return this;
    }

    public GetPlansFilter withCreatedBefore(Long createdBefore) {
        this.created_before = createdBefore;
        return this;
    }

    public GetPlansFilter withIsCompleted(Boolean isCompleted) {
        this.is_completed = booleanToInteger(isCompleted);
        return this;
    }

    public GetPlansFilter withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public GetPlansFilter withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public GetPlansFilter withCreatedBy(Long... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
        return this;
    }

    public GetPlansFilter withMilestoneId(Long... milestoneId) {
        this.milestone_id = toCommaSeparatedString(milestoneId);
        return this;
    }

}

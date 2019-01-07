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

import org.touchbit.testrail4j.core.query.GetResultsQueryMap;

/**
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetResultsFilter extends BaseQueryMap implements GetResultsQueryMap {

    /**
     * A comma-separated list of status IDs to filter by
     */
    private String status_id;

    /**
     * A comma-separated list of creators (user IDs) to filter by.
     */
    private String created_by;

    /**
     * Limit the test result
     */
    private Long limit;

    /**
     * Use to skip records
     */
    private Long offset;

    /**
     * Only return test results created after this date (as UNIX timestamp).
     */
    private Long created_after;

    /**
     * Only return test results created before this date (as UNIX timestamp).
     */
    private Long created_before;


    public String getCreatedBy() {
        return created_by;
    }

    public Long getCreatedAfter() {
        return created_after;
    }

    public Long getCreatedBefore() {
        return created_before;
    }

    public String getStatusId() {
        return status_id;
    }

    public Long getLimit() {
        return limit;
    }

    public Long getOffset() {
        return offset;
    }

    public void setCreatedAfter(Long createdAfter) {
        this.created_after = createdAfter;
    }

    public void setCreatedBefore(Long createdBefore) {
        this.created_before = createdBefore;
    }

    public void setCreatedBy(Long... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
    }

    public void setStatusId(Long... statusId) {
        this.status_id = toCommaSeparatedString(statusId);
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public GetResultsFilter withCreatedAfter(Long createdAfter) {
        this.created_after = createdAfter;
        return this;
    }

    public GetResultsFilter withCreatedBefore(Long createdBefore) {
        this.created_before = createdBefore;
        return this;
    }

    public GetResultsFilter withCreatedBy(Long... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
        return this;
    }

    public GetResultsFilter withStatusId(Long... statusId) {
        this.status_id = toCommaSeparatedString(statusId);
        return this;
    }

    public GetResultsFilter withLimit(Long limit) {
        this.limit = limit;
        return this;
    }

    public GetResultsFilter withOffset(Long offset) {
        this.offset = offset;
        return this;
    }

}

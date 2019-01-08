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
import org.touchbit.testrail4j.core.type.Type;

/**
 * Created by Oleg Shaburov on 11.11.2018
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetResultsFilter extends BaseFilter implements GetResultsQueryMap {

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
    private Number limit;

    /**
     * Use to skip records
     */
    private Number offset;

    /**
     * Only return test results created after this date (as UNIX timestamp).
     */
    private Number created_after;

    /**
     * Only return test results created before this date (as UNIX timestamp).
     */
    private Number created_before;


    public String getCreatedBy() {
        return created_by;
    }

    public Number getCreatedAfter() {
        return created_after;
    }

    public Number getCreatedBefore() {
        return created_before;
    }

    public String getStatusId() {
        return status_id;
    }

    public Number getLimit() {
        return limit;
    }

    public Number getOffset() {
        return offset;
    }

    public void setCreatedAfter(Number createdAfter) {
        this.created_after = createdAfter;
    }

    public void setCreatedBefore(Number createdBefore) {
        this.created_before = createdBefore;
    }

    public void setCreatedBy(Number... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
    }

    public void setStatusId(Number... statusId) {
        this.status_id = toCommaSeparatedString(statusId);
    }

    public void setStatusId(Type... statusId) {
        this.status_id = toCommaSeparatedString(statusId);
    }

    public void setLimit(Number limit) {
        this.limit = limit;
    }

    public void setOffset(Number offset) {
        this.offset = offset;
    }

    public GetResultsFilter withCreatedAfter(Number createdAfter) {
        this.created_after = createdAfter;
        return this;
    }

    public GetResultsFilter withCreatedBefore(Number createdBefore) {
        this.created_before = createdBefore;
        return this;
    }

    public GetResultsFilter withCreatedBy(Number... createdBy) {
        this.created_by = toCommaSeparatedString(createdBy);
        return this;
    }

    public GetResultsFilter withStatusId(Number... statusId) {
        this.status_id = toCommaSeparatedString(statusId);
        return this;
    }

    public GetResultsFilter withStatusId(Type... statuses) {
        this.status_id = toCommaSeparatedString(statuses);
        return this;
    }

    public GetResultsFilter withLimit(Number limit) {
        this.limit = limit;
        return this;
    }

    public GetResultsFilter withOffset(Number offset) {
        this.offset = offset;
        return this;
    }

}

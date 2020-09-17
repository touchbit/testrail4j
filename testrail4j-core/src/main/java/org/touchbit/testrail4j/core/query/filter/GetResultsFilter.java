/*
 * MIT License
 *
 * Copyright © 2020 TouchBIT.
 * Copyright © 2020 Oleg Shaburov.
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

import org.touchbit.testrail4j.core.query.GetResultsQueryMap;
import org.touchbit.testrail4j.core.type.Type;

/**
 * Get Results QueryMap Filter
 *
 * Snake_case in field names is used purposefully due to problems
 * when filling with URL parameters in some Feign versions.
 * <p>
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

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
public class GetResultsQueryMap {

    /**
     * A comma-separated list of status IDs to filter by
     */
    private Long status_id;

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

    /**
     * A comma-separated list of creators (user IDs) to filter by.
     */
    private String created_by;

    public Long getCreatedAfter() {
        return created_after;
    }

    public void setCreatedAfter(Long created_after) {
        this.created_after = created_after;
    }

    public Long getCreatedBefore() {
        return created_before;
    }

    public void setCreatedBefore(Long created_before) {
        this.created_before = created_before;
    }

    public String getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(String created_by) {
        this.created_by = created_by;
    }

    public Long getStatusId() {
        return status_id;
    }

    public void setStatusId(Long statusId) {
        this.status_id = statusId;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

}

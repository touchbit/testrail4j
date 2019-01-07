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

import org.touchbit.testrail4j.core.query.GetTestsQueryMap;

/**
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetTestsFilter extends BaseQueryMap implements GetTestsQueryMap {

    /**
     * A comma-separated list of status IDs to filter by.
     */
    private String status_id;

    /**
     * A comma-separated list of status IDs to filter by.
     */
    public String getStatusIds() {
        return status_id;
    }

    public void setStatusId(Long... statusIds) {
        this.status_id = toCommaSeparatedString(statusIds);
    }

    public GetTestsFilter withStatusId(Long... statusIds) {
        this.status_id = toCommaSeparatedString(statusIds);
        return this;
    }

}

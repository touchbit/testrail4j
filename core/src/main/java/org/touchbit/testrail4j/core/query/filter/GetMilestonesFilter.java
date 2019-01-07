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

import org.touchbit.testrail4j.core.query.GetMilestonesQueryMap;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetMilestonesFilter extends BaseFilter implements GetMilestonesQueryMap {

    /**
     * 1 to return completed milestones only. 0 to return open (active/upcoming) milestones only (available since TestRail 4.0).
     */
    private Number is_completed;

    /**
     * 1 to return started milestones only. 0 to return upcoming milestones only (available since TestRail 5.3).
     */
    private Number is_started;

    public Number getIsCompleted() {
        return is_completed;
    }

    public Number getIsStarted() {
        return is_started;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.is_completed = booleanToInteger(isCompleted);
    }

    public void setIsStarted(Boolean isStarted) {
        this.is_started = booleanToInteger(isStarted);
    }

    public GetMilestonesFilter withIsCompleted(Boolean isCompleted) {
        this.is_completed = booleanToInteger(isCompleted);
        return this;
    }

    public GetMilestonesFilter withIsStarted(Boolean isStarted) {
        this.is_started = booleanToInteger(isStarted);
        return this;
    }


}

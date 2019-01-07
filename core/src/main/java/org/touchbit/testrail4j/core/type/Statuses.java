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

package org.touchbit.testrail4j.core.type;

/**
 * The ID of the test status.
 * Untested - not allowed when adding a result
 *
 * <p>
 * Created by Oleg Shaburov on 02.01.2019
 * shaburov.o.a@gmail.com
 */
public enum Statuses implements Type {
    
    PASSED (1),
    BLOCKED (2),
    UNTESTED (3),
    RETEST (4),
    FAILED (5),
    CUSTOM_STATUS1 (6),
    CUSTOM_STATUS2 (7),
    CUSTOM_STATUS3 (8),
    CUSTOM_STATUS4 (9),
    CUSTOM_STATUS5 (10),
    CUSTOM_STATUS6 (11),
    CUSTOM_STATUS7 (12),
    ;

    long id;

    Statuses(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}

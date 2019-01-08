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
 * Default test case types
 * <p>
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
public enum CaseTypes implements Type {

    ACCEPTANCE (1),
    ACCESSIBILITY (2),
    AUTOMATED (3),
    COMPATIBILITY (4),
    DESTRUCTIVE (5),
    FUNCTIONAL (6),
    OTHER (7),
    PERFORMANCE (8),
    REGRESSION (9),
    SECURITY (10),
    SMOKE  (11),
    USABILITY (12),
    ;

    long id;

    CaseTypes(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
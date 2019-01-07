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
 * Default test case priorities
 * <p>
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
public enum Priorities implements Type {
    
    LOW (1),
    MEDIUM (2),
    HIGH (3),
    CRITICAL (4),
    ;

    long id;

    Priorities(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}

/*
 *  Copyright © 2018 Shaburov Oleg
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.touchbit.testrail4j.core.query.filter;

import org.touchbit.testrail4j.core.query.GetSectionsQueryMap;

/**
 * Created by Oleg Shaburov on 01.01.2019
 * shaburov.o.a@gmail.com
 */
@SuppressWarnings({"unused", "squid:S00116"})
public class GetSectionsFilter extends BaseQueryMap implements GetSectionsQueryMap {

    /**
     * The ID of the test suite (optional if the project is operating in single suite mode)
     */
    private Long suite_id;

    public Long getSuiteId() {
        return suite_id;
    }

    public void setSuiteId(Long suiteId) {
        this.suite_id = suiteId;
    }

    public GetSectionsFilter withSuiteId(Long suiteId) {
        this.suite_id = suiteId;
        return this;
    }

}

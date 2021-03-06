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

package org.touchbit.testrail4j.integration.tests.jackson2;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.Jackson2;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.integration.tests.BaseJackson2Test;
import org.touchbit.testrail4j.jackson2.model.TRUser;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(component = TestRail.class, service = Jackson2.class, interfaze = API.class, task = "users_operations")
public class UsersTests extends BaseJackson2Test {

    @Test(description = "Expecting successful receive of the existing user")
    @Details()
    public void test_20190106071009() {
        step("Get existing user");
        TRUser actUser = CLIENT.getUser(1L);
        assertThat(actUser).isNotNull();
        assertThat(actUser.getIsActive()).isTrue();
        assertThat(actUser.getAdditionalProperties()).isEmpty();
    }

    @Test(description = "Expecting successful receive of the existing user by email")
    @Details()
    public void test_20190106071959() {
        step("Get existing user");
        TRUser user = CLIENT.getUser(1L);
        step("Get existing user by email");
        TRUser actUser = CLIENT.getUserByEmail(user.getEmail());
        assertThat(actUser).isNotNull();
        assertThat(actUser.getIsActive()).isTrue();
    }

    @Test(description = "Expecting successful receive of the existing users list")
    @Details()
    public void test_20190106072059() {
        step("Get existing users list");
        List<TRUser> actUsers = CLIENT.getUsers();
        assertThat(actUsers).isNotEmpty();
    }

}

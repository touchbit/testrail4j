package org.touchbit.testrail4j.integration.tests;

import org.testng.annotations.Test;
import org.touchbit.buggy.core.model.Details;
import org.touchbit.buggy.core.model.Suite;
import org.touchbit.testrail4j.integration.goals.API;
import org.touchbit.testrail4j.integration.goals.TestRail;
import org.touchbit.testrail4j.jackson2.model.TRUser;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Oleg Shaburov on 06.01.2019
 * shaburov.o.a@gmail.com
 */
@Suite(service = TestRail.class, interfaze = API.class, task = "users_operations")
public class UsersTests extends BaseCorvusTest {

    @Test(description = "Expecting successful receive of the existing user")
    @Details()
    public void test_20190106071009() {
        step("Get existing user");
        TRUser actUser = CLIENT.getUser(1L);
        assertThat(actUser).isNotNull();
        assertThat(actUser.getIsActive()).isTrue();
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

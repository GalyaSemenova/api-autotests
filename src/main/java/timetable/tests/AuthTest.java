package timetable.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import timetable.api.Auth;
import timetable.api.ConfigValue;

public class AuthTest {
    Auth validAuth;

    @Before
    public void setup() {
        validAuth = new Auth("admin", "password");
    }

    @Test
    public void validTest() {
        int actual = validAuth.login().statusCode();
        Assert.assertEquals(ConfigValue.CODE_200, actual);
    }

}

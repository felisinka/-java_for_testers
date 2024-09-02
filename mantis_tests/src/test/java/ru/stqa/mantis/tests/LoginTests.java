package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase{
    @Test
    void canLogin(){
        app.http().login("administrator", "rtoo"); //root
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}

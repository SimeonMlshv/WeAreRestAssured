package com.weare.api.tests;

import com.example.selenium.weare.api.utils.SessionManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginTest extends BaseTestSetup {

    @Test
    public void loginTest() {
        String sessionCookie = SessionManager.getSessionCookie();
        assertNotNull(sessionCookie, "Session cookie should not be null.");
    }
}

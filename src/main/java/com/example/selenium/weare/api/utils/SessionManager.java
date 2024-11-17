package com.example.selenium.weare.api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SessionManager {
    private static String sessionCookie;

    // Method to get the session cookie, logging in only if not already logged in
    public static String getSessionCookie() {
        if (sessionCookie == null) {
            sessionCookie = performLogin();
        }
        return sessionCookie;
    }

    // Private method to perform login and retrieve the session cookie
    private static String performLogin() {
        String username = "testUser"; // Replace with actual credentials
        String password = "testPassword"; // Replace with actual credentials

        Response response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("username", username)
                .formParam("password", password)
                .post("/authenticate") // Replace with your login endpoint
                .then()
                .statusCode(302) // Ensure login was successful
                .extract()
                .response();

        String sessionId = response.getCookie("JSESSIONID");
        if (sessionId == null) {
            throw new RuntimeException("Login failed: No session cookie received.");
        }

        System.out.println("Logged in with session cookie: " + sessionId);
        return sessionId;
    }
}

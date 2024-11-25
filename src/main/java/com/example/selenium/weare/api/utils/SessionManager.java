package com.example.selenium.weare.api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SessionManager {
    public static String sessionCookie;

    public static String getSessionCookie() {
        if (sessionCookie == null) {
            sessionCookie = performLogin();
        }
        return sessionCookie;
    }


    private static String performLogin() {
        String username = "petkrastavici";
        String password = "test123456";

        Response response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("username", username)
                .formParam("password", password)
                .post("/authenticate")
                .then()
                .statusCode(302)
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

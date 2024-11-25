package com.weare.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostApiTests {

    private static String sessionCookie;
    private static String postId;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8081";

        Response loginResponse = given()
                .cookie("JSESSIONID", sessionCookie)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("username", "testUser")  // Replace with valid credentials
                .formParam("password", "testPassword") // Replace with valid credentials
                .post("/authenticate"); // Replace with your actual login endpoint

        sessionCookie = loginResponse.getCookie("JSESSIONID");
        if (sessionCookie == null) {
            throw new RuntimeException("Login failed! Session cookie is null.");
        }
        System.out.println("Logged in with session cookie: " + sessionCookie);
    }

    @Test
    public void findAllPostsTest() {
        Response response = given()
                .cookie("JSESSIONID", sessionCookie)
                .get("/api/post/");

        response.then().statusCode(200);

        postId = response.jsonPath().getString("postId[0]");

        assertNotNull(postId, "postId should not be null");
        System.out.println("Selected postId for commenting: " + postId);

        System.setProperty("postId", postId);
    }

    public static String getPostId() {
        return postId;
    }

    public static String getSessionCookie() {
        return sessionCookie;
    }
}

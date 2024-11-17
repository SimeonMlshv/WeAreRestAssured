package com.weare.api.tests;

import com.example.selenium.weare.api.utils.SessionManager;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostCreationTest extends BaseTestSetup {

    @Test
    public void createPostTest() {
        String sessionCookie = SessionManager.getSessionCookie();

        String postRequestBody = "{\n" +
                "  \"content\": \"Sample Post Content\",\n" +
                "  \"picture\": \"string\",\n" +
                "  \"public\": true\n" +
                "}";

        Response postResponse = given()
                .header("Content-Type", "application/json")
                .cookie("JSESSIONID", sessionCookie)
                .body(postRequestBody)
                .post("/api/post/auth/creator") // Replace with your endpoint
                .then()
                .extract()
                .response();

        assertEquals(201, postResponse.statusCode(), "Post creation should be successful");
        System.out.println("Post creation response: " + postResponse.getBody().asString());
    }
}

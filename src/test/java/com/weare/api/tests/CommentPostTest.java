package com.weare.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.Matchers.notNullValue;

public class CommentPostTest {

    @Test
    public void commentOnPostTest() {
        // Fetch the session cookie from PostApiTests
        String sessionCookie = PostApiTests.getSessionCookie();
        String postId = PostApiTests.getPostId();

        assertNotNull(postId, "No valid post found to comment on.");
        assertNotNull(sessionCookie, "Session cookie is missing.");

        String commentContent = "This is a test comment on post " + postId;
        String userId = "user123";

        String commentBody = "{\n" +
                "  \"content\": \"" + commentContent + "\",\n" +
                "  \"deletedConfirmed\": true,\n" +
                "  \"postId\": " + postId + ",\n" +
                "  \"userId\": \"" + userId + "\"\n" +
                "}";

        System.out.println("Generated Comment Body: " + commentBody);

        Response commentResponse = given()
                .cookie("JSESSIONID", sessionCookie)
                .contentType("application/json")
                .body(commentBody)
                .post("/api/comment/auth/creator");

        commentResponse.then().statusCode(200);

        commentResponse.then().body("commentId", notNullValue());
        commentResponse.then().body("content", notNullValue());
        commentResponse.then().body("likes", notNullValue());
        commentResponse.then().body("date", notNullValue());
        commentResponse.then().body("liked", notNullValue());

        String commentId = commentResponse.jsonPath().getString("commentId");
        System.out.println("Created comment with commentId: " + commentId);
    }
}

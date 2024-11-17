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
        String sessionCookie = PostApiTests.getSessionCookie(); // Use the session cookie from PostApiTests
        String postId = PostApiTests.getPostId(); // Use the postId from PostApiTests

        // Ensure postId and sessionCookie are available, else fail the test with an assertion
        assertNotNull(postId, "No valid post found to comment on.");
        assertNotNull(sessionCookie, "Session cookie is missing.");

        // Generate comment content and userId
        String commentContent = "This is a test comment on post " + postId;
        String userId = "user123"; // Set the user ID for the comment (can be dynamically generated if required)

        // Create the comment body (JSON payload)
        String commentBody = "{\n" +
                "  \"content\": \"" + commentContent + "\",\n" +  // Content of the comment
                "  \"deletedConfirmed\": true,\n" +             // Assuming 'deletedConfirmed' is needed for the API
                "  \"postId\": " + postId + ",\n" +  // The ID of the post you're commenting on
                "  \"userId\": \"" + userId + "\"\n" +  // The user ID of the commenter
                "}";

        // Print the generated body for debugging
        System.out.println("Generated Comment Body: " + commentBody);

        // Make the request to comment on the post
        Response commentResponse = given()
                .cookie("JSESSIONID", sessionCookie) // Pass the session cookie for authentication
                .contentType("application/json")
                .body(commentBody) // Pass the comment body in the request
                .post("/api/comment/auth/creator"); // Ensure this is the correct API endpoint

        // Validate the response status code
        commentResponse.then().statusCode(200);

        // Validate the response body to ensure it contains expected fields
        commentResponse.then().body("commentId", notNullValue());
        commentResponse.then().body("content", notNullValue());
        commentResponse.then().body("likes", notNullValue());
        commentResponse.then().body("date", notNullValue());
        commentResponse.then().body("liked", notNullValue());

        // Get and print the commentId from the response to verify the comment was created
        String commentId = commentResponse.jsonPath().getString("commentId");
        System.out.println("Created comment with commentId: " + commentId);
    }
}

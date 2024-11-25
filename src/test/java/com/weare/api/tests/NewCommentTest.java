package com.weare.api.tests;

import com.example.selenium.weare.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.example.selenium.weare.api.utils.SessionManager.getSessionCookie; // Use getSessionCookie here
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewCommentTest extends BaseTestSetup {

    @Test
    public void createCommentTest() {
        // Retrieve the postId from the system property set by CreatePostTest
        String postId = System.getProperty("testPost.postId");
        assertNotNull(postId, "Post ID should not be null. Make sure the post creation test ran successfully.");

        // Fetch userId from system properties (ensure this is set properly)
        String userId = System.getProperty("testUser.userId");
        assertNotNull(userId, "User ID should not be null. Make sure the registration test ran successfully.");

        // Fetch the session cookie (using getSessionCookie() to get the session)
        String session = getSessionCookie(); // Using getSessionCookie here
        assertNotNull(session, "Session cookie should not be null. Ensure the session is active.");

        // Generate a random comment
        String randomComment = TestDataGenerator.generateRandomString(30);

        // Build the comment payload
        String commentPayload = "{\n" +
                "  \"userId\": \"" + userId + "\",\n" +
                "  \"postId\": \"" + postId + "\",\n" +
                "  \"content\": \"" + randomComment + "\"\n" +
                "}";

        System.out.println("Sending comment payload: " + commentPayload);

        // Make the POST request to create the comment
        Response response = given()
                .cookie("JSESSIONID", session)  // Use the session cookie here
                .header("Content-Type", "application/json")
                .body(commentPayload)
                .post("/api/comment/auth/creator")  // Adjust the endpoint as per your API documentation
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Create Comment Response: " + response.asString());

        // Assert the response status code is 200
        assertEquals(200, statusCode, "Expected status code 200 but got " + statusCode);

        // Extract commentId from the response
        String commentId = response.jsonPath().getString("commentId");
        assertNotNull(commentId, "Comment ID should not be null. Check if the API returned the expected field.");
        System.out.println("Comment created successfully with ID: " + commentId);

        System.setProperty("testComment.commentId", commentId);
    }
}

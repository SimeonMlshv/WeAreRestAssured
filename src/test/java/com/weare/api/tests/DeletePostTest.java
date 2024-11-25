package com.weare.api.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.example.selenium.weare.api.utils.SessionManager.getSessionCookie;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class DeletePostTest extends BaseTestSetup {

    @Test
    public void deletePostTest() {
        // Reuse the postId from the created post
        String postId = System.getProperty("testPost.postId");
        assertNotNull(postId, "Post ID should not be null. Ensure the post creation test ran successfully.");

        // Fetch the session cookie
        String session = getSessionCookie();
        assertNotNull(session, "Session cookie should not be null. Ensure the session is active.");

        // Endpoint URL with the postId as a query parameter
        String endpoint = "/api/post/auth/manager?postId=" + postId;

        // Send the DELETE request to delete the post
        Response response = given()
                .cookie("JSESSIONID", session) // Use the session cookie
                .delete(endpoint)
                .then()
                .extract().response();

        // Log the response status and body
        int statusCode = response.getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Delete Post Response: " + response.asString());

        // Assert the response status code is 200
        assertEquals(200, statusCode, "Expected status code 200 but got " + statusCode);

        // Assert the response body is null or empty
        assertTrue(response.asString().isEmpty(), "Response body should be empty after deleting the post.");
    }
}

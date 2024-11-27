package com.weare.api.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.example.selenium.weare.api.utils.SessionManager.getSessionCookie;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class DeletePostTest extends BaseTestSetup {

    @Test
    public void deletePostTest() {

        String postId = System.getProperty("testPost.postId");
        assertNotNull(postId, "Post ID should not be null. Ensure the post creation test ran successfully.");

        String session = getSessionCookie();
        assertNotNull(session, "Session cookie should not be null. Ensure the session is active.");

        String endpoint = "/api/post/auth/manager?postId=" + postId;

        Response response = given()
                .cookie("JSESSIONID", session) // Use the session cookie
                .delete(endpoint)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Delete Post Response: " + response.asString());

        assertEquals(200, statusCode, "Expected status code 200 but got " + statusCode);

        assertTrue(response.asString().isEmpty(), "Response body should be empty after deleting the post.");
    }
}

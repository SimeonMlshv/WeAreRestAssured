package com.weare.api.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.example.selenium.weare.api.utils.SessionManager.getSessionCookie; // Use getSessionCookie here
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class LikeCommentTest extends BaseTestSetup {

    @Test
    public void likeCommentTest() {
        String commentId = System.getProperty("testComment.commentId");
        assertNotNull(commentId, "Comment ID should not be null. Make sure the comment creation test ran successfully.");

        String session = getSessionCookie();
        assertNotNull(session, "Session cookie should not be null. Ensure the session is active.");

        String endpoint = "/api/comment/auth/likesUp?commentId=" + commentId;


        Response response = given()
                .cookie("JSESSIONID", session) // Use the session cookie
                .header("Accept", "*/*")
                .post(endpoint)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Like Comment Response: " + response.asString());

        assertEquals(200, statusCode, "Expected status code 200 but got " + statusCode);

        assertNotNull(response.jsonPath().get("commentId"), "commentId field should exist in the response.");
        assertEquals(commentId, response.jsonPath().getString("commentId"), "commentId should match the liked comment ID.");
        assertNotNull(response.jsonPath().get("content"), "content field should exist in the response.");
        assertTrue(response.jsonPath().getList("likes").size() > 0, "likes array should have at least one entry.");
        assertNotNull(response.jsonPath().get("date"), "date field should exist in the response.");
        assertTrue(response.jsonPath().getBoolean("liked"), "liked field should be true.");
    }
}

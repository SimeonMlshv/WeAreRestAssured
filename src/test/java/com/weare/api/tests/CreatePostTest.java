package com.weare.api.tests;

import com.example.selenium.weare.api.utils.SessionManager;
import com.example.selenium.weare.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreatePostTest extends BaseTestSetup {

    @Test
    public void createPostTest() {

            String sessionCookie = SessionManager.getSessionCookie();
            assertNotNull(sessionCookie, "Session cookie should not be null.");

        String randomContent = TestDataGenerator.generateRandomString(50); // Generate a random string for content
        String randomPictureUrl = "https://aninu.de/cdn/shop/files/Pinguin_b456cb2f-332f-48b1-a507-436394f51f11.jpg?v=1722334856"
                + TestDataGenerator.generateRandomString(10) + ".jpg"; // Mock URL for picture

        String postPayload = "{\n" +
                "  \"content\": \"" + randomContent + "\",\n" +
                "  \"picture\": \"" + randomPictureUrl + "\",\n" +
                "  \"public\": \"true\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer YOUR_ACCESS_TOKEN")
                .body(postPayload)
                .post("/api/post/auth/creator")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Create Post Response: " + response.asString());

        if (statusCode == 200) {
            String postId = response.jsonPath().getString("postId");
            assertNotNull(postId, "Post ID should not be null");
            System.out.println("Post created with ID: " + postId);
        } else {
            System.out.println("Failed to create post. Status Code: " + statusCode);
            // Log the response for debugging purposes
            System.out.println("Response Body: " + response.asString());
        }
    }
}
package com.weare.api.tests;

import com.example.selenium.weare.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.example.selenium.weare.api.utils.SessionManager.sessionCookie;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreatePostTest extends BaseTestSetup {

    @Test
    public void createPostTest() {
        String randomContent = TestDataGenerator.generateRandomString(50);
        String randomPictureUrl = "https://aninu.de/cdn/shop/files/Pinguin_b456cb2f-332f-48b1-a507-436394f51f11.jpg?v=1722334856"
                + TestDataGenerator.generateRandomString(10) + ".jpg";

        String postPayload = "{\n" +
                "  \"content\": \"" + randomContent + "\",\n" +
                "  \"picture\": \"" + randomPictureUrl + "\",\n" +
                "  \"public\": \"true\"\n" +
                "}";

        Response response = given()
                .cookie("JSESSIONID", sessionCookie)
                .header("Content-Type", "application/json")
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

            System.setProperty("testPost.postId", postId);
        } else {
            System.out.println("Failed to create post. Status Code: " + statusCode);
            System.out.println("Response Body: " + response.asString());
        }
    }
}

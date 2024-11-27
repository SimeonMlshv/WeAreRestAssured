package com.weare.api.tests;

import com.example.selenium.weare.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;

public class RegistrationTest extends BaseTestSetup {

    @Test
    public void registrationTest() {
        String randomUsername = TestDataGenerator.generateUniqueUsername();
        String randomEmail = TestDataGenerator.generateRandomEmail();
        String randomPassword = TestDataGenerator.generateRandomPassword();

        String registrationPayload = "{\n" +
                "    \"username\": \"" + randomUsername + "\",\n" +
                "    \"email\": \"" + randomEmail + "\",\n" +
                "    \"password\": \"" + randomPassword + "\",\n" +
                "    \"confirmPassword\": \"" + randomPassword + "\",\n" +
                "    \"category\": { \"id\": 157 },\n" +
                "    \"authorities\": [\"ROLE_USER\"]\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(registrationPayload)
                .post("/api/users/")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Registration Response: " + response.asString());

        String responseMessage = response.asString();
        String userId = responseMessage.replaceAll(".*id (\\d+).*", "$1");
        assertNotNull(userId, "User ID should not be null");
        System.out.println("Generated User ID: " + userId);

        // Save credentials for future use
        System.setProperty("testUser.username", randomUsername);
        System.setProperty("testUser.password", randomPassword);
        System.setProperty("testUser.userId", userId);
        System.out.println("Saved credentials for user: " + randomUsername);
    }
}

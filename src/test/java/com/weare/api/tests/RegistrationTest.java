package com.weare.api.tests;

import com.example.selenium.weare.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

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
    }
}

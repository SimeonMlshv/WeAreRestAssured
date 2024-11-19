package com.weare.api.tests;

import com.example.selenium.weare.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class InvalidRegistrationTest extends BaseTestSetup {

    @Test
    public void registrationWithInvalidEmailTest() {
        String randomUsername = TestDataGenerator.generateUniqueUsername();
        String invalidEmail = "invalid-email-format"; // Некоректен формат на имейл
        String randomPassword = TestDataGenerator.generateRandomPassword();

        String registrationPayload = "{\n" +
                "    \"username\": \"" + randomUsername + "\",\n" +
                "    \"email\": \"" + invalidEmail + "\",\n" +
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
                .statusCode(400) // Очакваме грешка
                .extract().response();

        System.out.println("Invalid Email Response: " + response.asString());
    }

    @Test
    public void registrationWithMissingPasswordTest() {
        String randomUsername = TestDataGenerator.generateUniqueUsername();
        String randomEmail = TestDataGenerator.generateRandomEmail();
        String missingPassword = ""; // Липсва парола

        String registrationPayload = "{\n" +
                "    \"username\": \"" + randomUsername + "\",\n" +
                "    \"email\": \"" + randomEmail + "\",\n" +
                "    \"password\": \"" + missingPassword + "\",\n" +
                "    \"confirmPassword\": \"" + missingPassword + "\",\n" +
                "    \"category\": { \"id\": 157 },\n" +
                "    \"authorities\": [\"ROLE_USER\"]\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(registrationPayload)
                .post("/api/users/")
                .then()
                .statusCode(400) // Очакваме грешка
                .extract().response();

        System.out.println("Missing Password Response: " + response.asString());
    }

    @Test
    public void registrationWithShortPasswordTest() {
        String randomUsername = TestDataGenerator.generateUniqueUsername();
        String randomEmail = TestDataGenerator.generateRandomEmail();
        String shortPassword = "123"; // Твърде кратка парола

        String registrationPayload = "{\n" +
                "    \"username\": \"" + randomUsername + "\",\n" +
                "    \"email\": \"" + randomEmail + "\",\n" +
                "    \"password\": \"" + shortPassword + "\",\n" +
                "    \"confirmPassword\": \"" + shortPassword + "\",\n" +
                "    \"category\": { \"id\": 157 },\n" +
                "    \"authorities\": [\"ROLE_USER\"]\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(registrationPayload)
                .post("/api/users/")
                .then()
                .statusCode(400) // Очакваме грешка
                .extract().response();

        System.out.println("Short Password Response: " + response.asString());
    }
}

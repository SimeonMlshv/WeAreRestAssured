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

        //TODO
        //Get and save userId as variable (the response is: User with name Usernoyqh and id 64 was created)
        //userId is used as parameter in Commenting a Post

        //TODO
        //We need to save the newly created Username and Password, so we can use them to login.
        //We cannot hard-code this one as it will fail along the way


//        String userId = response.jsonPath().getString("id");
//        assertNotNull(userId,"User ID should not be null");
//        System.out.println("Generated User ID: " + userId);
    }
}

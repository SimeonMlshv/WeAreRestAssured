package com.example.selenium.weare.api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoint {

    private static final String BASE_URL = "https://localhost:8081";  // Replace with actual base URL

    // Method to register a user
    public static Response registerUser(String username, String password, String email) {
        String requestBody = "{\n" +
                "  \"username\": \"" + username + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"confirmPassword\": \"" + password + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"authorities\": [\"ROLE_USER\"]\n" +
                "}";

        return given()
                .contentType(ContentType.JSON)  // Specify content type as JSON
                .body(requestBody)              // Pass the request body
                .when()
                .post(BASE_URL + "/api/users/"); // Send POST request to the API
    }

    // Method to log in a user
    public static Response loginUser(String username, String password) {
        return given()
                .auth()
                .basic(username, password)     // Basic authentication with username and password
                .when()
                .post(BASE_URL + "/authenticate");  // Send POST request to authenticate
    }
}

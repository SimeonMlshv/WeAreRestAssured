package com.weare.api.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTestSetup {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8081"; // Replace with your base URI
    }
}

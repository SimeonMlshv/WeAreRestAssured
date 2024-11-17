package com.example.selenium.weare.api.utils;

import java.util.Random;

public class TestDataGenerator {

    private static final Random random = new Random();

    // Generate a unique username containing only alphabetic characters
    public static String generateUniqueUsername() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder username = new StringBuilder("User");
        for (int i = 0; i < 5; i++) {
            username.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return username.toString();
    }

    // Generate a random email
    public static String generateRandomEmail() {
        return "user" + random.nextInt(10000) + "@example.com";
    }

    // Generate a random password
    public static String generateRandomPassword() {
        String specialChars = "!@#$%^&*";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        StringBuilder password = new StringBuilder();
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        password.append(alphabet.charAt(random.nextInt(alphabet.length())));
        password.append(Character.toLowerCase(alphabet.charAt(random.nextInt(alphabet.length()))));
        password.append(numbers.charAt(random.nextInt(numbers.length())));

        // Add additional random characters to meet length requirements
        for (int i = 0; i < 4; i++) {
            password.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return password.toString();
    }
    public static String generateRandomCity() {
        String[] cities = {"New York", "London", "Paris", "Berlin", "Tokyo", "Sydney", "Rome", "Madrid", "Los Angeles"};
        return cities[random.nextInt(cities.length)];
    }

    // Generate a random user ID (this can be any number or string, depending on the requirements)
    public static String generateRandomUserId() {
        return String.valueOf(random.nextInt(10000)); // Generates a random user ID as a number
    }
}

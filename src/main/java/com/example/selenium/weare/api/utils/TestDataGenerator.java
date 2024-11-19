package com.example.selenium.weare.api.utils;

import java.util.Random;

public class TestDataGenerator {

    private static final Random random = new Random();

    public static String generateUniqueUsername() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder username = new StringBuilder("User");
        for (int i = 0; i < 5; i++) {
            username.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return username.toString();
    }

    public static String generateRandomEmail() {
        return "user" + random.nextInt(10000) + "@example.com";
    }

    public static String generateRandomPassword() {
        String specialChars = "!@#$%^&*";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        StringBuilder password = new StringBuilder();
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        password.append(alphabet.charAt(random.nextInt(alphabet.length())));
        password.append(Character.toLowerCase(alphabet.charAt(random.nextInt(alphabet.length()))));
        password.append(numbers.charAt(random.nextInt(numbers.length())));

        for (int i = 0; i < 4; i++) {
            password.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return password.toString();
    }
    public static String generateRandomCity() {
        String[] cities = {"New York", "London", "Paris", "Berlin", "Tokyo", "Sydney", "Rome", "Madrid", "Los Angeles"};
        return cities[random.nextInt(cities.length)];
    }

    public static String generateRandomUserId() {
        return String.valueOf(random.nextInt(10000));
    }
}

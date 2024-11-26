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

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

    public static String generateRandomAdjective() {
        String[] adjectives = {"Solid", "Fast", "Reliable", "Efficient", "Secure", "Scalable", "Flexible", "Powerful", "Dynamic"};
        return adjectives[random.nextInt(adjectives.length)];
    }
    public static int generateRandomCategoryId() {
        return 100 + random.nextInt(58); // 100 to 157 inclusive
    }

    public static String generateRandomCategoryName() {
        String[] categories = {"Technology", "Marketing", "Design", "Business", "Engineering", "Sales", "Support"};
        int index = random.nextInt(categories.length);
        return categories[index];
    }

    public static String generateRandomAbbreviation() {
        String[] abbreviations = {"SSL", "API", "URL", "CPU", "RAM", "JSON", "XML", "HTTP", "HTTPS"};
        return abbreviations[random.nextInt(abbreviations.length)];
    }

    public static String generateRandomNoun() {
        String[] nouns = {"Sensor", "Processor", "Module", "Framework", "Gateway", "Platform", "Protocol", "Database", "Network"};
        return nouns[random.nextInt(nouns.length)];
    }

    public static String generateRandomCountry() {
        String[] countries = {"United States", "Germany", "France", "India", "China", "Japan", "Brazil", "Australia", "Canada"};
        return countries[random.nextInt(countries.length)];
    }

    public static String generateRandomCurrencyName() {
        String[] currencies = {"USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "INR"};
        return currencies[random.nextInt(currencies.length)];
    }

    public static String generateRandomCompanyName() {
        String[] companies = {"Hermiston, O'Kon and Rohan", "Wilkinson Views", "Global Solutions", "Tech Innovators", "Bright Future"};
        return companies[random.nextInt(companies.length)];
    }

    public static String generateRandomStreetName() {
        String[] streets = {"Main Street", "Elm Avenue", "Park Lane", "Broadway", "Sunset Boulevard", "Highland Road", "Maple Drive"};
        return streets[random.nextInt(streets.length)];
    }

    public static int generateRandomAvailability() {
        // Array with specific availability values (4, 6, 8)
        int[] availabilityOptions = {4, 6, 8};

        // Get a random index and return the corresponding value
        int index = random.nextInt(availabilityOptions.length);
        return availabilityOptions[index];
    }

}

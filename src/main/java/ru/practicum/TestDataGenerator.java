package ru.practicum;

import java.util.concurrent.ThreadLocalRandom;

public class TestDataGenerator {
    public static String genName() {
        return "user_" + System.currentTimeMillis();
    }

    public static String genEmail() {
        return "email_" + System.currentTimeMillis() + "@example.com";
    }

    public static String genPass() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = ThreadLocalRandom.current().nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
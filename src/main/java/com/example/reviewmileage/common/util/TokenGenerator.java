package com.example.reviewmileage.common.util;
import java.util.UUID;

public class TokenGenerator {

    public static String randomCharacter(int length) {
        return UUID.randomUUID().toString().substring(length);
    }

    public static String randomCharacterWithPrefix(String prefix) {
        return prefix + randomCharacter(prefix.length());
    }
}
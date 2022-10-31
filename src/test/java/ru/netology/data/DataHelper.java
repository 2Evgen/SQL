package ru.netology.data;

import com.github.javafaker.Faker;

import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private final String login;
        private final String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAuthInfoWithInvalid() {
        Faker faker = new Faker();
        return new AuthInfo("vasya", "123456");
    }

    @Value
    public static class VerificationCode {
        String code;
    }
}

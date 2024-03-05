package com.example.englishapp.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Validation {
    public static boolean isLoginValid(Object value) {
        String login = (String) value;
        return Pattern.matches("(?i)[a-z0-9]{4,20}$", login);
    }

    public static boolean isPasswordValid(Object value) {
        String password = (String) value;
        return Pattern.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])\\S{4,30}$", password);
    }
}

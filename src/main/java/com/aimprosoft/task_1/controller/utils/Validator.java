package com.aimprosoft.task_1.controller.utils;

public final class Validator {

    private static final String STRING_REGEX = "^[a-zA-Zа-яА-Я]{3,15}$";
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+$";


    private Validator() {

    }

    public static boolean isValidName(String name) {
        return name.matches(STRING_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

}

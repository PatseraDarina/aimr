package com.aimprosoft.task_1.controller.utils;

public final class Validator {

    private static final String STRING_REGEX = "^[a-zA-Zа-яА-Я]{3,15}$";
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+$";
    private static final String DATE_REGEX = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
    private static final String DIGIT_REGEX = "^[0-9]{1,6}$";


    private Validator() {

    }

    public static boolean isValidName(String name) {
        return name.matches(STRING_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidDate(String date) {
        return date.matches(DATE_REGEX);
    }

    public static boolean isValidSalary(String salary) {
        return salary.matches(DIGIT_REGEX);
    }
}

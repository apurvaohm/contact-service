package com.contact.contactservice.util.validators;

import java.util.regex.Pattern;

public class AlphaNumericValidator {

    private static final String ALPHA_NUMERIC_PATTERN =  "^[a-zA-Z0-9]*$";

    private static boolean validate(String entry, String pattern) {
        return Pattern.matches(pattern, entry);
    }

    public static boolean validate(String stringToValidate) {
        if(stringToValidate.isEmpty())
            return true;
        if (validate(stringToValidate, ALPHA_NUMERIC_PATTERN))
            return true;
        return false;
    }
}

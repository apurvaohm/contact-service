package com.contact.contactservice.util.validators;

import java.util.regex.Pattern;

public class NameValidator {

    private static final String NAME_PATTERN =  "^[\\p{L} .'-]+$";

    private static boolean validate(String entry, String pattern) {
        return Pattern.matches(pattern, entry);
    }

    public static boolean validate(String stringToValidate) {
        if(stringToValidate.isEmpty())
            return true;
        if (validate(stringToValidate, NAME_PATTERN))
            return true;
        return false;
    }
}

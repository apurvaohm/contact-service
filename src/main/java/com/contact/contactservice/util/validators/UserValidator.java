package com.contact.contactservice.util.validators;

import com.contact.contactservice.core.PhoneNumber;
import com.contact.contactservice.core.User;

import java.util.Map;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String NAME_PATTERN = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}"; //will allow José Brasão

    private static boolean validate(String entry, String pattern) {
        return Pattern.matches(pattern, entry);
    }

    public static boolean validate(User user) {
        boolean valid = false;
        if (validate(user.getPersonDetails().getFirstName(), NAME_PATTERN) && validate(user.getPersonDetails().getLastName(), NAME_PATTERN)
                && DateValidator.validate(user.getPersonDetails().getDateOfBirth().toString()) && EmailValidator.validate(user.getEmail()) )
            valid = true;
        return valid;
    }

}

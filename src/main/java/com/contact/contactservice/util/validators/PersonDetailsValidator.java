package com.contact.contactservice.util.validators;

import com.contact.contactservice.core.PersonDetails;
import com.contact.contactservice.core.User;

import java.util.regex.Pattern;

public class PersonDetailsValidator {

    private static final String NAME_PATTERN = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}"; //will allow José Brasão

    private static boolean validate(String entry, String pattern) {
        return Pattern.matches(pattern, entry);
    }

    public static boolean validateFirstName(String stringToValidate) {
        if(stringToValidate.isEmpty())
            return true;
        if (validate(stringToValidate, NAME_PATTERN))
            return true;
        return false;
    }

    public static boolean validateLastName(String stringToValidate) {
        if(stringToValidate.isEmpty())
            return true;
        if (validate(stringToValidate, NAME_PATTERN))
            return true;
        return false;
    }

    public static boolean validateDob(String dobString) {
        if(dobString.isEmpty())
            return true;
        if (DateValidator.validate(dobString))
            return true;
        return false;
    }

    public static boolean validate(PersonDetails personDetails) {
        if(validateFirstName(personDetails.getFirstName())
                && validateLastName(personDetails.getLastName())
                && validateDob(personDetails.getDateOfBirth().toString()))
            return true;
        return false;
    }
}

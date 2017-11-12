package com.contact.contactservice.util.validators;

import com.contact.contactservice.core.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    // force starting with 3 digits follow by a “-” and 7 digits at the end.
    private static final String PHONENO_VALUE_PATTERN = "\\d{3}-\\d{7}";
    private static final String PHONENO_TYPE_PATTERN = "[a-zA-Z0-9]*";

    private static boolean validate(String entry, String pattern) {
        return Pattern.matches(pattern, entry);
    }

    public static boolean validatePhoneType(String phoneNumber) {
        boolean valid = false;
        if (validate(phoneNumber, PHONENO_TYPE_PATTERN))
            valid = true;
        return valid;
    }

    public static boolean validatePhoneValue(String phoneNumber) {
        boolean valid = false;
        if (validate(phoneNumber, PHONENO_VALUE_PATTERN))
            valid = true;
        return valid;
    }

    public static boolean validate(PhoneNumber phoneNumber) {
        boolean valid = false;
        if (validatePhoneType(phoneNumber.getType()) && validatePhoneValue(phoneNumber.getValue()) )
            valid = true;
        return valid;
    }

    public static boolean validate(List<PhoneNumber> phoneNumbers) {
        boolean valid = false;
        List<Boolean> validateBoooleans = new ArrayList<>();
        for(PhoneNumber phoneNumber: phoneNumbers){
            validateBoooleans.add(validate(phoneNumber));
        }
        if(!validateBoooleans.contains(false)) valid = true;
        return valid;
    }
}

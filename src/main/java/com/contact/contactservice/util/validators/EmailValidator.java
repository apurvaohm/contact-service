package com.contact.contactservice.util.validators;

import com.contact.contactservice.core.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_VALUE_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String EMAIL_TYPE_PATTERN = "[a-zA-Z0-9]*";


    private static boolean validate(String entry, String pattern) {
        return Pattern.matches(pattern, entry);
    }

    public static boolean validateEmailValue(String email) {
        if (validate(email, EMAIL_VALUE_PATTERN))
            return true;
        return false;
    }

    public static boolean validateEmailType(String email) {
        if (validate(email, EMAIL_TYPE_PATTERN))
            return true;
        return false;
    }

    public static boolean validate(Email email) {
        if (validateEmailValue(email.getValue()) && validateEmailType(email.getType()) )
            return true;
        return false;
    }

    public static boolean validate(List<Email> emails) {
        boolean valid = false;
        List<Boolean> validateBoooleans = new ArrayList<>();
        for(Email email: emails){
           validateBoooleans.add(validate(email));
        }
        if(!validateBoooleans.contains(false)) valid = true;
        return valid;
    }
}

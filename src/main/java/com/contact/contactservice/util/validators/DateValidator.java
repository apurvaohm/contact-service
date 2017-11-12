package com.contact.contactservice.util.validators;

import org.springframework.cglib.core.Local;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateValidator {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean validate(String dateString) {
        try {
            format.setLenient(false);
            if(dateString.isEmpty())
                return true;
            if (!dateString.matches("\\d{4}-[01]\\d-[0-3]\\d"))
                return false;

                format.parse(dateString);
                return true;
        }
        catch(ParseException e){
            return false;
        }
    }
}

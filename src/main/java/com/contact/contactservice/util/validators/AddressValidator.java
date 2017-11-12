package com.contact.contactservice.util.validators;

import com.contact.contactservice.core.Address;
import com.contact.contactservice.core.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressValidator {

    //"[A-Za-z0-9'\\.\\-\\s\\,]"
    //private static final String ADDRESS_LINE_PATTERN = "[\\w',-\\\\/.\\s]"; //words(characters,numbers) apostrophe,comma,period and hyphen, front and back slashes so \/ and finally whitespaces with \s.
    //private static final String ADDRESS_LINE_PATTERN = "(?:[A-Z][a-z0-9.-]+[ ]?)+";
    private static final String ADDRESS_LINE_PATTERN =  "^[a-zA-Z0-9]*$";
    private static final String STREET_PATTERN = "\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\\.?";
    private static final String CITY_PATTERN = "(?:[A-Z][a-z.-]+[ ]?)+";
    private static final String STATE_PATTERN = "AL|AK|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID|IL|IN|IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MT\n" +
            "|NE|NV|NH|NJ|NM|NY|NC|ND|MP|OH|OK|OR|PW|PA|PR|RI|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY";
    private static final String POSTAL_CODE_PATTERN = "\\b\\d{5}(?:-\\d{4})?\\b"; //numbers


    private static boolean validate(String entry, String pattern) {
        return Pattern.matches(pattern, entry);
    }

    public static boolean validateAddressLine(String addressLine) {
        if(addressLine.isEmpty())
            return true;
        if (validate(addressLine, ADDRESS_LINE_PATTERN))
            return true;
        return false;
    }

    public static boolean validateStreet(String addressLine) {
        if(addressLine.isEmpty())
            return true;
        if (validate(addressLine, STREET_PATTERN))
            return true;
        return false;
    }

    public static boolean validateCity(String addressLine) {
        if(addressLine.isEmpty())
            return true;
        if (validate(addressLine, CITY_PATTERN))
            return true;
        return false;
    }

    public static boolean validateState(String addressLine) {
        if(addressLine.isEmpty())
            return true;
        if (validate(addressLine, STATE_PATTERN))
            return true;
        return false;
    }

    public static boolean validatePostalCode(String addressLine) {
        if(addressLine.isEmpty())
            return true;
        if (validate(addressLine, POSTAL_CODE_PATTERN))
            return true;
        return false;
    }

    public static boolean validate(Address address) {
           /* if (validate(address.getApartment(), ADDRESS_LINE_PATTERN) || validate(address.getStreet(), STREET_PATTERN) || validate(address.getCity(), CITY_PATTERN)
                    || validate(address.getState(), STATE_PATTERN) ||  validate(address.getState(), POSTAL_CODE_PATTERN) ||validate(address.getCountry(), ADDRESS_LINE_PATTERN))
                valid = true;*/
        if (validateAddressLine(address.getApartment())
                && validateStreet(address.getStreet())
                && validateCity(address.getCity())
                && validateState(address.getState())
                && validatePostalCode(address.getPostalCode())
                && validateAddressLine(address.getCountry()))
           return true;
        return false;
    }

    public static boolean validate(List<Address> addresses) {
        boolean valid = false;
        List<Boolean> validateBoooleans = new ArrayList<>();
        for(Address address: addresses){
            validateBoooleans.add(validate(address));
        }
        if(!validateBoooleans.contains(false)) valid = true;
        return valid;
    }
}

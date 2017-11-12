package com.contact.contactservice.util.validators;

import com.contact.contactservice.core.Contact;

import java.util.Map;
import java.util.regex.Pattern;

public class ContactValidator {

    private static boolean validate(String entry, String pattern) {
        return Pattern.matches(pattern, entry);
    }

    public static boolean validate(Contact contact) {
/*      boolean pd = PersonDetailsValidator.validate(contact.getPersonDetails());
        boolean company = NameValidator.validate(contact.getCompany());
        boolean email = EmailValidator.validate(contact.getEmail());
        boolean ph = PhoneNumberValidator.validate(contact.getPhoneNumber());
        boolean add = AddressValidator.validate(contact.getAddress());*/

        if (PersonDetailsValidator.validate(contact.getPersonDetails())
                && NameValidator.validate(contact.getCompany())
                && EmailValidator.validate(contact.getEmail())
                && PhoneNumberValidator.validate(contact.getPhoneNumber())
                && AddressValidator.validate(contact.getAddress()))
            return true;
        return false;
    }
}

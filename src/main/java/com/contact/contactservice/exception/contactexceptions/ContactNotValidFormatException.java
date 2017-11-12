package com.contact.contactservice.exception.contactexceptions;

import com.contact.contactservice.exception.contactexceptions.ContactException;

public class ContactNotValidFormatException extends ContactException {

    public ContactNotValidFormatException() {
        super("Contact does not have a valid format.");
    }
}

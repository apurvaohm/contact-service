package com.contact.contactservice.exception.contactexceptions;

import com.contact.contactservice.exception.contactexceptions.ContactException;

public class ContactExistsException extends ContactException {
    public ContactExistsException() {
        super("Contact already exists in the database.");
    }
}

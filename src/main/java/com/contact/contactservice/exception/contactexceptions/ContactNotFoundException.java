package com.contact.contactservice.exception.contactexceptions;

import com.contact.contactservice.exception.contactexceptions.ContactException;

public class ContactNotFoundException extends ContactException {
    public ContactNotFoundException() {
        super("Contact not found.");
    }
}

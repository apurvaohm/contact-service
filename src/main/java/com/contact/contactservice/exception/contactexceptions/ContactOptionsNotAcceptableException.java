package com.contact.contactservice.exception.contactexceptions;

import com.contact.contactservice.exception.contactexceptions.ContactException;

public class ContactOptionsNotAcceptableException extends ContactException {
    public ContactOptionsNotAcceptableException() {
        super("Options sent are not acceptable.");
    }
}

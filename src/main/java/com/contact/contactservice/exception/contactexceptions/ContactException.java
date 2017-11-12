package com.contact.contactservice.exception.contactexceptions;

public abstract class ContactException extends Exception{

    public ContactException(String msg) {
        super(msg);
    }
}

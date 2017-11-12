package com.contact.contactservice.exception.userexceptions;

public class UserOptionsNotAcceptableException extends UserException {
    public UserOptionsNotAcceptableException() {
        super("Options sent are not acceptable.");
    }
}

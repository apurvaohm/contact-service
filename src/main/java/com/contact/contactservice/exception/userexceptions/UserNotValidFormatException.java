package com.contact.contactservice.exception.userexceptions;

public class UserNotValidFormatException extends UserException{
    public UserNotValidFormatException() {
        super("User does not have a valid format.");
    }
}

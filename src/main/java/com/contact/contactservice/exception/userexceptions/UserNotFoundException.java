package com.contact.contactservice.exception.userexceptions;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(){
        super("User not found.");
    }
}


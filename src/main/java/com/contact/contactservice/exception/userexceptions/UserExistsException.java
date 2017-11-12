package com.contact.contactservice.exception.userexceptions;

public class UserExistsException extends UserException {
    public UserExistsException(){
        super("User already exists in the database.");
    }
}

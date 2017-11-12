package com.contact.contactservice.exception.userexceptions;

public abstract class UserException extends Exception{
    public UserException(String msg){
        super(msg);
    }
}


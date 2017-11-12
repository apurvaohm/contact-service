package com.contact.contactservice.service.baseservice;

import com.contact.contactservice.service.contactservice.ContactService;
import com.contact.contactservice.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceProvider {

    @Autowired
    public ContactService contactService;

    @Autowired
    public UserService userService;
}

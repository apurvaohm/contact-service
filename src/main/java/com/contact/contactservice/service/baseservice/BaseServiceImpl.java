package com.contact.contactservice.service.baseservice;

import com.contact.contactservice.repository.ContactRepository;
import com.contact.contactservice.repository.UserRepository;
import com.contact.contactservice.util.ContactServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl {

    @Autowired
    public ContactServiceUtil contactServiceUtil;

    @Autowired
    public ContactRepository contactRepository;

    @Autowired
    public UserRepository userRepository;
}

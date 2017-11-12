package com.contact.contactservice.service.userservice;

import com.contact.contactservice.core.User;
import com.contact.contactservice.exception.userexceptions.UserException;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUserByEmail(String emailId) throws UserException;

    User addUser(User user) throws UserException;

    void removeUserByEmail(String email) throws UserException;

    List<User> getAllUser();

    }

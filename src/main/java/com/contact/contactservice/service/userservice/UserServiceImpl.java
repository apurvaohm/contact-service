package com.contact.contactservice.service.userservice;

import com.contact.contactservice.core.Contact;
import com.contact.contactservice.core.User;
import com.contact.contactservice.exception.contactexceptions.ContactException;
import com.contact.contactservice.exception.contactexceptions.ContactNotFoundException;
import com.contact.contactservice.exception.contactexceptions.ContactNotValidFormatException;
import com.contact.contactservice.exception.userexceptions.*;
import com.contact.contactservice.service.baseservice.BaseServiceImpl;
import com.contact.contactservice.util.validators.AlphaNumericValidator;
import com.contact.contactservice.util.validators.EmailValidator;
import com.contact.contactservice.util.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

@Component
public class UserServiceImpl extends BaseServiceImpl implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public User addUser(User user) throws UserException {
        String email = (String) user.getEmail().getValue();
        User eUser = userRepository.findByEmail(email);
        if(eUser != null)
            throw new UserExistsException();
        else{
            if (UserValidator.validate(user))
                return userRepository.save(user);
            else
                throw new UserNotValidFormatException();
        }
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        if(EmailValidator.validateEmailValue(email)){
            User userByemail = userRepository.findByEmail(email);
            if(userByemail != null)
                return userByemail;
            else
                throw new UserNotFoundException();
        }
        else
            throw new UserOptionsNotAcceptableException();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void removeUserByEmail(String email) throws UserException {
        if(EmailValidator.validateEmailValue(email)){
            User userByEmail = userRepository.findByEmail(email);
            if(userByEmail != null)
                userRepository.delete(userByEmail.getUserId());
            else
                throw new UserNotFoundException();
        }
        else
            throw new UserOptionsNotAcceptableException();
    }

}

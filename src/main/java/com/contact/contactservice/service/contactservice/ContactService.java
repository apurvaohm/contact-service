package com.contact.contactservice.service.contactservice;

import com.contact.contactservice.core.Contact;
import com.contact.contactservice.core.User;
import com.contact.contactservice.exception.contactexceptions.ContactException;
import com.contact.contactservice.exception.userexceptions.UserException;

import java.util.Map;

public interface ContactService {

    Contact getUserContact(String id) throws ContactException;

    Iterable<Contact> getUserContacts(User eUser, Map<String, String> options) throws ContactException, UserException;

    Contact addUserContact(User user, Contact contact) throws ContactException, UserException;

    Contact updateUserContact(User user, String contactId, Contact contact) throws ContactException, UserException;

    void removeUserContact(User user,String contactId) throws ContactException;

    public void removeAllUserContact(User user) throws ContactException;

    Iterable<Contact> getAllContactsByAddress(Map<String, String> options) throws ContactException;

}

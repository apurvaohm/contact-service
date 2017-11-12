package com.contact.contactservice.service.contactservice;

import com.contact.contactservice.core.Contact;
import com.contact.contactservice.core.User;
import com.contact.contactservice.exception.contactexceptions.ContactException;
import com.contact.contactservice.exception.contactexceptions.ContactNotFoundException;
import com.contact.contactservice.exception.contactexceptions.ContactNotValidFormatException;
import com.contact.contactservice.exception.contactexceptions.ContactOptionsNotAcceptableException;
import com.contact.contactservice.exception.userexceptions.UserException;
import com.contact.contactservice.service.baseservice.BaseServiceImpl;
import com.contact.contactservice.util.ContactServiceUtil;
import com.contact.contactservice.util.ContactServiceUtil.ValidRequestParamsForSearchUserContactByEmailPhone;
import com.contact.contactservice.util.ContactServiceUtil.ValidRequestParamsForSearchAllContactByAddress;
import com.contact.contactservice.util.validators.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

@Component
public class ContactServiceImpl extends BaseServiceImpl implements ContactService {

    private static final Integer DEFAULT_PAGE = 0;
    private static final Integer DEFAULT_SIZE = 10;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Contact getUserContact(String id) throws ContactException {
        return null;
    }

    @Override
    public Contact addUserContact(User user,Contact contact) throws ContactException{
        if (ContactValidator.validate(contact)) {
            contact.setUserId(user.getUserId());
            return contactRepository.save(contact);
        }
        else
            throw new ContactNotValidFormatException();
    }

    @Override
    public Contact updateUserContact(User user, String contactId, Contact contact) throws ContactException {
        if (ContactValidator.validate(contact) && AlphaNumericValidator.validate(contactId)) {
            Contact eContact = contactRepository.findByUserIdAndContactId(user.getUserId(), contactId);
            if(eContact != null){
                BeanUtils.copyProperties(contact,eContact, new String[] {"contactId", "userId"});
                return contactRepository.save(eContact);
            }else
                throw new ContactNotFoundException();
        }
        else
            throw new ContactNotValidFormatException();
    }

    @Override
    public void removeUserContact(User user,String contactId) throws ContactException {
        if (AlphaNumericValidator.validate(contactId)) {
            Contact eContact = contactRepository.findByUserIdAndContactId(user.getUserId(), contactId);
            if(eContact != null){
                contactRepository.delete(contactId);
            }else
                throw new ContactNotFoundException();
        }
        else
            throw new ContactNotValidFormatException();
    }

    @Override
    public void removeAllUserContact(User user) throws ContactException {
           List<Contact> eContact = contactRepository.findByUserId(user.getUserId());
            if(!eContact.isEmpty()){
                for (Contact contact: eContact) {
                    contactRepository.delete(contact.getContactId());
                }
            }else
                throw new ContactNotFoundException();
    }

    @Override
    public Iterable<Contact> getUserContacts(User eUser, Map<String, String> options) throws ContactException {
        // check if request param contains valid values: email, phone  - allowed options
        if (!contactServiceUtil.isRequestParamsValid(options, ValidRequestParamsForSearchUserContactByEmailPhone.class))
            throw new ContactOptionsNotAcceptableException();

        //email & phone both are present in options
        if (options.containsKey("email") && options.containsKey("phone")){
            if(EmailValidator.validateEmailValue(options.get("email")) && PhoneNumberValidator.validatePhoneValue(options.get("phone")))
                return contactRepository.findByUserIdAndEmailAndPhoneNumber(eUser.getUserId(), options.get("email"), options.get("phone"));
            else
                throw new ContactOptionsNotAcceptableException();
        }
        //email is present in options
        else if (options.containsKey("email")){
            if(EmailValidator.validateEmailValue(options.get("email")))
                return contactRepository.findByUserIdAndEmail(eUser.getUserId(), options.get("email"));
            else
                throw new ContactOptionsNotAcceptableException();
        }
        //phone is present in options
        else if (options.containsKey("phone")){
            if(PhoneNumberValidator.validatePhoneValue(options.get("phone")))
                return contactRepository.findByUserIdAndPhoneNumber(eUser.getUserId(), options.get("phone"));
            else
                throw new ContactOptionsNotAcceptableException();
        }
        //if neither phone nor email is present in options, then return ALL contants belonging to the user
        else{
            return contactRepository.findByUserId(eUser.getUserId());
        }
    }

    @Override
    public Iterable<Contact> getAllContactsByAddress(Map<String, String> options) throws ContactException {
        //assign default page and size values for pagination, if not included in options
        int page = options.containsKey("page") ? Integer.parseInt(options.get("page")) : DEFAULT_PAGE;
        int size = options.containsKey("size") ? Integer.parseInt(options.get("size")) : DEFAULT_SIZE;

        // check if request param contains valid values: email, phone  - allowed options
        if (!contactServiceUtil.isRequestParamsValid(options, ValidRequestParamsForSearchAllContactByAddress.class))
            throw new ContactOptionsNotAcceptableException();

        //state & city both are present in options
        if (options.containsKey("state") && options.containsKey("city")){
            if(AddressValidator.validateState(options.get("state")) && AddressValidator.validateCity(options.get("city")))
                return contactRepository.findByStateAndCity(options.get("state"), options.get("city"), new PageRequest(page, size));
            else
                throw new ContactOptionsNotAcceptableException();
        }
        //state is present in options
        else if (options.containsKey("state")){
            if(AddressValidator.validateState(options.get("state")))
                return contactRepository.findByState(options.get("state"), new PageRequest(page, size));
            else
                throw new ContactOptionsNotAcceptableException();
        }
        //city is present in options
        else if (options.containsKey("city")){
            if(AddressValidator.validateCity(options.get("city")))
                return contactRepository.findByCity(options.get("city"), new PageRequest(page, size));
            else
                throw new ContactOptionsNotAcceptableException();
        }
        else
            throw new ContactOptionsNotAcceptableException();

    }
}

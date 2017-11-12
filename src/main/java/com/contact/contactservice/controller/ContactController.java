package com.contact.contactservice.controller;

import com.contact.contactservice.core.Contact;
import com.contact.contactservice.core.User;
import com.contact.contactservice.exception.contactexceptions.*;
import com.contact.contactservice.exception.userexceptions.UserException;
import com.contact.contactservice.exception.userexceptions.UserNotFoundException;
import com.contact.contactservice.exception.userexceptions.UserNotValidFormatException;
import com.contact.contactservice.service.baseservice.BaseServiceProvider;
import com.contact.contactservice.util.validators.ContactValidator;
import com.contact.contactservice.util.validators.EmailValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class ContactController extends BaseServiceProvider {

    @PostMapping("user/{email}/contact")
    public ResponseEntity<?> addContact(@PathVariable("email") String email,@RequestBody Contact contact) {
        try {
            User eUser = userService.getUserByEmail(email);
            if(eUser != null)
                return ResponseEntity.ok(contactService.addUserContact(eUser,contact));
            else
                throw new UserNotFoundException();
        } catch (ContactExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (ContactNotValidFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (ContactException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UserNotValidFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("user/{email}/contact/{contactId}")
    public ResponseEntity<?> updateContact(@PathVariable("email") String email, @PathVariable("contactId") String contactId,@RequestBody Contact contact) {
        try {
            User eUser = userService.getUserByEmail(email);
            if(eUser != null)
                return ResponseEntity.ok(contactService.updateUserContact(eUser,contactId,contact));
            else
                throw new UserNotFoundException();
        } catch (ContactNotValidFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (ContactNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ContactException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UserNotValidFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("user/{email}/contact/{contactId}")
    public ResponseEntity<?> deleteContact(@PathVariable("email") String email, @PathVariable("contactId") String contactId) {
        try {
            User eUser = userService.getUserByEmail(email);
            if(eUser != null) {
                contactService.removeUserContact(eUser, contactId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            else
                throw new UserNotFoundException();
        } catch (ContactNotValidFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (ContactNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ContactException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UserNotValidFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("user/{email}/contact")
    public ResponseEntity<?> deleteContacts(@PathVariable("email") String email) {
        try {
            User eUser = userService.getUserByEmail(email);
            if(eUser != null) {
                contactService.removeAllUserContact(eUser);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            else
                throw new UserNotFoundException();
        } catch (ContactNotValidFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (ContactNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ContactException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UserNotValidFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("user/{email}/contact")
    public ResponseEntity<?> getContacts(@PathVariable("email") String email, @RequestParam(required = false) Map<String, String> options) {
        try {
            User eUser = userService.getUserByEmail(email);
            if(eUser != null)
                return ResponseEntity.ok(contactService.getUserContacts(eUser,options));
            else
                throw new UserNotFoundException();
        } catch (ContactOptionsNotAcceptableException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (ContactException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UserNotValidFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("contact/address")
    public ResponseEntity<?> getContactsByStateAndCity(@RequestParam(required = false) Map<String, String> options) {
        try {
                return ResponseEntity.ok(contactService.getAllContactsByAddress(options));
        } catch (ContactOptionsNotAcceptableException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (ContactException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

package com.contact.contactservice.controller;

import com.contact.contactservice.core.Contact;
import com.contact.contactservice.core.User;
import com.contact.contactservice.exception.contactexceptions.ContactException;
import com.contact.contactservice.exception.contactexceptions.ContactExistsException;
import com.contact.contactservice.exception.contactexceptions.ContactNotValidFormatException;
import com.contact.contactservice.exception.userexceptions.*;
import com.contact.contactservice.service.baseservice.BaseServiceProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class UserController extends BaseServiceProvider {

    @PostMapping("user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.addUser(user));
        } catch (UserExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (UserNotValidFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("user/{emailId}/")
    public ResponseEntity<?> getUser(@PathVariable("emailId") String emailId) {
        try {
            String email = emailId.toString();
            return ResponseEntity.ok(userService.getUserByEmail(email));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("user")
    public ResponseEntity<?> getUsers() {
            return ResponseEntity.ok(userService.getAllUser());
    }

    @DeleteMapping("user/{emailId}/")
    public ResponseEntity<?> deleteUser(@PathVariable("emailId") String emailId) {
        try {
            //remove all user contacts before removing user
            User eUser = userService.getUserByEmail(emailId);
            contactService.removeAllUserContact(eUser);
            userService.removeUserByEmail(emailId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (ContactException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

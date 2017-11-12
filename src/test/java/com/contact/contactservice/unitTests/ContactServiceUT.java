/*
package com.contact.contactservice;

import com.contact.contactservice.core.Email;
import com.contact.contactservice.core.PersonDetails;
import com.contact.contactservice.core.User;
import com.contact.contactservice.exception.userexceptions.UserException;
import com.contact.contactservice.service.baseservice.BaseServiceProvider;
import com.contact.contactservice.service.contactservice.ContactService;
import com.contact.contactservice.service.userservice.UserService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMongoConfiguration.class})
public class ContactServiceUT extends BaseServiceProvider{

    @Test
    public void happyTest() throws UserException {
       User user = new User();
       user.setPersonDetails(new PersonDetails("George", "Tucker", "1983-04-06"));
       user.setEmail(new Email("primary", "GeorgeTucker@gmail.com"));
       userService.addUser(user);
       User eUser = userService.getUserByEmail("GeorgeTucker@gmail.com");
       System.out.println("User with email found: " + eUser.getEmail());
       assertEquals(user, eUser);
    }
}
*/

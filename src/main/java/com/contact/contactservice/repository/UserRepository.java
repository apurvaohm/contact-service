package com.contact.contactservice.repository;

import com.contact.contactservice.core.Contact;
import com.contact.contactservice.core.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>, PagingAndSortingRepository<User, String> {

    @Query("{'email.value':?0}")
    User findByEmail(String email);

    @Query("{'personDetails.firstName':?0}")
    User findByFirstName(String fname);

}

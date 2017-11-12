package com.contact.contactservice.repository;

import com.contact.contactservice.core.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact, String>, PagingAndSortingRepository<Contact, String> {

    @Query("{'userId':?0 }")
    List<Contact> findByUserId(String userId);

    @Query("{'userId':?0, 'contactId':?1 }")
    Contact findByUserIdAndContactId(String userId, String contactId);

    @Query("{'userId':?0, 'email.value':?1, 'phoneNumber.value':?2 }")
    List<Contact> findByUserIdAndEmailAndPhoneNumber(String userId, String email, String phoneNumber);

    @Query("{'userId':?0, 'email.value':?1 }")
    List<Contact> findByUserIdAndEmail(String userId, String email);

    @Query("{'userId':?0, 'phoneNumber.value':?1 }")
    List<Contact> findByUserIdAndPhoneNumber(String userId, String phoneNumber);

    @Query("{'address.city':?0, 'address.state':?1 }")
    List<Contact> findByStateAndCity(String city, String state, Pageable pageable);

    @Query("{'address.city':?0 }")
    List<Contact> findByCity(String city, Pageable pageable);

    @Query("{'address.state':?0 }")
    List<Contact> findByState(String state, Pageable pageable);

}

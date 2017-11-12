package com.contact.contactservice.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "contacts")
public class Contact {
    @Id
    private String contactId;

    private String userId;

    private PersonDetails personDetails;

    private String company;

    private byte[] profileImage;

    private List<Email> email;

    private List<PhoneNumber> phoneNumber;

    private List<Address> address;
}

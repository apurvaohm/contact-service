package com.contact.contactservice.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDetails {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}

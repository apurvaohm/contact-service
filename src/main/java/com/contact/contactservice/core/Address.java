package com.contact.contactservice.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String apartment;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}

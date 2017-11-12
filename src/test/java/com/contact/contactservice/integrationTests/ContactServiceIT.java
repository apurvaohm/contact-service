package com.contact.contactservice.integrationTests;

import com.contact.contactservice.core.Contact;
import com.contact.contactservice.core.User;
import com.contact.contactservice.integrationTests.AbstractIT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ContactServiceIT extends AbstractIT {


    @Test
    public void postMonitorWithMonitorableIdFilter() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        //Create user
        // Endpoint - http://localhost:8088/api/v1/user
        String userString = "{ \n" +
                "    \"personDetails\" : {\n" +
                "        \"firstName\" : \"Zoe\", \n" +
                "        \"lastName\" : \"Hart\", \n" +
                "        \"dateOfBirth\" : \"1985-02-06\"\n" +
                "    }, \n" +
                "    \"email\" : {\n" +
                "        \"type\" : \"primary\", \n" +
                "        \"value\" : \"ZoeHart@gmail.com\"\n" +
                "    }\n" +
                "}";
        User newUser = mapper.readValue(userString, User.class);
        given()
                .contentType("application/json")
                .body(newUser)
                .when().post("/api/v1/user").then()
                .statusCode(200);


        //Create contact
        //Endpoint - http://localhost:8088/api/v1/user/johnDoe@gmail.com/contact
        String contactString = "{\n" +
                "  \"personDetails\": {\n" +
                "    \"firstName\": \"Lemon\",\n" +
                "    \"lastName\": \"Breeland\",\n" +
                "\t\"dateOfBirth\": \"1985-04-06\"\n" +
                "\t},\n" +
                "  \"company\": \"\", \n" +
                "  \"profileImage\": \"\", \n" +
                "  \"email\": [\n" +
                "      {\n" +
                "\t  \"type\": \"primary\",\n" +
                "      \"value\": \"contact7@gmail.com\"\n" +
                "      },\n" +
                "      {\n" +
                "      \"type\": \"primary\",\n" +
                "      \"value\": \"contact8@gmail.com\"\n" +
                "      }\n" +
                "    ],\t \n" +
                "  \"phoneNumber\": [\n" +
                "      {\n" +
                "        \"type\": \"Home\",\n" +
                "        \"value\": \"881-5551315\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"type\": \"Work\",\n" +
                "        \"value\": \"377-1221217\"\n" +
                "      }\n" +
                "    ],\n" +
                "  \"address\": [\n" +
                "      {\n" +
                "        \"apartment\": \"2614\",\n" +
                "        \"street\": \"2945 N Sheridan Road\",\n" +
                "\t\t\"city\": \"New York\",\n" +
                "        \"state\": \"NY\",\n" +
                "\t\t\"postalCode\": \"60657\",\n" +
                "        \"country\": \"USA\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"apartment\": \"2213\",\n" +
                "        \"street\": \"W Monroe Street\",\n" +
                "\t\t\"city\": \"New York\",\n" +
                "        \"state\": \"NY\",\n" +
                "\t\t\"postalCode\": \"60628\",\n" +
                "        \"country\": \"USA\"\n" +
                "      }\n" +
                "    ]\t\n" +
                "}";

        Contact contact = mapper.readValue(contactString, Contact.class);
        given()
                .contentType("application/json")
                .body(contact)
                .when().post("/api/v1/user/ZoeHart@gmail.com/contact").then()
                .statusCode(200);


        //delete all user contacts
        //Endpoint - http://localhost:8088/api/v1/user/ZoeHart@gmail.com/contact
        given()
                .when().delete("user/ZoeHart@gmail.com/contact")
                .then().statusCode(204);

        //delete user
        //Endpoint - http://localhost:8088/api/v1/user/ZoeHart@gmail.com/
        given()
                .when().delete("user/ZoeHart@gmail.com/")
                .then().statusCode(204);


    }
}

package com.contact.contactservice.integrationTests;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class AbstractIT {

    @BeforeClass
    public static void configureRestAssured() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = Integer.getInteger("http.port", 8088);
    }

    @AfterClass
    public static void unconfigureRestAssured() {
        RestAssured.reset();
    }
}

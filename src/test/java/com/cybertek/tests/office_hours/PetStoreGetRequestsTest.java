package com.cybertek.tests.office_hours;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PetStoreGetRequestsTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "https://petstore.swagger.io/v2";
    }

    /**
     * accept type is json
     * get request to /store/inventory
     * Then status code is 200
     * And content type is json
     * and available is 573
     */

    @Test
    public void getInventoryTest() {

    }

}

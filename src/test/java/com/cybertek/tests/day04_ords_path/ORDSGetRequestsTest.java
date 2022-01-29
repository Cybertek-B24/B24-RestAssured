package com.cybertek.tests.day04_ords_path;


import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSGetRequestsTest {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("ords.url"); //http://54.205.239.177:1000/ords/hr
    }

    /**
     * Given accept type is json
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */

    @DisplayName("GET Request to /ords/hr/regions")
    @Test
    public void getAllRegionsTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/regions");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200 , response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();

        assertTrue(response.asString().contains("Europe"));

    }

    /**
     * Given accept type is json
    And Path param value is 1
    When user send get request to /ords/hr/regions/{region_id}
    Status code should be 200
    Content type should be "application/json"
    And body should contain "Europe"
    */
    @DisplayName("GET Request to /ords/hr/regions/1")
    @Test
    public void getSingleRegionsWithParam() {
        //BREAK TILL 12:18 PM EST
    }

}

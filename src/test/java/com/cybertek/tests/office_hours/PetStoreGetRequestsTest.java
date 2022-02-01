package com.cybertek.tests.office_hours;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PetStoreGetRequestsTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "https://petstore.swagger.io/v2";
    }

    /**
     * accept type is json
     * get request to https://petstore.swagger.io/v2/store/inventory
     * Then status code is 200
     * And content type is json
     * And date header should be present
     * and available is more than 500
     */

    @Test
    public void getInventoryTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/store/inventory");

        System.out.println("Status code = " + response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        System.out.println("content type = " + response.contentType());
        System.out.println("date = " + response.getHeader("Date"));

        assertEquals("application/json", response.contentType());
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));

        //path, jsonPath
        JsonPath json = response.jsonPath();

        //print available count
        System.out.println("available = " + json.getInt("available"));

        //available is more than 500
        assertEquals(Boolean.TRUE, json.getInt("available") >= 500);
        assertTrue(json.getInt("available") >= 500);

        response.prettyPrint();

    }

}

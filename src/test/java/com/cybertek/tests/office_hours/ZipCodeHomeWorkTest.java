package com.cybertek.tests.office_hours;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipCodeHomeWorkTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://api.zippopotam.us";
    }

    /**
     Given Accept application/json
     And path zipcode is 22031
     When I send a GET request to /us endpoint
     Then status code must be 200
     And content type must be application/json
     And Server header is cloudflare
     And Report-To header exists
     And body should contains following information
     post code is 22031
     country  is United States
     country abbreviation is US
     place name is Fairfax
     state is Virginia
     latitude is 38.8604
     */

    @Test
    public void zipcodeTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("postal-code" , 22031)
                .when().get("/us/{postal-code}");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200 , response.statusCode());

        assertEquals("application/json" , response.contentType());

        System.out.println("server = " + response.getHeader("Server"));
        assertEquals("cloudflare" , response.getHeader("Server"));

        System.out.println("report to exists? = " + response.getHeaders().hasHeaderWithName("Report-To"));
        assertTrue(response.getHeaders().hasHeaderWithName("Report-To"));

        //Json body verifications
        JsonPath json = response.jsonPath();

        //post code is 22031
        System.out.println("post code = " + json.getString("\"post code\""));
        assertEquals(22031 , json.getInt("\"post code\""));
        assertEquals("22031" , json.getString("\"post code\""));

        //country  is United States
        System.out.println("country = " + json.getString("country"));
        assertEquals("United States", json.getString("country"));

        //country abbreviation is US
        System.out.println("country abbreviation = " + json.getString("'country abbreviation'")); //"\"country abbreviation\""
        assertEquals("US" , json.getString("'country abbreviation'"));

        // place name is Fairfax
        System.out.println("place name = " + json.getString("places[0].'place name'"));
        assertEquals("Fairfax" , json.getString("places[0].'place name'"));

        //state is Virginia
        System.out.println("state = " + json.getString("places[0].state"));
        assertEquals("Virginia" , json.getString("places[0].state"));

        //latitude is 38.8604
        System.out.println("latitude = " + json.getString("places[0].latitude"));
        assertEquals("38.8604" , json.getString("places[0].latitude"));
    }

}











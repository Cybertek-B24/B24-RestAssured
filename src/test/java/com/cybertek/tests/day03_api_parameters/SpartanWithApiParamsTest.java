package com.cybertek.tests.day03_api_parameters;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithApiParamsTest {

    @BeforeAll //RUN ONCE BEFORE ALL TESTS
    public static void setUp() {
        System.out.println("Set up method: assigning value to baseURI variable");
        baseURI = ConfigurationReader.getProperty("spartan.url");
    }

    /**
     Given Accept type is json
     And path parameter id is 24
     When I send request to /api/spartans/24
     ----
     Then status code should be 200
     And content type should be "application/json"
     and json body should contain "Correy"
     */

    @Test
    public void getSpartanPathParamTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/24");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
        assertTrue(response.asString().contains("Correy"));

    }

}

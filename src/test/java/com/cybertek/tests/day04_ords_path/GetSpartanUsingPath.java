package com.cybertek.tests.day04_ords_path;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class GetSpartanUsingPath extends SpartanTestBase {
    /**
     * Given accept is json
     * And path param id is 13
     * When I send get request to /api/spartans/{id}
     * ----------
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */
    @Test
    public void readJsonWithPathTest() {
        //REQUEST
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13)
                .when().get("/api/spartans/{id}");

        //RESPONSE validations
        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));

    }
}

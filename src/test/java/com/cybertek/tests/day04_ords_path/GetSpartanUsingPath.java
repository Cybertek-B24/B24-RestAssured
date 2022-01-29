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

        /**
         {
         "id": 13,
         "name": "Jaimie",
         "gender": "Female",
         "phone": 7842554879
         }
         */
        //PRINT values from JSON
        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = " + response.path("gender"));
        System.out.println("phone = " + response.path("phone"));

        //store values from JSON to variables
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phoneNum = response.path("phone");

        System.out.println("PRINTING VARIABLES");
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phoneNum = " + phoneNum);

        //assertions
        assertEquals(13 , id);
        assertEquals("Jaimie" , name);
        assertEquals("Female" , response.path("gender"));//assertEquals("Female" , gender)
        assertEquals(7842554879L , phoneNum);
        assertEquals("7842554879", response.path("phone").toString());
    }

    /**
     Given accept is json
     When I send get request to /api/spartans
     Then status code is 200
     And content type is json
     And I can navigate json array object
     */

    @Test
    public void readJsonArrayTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        System.out.println("id = " + response.path("id")); //all IDs
        System.out.println("id of first spartan = " + response.path("[0].id"));

        System.out.println("name of second person = " + response.path("[1].name"));



    }

}

package com.cybertek.tests.day01_intro_to_api;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReqResGetUsersTest {

    /**
     * Send get request to API Endpoint:
     * 	"https://reqres.in/api/users"
     * Response status code should be 200
     * Response body should contain user info "George"
     */

    @Test
    public void getAllUsersApiTest() {
      Response response = RestAssured.get(ConfigurationReader.getProperty("reqres.users.api.url"));

        System.out.println("Status code = " + response.statusCode());
        //Response status code should be 200
        Assertions.assertEquals(200, response.statusCode());

        //different ways to print response body
        response.prettyPrint();
        System.out.println("Response body = " + response.body().asString());
        System.out.println(response.asString());

        Assertions.assertTrue(response.asString().contains("George"));
    }

    /**
     * Send get request to API Endpoint:
     * 	"https://reqres.in/api/users/5"
     * Response status code should be 200
     * Response body should contain user info "Charles"
     */
    
    @Test
    public void getSingleUserApiTest() {
        Response response = RestAssured.get(ConfigurationReader.getProperty("reqres.users.api.url")+"/5");

        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        Assertions.assertEquals(200, statusCode);

        response.prettyPrint();
        Assertions.assertTrue(response.asString().contains("Charles"));
    }

    /**
     * Send get request to API Endpoint:
     * 	"https://reqres.in/api/users/50"
     * Response status code should be 404
     * Response body should contain "{}"
     */

    @Test
    public void getSingleUserNegativeApiTest() {
        Response response = RestAssured.get(ConfigurationReader.getProperty("reqres.users.api.url")+"/50");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(404, response.statusCode());

        response.prettyPrint();
        Assertions.assertTrue(response.asString().contains("{\n    \n}"));

    }

}

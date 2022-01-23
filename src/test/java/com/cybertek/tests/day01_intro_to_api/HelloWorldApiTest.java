package com.cybertek.tests.day01_intro_to_api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloWorldApiTest {
    String url = "https://sandbox.api.service.nhs.uk/hello-world/hello/world";

    @Test
    public void helloWorldGetRequestTest(){
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        //print response status code
        System.out.println(response.statusCode());
        Assertions.assertEquals(200, response.statusCode());
        //print response body
        response.prettyPrint();

    }

}

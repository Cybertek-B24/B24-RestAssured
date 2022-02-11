package com.cybertek.tests.day12_api_authentication;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryApiAccessTokenTest {

    /**
     Given accept is json
     and form parameters email and password
     with values "student30@library", "Sdet2022*"
     When i send post request to https://library2.cybertekschool.com/rest/v1/login
     Then status code 200
     And I can extract the access token
     */

    @Test
    public void getLibraryTokenTest() {
        String token = given().accept(ContentType.JSON)
                .and().formParams("email" , "student30@library",
                                 "password" , "Sdet2022*")
                .when().post("https://library2.cybertekschool.com/rest/v1/login")
                .then().assertThat().statusCode(200)
                .and().extract().body().path("token");
        System.out.println("token = " + token);
    }

}

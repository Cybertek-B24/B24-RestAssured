package com.cybertek.tests.day12_api_authentication;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
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

    String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMTYwIiwiZnVsbF9uYW1lIjoiVGVzdCBTdHVkZW50IDMwIiwiZW1haWwiOiJzdHVkZW50MzBAbGlicmFyeSIsInVzZXJfZ3JvdXBfaWQiOiIzIn0sImlhdCI6MTY0NDU0NzQyMywiZXhwIjoxNjQ3MTM5NDIzfQ.8smrOO7spUfjq-DI_ABx0AAejUmulSUbb4zZccDiJ8A";

    //https://library2.cybertekschool.com/rest/v1/get_book_list_for_borrowing

    @Test
    public void getBooksForBorrowing() {
        Response response = given().accept(ContentType.JSON)
                .and().header("x-library-token" , accessToken)
                .when().get("https://library2.cybertekschool.com/rest/v1/get_book_list_for_borrowing");

        System.out.println("status code = " + response.statusCode());
        response.prettyPrint();

    }

}

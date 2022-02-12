package com.cybertek.tests.day13_bookit;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookItLoginApiTest {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("bookit.base.url");
    }

    /**
     * Given accept type is json
     * and query params: email = lfinnisz@yolasite.com
     * & password = lissiefinnis
     * When I send get request to /sign
     * Then status code is 200
     * and response json body contains the token
     */

    @Test
    public void loginAsTeamLeaderTest() {
        given().accept(ContentType.JSON)
                .and().queryParams("email","lfinnisz@yolasite.com",
                        "password","lissiefinnis")
                .when().get("/sign")
                .then().assertThat().statusCode(200)
                .and().body("accessToken", not(emptyString()))
                .log().all();
    }

}

package com.cybertek.tests.day13_bookit;

import com.cybertek.tests.BookItTestBase;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookItCampusTest extends BookItTestBase {
    @Test
    public void getAllCampuses() {
        String accessToken = getAccessToken(ConfigurationReader.getProperty("teacher_email") ,
                ConfigurationReader.getProperty("teacher_password") );
        System.out.println("accessToken = " + accessToken);

        given().accept(ContentType.JSON)
                .and().header("Authorization" , accessToken)
                .when().get("/api/campuses")
                .then().assertThat().statusCode(200);


    }
}

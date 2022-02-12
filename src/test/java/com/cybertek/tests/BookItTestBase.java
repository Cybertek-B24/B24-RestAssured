package com.cybertek.tests;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public abstract class BookItTestBase {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("bookit.base.url");
    }

    /**
     * Reusable method to get access token for any user of BookIt app
     * @param email
     * @param password
     * @return
     */

    public String getAccessToken(String email, String password) {
       String accessToken = given().accept(ContentType.JSON)
                .and().queryParams("email", email,
                        "password",password)
                .when().get("/sign")
                .then().statusCode(200)
                .and().extract().body().path("accessToken");
       return accessToken;
    }
}

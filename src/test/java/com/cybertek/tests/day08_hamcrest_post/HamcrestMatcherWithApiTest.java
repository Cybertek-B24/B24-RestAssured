package com.cybertek.tests.day08_hamcrest_post;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HamcrestMatcherWithApiTest {
    @Test
    public void helloWorldApiTest() {
        given().accept(ContentType.JSON)
                .when().get("https://sandbox.api.service.nhs.uk/hello-world/hello/world")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json")
                .and().body("message" , equalTo("Hello World!"));
    }
}

package com.cybertek.tests.day08_hamcrest_post;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanHamcrestTest extends SpartanTestBase {

    @Test
    public void singleSpartanTest() {
        given().accept(ContentType.JSON)
                .when().get("/api/spartans/24")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json")
                .and().body("id",is(24),"name",is("Nadir") ,
                        "gender", is("Male"), "phone", is(1321321321))
                .log().all();
    }
}

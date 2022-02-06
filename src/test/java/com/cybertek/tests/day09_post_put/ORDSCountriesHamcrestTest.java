package com.cybertek.tests.day09_post_put;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSCountriesHamcrestTest extends ORDSTestBase {

    /**
     * given accept type is json
     * when I send get request to /countries
     * Then status code is 200
     * and content type is json
     * and count should be 25
     * and country ids should contain "AR,AU,BE,BR,CA"
     * and country names should have "Argentina,Australia,Belgium,Brazil,Canada"
     *
     * items[0].country_id ==> AR
     * items[1].country_id ==> AU
     * items.country_id ==> AR, AU, .... all of them as a list of string
     */
    @Test
    public void allCountriesHamcrestTest() {
                //request
        given().accept(ContentType.JSON)
                .when().get("/countries")
                //response assertions/verification:
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("count", equalTo(25),
                        "items.country_id" , hasItems("AR", "AU", "BE", "BR", "CA") ,
                        "items.country_name" , hasItems("Argentina", "Australia", "Belgium", "Brazil", "Canada"))
                .log().all();

        //Second way of using hamcrest for API response verifications.
        //store response then do assertions.
        Response response = given().accept(ContentType.JSON)
                .when().get("/countries");

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.contentType(), is("application/json"));
        assertThat(response.path("count"), is(25));
        assertThat(response.path("items.country_id"), hasItems("AR",  "BE", "BR", "CA", "AU"));
        assertThat(response.path("items.country_name"), hasItems("Argentina", "Australia", "Belgium", "Brazil", "Canada"));

    }

}

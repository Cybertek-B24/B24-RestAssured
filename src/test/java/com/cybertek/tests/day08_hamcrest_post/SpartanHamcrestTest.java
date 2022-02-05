package com.cybertek.tests.day08_hamcrest_post;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Map;

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
    
    @Test
    public void getMapTest() {
        /**
         * in below example:
         * send get request
         * verify status code and header
         * then convert json body to Map object and return
         * DE-serialization
         */
      Map<String, Object> spartanMap =  given().accept(ContentType.JSON)
                .when().get("/api/spartans/24")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json").log().all()
                .and().extract().body().as(Map.class); //convert json body to Map object and return

        System.out.println("spartanMap = " + spartanMap);
        //read id value from map then assert it is 24
        assertThat(spartanMap.get("id"), equalTo(24));

        //check the keys of json response
        assertThat(spartanMap.keySet(), containsInAnyOrder("id","name","gender","phone"));

        //check all values of json
        assertThat(spartanMap.values(), hasItems(24, "Nadir", "Male" , 1321321321));
    }


    
}

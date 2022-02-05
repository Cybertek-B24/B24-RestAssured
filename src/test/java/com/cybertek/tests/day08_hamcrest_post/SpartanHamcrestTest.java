package com.cybertek.tests.day08_hamcrest_post;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    /**
     * given accept type json
     * get request to /api/spartans
     * then status code is 200
     * and content type is json
     * Then extract names of spartans into a List<String>
     */
    @Test
    public void getSpartanNamesTest() {
        List<String> names = given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().extract().body().jsonPath().getList("name");
                //.and().extract().body().path("name");

        System.out.println("names = " + names);
        assertThat(names, hasSize(238));
        assertThat(names, hasItems("Nadir", "Eric" , "Melania"));
    }

    /**
     * given accept type json
     * nameContains "v"
     * gender is "Male'
     * get request to /api/spartans/search
     * then status code is 200
     * and content type is json
     * and return/extract totalElement value as an int
     * int totalElement = given...
     */
    @Test
    public void getTotalElementTest() {
       int totalElement=  given().accept(ContentType.JSON)
                .and().queryParam("nameContains" , "v")
                .and().queryParam("gender" , "Male")
                .when().get("/api/spartans/search")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON).log().all()
                .and().extract().path("totalElement");
                //.and().extract().body().jsonPath().getInt("totalElement");

        System.out.println("totalElement = " + totalElement);
        assertThat(totalElement, is(equalTo(5)));
    }

    /**
     * get /api/spartans/2400
     * in single statement, verify status code 404 and status is "not found"
     */
    @Test
    public void invalidSpartanTest() {
        given().accept(ContentType.JSON)
                .when().get("/api/spartans/2400")
                .then().assertThat().statusCode(404)
                .and().body("error" , equalTo("Not Found"));

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/2400");
        //junit
        assertEquals(404, response.statusCode());
        assertEquals("Not Found" , response.path("error"));
        //hamcrest
        assertThat(response.statusCode(), is(404));
        assertThat(response.path("error") , is("Not Found"));
    }


}

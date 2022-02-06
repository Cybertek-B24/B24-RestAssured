package com.cybertek.tests.day09_post_put;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPostRequestTest extends SpartanTestBase {
    /**
     * given accept is json
     * and content type is json
     * when I send POST request to /api/spartans
     * with {
     *   "gender": "Male",
     *   "name": "RestAssured Post",
     *   "phone": 2115552345
     * }
     * Then status code is 201
     * And "spartan is born" message should be displayed
     */
    @Test
    public void postSpartanTest() {
        String requestJson = "{\"gender\": \"Male\",\n" +
                                "\"name\": \"RestAssuredPost\",\n" +
                                "\"phone\": 2115552345}";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestJson)
                .when().post("/api/spartans");

        System.out.println("status code = " + response.statusCode());
        response.prettyPrint();
        //verify status code
        assertThat(response.statusCode(), is(201));
        assertEquals(201, response.statusCode());
        //verify header
        assertThat(response.contentType(), is("application/json"));
        //verify json response body
        //And "A Spartan is Born!" message should be displayed
        System.out.println("message = " + response.path("success"));
        assertThat(response.path("success"), is(equalTo("A Spartan is Born!")));
    }

    @Test
    public void postSpartanWithMapTest() {
        Map<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("name", "TestMapPost");
        requestMap.put("gender", "Female");
        requestMap.put("phone", 3211231234L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap) //Java object > Json
                .when().post("/api/spartans");

        System.out.println("status code = " + response.statusCode());
        /**
         * Response verifications
         * status code is 201
         * content type is json
         * message is A Spartan is Born!
         * name is TestMapPost
         * gender is Female
         * phone is 3211231234L
         * id is more than 0
         */
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        JsonPath json = response.jsonPath();
        assertThat(json.getString("success"), is("A Spartan is Born!"));
        assertThat(json.getString("data.name"), is("TestMapPost"));
        assertThat(json.getString("data.gender") , is("Female"));
        assertThat(json.getLong("data.phone") , is(3211231234L));
        assertThat(json.getInt("data.id"), greaterThan(0));

    }



}

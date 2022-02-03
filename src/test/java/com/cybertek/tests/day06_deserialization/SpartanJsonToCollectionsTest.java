package com.cybertek.tests.day06_deserialization;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanJsonToCollectionsTest extends SpartanTestBase {

    /**
     * given accept type is json
     * when I send get request to /api/spartans/24
     * Then status code is 200
     * And content type is json
     * And id is 24, name is Nadir, gender is Male, phone is 1321321321
     */
    @Test
    public void singleSpartanToMapTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/24");
        //convert json response to Map object. Key+value
        Map<String, Object> spartanMap = response.as(Map.class);
        System.out.println("spartanMap = " + spartanMap);
    }

}

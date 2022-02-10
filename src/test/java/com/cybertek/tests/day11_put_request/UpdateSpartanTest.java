package com.cybertek.tests.day11_put_request;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateSpartanTest extends SpartanTestBase {
    /**
     * Given content type is json
     * When i send put request to /api/spartans/1126
     * With json body:
     * {
     *   "gender": "Male",
     *   "name": "Changed",
     *   "phone": 1234567890
     * }
     * Then status code is 204
     */

    @Test
    public void updateSpartanTest() {
        Map<String, Object> spartanMap = new LinkedHashMap<>();
        spartanMap.put("gender", "Female");
        spartanMap.put("name", "Nammeeee");
        spartanMap.put("phone", 8884567890L);

        given().contentType(ContentType.JSON)
                .and().body(spartanMap)
                .when().put("/api/spartans/1126")
                .then().assertThat().statusCode(204);
    }
}

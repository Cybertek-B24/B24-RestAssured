package com.cybertek.tests.day10_post_put_delete;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSPostPutDeleteRegionTest extends ORDSTestBase {
    /**
     * given accept is json
     * and content type is json
     * When I send post request to "/regions"
     * With json:
     * {
     *     "region_id":100,
     *     "region_name":"Test Region"
     * }
     * Then status code is 201
     * content type is json
     * region_id is 100
     * region_name is Test Region
     */
    @Test
    public void postARegionTest() {
        //delete region by id, before posting:
        deleteRegion(999);

        Map<String, Object> regionRequestMap = new LinkedHashMap<>();
        regionRequestMap.put("region_id", 999);
        regionRequestMap.put("region_name","Test Region");
        
        Map<String, Object> regionResponseMap = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(regionRequestMap)
                .when().post("/regions/")
                .then().assertThat().statusCode(201)
                .and().contentType(ContentType.JSON)
                .and().extract().body().as(Map.class);

        System.out.println("regionResponseMap = " + regionResponseMap);
        //assertions
    }

    public static void deleteRegion(int regionId) {
        when().delete("/regions/" + regionId);
    }

}

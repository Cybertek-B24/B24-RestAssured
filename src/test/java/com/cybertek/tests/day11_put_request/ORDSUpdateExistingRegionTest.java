package com.cybertek.tests.day11_put_request;

import com.cybertek.tests.ORDSTestBase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSUpdateExistingRegionTest extends ORDSTestBase {

    /**
     * Given accept type is Json
     * And content type is json
     * When i send put request to /regions/999
     * With json body:
     *    {
     *    	 "region_id": 999,
     *    	 "region_name": "Wooden Region"
     *    }
     * Then status code is 200
     * And content type is json
     * And json response shoul contain updated values
     */
    @Test
    public void updateExistingRegionTest() {

    }

}

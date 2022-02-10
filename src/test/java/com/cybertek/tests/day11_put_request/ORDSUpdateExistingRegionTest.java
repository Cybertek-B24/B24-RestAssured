package com.cybertek.tests.day11_put_request;

import com.cybertek.tests.ORDSTestBase;
import com.cybertek.tests.pojo.Region;
import io.restassured.http.ContentType;
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
        //target region id to be updated. please use the one that you posted last class
        int regionId = 999;

        //create pojo instance for put request
        Region reqRegion = new Region();
        reqRegion.setRegionId(regionId);
        reqRegion.setRegionName("hello hello");

        Region resRegion = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(reqRegion)   //serialization - JAVA object > Json
                .when().put("/regions/"+regionId)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                //.and().body("region_id", is(999))
                .and().extract().body().as(Region.class);

        //verify json response should contain updated values
       // response.path("region_name") => we could use this if we used response object.

        assertThat(reqRegion.getRegionId(), is(equalTo(resRegion.getRegionId())));
        assertThat(reqRegion.getRegionName(), is(equalTo(resRegion.getRegionName())));
    }

}

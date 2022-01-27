package com.cybertek.tests.day02_headers_params;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanGetRequestsApiTest {

    String baseUrl = ConfigurationReader.getProperty("spartan.url");

    /**
     • When I send a GET request to
     • spartan_base_url/api/spartans
     • Then Response STATUS CODE must be 200
     • And I Should see all Spartans data in JSON format
     */

    @Test
    public void getAllSpartansTest() {
        Response response = when().get(baseUrl + "/api/spartans");
        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        response.prettyPrint();
        assertTrue(response.asString().contains("Correy"));

    }

}

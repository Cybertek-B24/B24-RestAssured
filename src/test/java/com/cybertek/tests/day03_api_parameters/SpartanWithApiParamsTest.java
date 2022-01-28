package com.cybertek.tests.day03_api_parameters;

import com.cybertek.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanWithApiParamsTest {

    @BeforeAll
    public void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.url");
    }

    /**
     *
     Given Accept type is json
     And path parameter id is 24
     When I send request to /api/spartans/24
     Then status code should be 200
     And content type should be "application/json"
     and json body should contain "Correy"
     */

    @Test
    public void getSpartanPathParamTest() {

    }

}

package com.cybertek.tests.day04_ords_path;

import com.cybertek.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSGetRequestsTest {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("ords.url");
    }

    @Test
    public void getAllRegionsTest() {

    }

}

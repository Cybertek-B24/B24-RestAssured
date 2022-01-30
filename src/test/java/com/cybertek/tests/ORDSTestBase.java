package com.cybertek.tests;

import com.cybertek.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class ORDSTestBase {
    @BeforeAll
    public static void setUp() {
        System.out.println("Set up method: assigning value to baseURI variable");
        baseURI = ConfigurationReader.getProperty("ords.url");
    }
}

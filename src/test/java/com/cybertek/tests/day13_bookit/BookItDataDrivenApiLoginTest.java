package com.cybertek.tests.day13_bookit;

import com.cybertek.utilities.ConfigurationReader;
import com.cybertek.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

public class BookItDataDrivenApiLoginTest {

    public static List<Map<String, String>> getAllUserCredentials() {
        //open excel file/sheet
        ExcelUtil excelUtil = new ExcelUtil("BookItQa3.xlsx","QA3");
        //returns excel data as list of maps
        return excelUtil.getDataList();
    }

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("bookit.base.url");
    }

    /**
     * Given accept type is json
     * and query params: email = lfinnisz@yolasite.com
     * & password = lissiefinnis
     * When I send get request to /sign
     * Then status code is 200
     * and response json body contains the token
     */

    @ParameterizedTest
    @MethodSource("getAllUserCredentials")
    public void allUsersLoginTest(Map<String, String> userInfo) {
        given().accept(ContentType.JSON)
                .and().queryParams(userInfo)
                .when().get("/sign")
                .then().assertThat().statusCode(200)
                .and().body("accessToken", not(emptyString()))
                .log().all();
    }

}

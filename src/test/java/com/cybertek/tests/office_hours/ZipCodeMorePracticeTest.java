package com.cybertek.tests.office_hours;

import com.cybertek.tests.pojo.zipcode.Place;
import com.cybertek.tests.pojo.zipcode.ZipInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipCodeMorePracticeTest {
    @BeforeAll
    public static void setUp() {
        baseURI = "http://api.zippopotam.us";
    }

    //pojo class for zipcode info json
    @Test
    public void zipCodePojoTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/us/12189");

        assertThat(response.statusCode(), is(200));
        //de-serialization of json response body. Jackson json parser library
        ZipInfo zipInfo = response.as(ZipInfo.class);

        System.out.println("zipInfo = " + zipInfo);

        assertThat(zipInfo.getPostCode(), equalTo("12189"));
        assertThat(zipInfo.getCountry(), equalTo("United States"));
        assertThat(zipInfo.getCountryAbbreviation(), is(equalTo("US")));

        System.out.println("zipInfo.getPlaces().get(0).getPlaceName() = " + zipInfo.getPlaces().get(0).getPlaceName());
        Place place = zipInfo.getPlaces().get(0);
        assertThat(place.getPlaceName(), equalTo("Watervliet"));
        assertThat(place.getState(),     equalTo("New York"));
        assertThat(place.getLatitude(),  equalTo("42.7298"));
        assertThat(place.getLongitude(), equalTo("-73.7123"));
        assertThat(place.getStateAbbreviation(), equalTo("NY"));
    }


}

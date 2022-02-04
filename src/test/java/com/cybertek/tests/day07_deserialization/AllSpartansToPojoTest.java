package com.cybertek.tests.day07_deserialization;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.AllSpartans;
import com.cybertek.tests.pojo.Spartan;
import com.cybertek.tests.pojo.SpartanSearch;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllSpartansToPojoTest extends SpartanTestBase {

    @Test
    public void getAllSpartansTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        System.out.println("status code = " + response.statusCode());

        //AllSpartans allSpartans1 = response.as(AllSpartans.class);
        List<Spartan> allSpartans = response.jsonPath().getList("" , Spartan.class);
        System.out.println(allSpartans.get(0));
        System.out.println("total spartans count = " + allSpartans.size());

        //loop through the list and print each spartan info in separate line:
        for (Spartan eachSpartan : allSpartans) {
            if (eachSpartan.getGender().equals("Female")) {
                System.out.println(eachSpartan.toString());
            }
        }
    }

    @Test
    public void searchSpartansToPojoTest() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "ai")
                .and().queryParam("gender", "Female")
                .when().get("/api/spartans/search");

        System.out.println("status code = " + response.statusCode());

        //De-serialization. json body >> SpartanSearch java object
        SpartanSearch spartanSearch = response.body().as(SpartanSearch.class);

        System.out.println("total element = " + spartanSearch.getTotalElement());
        System.out.println("spartans info content = " + spartanSearch.getContent());
        System.out.println("spartan count = " + spartanSearch.getContent().size());

        //store Jaimie info into separate spartan variable:
        //Ferhat Demir — Today at 9:05 PM
        Spartan first = spartanSearch.getContent().get(0);
        System.out.println("id = " + first.getId());
        System.out.println("name = " + first.getName());
        System.out.println("gender = " + first.getGender());
        System.out.println("phone = " + first.getPhone());

        assertEquals(13 , first.getId());
        assertEquals("Jaimie" ,  first.getName());
        assertEquals("Female" , first.getGender());
        assertEquals(7842554879L , first.getPhone());

    }
}

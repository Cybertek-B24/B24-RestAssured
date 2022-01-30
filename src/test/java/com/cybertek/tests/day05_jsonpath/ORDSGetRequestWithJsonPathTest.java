package com.cybertek.tests.day05_jsonpath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSGetRequestWithJsonPathTest extends ORDSTestBase {
    /**
     Given accept is json
     when I send get request to ords/hr/employees/103
     Then status code is 200
     and content type header is json
     and employee_id is 103
     and first_name is Alexander
     and last_name is Hunold
     and salary is 9000
     */
    @DisplayName("GET ords/hr/employees/103 and jsonPath")
    @Test
    public void jsonPathSingleEmpInfoTest() {
        Response response = given().accept(ContentType.JSON)
                .and().get("/employees/103");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("content type header = " + response.contentType());
        assertEquals("application/json", response.contentType());

        //assign json response body to JsonPath object
        JsonPath json = response.jsonPath();

        //read values from jsonPath object
        int empID = json.getInt("employee_id");
        String firstName = json.getString("first_name");
        String lastName = json.getString("last_name");
        int salary = json.getInt("salary");

        System.out.println("empID = " + empID);
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("salary = " + salary);

        assertEquals(103 ,empID);
        assertEquals("Alexander" ,firstName);
        assertEquals("Hunold" ,lastName);
        assertEquals(9000 ,salary);
    }

    @DisplayName("GET ords/hr/employees and using jsonPath filters")
    @Test
    public void jsonPathFilterTest() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit",500)
                .when().get("/employees");

        System.out.println("response status code = " + response.statusCode());

        response.prettyPrint();
        JsonPath json = response.jsonPath();
        //names of employees who work in department 90
        List<String> empList = json.getList("items.findAll {it.department_id==90}.first_name");
        System.out.println("empList = " + empList);
        
        ////names of employees who are "IT_PROG"
        List<String> itProgsList = json.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");
        System.out.println("itProgsList = " + itProgsList);

        //emp ids of Employees whose salary is more than 5000
        List<Integer> empIds = json.getList("items.findAll{it.salary >= 5000}.employee_id");
        System.out.println("empIds = " + empIds);
        System.out.println("empIds.size() = " + empIds.size()); 
        
        //find the person firstname with maximum salary
        String firstNameMaxSalary = json.getString("items.max{it.salary}.first_name");

        System.out.println("firstNameMaxSalary = " + firstNameMaxSalary);
    }
}

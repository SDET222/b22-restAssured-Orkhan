package com.cybertek.day5;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.cybertek.utilities.HrTestBase;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

public class ORDS_HamcrestTest extends HrTestBase {


    @DisplayName("Get request to Employees IT_PROG endpoint and chaining")
    @Test
    public void test1(){
        //send a get request to employees endpoint with query param job-id IT_PROG
        //verify each job_id is IT_PROG
        //verify first_names are ...
        //verify email without checking order
        List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .and()
                .then()
                .body("items.job_id", equalTo("IT_PROG"))
                .and()
                .body("items.first_name", everyItem(equalTo("IT_PROG")))
                .body("items.first_name",containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana")) //contains with order
                .body("items.email",containsInAnyOrder("VPATABAL","DAUSTIN","BERNST","AHUNOLD","DLORENTZ")) //contains without order
                .body("items.first_name", equalTo(names)); // equality of lists assertion

    }


    @Test
    public void employeesTest2(){
        //we want to chain and also get response object


        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();

        //assertThat(jsonPath.getList("items.first_name", hasSize(5)));
        assertThat(jsonPath.getList("items.first_name"),hasSize(5));

        //assert firstnames order
        assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"));

    }







    }







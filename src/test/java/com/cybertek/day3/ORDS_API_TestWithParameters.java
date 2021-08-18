package com.cybertek.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ORDS_API_TestWithParameters {



    /*
            Given accept type is Json
            And parameters: q = {"region_id":2}
            When users sends a GET request to "/countries"
            Then status code is 200
            And Content type is application/json
            And Payload should contain "United States of America"
         */
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
            .and().queryParam("q","{\"region_id\":2}")
            .log().all()
            .when()
            .get("/countries");

    assertEquals(200, response.statusCode());
    assertEquals("application/json", response.header("Content-Type"));
    assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();


    }
//{ "job_id": "IT_PROG"}
@Test
public void test2() {
    Response response = given().accept(ContentType.JSON)
            .and().queryParam("q", "{ \"job_id\": \"IT_PROG\"}")
            .log().all()
            .when()
            .get("/employees");

    assertEquals(200, response.statusCode());
    assertEquals("application/json", response.header("Content-Type"));
    assertTrue(response.body().asString().contains("Alexander"));

}

}

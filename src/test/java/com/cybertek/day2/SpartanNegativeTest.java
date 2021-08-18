package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanNegativeTest {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.86.27.247:8000";
    }

    /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */
    @DisplayName("GET request to /api/spartans/10")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.XML)
                .get("/api/spartans/10");


        assertEquals(406,response.statusCode());
        assertEquals("application/xml;charset=UTF-8",response.contentType());



    }






}

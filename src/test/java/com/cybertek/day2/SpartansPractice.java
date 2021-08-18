package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartansPractice {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://3.86.27.247:8000";

    }

    @Test
    public void test_012021(){
        Response response = given().accept(ContentType.JSON)

                        .get("/api/spartans");

          assertEquals(200,response.statusCode());
          assertEquals("application/json",response.contentType());

        assertTrue(response.headers().hasHeaderWithName("Connection"));
        assertEquals("chunked",response.header("Transfer-Encoding"));
        System.out.println(response.headers());

    }


}

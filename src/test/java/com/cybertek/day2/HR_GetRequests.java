package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HR_GetRequests {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.86.27.247:1000/ords/hr";
    }

    @Test
    public void tes1() {

        Response response = get("/regions");
        System.out.println(response.statusCode());

    }
    @Test
    /*
    Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains Americas
     */

    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/regions/2");
        response.prettyPrint();
        assertEquals(200,response.statusCode());
       assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Americas"));

    }


}

package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String url = "http://3.86.27.247:8000";

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertEquals(response.contentType(), "application/json");

    }

    @DisplayName("Get one spartan /api/spartans/3")
    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans/3");

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        //not good way to assert
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }
    @Test
    public  void test3(){
        Response response = RestAssured
                .when()
                .get(url +"/api/hello");

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        //verify we have headers named Date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        //verify Content-Length is 17
        Assertions.assertEquals("17",response.header("Content-Length"));

        Assertions.assertEquals("Hello from Sparta",response.body().asString());


    }








}

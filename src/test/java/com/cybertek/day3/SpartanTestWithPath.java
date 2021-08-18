package com.cybertek.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanTestWithPath {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://52.207.61.129:8000";
    }
    /*
         Given accept type is json
         And path param id is 10
         When user sends a get request to "api/spartans/{id}"
         Then status code is 200
         And content-type is "application/json"
         And response payload values match the following:
              id is 10,
              name is "Lorenza",
              gender is "Female",
              phone is 3312820936
       */
    @DisplayName("GET one spartan with Path Method")
    @Test
    public void test1() {

       Response response = given().accept(ContentType.JSON)

                .and().pathParam("id",10)
                .when().get("api/spartans/{id}");
       assertEquals(200,response.statusCode());
       assertEquals("application/json",response.contentType());

        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936l,phone);








    }




}

package com.cybertek.day4;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;

public class Spartan_JsonToCollection_Deserialization extends SpartanTestBase {


    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");


        Map<String, Object> spartanMap = response.body().as(Map.class);
        System.out.println("spartanMap = " + spartanMap);



    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)

                .when().get("/api/spartans/");


        List<Map<String, Object>> spartanMapList = response.body().as(List.class);
       // System.out.println("spartanMap = " + spartanMapList);

        System.out.println(spartanMapList.get(0));
        Map<String,Object> firstSpartan = spartanMapList.get(0);
        System.out.println(firstSpartan.get("name"));

        int counter = 1;
        for (Map<String,Object> map:spartanMapList) {
            System.out.println(counter + " -spartan " + map);
            counter++;
        }






    }



}

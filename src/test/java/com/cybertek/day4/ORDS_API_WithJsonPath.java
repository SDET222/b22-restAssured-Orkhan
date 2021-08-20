package com.cybertek.day4;

import com.cybertek.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ORDS_API_WithJsonPath extends HrTestBase {

@Test
    public void test1(){
    Response response = get("/countries");


    JsonPath jsonPath = response.jsonPath();

    String secondCountryName = jsonPath.getString("items[1].country_name");
    System.out.println("secondCountryName = " + secondCountryName);

    //get all country names

    List<String> allCountriesID = jsonPath.getList("items.country_id");
    System.out.println(allCountriesID);

    List<String> countryNameWithRegionID2 =
            jsonPath.getList("items.findAll {it.region_id==2}.country_name");
    System.out.println(countryNameWithRegionID2);


}

    @Test
    public void test2() {

        Response response = given().queryParam("limit",107)
            .when().get("/employees");

    //get all email of employee who is IT_PROG

        JsonPath jsonPath = response.jsonPath();

        List<String> employyeITProgs =  jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email") ;
        System.out.println(employyeITProgs);

// employees first name who is making >10000
        List<String> emplMaking10 = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("emplMaking10 = " + emplMaking10);
    }



}

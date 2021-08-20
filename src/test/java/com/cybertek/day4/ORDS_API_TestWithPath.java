package com.cybertek.day4;

import com.cybertek.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ORDS_API_TestWithPath extends HrTestBase {

    @DisplayName("Get request to countries with Path method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");
        assertEquals(200,response.statusCode());

        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        String firstCountryID = response.path("items[0].country_id");
        System.out.println("firstCountryID = " + firstCountryID);

        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);
        System.out.println(secondCountryName);

        String cAhref = response.path("items[2].links[0].href");
        System.out.println("cA LINK = " + cAhref);

        //get all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryName = " + countryNames);

        //assert that all region id are = 2

        List<Integer> allRegionIDs = response.path("items.region_id");
        System.out.println("allRegionIDs = " + allRegionIDs);

        for (Integer RegionID : allRegionIDs) {
            System.out.println("RegionID = " + RegionID);
            assertEquals(2,RegionID);
        }




    }









}

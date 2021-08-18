package com.cybertek.day3;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanTestsWithParameters extends SpartanTestBase {



    @DisplayName("Get request to api/spartans/{id} with ID 5")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when()
                .get("api/spartans/{id}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Blythe"));


    }

    @Test
    public void test2() {
 /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");
        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("\"Not Found\""));

    }
    @DisplayName("Get request to /api/spartans/search with Query Params")
    @Test
    public void test3() {

       Response response= given().log().all()
                .accept(ContentType.JSON)
                .and().queryParam("nameContains","e")
                .and().queryParam("gender", "Female")
                .when()
                .get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));


    }
    @DisplayName("Get request to /api/spartans/search with Query Params(MAP)")
    @Test
    public void test4() {

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response= given().log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)

                .when()
                .get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));


    }



}

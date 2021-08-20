package com.cybertek.day4;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CT_TrainingWith_API_JsonPath {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = ConfigurationReader.getProperty("ctUrl");

    }
    //send a get request to student id 23401 as a path parameter and accept header application/json
    //verify status code /content type /Content-Encoding = gzip
    //verify Date header exists
    //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */


    @DisplayName("Get request to student")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 23401)
                .when().get("/student/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("Content-Encoding = gzip",response.contentType());

        JsonPath jsonPath = response.jsonPath();



    }




}

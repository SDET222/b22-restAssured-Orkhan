package com.cybertek.day4;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanWithJsonPath extends SpartanTestBase {

    @DisplayName("Get one spartan with JsonPath")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        System.out.println(id);

    }
}

package com.cybertek.day5;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
public class HamcrestMatchersApiTest extends SpartanTestBase {

    @Test
    public void test1(){

        given()
                .accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("id",equalTo(15), "name", is ("Meta"),
                        "gender", is("Female"), "phone", is(1938695106));

    }












}
